package be.unamur.info.b314.compiler.semantics;

import static be.unamur.info.b314.compiler.semantics.symtab.PredefinedType.BOOLEAN;
import static be.unamur.info.b314.compiler.semantics.symtab.PredefinedType.INTEGER;
import static be.unamur.info.b314.compiler.semantics.symtab.PredefinedType.SQUARE;
import static be.unamur.info.b314.compiler.semantics.symtab.PredefinedType.SQUARE_ITEM;
import static be.unamur.info.b314.compiler.semantics.symtab.PredefinedType.VARIABLE;

import be.unamur.info.b314.compiler.B314BaseListener;
import be.unamur.info.b314.compiler.B314Parser;
import be.unamur.info.b314.compiler.B314Parser.ArenaEltContext;
import be.unamur.info.b314.compiler.B314Parser.ArrayContext;
import be.unamur.info.b314.compiler.B314Parser.ArrayEltContext;
import be.unamur.info.b314.compiler.B314Parser.BoolNotContext;
import be.unamur.info.b314.compiler.B314Parser.ClauseWhenContext;
import be.unamur.info.b314.compiler.B314Parser.ComputeContext;
import be.unamur.info.b314.compiler.B314Parser.EnvCaseNearbyContext;
import be.unamur.info.b314.compiler.B314Parser.ExprDBoolContext;
import be.unamur.info.b314.compiler.B314Parser.ExprDCaseContext;
import be.unamur.info.b314.compiler.B314Parser.ExprDContext;
import be.unamur.info.b314.compiler.B314Parser.ExprDFctContext;
import be.unamur.info.b314.compiler.B314Parser.ExprDGContext;
import be.unamur.info.b314.compiler.B314Parser.ExprDIntContext;
import be.unamur.info.b314.compiler.B314Parser.ExprDOpBoolContext;
import be.unamur.info.b314.compiler.B314Parser.ExprDOpIntContext;
import be.unamur.info.b314.compiler.B314Parser.ExprDParContext;
import be.unamur.info.b314.compiler.B314Parser.ExprFctContext;
import be.unamur.info.b314.compiler.B314Parser.ExprGContext;
import be.unamur.info.b314.compiler.B314Parser.FctDeclContext;
import be.unamur.info.b314.compiler.B314Parser.FctReturnDeclContext;
import be.unamur.info.b314.compiler.B314Parser.FctTypeDeclContext;
import be.unamur.info.b314.compiler.B314Parser.IfThenElseContext;
import be.unamur.info.b314.compiler.B314Parser.LocalVarDeclContext;
import be.unamur.info.b314.compiler.B314Parser.ProgramContext;
import be.unamur.info.b314.compiler.B314Parser.ScalarContext;
import be.unamur.info.b314.compiler.B314Parser.SetToContext;
import be.unamur.info.b314.compiler.B314Parser.TypeContext;
import be.unamur.info.b314.compiler.B314Parser.VarContext;
import be.unamur.info.b314.compiler.B314Parser.VarDeclContext;
import be.unamur.info.b314.compiler.B314Parser.WhileContext;
import be.unamur.info.b314.compiler.semantics.exception.AlreadyDeclaredAsFunction;
import be.unamur.info.b314.compiler.semantics.exception.AlreadyDeclaredFunction;
import be.unamur.info.b314.compiler.semantics.exception.AlreadyDeclaredVariable;
import be.unamur.info.b314.compiler.semantics.exception.CannotUseFunctionAsVariable;
import be.unamur.info.b314.compiler.semantics.exception.DuplicateVariable;
import be.unamur.info.b314.compiler.semantics.exception.ExceptionHandler;
import be.unamur.info.b314.compiler.semantics.exception.NotBooleanCondition;
import be.unamur.info.b314.compiler.semantics.exception.NotMatchingType;
import be.unamur.info.b314.compiler.semantics.exception.NotReturnVoidFucntion;
import be.unamur.info.b314.compiler.semantics.exception.UndeclaredFunction;
import be.unamur.info.b314.compiler.semantics.exception.UndeclaredVariable;
import be.unamur.info.b314.compiler.semantics.symtab.ArrayType;
import be.unamur.info.b314.compiler.semantics.symtab.B314FunctionType;
import be.unamur.info.b314.compiler.semantics.symtab.ClauseDefaultScope;
import be.unamur.info.b314.compiler.semantics.symtab.ClauseWhenScope;
import be.unamur.info.b314.compiler.semantics.symtab.PredefinedType;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import org.antlr.symtab.FunctionSymbol;
import org.antlr.symtab.GlobalScope;
import org.antlr.symtab.LocalScope;
import org.antlr.symtab.ParameterSymbol;
import org.antlr.symtab.Scope;
import org.antlr.symtab.Symbol;
import org.antlr.symtab.SymbolTable;
import org.antlr.symtab.Type;
import org.antlr.symtab.VariableSymbol;
import org.antlr.v4.runtime.RuleContext;
import org.antlr.v4.runtime.tree.ParseTree;

