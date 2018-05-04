package be.unamur.info.b314.compiler.semantics;

import be.unamur.info.b314.compiler.B314BaseListener;
import be.unamur.info.b314.compiler.B314Parser.ArenaEltContext;
import be.unamur.info.b314.compiler.B314Parser.ArrayContext;
import be.unamur.info.b314.compiler.B314Parser.ArrayEltContext;
import be.unamur.info.b314.compiler.B314Parser.ClauseWhenContext;
import be.unamur.info.b314.compiler.B314Parser.ComputeContext;
import be.unamur.info.b314.compiler.B314Parser.EnvCaseContext;
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
import be.unamur.info.b314.compiler.B314Parser.IfThenElseContext;
import be.unamur.info.b314.compiler.B314Parser.RootContext;
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
import be.unamur.info.b314.compiler.semantics.exception.DuplicateParameter;
import be.unamur.info.b314.compiler.semantics.exception.NotBooleanCondition;
import be.unamur.info.b314.compiler.semantics.exception.NotMatchingType;
import be.unamur.info.b314.compiler.semantics.exception.NotPositiveSizeForArray;
import be.unamur.info.b314.compiler.semantics.exception.UndeclaredFunction;
import be.unamur.info.b314.compiler.semantics.exception.UndeclaredVariable;
import be.unamur.info.b314.compiler.semantics.exception.NotReturnVoidFucntion;
import be.unamur.info.b314.compiler.semantics.symtab.ArrayType;
import java.util.Collections;
import java.util.Map;
import org.antlr.symtab.FunctionSymbol;
import org.antlr.symtab.GlobalScope;
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
  public void enterRoot(RootContext ctx) {
    pushScope(symTable.GLOBALS);
  }

  @Override
  public void exitRoot(RootContext ctx) {
    popScope();
  }

  /**
   * @effects Insert a new {@link VariableSymbol} or {@link ParameterSymbol} <br>
   *          depending on the current scope into the current scope.
   * @throws AlreadyDeclaredVariable if the scope is global and another variable has been declared <br>
   *        with the same name.
   * @throws AlreadyDeclaredAsFunction if the current scope is a function <br>
   *         and the the parameter uses the same name as the function.
   * @throws DuplicateParameter if the current scope is a function <br>
 *           and the parameter uses the same name as another.
   */
  @Override
  public void enterVarDecl(VarDeclContext ctx) {
    String name = ctx.name.getText();
    VariableSymbol var = null;

    try {
      // ? Am I inside a function ?
      if(currentScope instanceof FunctionSymbol) {
        if(currentScope.getName().equals(name)) // Param name == Function name ?
          throw  new AlreadyDeclaredAsFunction(name);

        // Check for duplicate parameter
        if(currentScope.getSymbol(name) != null)
          throw new DuplicateParameter(name);

        var = new ParameterSymbol(name);
      } else {
        if (currentScope instanceof GlobalScope)
          if(symTable.GLOBALS.getSymbol(name) != null)
            throw new AlreadyDeclaredVariable(name);

        var = new VariableSymbol(name);
      }

      currentScope.define(var);
    } catch (IllegalArgumentException e) {
      // throw IllegalArgumentException  if the symbol cannot be defined
      return;
    }
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
      varType = PredefinedType.get(type.getText()).type();
      var.setType(varType);
    } else {
      ArrayContext typeArray = (ArrayContext) type;

      // Check for the positivity of the array' size
      int sizeArray = Integer.parseInt(typeArray.one.INTEGER().getText());
      if(sizeArray <= 0){
        throw new NotPositiveSizeForArray(""+sizeArray);
      }

      int secondSizeArray = 0;
      // If defined, check for the positivity of the second array' size
      if(typeArray.second != null) {
        secondSizeArray = Integer.parseInt(typeArray.second.INTEGER().getText());
        if (secondSizeArray <= 0) {
          throw new NotPositiveSizeForArray("" + secondSizeArray);
        }
      }

      // First, get the type of this array vaWr.
      varType = PredefinedType.get(typeArray.scalar().getText()).type();

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
   * @return the @see {@link PredefinedType} of the array or the nested one inside.
   */
  private PredefinedType getArrayType(VariableSymbol varSym) {
    // Check for the array var
    ArrayType arr = (ArrayType) varSym.getType();
    PredefinedType arrPredefType;

    // Check if contains nested array ?
    if(arr.getType() instanceof ArrayType) {
      // Then, get the type from the nested
      arr  = (ArrayType) arr.getType();
    }

    return PredefinedType.get(arr.getType());
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

    if(exprGType == PredefinedType.SQUARE) {
      if(exprDType == null || !exprDType.equals(PredefinedType.SQUARE_ITEM))
        throw new NotMatchingType(ctx.toString());
    } else {

      // Retrieve the variable from the exprGContext
      Symbol varSym = getVarFromSymTab(exprG);

      // Check for it type's matching

      if(exprDType.equals(PredefinedType.VOID)) {
        throw new NotReturnVoidFucntion(ctx.getText());
      }

      if(exprG instanceof VarContext) {
        // Check if both expr's (var to exprD) type matches
        if(!((VariableSymbol)varSym).getType().equals(exprDType.type()))
          throw new NotMatchingType(ctx.toString());
      } else {
        // Check if both expr's (arrayVar to exprD) type matches
        if(!exprDType.equals(getArrayType((VariableSymbol)varSym)))
          throw new NotMatchingType(ctx.toString());
      }
    }

    super.enterSetTo(ctx);
  }

  /**
   * @effects Retrieve the name from the exprG and use it to fetch the Symbol.s
   * @see SymTableFiller#getVarFromSymTab(String)
   */
  private Symbol getVarFromSymTab(ExprGContext exprG) {
    String varSymName;
    if(exprG instanceof VarContext) { // ? is var Scalar?
      varSymName = ((VarContext)exprG).name.getText();
    } else {
      varSymName = ((ArrayEltContext)exprG).name.getText();
    }
    return getVarFromSymTab(varSymName);
  }

  /**
   * @effects Fetch the corresponding Symbol with the provided name from the Symbol Table.
   * @returns the corresponding Symbol
   * @throws UndeclaredVariable if the retrieved name is not in the Symbol Table
   * @throws CannotUseFunctionAsVariable if the fetched symbol is not a variable.
   */
  private Symbol getVarFromSymTab(String varName) {
    Symbol varSym = currentScope.resolve(varName);
    if(varSym == null)
      throw new UndeclaredVariable(varName);

    // Check if the variable is really defined as variable
    if(!(varSym instanceof VariableSymbol))
      throw new CannotUseFunctionAsVariable(varName);

    return varSym;
  }

  /**
   * @return The corresponding the {@see PredefinedType} for this expression <br>
   *          otherwise <b>null</b>.
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
   * @return The corresponding {@see PredefinedType} for this expression <br>
   *          otherwise <b>null</b>.
   */
  private PredefinedType getTypeOfExprD(ExprDContext expr) {
    RuleContext ctx = expr.getRuleContext();

    if(ctx instanceof ExprDIntContext || ctx instanceof ExprDOpIntContext)
      return PredefinedType.INTEGER;

    if(ctx instanceof ExprDBoolContext || ctx instanceof ExprDOpBoolContext)
      return PredefinedType.BOOLEAN;

    if(ctx instanceof ExprDCaseContext){
      if(((ExprDCaseContext)ctx).children.get(0) instanceof EnvCaseContext)
        return PredefinedType.SQUARE_ITEM;
      else
        return null;
    }

    if(ctx instanceof ExprDGContext)
      return this.getTypeOfExprG(((ExprGContext)ctx.getChild(0)));

    if(ctx instanceof ExprDFctContext)
      return getTypeOfExprFunction((ExprFctContext)ctx.getChild(0));

    if(ctx instanceof ExprDParContext)
      return this.getTypeOfExprD(((ExprDParContext)ctx).expr);

    return null;
  }

  /**
   * @effects Check the existence of the function and the matching of its parameters.
   * @return the corresponding {@see PredefinedType} of the expression function
   */
  private PredefinedType getTypeOfExprFunction(ExprFctContext exprFct) {
    String symName = exprFct.name.getText();
    // Check if the exprFct var or array is inside the symtab
    FunctionSymbol fctSym = (FunctionSymbol) currentScope.resolve(symName);
    if(fctSym == null)
      throw new UndeclaredFunction(exprFct.toString());

    // Check if the nb of parameters matches
    if(fctSym.getNumberOfParameters() != exprFct.param.size())
      throw new UndeclaredFunction(exprFct.toString());

    return PredefinedType.get(fctSym.getType());
  }


  @Override
  public void enterIfThenElse(IfThenElseContext ctx) {
    PredefinedType condType = this.getTypeOfExprD(ctx.condition);

    if(condType == null || !condType.equals(PredefinedType.BOOLEAN))
      throw new NotBooleanCondition(ctx.getText());

    super.enterIfThenElse(ctx);
  }

  @Override
  public void enterWhile(WhileContext ctx) {
    PredefinedType condType = this.getTypeOfExprD(ctx.condition);

    if(condType == null || !condType.equals(PredefinedType.BOOLEAN))
      throw new NotBooleanCondition(ctx.getText());

    super.enterWhile(ctx);
  }

  @Override
  public void enterCompute(ComputeContext ctx) {
    PredefinedType condType = this.getTypeOfExprD(ctx.fct);

    if (condType == null || !condType.equals(PredefinedType.VOID))
      throw new NotReturnVoidFucntion(ctx.getText());

    super.enterCompute(ctx);
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
      throw new AlreadyDeclaredFunction(name);
    else
      fctSym = new FunctionSymbol(name);

    // Set the current context to the symbol
    ((FunctionSymbol) fctSym).setDefNode(ctx);
    ((FunctionSymbol) fctSym).setEnclosingScope(symTable.GLOBALS);

    // Set the type of this function
    PredefinedType fctPredefType = PredefinedType.VOID;
    if(ctx.fctType != null)
      fctPredefType = PredefinedType.get(ctx.fctType.getText());

    ((FunctionSymbol) fctSym).setType(fctPredefType.type());
    currentScope.define(fctSym); // Add the function to this scope
    pushScope((Scope) fctSym); // Place this fctSymbol as the current scope

    super.enterFctDecl(ctx); // Continue the exploration
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
    PredefinedType condType = this.getTypeOfExprD(ctx.condition);
    if(condType == null)
        throw new NotBooleanCondition(ctx.getText());

    if(condType.equals(PredefinedType.VARIABLE)) {
      VarContext varCtx = (VarContext) ((ExprDGContext)ctx.condition).children.get(0);
      Symbol varSym = getVarFromSymTab(varCtx.name.getText());
      condType = PredefinedType.get(((VariableSymbol) varSym).getType());
    }

    if(!condType.equals(PredefinedType.BOOLEAN))
      throw new NotBooleanCondition(ctx.getText());

    super.enterClauseWhen(ctx);
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
