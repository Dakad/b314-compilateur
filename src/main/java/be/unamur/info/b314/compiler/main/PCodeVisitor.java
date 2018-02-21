package be.unamur.info.b314.compiler.main;

import be.unamur.info.b314.compiler.B314BaseVisitor;
import be.unamur.info.b314.compiler.B314Parser.RootContext;
import java.util.Map;

/**
 * Print PCode for a given tree using provided symbol table and printer. This 
 * class uses ANTLR visitor mechanism.
 * @author Xavier Devroey - xavier.devroey@unamur.be
 */
public class PCodeVisitor extends B314BaseVisitor<Object> {

  private final Map<String, Integer> symTable;

  private final PCodePrinter printer;

  public PCodeVisitor(Map<String, Integer> symTable, PCodePrinter printer) {
    this.symTable = symTable;
    this.printer = printer;
  }

  @Override
  public Object visitRoot(RootContext ctx) {
    this.printer.printSetStackPointer(symTable.size()); // Reserve space for variables
    this.printer.printComments("Start declare ...");
    super.visitRoot(ctx);  // Print instructions
    this.printer.printComments("End declare ... ");
    this.printer.printStop(); // Stop execution
    return null;
  }



/*
  @Override
  public Object visitAffectInstr(B314Parser.AffectInstrContext ctx) {
    String var = ctx.ID().getText();
    printer.printLoadAdress(PCodeTypes.Int, 0, symTable.get(var)); // Load variable adress
    ctx.expression().accept(this); // Compute expression
    printer.printStore(PCodeTypes.Int);
    return null;
  }

  @Override
  public Object visitConstantExpr(B314Parser.ConstantExprContext ctx) {
    int value = Integer.parseInt(ctx.NUMBER().getText()); // Get value
    printer.printLoadConstant(PCodeTypes.Int, value); // Load constant value
    return null;
  }

  @Override
  public Object visitVariableExpr(B314Parser.VariableExprContext ctx) {
    String var = ctx.ID().getText();
    printer.printLoad(PCodeTypes.Int, 0, symTable.get(var)); // Load value in cell at given adress
    return null;
  }

  @Override
  public Object visitPlusMinusExpr(B314Parser.PlusMinusExprContext ctx) {
    ctx.expression(0).accept(this); // Print left expression
    ctx.expression(1).accept(this); // Print right expression
    if(ctx.PLUS()!= null){
      printer.printAdd(PCodeTypes.Int);
    } else {
      printer.printSub(PCodeTypes.Int);
    }
    return null;
  }*/

}