/**
 * @overview SymTableFiller has to fills a symbol table using ANTLR listener for B314 langage.
 * SymTableFiller is mutable.
 * @specfield symbolTable : Holds all scopes and symbols of the parsed .B314
 * @specfield currentScope : Represents the last Scope entered
 * @inv symbolTable must contains at least the global scope and the predefined types such as
 * Boolean, Integer, Square.
 */
public class SymTableFiller extends B314BaseListener {

  private final SymbolTable symTable;

  private Scope currentScope;


  public SymTableFiller() {
    this.symTable = new SymbolTable();
  }

  /**
   * @return a read-only view of the Symbol table.
   * @throws UnsupportedOperationException if attempts to modify the Map in any way.
   */
  public Map<String, ? extends Symbol> getSymTable() {
    return Collections.unmodifiableMap(symTable.GLOBALS.getMembers());
  }


  /**
   * @return the number of global variables stored
   */
  public int countVariables() {
    return symTable.GLOBALS.getNumberOfSymbols();
  }

  private void pushScope(Scope scope) {
    currentScope = scope;
  }

  private void popScope() {
    currentScope = currentScope.getEnclosingScope();
  }


  @Override
  public void enterProgram(ProgramContext ctx) { pushScope(symTable.GLOBALS); }

  @Override
  public void exitProgram(ProgramContext ctx) {
    popScope();
  }

  /**
   * @effects Insert a new {@link VariableSymbol} or {@link ParameterSymbol} <br>
   *          depending on the current scope into the current scope.
   * @throws AlreadyDeclaredVariable if the scope is global and another variable has been declared <br>
   *        with the same name.
   * @throws AlreadyDeclaredAsFunction if the current scope is a function <br>
   *         and the the parameter uses the same name as the function.
   * @throws DuplicateVariable if the current scope is a function <br>
   *         and the parameter uses the same name as another.
   */
  @Override
  public void enterVarDecl(VarDeclContext ctx) {
    String name = ctx.name.getText();
    VariableSymbol var = null;

      // ? Am I inside a function ?
      if(currentScope instanceof FunctionSymbol) {
        if(currentScope.getName().equals(name)) // Param name == Function name ?
          ExceptionHandler.throwAlreadyDeclaredAsFunction(ctx);

        // Check for duplicate parameter
        if(currentScope.getSymbol(name) != null)
          ExceptionHandler.throwDuplicateVariable(ctx);

        var = new ParameterSymbol(name);
      } else {
          if(currentScope.getSymbol(name) != null)
            ExceptionHandler.throwAlreadyDeclaredVariable(ctx);

        var = new VariableSymbol(name);
      }

      var.setDefNode(ctx);
      currentScope.define(var);
  }

  /**
   * @effects Define the type of an SymbolVariable already inserted in the symtable.
   */
  @Override
  public void enterType(TypeContext ctx) {
    ParseTree type = ctx.getChild(0);
    if (type == null) {
      return;
    }

    Type varType;
    String varName = ((VarDeclContext) ctx.getParent()).name.getText();
    VariableSymbol var =  (VariableSymbol) currentScope.getSymbol(varName);

    if (type instanceof ScalarContext) {
      varType = PredefinedType.get(type.getText());
      var.setType(varType);
    } else {
      ArrayContext typeArray = (ArrayContext) type;

      // Check for the positivity of the array' size
      int sizeArray = Integer.parseInt(typeArray.one.INTEGER().getText());
      if(sizeArray <= 0){
        ExceptionHandler.throwNotPositiveSizeForArray(ctx);
      }

      int secondSizeArray = 0;
      // If defined, check for the positivity of the second array' size
      if(typeArray.second != null) {
        secondSizeArray = Integer.parseInt(typeArray.second.INTEGER().getText());
        if (secondSizeArray <= 0) {
          ExceptionHandler.throwNotPositiveSizeForArray(ctx);
        }
      }

      // First, get the type of this array vaWr.
      varType = PredefinedType.get(typeArray.scalar().getText());

      // Init the array
      ArrayType array = this.createArrayType(varType, sizeArray, secondSizeArray);
      var.setType(array);
    }
  }

