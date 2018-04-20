package be.unamur.info.b314.compiler.semantics;

import be.unamur.info.b314.compiler.B314BaseListener;
import be.unamur.info.b314.compiler.B314Parser.ArenaContext;
import be.unamur.info.b314.compiler.B314Parser.ArrayContext;
import be.unamur.info.b314.compiler.B314Parser.BoardContext;
import be.unamur.info.b314.compiler.B314Parser.BoolValContext;
import be.unamur.info.b314.compiler.B314Parser.CaseContext;
import be.unamur.info.b314.compiler.B314Parser.ClauseDefaultContext;
import be.unamur.info.b314.compiler.B314Parser.ClauseWhenContext;
import be.unamur.info.b314.compiler.B314Parser.ComputeContext;
import be.unamur.info.b314.compiler.B314Parser.EnvCaseContext;
import be.unamur.info.b314.compiler.B314Parser.ExprBoolContext;
import be.unamur.info.b314.compiler.B314Parser.ExprDContext;
import be.unamur.info.b314.compiler.B314Parser.ExprDFctContext;
import be.unamur.info.b314.compiler.B314Parser.ExprIntContext;
import be.unamur.info.b314.compiler.B314Parser.FctDeclContext;
import be.unamur.info.b314.compiler.B314Parser.FileDeclContext;
import be.unamur.info.b314.compiler.B314Parser.IfThenContext;
import be.unamur.info.b314.compiler.B314Parser.IfThenElseContext;
import be.unamur.info.b314.compiler.B314Parser.ImpDeclContext;
import be.unamur.info.b314.compiler.B314Parser.IntValContext;
import be.unamur.info.b314.compiler.B314Parser.LocalVarDeclContext;
import be.unamur.info.b314.compiler.B314Parser.MoveContext;
import be.unamur.info.b314.compiler.B314Parser.NearbyContext;
import be.unamur.info.b314.compiler.B314Parser.NextContext;
import be.unamur.info.b314.compiler.B314Parser.NothingContext;
import be.unamur.info.b314.compiler.B314Parser.OpBoolCompareContext;
import be.unamur.info.b314.compiler.B314Parser.OpBoolContext;
import be.unamur.info.b314.compiler.B314Parser.OpIntContext;
import be.unamur.info.b314.compiler.B314Parser.ProgramContext;
import be.unamur.info.b314.compiler.B314Parser.ProgramMondeContext;
import be.unamur.info.b314.compiler.B314Parser.ProgramMondeGlobalDeclContext;
import be.unamur.info.b314.compiler.B314Parser.ProgramStratContext;
import be.unamur.info.b314.compiler.B314Parser.ProgramStratGlobalDeclContext;
import be.unamur.info.b314.compiler.B314Parser.RootContext;
import be.unamur.info.b314.compiler.B314Parser.ScalarContext;
import be.unamur.info.b314.compiler.B314Parser.SetToContext;
import be.unamur.info.b314.compiler.B314Parser.ShootContext;
import be.unamur.info.b314.compiler.B314Parser.SkipContext;
import be.unamur.info.b314.compiler.B314Parser.TypeContext;
import be.unamur.info.b314.compiler.B314Parser.UseContext;
import be.unamur.info.b314.compiler.B314Parser.VarContext;
import be.unamur.info.b314.compiler.B314Parser.VarDeclContext;
import be.unamur.info.b314.compiler.B314Parser.WhileContext;
import be.unamur.info.b314.compiler.semantics.exception.AlreadyGlobalDeclared;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import org.antlr.symtab.ArrayType;
import org.antlr.symtab.GlobalScope;
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
    this.definePrimitiveType();
  }

  private void definePrimitiveType() {
    this.symTable.definePredefinedSymbol(PredefinedType.BOOLEAN.type());
    this.symTable.definePredefinedSymbol(PredefinedType.INTEGER.type());
    this.symTable.definePredefinedSymbol(PredefinedType.SQUARE.type());
    this.symTable.definePredefinedSymbol(PredefinedType.VOID.type());
  }

  /**
   * @return a read-only view of the Symbol table.
   * @throws UnsupportedOperationException if attemps to modifiy the Map in any way.
   */
  public Map<String, ? extends Symbol> getSymTable() {
    return Collections.unmodifiableMap(symTable.GLOBALS.getMembers());
  }


  /**
   * @return the number of global variables declared
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


  @Override
  public void enterType(TypeContext ctx) {
    ParseTree type = ctx.getChild(0);
    if (type == null) {
      return;
    }

    Type predefType = null;
    String varName = ((VarDeclContext) ctx.getParent()).name.getText();
    VariableSymbol var =  (VariableSymbol) currentScope.getSymbol(varName);

    if (type instanceof ScalarContext) {
      predefType = PredefinedType.get(type.getText()).type();
      var.setType(predefType);
    } else {
      ArrayContext typeArray = (ArrayContext) type;
      // First, get the type of this array vaWr.
      predefType = PredefinedType.get(typeArray.scalar().getText()).type();

      // Init the array
      ArrayType array = this.createArrayType(typeArray.elt, predefType);
      var.setType(array);
    }
  }


  @Override
  public void enterScalar(ScalarContext ctx) {
    super.enterScalar(ctx);
  }

  @Override
  public void enterArray(ArrayContext ctx) {
    super.enterArray(ctx);
  }

  private ArrayType createArrayType(List<IntValContext> arraySizes, Type type) {
    if (arraySizes.isEmpty()) {
      return null;
    }

    if (arraySizes.size() > 1) { // ? Is multi-dimension array ?
      ArrayType nestedArray;
      for (int i = arraySizes.size() - 1, sizeNested; i > 0; --i) {
        sizeNested = Integer.parseInt(arraySizes.get(i).INTEGER().getText());
        nestedArray = new ArrayType(type, sizeNested);
        type = nestedArray;
      }
    }
    int size = Integer.parseInt(arraySizes.get(0).INTEGER().getText());
    return new ArrayType(type, size);
  }

  @Override
  public void enterBoard(BoardContext ctx) {
    super.enterBoard(ctx);
  }

  @Override
  public void exitBoard(BoardContext ctx) {
    super.exitBoard(ctx);
  }

  @Override
  public void enterVarDecl(VarDeclContext ctx) {
    String name = ctx.name.getText();
    if (symTable.GLOBALS.getSymbol(name) != null) {
      throw new AlreadyGloballyDeclared(name);
    }
    try {
      VariableSymbol var = new VariableSymbol(name);
      currentScope.define(var);
    } catch (IllegalArgumentException e) {
      return;
    }
  }


  @Override
  public void enterImpDecl(ImpDeclContext ctx) {
    super.enterImpDecl(ctx);
  }

  @Override
  public void exitImpDecl(ImpDeclContext ctx) {
    super.exitImpDecl(ctx);
  }

  @Override
  public void enterFileDecl(FileDeclContext ctx) {
    super.enterFileDecl(ctx);
  }

  @Override
  public void exitFileDecl(FileDeclContext ctx) {
    super.exitFileDecl(ctx);
  }

  @Override
  public void enterMove(MoveContext ctx) {
    super.enterMove(ctx);
  }

  @Override
  public void exitMove(MoveContext ctx) {
    super.exitMove(ctx);
  }

  @Override
  public void enterShoot(ShootContext ctx) {
    super.enterShoot(ctx);
  }

  @Override
  public void exitShoot(ShootContext ctx) {
    super.exitShoot(ctx);
  }

  @Override
  public void enterUse(UseContext ctx) {
    super.enterUse(ctx);
  }

  @Override
  public void exitUse(UseContext ctx) {
    super.exitUse(ctx);
  }

  @Override
  public void enterNothing(NothingContext ctx) {
    super.enterNothing(ctx);
  }

  @Override
  public void exitNothing(NothingContext ctx) {
    super.exitNothing(ctx);
  }

  @Override
  public void enterIntVal(IntValContext ctx) {
    super.enterIntVal(ctx);
  }

  @Override
  public void exitIntVal(IntValContext ctx) {
    super.exitIntVal(ctx);
  }

  @Override
  public void enterOpInt(OpIntContext ctx) {
    super.enterOpInt(ctx);
  }

  @Override
  public void exitOpInt(OpIntContext ctx) {
    super.exitOpInt(ctx);
  }

  @Override
  public void enterBoolVal(BoolValContext ctx) {
    super.enterBoolVal(ctx);
  }

  @Override
  public void exitBoolVal(BoolValContext ctx) {
    super.exitBoolVal(ctx);
  }

  @Override
  public void enterOpBool(OpBoolContext ctx) {
    super.enterOpBool(ctx);
  }

  @Override
  public void exitOpBool(OpBoolContext ctx) {
    super.exitOpBool(ctx);
  }

  @Override
  public void enterOpBoolCompare(OpBoolCompareContext ctx) {
    super.enterOpBoolCompare(ctx);
  }

  @Override
  public void exitOpBoolCompare(OpBoolCompareContext ctx) {
    super.exitOpBoolCompare(ctx);
  }

  @Override
  public void enterExprDFct(ExprDFctContext ctx) {
    super.enterExprDFct(ctx);
  }

  @Override
  public void exitExprDFct(ExprDFctContext ctx) {
    super.exitExprDFct(ctx);
  }

  @Override
  public void enterExprD(ExprDContext ctx) {
    super.enterExprD(ctx);
  }

  @Override
  public void exitExprD(ExprDContext ctx) {
    super.exitExprD(ctx);
  }

  @Override
  public void enterExprInt(ExprIntContext ctx) {
    super.enterExprInt(ctx);
  }

  @Override
  public void exitExprInt(ExprIntContext ctx) {
    super.exitExprInt(ctx);
  }

  @Override
  public void enterExprBool(ExprBoolContext ctx) {
    super.enterExprBool(ctx);
  }

  @Override
  public void exitExprBool(ExprBoolContext ctx) {
    super.exitExprBool(ctx);
  }

  @Override
  public void enterEnvCase(EnvCaseContext ctx) {
    super.enterEnvCase(ctx);
  }

  @Override
  public void exitEnvCase(EnvCaseContext ctx) {
    super.exitEnvCase(ctx);
  }

  @Override
  public void enterNearby(NearbyContext ctx) {
    super.enterNearby(ctx);
  }

  @Override
  public void exitNearby(NearbyContext ctx) {
    super.exitNearby(ctx);
  }

  @Override
  public void enterVar(VarContext ctx) {
    super.enterVar(ctx);
  }

  @Override
  public void exitVar(VarContext ctx) {
    super.exitVar(ctx);
  }

  @Override
  public void enterArena(ArenaContext ctx) {
    super.enterArena(ctx);
  }

  @Override
  public void exitArena(ArenaContext ctx) {
    super.exitArena(ctx);
  }

  @Override
  public void enterCase(CaseContext ctx) {
    super.enterCase(ctx);
  }

  @Override
  public void exitCase(CaseContext ctx) {
    super.exitCase(ctx);
  }

  @Override
  public void enterFctDecl(FctDeclContext ctx) {
    super.enterFctDecl(ctx);
  }

  @Override
  public void exitFctDecl(FctDeclContext ctx) {
    super.exitFctDecl(ctx);
  }

  @Override
  public void enterSkip(SkipContext ctx) {
    super.enterSkip(ctx);
  }

  @Override
  public void exitSkip(SkipContext ctx) {
    super.exitSkip(ctx);
  }

  @Override
  public void enterIfThen(IfThenContext ctx) {
    super.enterIfThen(ctx);
  }

  @Override
  public void exitIfThen(IfThenContext ctx) {
    super.exitIfThen(ctx);
  }

  @Override
  public void enterIfThenElse(IfThenElseContext ctx) {
    super.enterIfThenElse(ctx);
  }

  @Override
  public void exitIfThenElse(IfThenElseContext ctx) {
    super.exitIfThenElse(ctx);
  }

  @Override
  public void enterWhile(WhileContext ctx) {
    super.enterWhile(ctx);
  }

  @Override
  public void exitWhile(WhileContext ctx) {
    super.exitWhile(ctx);
  }

  @Override
  public void enterSetTo(SetToContext ctx) {
    super.enterSetTo(ctx);
  }

  @Override
  public void exitSetTo(SetToContext ctx) {
    super.exitSetTo(ctx);
  }

  @Override
  public void enterCompute(ComputeContext ctx) {
    super.enterCompute(ctx);
  }

  @Override
  public void exitCompute(ComputeContext ctx) {
    super.exitCompute(ctx);
  }

  @Override
  public void enterNext(NextContext ctx) {
    super.enterNext(ctx);
  }

  @Override
  public void exitNext(NextContext ctx) {
    super.exitNext(ctx);
  }

  @Override
  public void enterProgram(ProgramContext ctx) {
    super.enterProgram(ctx);
  }

  @Override
  public void exitProgram(ProgramContext ctx) {
    super.exitProgram(ctx);
  }

  @Override
  public void enterProgramMonde(ProgramMondeContext ctx) {
    super.enterProgramMonde(ctx);
  }

  @Override
  public void exitProgramMonde(ProgramMondeContext ctx) {
    super.exitProgramMonde(ctx);
  }

  @Override
  public void enterProgramMondeGlobalDecl(ProgramMondeGlobalDeclContext ctx) {
    super.enterProgramMondeGlobalDecl(ctx);
  }

  @Override
  public void exitProgramMondeGlobalDecl(ProgramMondeGlobalDeclContext ctx) {
    super.exitProgramMondeGlobalDecl(ctx);
  }

  @Override
  public void enterProgramStrat(ProgramStratContext ctx) {
    super.enterProgramStrat(ctx);
  }

  @Override
  public void exitProgramStrat(ProgramStratContext ctx) {
    super.exitProgramStrat(ctx);
  }

  @Override
  public void enterProgramStratGlobalDecl(ProgramStratGlobalDeclContext ctx) {
    super.enterProgramStratGlobalDecl(ctx);
  }

  @Override
  public void exitProgramStratGlobalDecl(ProgramStratGlobalDeclContext ctx) {
    super.exitProgramStratGlobalDecl(ctx);
  }

  @Override
  public void enterClauseDefault(ClauseDefaultContext ctx) {
    super.enterClauseDefault(ctx);
  }

  @Override
  public void exitClauseDefault(ClauseDefaultContext ctx) {
    super.exitClauseDefault(ctx);
  }

  @Override
  public void enterLocalVarDecl(LocalVarDeclContext ctx) {
    super.enterLocalVarDecl(ctx);
  }

  @Override
  public void exitLocalVarDecl(LocalVarDeclContext ctx) {
    super.exitLocalVarDecl(ctx);
  }

  @Override
  public void enterClauseWhen(ClauseWhenContext ctx) {
    super.enterClauseWhen(ctx);
  }

  @Override
  public void exitClauseWhen(ClauseWhenContext ctx) {
    super.exitClauseWhen(ctx);
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
  protected Object clone() throws CloneNotSupportedException {
    return super.clone();
  }

  @Override
  public String toString() {
    return super.toString();
  }
}
