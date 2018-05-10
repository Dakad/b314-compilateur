package be.unamur.info.b314.compiler.semantics.symtab;

import org.antlr.symtab.Scope;
import org.antlr.v4.runtime.ParserRuleContext;

/**
 *
 */
public class ClauseDefaultScope extends org.antlr.symtab.SymbolWithScope {

    public static final String NAME = "clause_default";

    private ParserRuleContext def;

    /**
     * @requires enclosingsScope not nul
     * @requires name not nul
     * @param enclosingScope
     * @effects Initialise the scope
     */

    public ClauseDefaultScope(Scope enclosingScope) {
        super(NAME);
        this.setEnclosingScope(enclosingScope);

    }

    public void setDef(ParserRuleContext def) {
        this.def = def;
    }

    public ParserRuleContext getDef() {
        return def;
    }
}