  /**
   *
   * @requires type - The scalar type of the array. Must be defined
   * @requires size - The size of the array. Must be > 0
   * @return the {@link ArrayType} with the nested type if sizeSecondArray is defined.
   */
  private ArrayType createArrayType(Type type,int size, int sizeSecondArray) {
    if (sizeSecondArray > 0) {
      // Create an nested array if the array two-dimensional
      ArrayType nestedArray = new ArrayType(type, sizeSecondArray);
      type = nestedArray;
    }

    return new ArrayType(type, size);
  }

  /**
   * @requires varSym - The Symbol representing the array variable. Must be defined
   * @return the {@link PredefinedType} of the array or the nested one inside.
   */
  private PredefinedType getArrayType(VariableSymbol varSym) {
    // Check for the array var
    ArrayType arr = (ArrayType) varSym.getType();
    Type arrPredefType = arr.getType();

    // Check if contains nested array ?
    if(arrPredefType instanceof ArrayType) {
      // Then, get the type from the nested
      arrPredefType  = ((ArrayType) arrPredefType).getType();
    }

    return PredefinedType.get(arrPredefType);
  }

  /**
   * @effects Check the types of the instruction, if the types of the Expr named <i>var</i> and <i>value</i> are
   *          compatible.
   * @throws NotMatchingType if the types of both expr in instruction are not compatible.
   * @throws NotReturnVoidFucntion if try to set a void function.
   */
  @Override
  public void enterSetTo(SetToContext ctx) {
    ExprGContext exprG = ctx.var;
    ExprDContext exprD = ctx.value;
    PredefinedType exprGType = getTypeOfExprG(exprG);
    PredefinedType exprDType = getTypeOfExprD(exprD);
    boolean isMatching = false;

    if(exprDType == null)
      ExceptionHandler.throwNotMatchingType(ctx);

    // Check if the function is not VOID
    if(exprDType.equals(PredefinedType.FUNCTION)) {
      exprDType = getTypeOfExprFunction((ExprFctContext)exprD.getChild(0));
      if(exprDType.equals(PredefinedType.VOID))
        throw new NotReturnVoidFucntion(ctx.getText());
    }

    // Check if set SQUARE to SQR_ITEM
    if(exprGType == PredefinedType.SQUARE) {
      if(!exprDType.equals(PredefinedType.SQUARE_ITEM)) {
        ExceptionHandler.throwNotMatchingType(ctx);
      }
    } else {
      // Retrieve the variable from the exprGContext
      Symbol varSym = getVarFromSymTable(exprG);

      // If the exprD is a VARIABLE, get it PredefinedType
      if(exprDType.equals(PredefinedType.VARIABLE)) {
        ParseTree varFromExprD = exprD.getChild(0);
        String varNomFromExprD = "";
        VariableSymbol varSymFromExprD;
        if(varFromExprD instanceof VarContext) {
          varNomFromExprD = ((VarContext) varFromExprD).name.getText();
          varSymFromExprD = getVarFromSymTable(varNomFromExprD);
          exprDType = PredefinedType.get(varSymFromExprD.getType());
        } else {
          varNomFromExprD = ((ArrayEltContext) varFromExprD).name.getText();
          varSymFromExprD = getVarFromSymTable(varNomFromExprD);
          exprDType = getArrayType(varSymFromExprD);
        }
      }

      if(exprG instanceof VarContext) {
        // Check if both expr's (var to exprD) type matches
        exprGType = (PredefinedType) ((VariableSymbol)varSym).getType();
      } else {
        // Check if both expr's (arrayVar to exprD) type matches
        exprGType = getArrayType((VariableSymbol)varSym);
      }

      // Check for it type's matching

      switch (exprGType) {
        case SQUARE:
          isMatching = (exprDType == PredefinedType.SQUARE || exprDType == PredefinedType.SQUARE_ITEM);
          break;
        case BOOLEAN:
        case INTEGER:
          isMatching = (exprGType == exprDType);
          break;
        default:
          isMatching = false;
      }

      if(!isMatching)
        ExceptionHandler.throwNotMatchingType(ctx);
    }

    super.enterSetTo(ctx);
  }


