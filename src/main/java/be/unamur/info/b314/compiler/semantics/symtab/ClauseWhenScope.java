package be.unamur.info.b314.compiler.semantics.symtab;


import org.antlr.symtab.Scope;
import org.antlr.v4.runtime.ParserRuleContext;

/**
 * @overview
 */
public class ClauseWhenScope extends org.antlr.symtab.SymbolWithScope {

  private ParserRuleContext defNode;

  /**
   * @requires enclosiing to not be null
   * @effects Initialise the Scope.
   */
  public ClauseWhenScope(Scope enclosingScope) {
    super("clause_when");
    this.setEnclosingScope(enclosingScope);
  }


  public void setDefNode(ParserRuleContext defNode) {
    this.defNode = defNode;
  }

  public ParserRuleContext getDefNode() {
    return defNode;
  }
}
