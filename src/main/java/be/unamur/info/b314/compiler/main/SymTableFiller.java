package be.unamur.info.b314.compiler.main;

import com.google.common.collect.Maps;

import java.util.Map;

/**
 * Fills a symbol table using ANTLR listener for DEMO langage.
 *
 * @author Xavier Devroey - xavier.devroey@unamur.be
 */
public class SymTableFiller extends DEMOBaseListener {

    private final Map<String, Integer> symTable;

    private int offset = 0; // The offset of the variable in the PMachine stack

    public SymTableFiller() {
        this.symTable = Maps.newHashMap();
    }

    @Override
    public void enterAffectInstr(DEMOParser.AffectInstrContext ctx) {
        addVariable(ctx.ID().getText());
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