  /**
   * @effects Retrieve the name from the exprG and use it to fetch the Symbol
   * @link SymTableFiller#getVarFromSymTable(String)
   */
  private VariableSymbol getVarFromSymTable(ExprGContext exprG) {
    String varSymName;
    if(exprG instanceof VarContext) { // ? is var Scalar?
      varSymName = ((VarContext)exprG).name.getText();
    } else {
      varSymName = ((ArrayEltContext)exprG).name.getText();
    }
    return getVarFromSymTable(varSymName);
  }

  /**
   * @effects Fetch the corresponding Symbol with the provided name from the Symbol Table.
   * @returns the corresponding Symbol
   * @throws UndeclaredVariable if the provided name is not in the Symbol Table
   * @throws CannotUseFunctionAsVariable if the fetched symbol is not a variable.
   */
  private VariableSymbol getVarFromSymTable(String varName) {
    Symbol varSym;

    // Check in localScope, if exist
    if (!( currentScope instanceof GlobalScope)) {
      List<Scope> nestedSCopes = currentScope.getNestedScopes();
      if(!nestedSCopes.isEmpty()) {
        varSym = nestedSCopes.get(0).getSymbol(varName);
        if( varSym != null)
          return (VariableSymbol) varSym;
      }
    }

    varSym = currentScope.resolve(varName);
    if(varSym == null)
      throw new UndeclaredVariable("ERROR : Undeclared variable - "+varName);

    // Check if the variable is really defined as variable
    if(!(varSym instanceof VariableSymbol))
      throw new CannotUseFunctionAsVariable("ERROR : Cannot use fucntion as variable - "+varName);

    return (VariableSymbol) varSym;
  }

  /**
   * @effects Fetch the corresponding {@link FunctionSymbol} withthe provided name from the Symbol Table.
   * @returns the corresponding {@link FunctionSymbol}
   * @throws UndeclaredFunction if the provided name is not in the Symbol Table
   */
  private FunctionSymbol getFctFromSymTable(String fctName) {
    FunctionSymbol fctSym = (FunctionSymbol) symTable.GLOBALS.getSymbol(fctName);
    if(fctSym == null)
      throw  new UndeclaredFunction(fctName);
    return fctSym;
  }

  /**
   * @return The corresponding the {@link PredefinedType} for this expression which can only be<br>
   *          <b>{@link PredefinedType#SQUARE}</b>,
   *          <b>{@link PredefinedType#ARRAY}</b>
   *          or <b>{@link PredefinedType#VARIABLE}</b>.
   */
  private PredefinedType getTypeOfExprG(ExprGContext expr) {
    RuleContext ctx = expr.getRuleContext();
    if(ctx instanceof ArenaEltContext)
      return PredefinedType.SQUARE;

    if (ctx instanceof ArrayEltContext)
      return PredefinedType.ARRAY;

    return PredefinedType.VARIABLE;
  }

  /**
   * @return The corresponding {@link PredefinedType} for this expression <br>
   *          otherwise <b>null</b>.
   */
  private PredefinedType getTypeOfExprD(ExprDContext expr) {
    RuleContext ctx = expr.getRuleContext();

    if(ctx instanceof ExprDIntContext || ctx instanceof ExprDOpIntContext)
      return PredefinedType.INTEGER;

    if(ctx instanceof ExprDBoolContext || ctx instanceof ExprDOpBoolContext)
      return PredefinedType.BOOLEAN;

    if(ctx instanceof ExprDCaseContext)
        return PredefinedType.SQUARE_ITEM;

    if(ctx instanceof ExprDGContext)
      return this.getTypeOfExprG(((ExprGContext)ctx.getChild(0)));

    if(ctx instanceof ExprDFctContext)
      return PredefinedType.FUNCTION;

    if(ctx instanceof ExprDParContext)
      return this.getTypeOfExprD(((ExprDParContext)ctx).expr);

    return null;
  }

  /**
   * @effects Check the existence of the function and the matching of its parameters.
   * @return the corresponding {@link PredefinedType} of the expression function
   */
  private PredefinedType getTypeOfExprFunction(ExprFctContext exprFct) {
    FunctionSymbol fctSym = getFctFromSymTable(exprFct.name.getText());

    // Check if the nb of parameters matches
    if(fctSym.getNumberOfParameters() != exprFct.param.size())
      //ExceptionHandler.throwUndeclaredFunction(exprFct);
      throw new UndeclaredFunction(exprFct.getText());

    return ((B314FunctionType)fctSym.getType()).getReturnType();
  }


