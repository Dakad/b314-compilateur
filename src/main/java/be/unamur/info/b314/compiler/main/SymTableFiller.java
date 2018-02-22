package be.unamur.info.b314.compiler.main;

import be.unamur.info.b314.compiler.B314BaseListener;
import be.unamur.info.b314.compiler.B314Parser;
import be.unamur.info.b314.compiler.B314Parser.RootContext;
import com.google.common.collect.Maps;

import java.util.Map;
import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.tree.ErrorNode;
import org.antlr.v4.runtime.tree.TerminalNode;

/**
 * Fills a symbol table using ANTLR listener for B314 langage.
 *
 * @author Xavier Devroey - xavier.devroey@unamur.be
 */
public class SymTableFiller extends B314BaseListener {

  private final Map<String, Integer> symTable;

  private int offset = 0; // The offset of the variable in the PMachine stack

  public SymTableFiller() {
    this.symTable = Maps.newHashMap();
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

  private void addVariable(String var) {
    if (!symTable.containsKey(var)) {
      symTable.put(var, offset);
      offset++;
    }
  }

  public Map<String, Integer> getSymTable() {
    return symTable;
  }

}
