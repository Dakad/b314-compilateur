package be.unamur.info.b314.compiler.semantics.symtab;


import org.antlr.symtab.Scope;
import org.antlr.v4.runtime.ParserRuleContext;

/**
 * @overview //TODO Ajouter overview
 */
public class ClauseWhenScope extends org.antlr.symtab.SymbolWithScope {

  public static final String NAME = "clause_when_";

  private ParserRuleContext defNode;

  /**
   * @requires enclosiing to not be null
   * @requires condition to not be null
   * @effects Initialise the Scope.
   */
  public ClauseWhenScope(Scope enclosingScope, String condition) {
    super(NAME + condition);
    this.setEnclosingScope(enclosingScope);
  }


  public void setDefNode(ParserRuleContext defNode) {
    this.defNode = defNode;
  }

  public ParserRuleContext getDefNode() {
    return defNode;
  }
}