  @Override
  public void enterIfThenElse(IfThenElseContext ctx) {
    checkConditionStatement(ctx.condition);
    super.enterIfThenElse(ctx);
  }

  /**
   * @effects Check if the condition statement is a boolean type.
   * @throws NotBooleanCondition if the condition statement is not a boolean type.
   * @throws UndeclaredVariable if the condition statement uses an undeclared variable.
   * @throws CannotUseFunctionAsVariable if the condition statement tries to use an function as a variable.
   */
  private void checkConditionStatement(ExprDContext condition) {
    PredefinedType condType = this.getTypeOfExprD(condition);
    switch (condType) {
      case BOOLEAN:
      case VARIABLE:
      case FUNCTION:
        if(checkExprD(condition, BOOLEAN))
          return;
      default:
        condType = null;
    }

    // Finally, check if the condition is really BOOLEAN
    if(condType != BOOLEAN)
      ExceptionHandler.throwNotBooleanCondition(condition);
  }

  @Override
  public void enterWhile(WhileContext ctx) {
    checkConditionStatement(ctx.condition);
    super.enterWhile(ctx);
  }

  @Override
  public void enterCompute(ComputeContext ctx) {
    PredefinedType exprDType = getTypeOfExprD(ctx.exprD());

    switch (exprDType) {
      case FUNCTION:
        getTypeOfExprFunction((ExprFctContext) ctx.exprD().getChild(0));
        return;
      case SQUARE:
      case SQUARE_ITEM:
      case VARIABLE:
        return;
        default:
          if(!checkExprD(ctx.exprD(), exprDType))
            ExceptionHandler.throwNotMatchingType(ctx.exprD());

    }

  }

  @Override
  public void enterBoolNot(BoolNotContext ctx) {
    if(!checkExprD(ctx.exprD(), PredefinedType.BOOLEAN))
      ExceptionHandler.throwNotMatchingType(ctx);
  }

  @Override
  public void enterEnvCaseNearby(EnvCaseNearbyContext ctx) {
    for (ExprDContext index : ctx.elt) {
      if(!checkExprD(index, PredefinedType.INTEGER))
        ExceptionHandler.throwNotMatchingType(ctx);
    }
  }

  @Override
  public void enterArrayElt(ArrayEltContext ctx) {
    // Check for the index, one or 2 dim ?

    VariableSymbol varSym = getVarFromSymTable(ctx);
    if(!(varSym.getType() instanceof ArrayType))
      ExceptionHandler.throwNotMatchingType(ctx);

    ArrayType arr = (ArrayType) varSym.getType();

    // Array of 2 dim and missing second index
    // var with second index but not array of 2 dim
    if(  (arr.getType() instanceof ArrayType && ctx.second == null)
        || (ctx.second != null && !(arr.getType() instanceof ArrayType))
      )
      ExceptionHandler.throwNotMatchingType(ctx);

    // Check for the type

    boolean isIntType = checkExprD(ctx.one, PredefinedType.INTEGER);
    if( isIntType && ctx.second != null)
      isIntType = checkExprD(ctx.second, PredefinedType.INTEGER);

    if(!isIntType)
      ExceptionHandler.throwNotMatchingType(ctx);
  }

  @Override
  public void enterExprDOpBool(ExprDOpBoolContext ctx) {
    if(!checkIfExprDIsBool(ctx))
      ExceptionHandler.throwNotMatchingType(ctx);
  }


  private boolean checkExprD(ExprDContext exprD, PredefinedType type) {
    // Check if is a in (.)
    if(exprD instanceof ExprDParContext)
      return checkExprD(((ExprDParContext)exprD).expr,type);

    // Check if is a fct()
    if(exprD instanceof ExprDGContext) {
      ExprGContext exprVar = (ExprGContext) exprD.getChild(0);
      if(exprVar instanceof ArenaEltContext)
        return type == SQUARE || type == SQUARE_ITEM;

      VariableSymbol varSym = getVarFromSymTable(exprVar);
      Type varType = varSym.getType();
      if(varType instanceof ArrayType)
        return type == getArrayType(varSym);
      else
        return type == PredefinedType.get(varType);

    }

    // Check if is a var
    if(exprD instanceof ExprDFctContext)
      return type == getTypeOfExprFunction((ExprFctContext) exprD.getChild(0));

    switch (type) {
      case SQUARE   : return (exprD instanceof ExprDCaseContext);
      case BOOLEAN  : return checkIfExprDIsBool(exprD);
      case INTEGER  : return checkIfExprDIsInt(exprD);
    }

    return false;
  }

