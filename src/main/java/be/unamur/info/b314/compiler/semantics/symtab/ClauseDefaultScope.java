package be.unamur.info.b314.compiler.semantics.symtab;

import org.antlr.symtab.Scope;
import org.antlr.v4.runtime.ParserRuleContext;

/**
 * @overview This class is used to represent the scope of the clause 'when '.
 */
public class ClauseDefaultScope extends org.antlr.symtab.SymbolWithScope {

    public static final String NAME = "clause_default";

    private ParserRuleContext defNode;

    /**
     * @requires enclosingsScope not nul
     * @requires name not nul
     * @effects Initialise the scope with it  enclosing scope
     */
    public ClauseDefaultScope(Scope enclosingScope) {
        super(NAME);
        this.setEnclosingScope(enclosingScope);
    }

    public void setDefNode(ParserRuleContext def) {
        this.defNode = defNode;
    }

    public ParserRuleContext getDefNode() {
        return defNode;
    }
}
