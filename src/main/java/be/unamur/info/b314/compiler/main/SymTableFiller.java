package be.unamur.info.b314.compiler.main;

import be.unamur.info.b314.compiler.B314BaseListener;
import be.unamur.info.b314.compiler.B314Parser.RootContext;

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
    super.enterRoot(ctx);
  }

  @Override
  public void exitRoot(RootContext ctx) {
    super.exitRoot(ctx);
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
    super.visitTerminal(node);
  }

  @Override
  public void visitErrorNode(ErrorNode node) {
    super.visitErrorNode(node);
  }

}