  /**
   * @requires exprD to be not null
   * @return <b>true</b> if the expr can be comparable otherwise <b>false</b>.
   */
  private boolean checkIfExprDIsBool(ExprDContext exprD) {
    // Check the exprD in  NOT exprD
    if (exprD instanceof ExprDBoolContext) {
      if (exprD.getChild(0) instanceof BoolNotContext) {
        exprD = ((BoolNotContext) exprD.getChild(0)).exprD();
        return checkExprD(exprD, BOOLEAN);
      }
      return true;
    }

    if (exprD instanceof ExprDOpBoolContext) {
      ExprDOpBoolContext exprDBool = (ExprDOpBoolContext) exprD;

      // Check if OpBoolContext and both left &&_|| Right expr are BOOLEAN
      // AND | OR
      if (exprDBool.AND() != null || exprDBool.OR() != null ){
        if(getTypeOfExprD(exprDBool.left) == VARIABLE || getTypeOfExprD(exprDBool.right) == VARIABLE )
          return false;
        return (checkExprD(exprDBool.left, BOOLEAN))  &&  checkExprD(exprDBool.right, BOOLEAN);
      }

      // Check if OpBoolContext and both left  Right expr are comparable with LT | GT | LE | GE
      boolean isLTorGT = exprDBool.LT() != null || exprDBool.GT() != null
                        || exprDBool.LE() != null  || exprDBool.GE() != null;
      if (isLTorGT)
        return (checkExprD(exprDBool.left, INTEGER)) && checkExprD(exprDBool.right, INTEGER);

      // Check if OpBoolContext and both left  Right expr are comparable with EQ
      boolean isWellTyped = checkExprD(exprDBool.left, INTEGER) && checkExprD(exprDBool.right, INTEGER);

      // EQ comparable for Int Bool or Square
      if(!isWellTyped) {
        isWellTyped = checkExprD(exprDBool.left, BOOLEAN)
              && checkExprD(exprDBool.right, BOOLEAN);
        if(!isWellTyped) {
          // Check if type Case
            return ( checkExprD(exprDBool.left, SQUARE)
                    && checkExprD(exprDBool.right, SQUARE) )
                  || ( checkExprD(exprDBool.left, SQUARE_ITEM)
                    && checkExprD(exprDBool.right, SQUARE) )
                  || ( checkExprD(exprDBool.left, SQUARE)
                    && checkExprD(exprDBool.right, SQUARE_ITEM) )
                  || ( checkExprD(exprDBool.left, SQUARE_ITEM)
                    && checkExprD(exprDBool.right, SQUARE_ITEM) )
             ;
        }
      }
      return isWellTyped;
    }


    return false;
  }

  private boolean checkIfExprDIsInt(ExprDContext exprD)  {
    if (exprD instanceof ExprDOpIntContext) {
      if (checkExprD(((ExprDOpIntContext) exprD).left, INTEGER))
        return checkExprD(((ExprDOpIntContext) exprD).right, INTEGER);
      return false;
    }
    return (exprD instanceof ExprDIntContext);
  }


  /**
   * @effects Insert a new {@link FunctionSymbol} <br> into the current scope <br>
   *          and use it to replace the current scope.
   * @throws AlreadyDeclaredFunction if the current function name has already used for another function
   */
  @Override
  public void enterFctDecl(FctDeclContext ctx) {
    String name = ctx.name.getText();

    Symbol fctSym = symTable.GLOBALS.getSymbol(name);

    if(fctSym != null && fctSym instanceof FunctionSymbol)
      ExceptionHandler.throwAlreadyDeclaredFunction(ctx);

    fctSym = new FunctionSymbol(name);
    ((FunctionSymbol) fctSym).setDefNode(ctx); // Set the current context to the symbol
    ((FunctionSymbol) fctSym).setEnclosingScope(symTable.GLOBALS);

    currentScope.define(fctSym); // Add the function to the Global Scope
    pushScope((Scope) fctSym); // Place this fctSymbol as the current scope
  }

