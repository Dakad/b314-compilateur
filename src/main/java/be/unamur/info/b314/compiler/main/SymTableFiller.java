package be.unamur.info.b314.compiler.main;

import be.unamur.info.b314.compiler.B314BaseListener;
import be.unamur.info.b314.compiler.B314Parser.ActionContext;
import be.unamur.info.b314.compiler.B314Parser.ArrayContext;
import be.unamur.info.b314.compiler.B314Parser.BoardContext;
import be.unamur.info.b314.compiler.B314Parser.BoolValContext;
import be.unamur.info.b314.compiler.B314Parser.ClauseDefaultContext;
import be.unamur.info.b314.compiler.B314Parser.ClauseWhenContext;
import be.unamur.info.b314.compiler.B314Parser.ExprBoolContext;
import be.unamur.info.b314.compiler.B314Parser.ExprCaseContext;
import be.unamur.info.b314.compiler.B314Parser.ExprDContext;
import be.unamur.info.b314.compiler.B314Parser.ExprDFctContext;
import be.unamur.info.b314.compiler.B314Parser.ExprGContext;
import be.unamur.info.b314.compiler.B314Parser.ExprIntContext;
import be.unamur.info.b314.compiler.B314Parser.FctDeclContext;
import be.unamur.info.b314.compiler.B314Parser.FileDeclContext;
import be.unamur.info.b314.compiler.B314Parser.ImpDeclContext;
import be.unamur.info.b314.compiler.B314Parser.InstrContext;
import be.unamur.info.b314.compiler.B314Parser.IntValContext;
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
import be.unamur.info.b314.compiler.B314Parser.TypeContext;
import be.unamur.info.b314.compiler.B314Parser.VarDeclContext;
import org.antlr.symtab.GlobalScope;
import org.antlr.symtab.PrimitiveType;
import org.antlr.symtab.Scope;
import org.antlr.symtab.SymbolTable;
import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.tree.ErrorNode;
import org.antlr.v4.runtime.tree.TerminalNode;

/**
 * @overview SymTableFiller has to fills a symbol table
 *  using ANTLR listener for B314 langage.
 *  SymTableFiller is mutable.
 *
 *  @specfield symbolTable : Holds all scope and symbol of the parsed .B314
 *  @specfield currentScope : Represent the last Scope entered
 *
 *  @inv symbolTable will contains at least the global scope and the predifined types
 *  such as Boolean, Integer, Square
 *
 *
 *
 */
public class SymTableFiller extends B314BaseListener {

  private final SymbolTable symTable;

  private Scope currentScope;


  public SymTableFiller() {
    this.symTable = new SymbolTable();
    this.definePrimitiveType();
  }

  private void definePrimitiveType() {
    this.symTable.definePredefinedSymbol(new PrimitiveType("boolean"));
    this.symTable.definePredefinedSymbol(new PrimitiveType("integer"));
    this.symTable.definePredefinedSymbol(new PrimitiveType("square"));
  }


  private void pushScope(Scope scope) {
    currentScope = scope;
  }

  private void popScope() {
    currentScope = currentScope.getEnclosingScope();
  }

  /**
   * @return the number of global variables declared
   */
  public int countVariables() {
    return symTable.GLOBALS.getNumberOfSymbols();
  }

  @Override
  public void enterRoot(RootContext ctx) {
    GlobalScope gs = symTable.GLOBALS;
    pushScope(gs);
  }

  @Override
  public void exitRoot(RootContext ctx) {
    popScope();
  }

  @Override
  public void enterEveryRule(ParserRuleContext ctx) {
    super.enterEveryRule(ctx);
  }

  @Override
  public void exitEveryRule(ParserRuleContext ctx) {
    super.exitEveryRule(ctx);
  }

  @Override
  public void visitTerminal(TerminalNode node) {
    node.
    super.visitTerminal(node);
  }

  @Override
  public void visitErrorNode(ErrorNode node) {
    super.visitErrorNode(node);
  }

  @Override
  public void enterType(TypeContext ctx) {
    super.enterType(ctx);
  }

  @Override
  public void exitType(TypeContext ctx) {
    super.exitType(ctx);
  }

  @Override
  public void enterScalar(ScalarContext ctx) {
    super.enterScalar(ctx);
  }

  @Override
  public void exitScalar(ScalarContext ctx) {
    super.exitScalar(ctx);
  }

  @Override
  public void enterArray(ArrayContext ctx) {
    super.enterArray(ctx);
  }

  @Override
  public void exitArray(ArrayContext ctx) {
    super.exitArray(ctx);
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
    super.enterVarDecl(ctx);
  }

  @Override
  public void exitVarDecl(VarDeclContext ctx) {
    super.exitVarDecl(ctx);
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
  public void enterAction(ActionContext ctx) {
    super.enterAction(ctx);
  }

  @Override
  public void exitAction(ActionContext ctx) {
    super.exitAction(ctx);
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
  public void enterExprCase(ExprCaseContext ctx) {
    super.enterExprCase(ctx);
  }

  @Override
  public void exitExprCase(ExprCaseContext ctx) {
    super.exitExprCase(ctx);
  }

  @Override
  public void enterExprG(ExprGContext ctx) {
    super.enterExprG(ctx);
  }

  @Override
  public void exitExprG(ExprGContext ctx) {
    super.exitExprG(ctx);
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
  public void enterInstr(InstrContext ctx) {
    super.enterInstr(ctx);
  }

  @Override
  public void exitInstr(InstrContext ctx) {
    super.exitInstr(ctx);
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