  @Override
  public void enterFctTypeDecl(FctTypeDeclContext ctx) {
    FunctionSymbol fctSym =  (FunctionSymbol) currentScope;

    List<Type> paramsTypes = new ArrayList<>(fctSym.getNumberOfParameters());
    for(Symbol sb : fctSym.getSymbols()) {
      if(sb instanceof ParameterSymbol)
        paramsTypes.add(((ParameterSymbol) sb).getType());
    }

    // Get the PredefinedType of the return
    PredefinedType returnType = PredefinedType.VOID;
    if(ctx.fctType != null)
      returnType = PredefinedType.get(ctx.fctType.getText());

    fctSym.setType(new B314FunctionType(returnType, paramsTypes));
  }

  @Override
  public void enterFctReturnDecl(FctReturnDeclContext ctx) {
    FunctionSymbol fctSym =  (FunctionSymbol) currentScope;
    PredefinedType fctReturnType = ((B314FunctionType) fctSym.getType()).getReturnType();

    // Get the return value type
    PredefinedType returnValType = PredefinedType.VOID;
    if(ctx.returnVal != null)
      returnValType = getTypeOfExprD(ctx.returnVal);

    switch (returnValType) {
      case ARRAY:
      case VARIABLE:
        ExprGContext varCtx = (ExprGContext) ((ExprDGContext)ctx.returnVal).children.get(0);
        if(varCtx instanceof VarContext) {
          VariableSymbol varSym = getVarFromSymTable(((VarContext)varCtx).name.getText());
          returnValType = (PredefinedType) varSym.getType();
          break;
        }else {
          VariableSymbol varSym = getVarFromSymTable(((ArrayEltContext) varCtx).name.getText());
          returnValType = getArrayType(varSym);
        }

        if(varCtx instanceof ArenaEltContext)
          returnValType = PredefinedType.SQUARE;

        break;
      case FUNCTION:
        ExprFctContext fctCtx = (ExprFctContext) ((ExprDFctContext)ctx.returnVal).children.get(0);
        returnValType = getTypeOfExprFunction(fctCtx);
        break;
      default:
        break;
    }

    if(returnValType != fctReturnType)
      ExceptionHandler.throwNotMatchingReturnType(ctx);
  }

  @Override
  public void exitFctDecl(FctDeclContext ctx) {
    popScope();
  }

  /**
   * @effects Check if the condition statement is a boolean type.
   * @throws NotBooleanCondition if the condition statement is not a boolean type.
   * @throws UndeclaredVariable if the condition statement use an undeclared variable.
   * @throws CannotUseFunctionAsVariable if the condition statement tries to use an function as a variable.
   */
  @Override
  public void enterClauseWhen(ClauseWhenContext ctx) {
    checkConditionStatement(ctx.condition);
    ClauseWhenScope clauseWhenScope = new ClauseWhenScope(symTable.GLOBALS);
    clauseWhenScope.setDefNode(ctx);
    currentScope.define(clauseWhenScope);
    pushScope(clauseWhenScope);
  }

  /**
   * @effects creation of Scope for ClauseDefault
   */
  @Override
  public void enterClauseDefault(B314Parser.ClauseDefaultContext ctx) {
    ClauseDefaultScope clauseDefaultScope = new ClauseDefaultScope(symTable.GLOBALS);
    clauseDefaultScope.setDef(ctx);
    currentScope.define(clauseDefaultScope);
    pushScope(clauseDefaultScope);
  }

  @Override
  public void exitClauseDefault(B314Parser.ClauseDefaultContext ctx) {
    popScope();
  }

    @Override
  public void exitClauseWhen(ClauseWhenContext ctx) {
    popScope();
  }

  @Override
  public void enterLocalVarDecl(LocalVarDeclContext ctx) {
    Scope localScope = new LocalScope(currentScope);
    currentScope.nest(localScope);
    pushScope(localScope);
    super.enterLocalVarDecl(ctx);
  }

  @Override
  public void exitLocalVarDecl(LocalVarDeclContext ctx) {
    popScope();
  }

  @Override
  public int hashCode() {
    return super.hashCode();
  }

  @Override
  public boolean equals(Object obj) {
    return super.equals(obj);
  }

  @Override
  public String toString() {
    return super.toString();
  }


}
