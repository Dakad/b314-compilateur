package be.unamur.info.b314.compiler.main;

import be.unamur.info.b314.compiler.B314BaseVisitor;
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
import java.util.Map;

/**
 * Print PCode for a given tree using provided symbol table and printer.
 * This class uses ANTLR visitor mechanism.
 * @author Xavier Devroey - xavier.devroey@unamur.be
 */
public class PCodeVisitor extends B314BaseVisitor<Object> {

  /**
   * "Memory" of B314; variable/value pairs go here
   */
  private final SymTableFiller symTable;

  private final PCodePrinter printer;

  public PCodeVisitor(SymTableFiller symTable, PCodePrinter printer) {
    this.symTable = symTable;
    this.printer = printer;
  }

  @Override
  public Object visitRoot(RootContext ctx) {
    this.printer.printSetStackPointer(symTable.countVariables()); // Reserve space for variables
    this.printer.printComments("Start root rule ...");
    super.visitRoot(ctx);  // Print instructions
    this.printer.printComments("End root rule ... ");
    this.printer.printStop(); // Stop execution
    return null;
  }

  @Override
  public Object visitImpDecl(ImpDeclContext ctx) {
    return super.visitImpDecl(ctx);
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


  @Override
  public Object visitType(TypeContext ctx) {
    return super.visitType(ctx);
  }

  @Override
  public Object visitScalar(ScalarContext ctx) {
    return super.visitScalar(ctx);
  }

  @Override
  public Object visitArray(ArrayContext ctx) {
    return super.visitArray(ctx);
  }

  @Override
  public Object visitBoard(BoardContext ctx) {
    return super.visitBoard(ctx);
  }

  @Override
  public Object visitVarDecl(VarDeclContext ctx) {
    return super.visitVarDecl(ctx);
  }

  @Override
  public Object visitFileDecl(FileDeclContext ctx) {
    return super.visitFileDecl(ctx);
  }

  @Override
  public Object visitAction(ActionContext ctx) {
    return super.visitAction(ctx);
  }

  @Override
  public Object visitIntVal(IntValContext ctx) {
    return super.visitIntVal(ctx);
  }

  @Override
  public Object visitOpInt(OpIntContext ctx) {
    return super.visitOpInt(ctx);
  }

  @Override
  public Object visitBoolVal(BoolValContext ctx) {
    return super.visitBoolVal(ctx);
  }

  @Override
  public Object visitOpBool(OpBoolContext ctx) {
    return super.visitOpBool(ctx);
  }

  @Override
  public Object visitOpBoolCompare(OpBoolCompareContext ctx) {
    return super.visitOpBoolCompare(ctx);
  }

  @Override
  public Object visitExprDFct(ExprDFctContext ctx) {
    return super.visitExprDFct(ctx);
  }

  @Override
  public Object visitExprD(ExprDContext ctx) {
    return super.visitExprD(ctx);
  }

  @Override
  public Object visitExprInt(ExprIntContext ctx) {
    return super.visitExprInt(ctx);
  }

  @Override
  public Object visitExprBool(ExprBoolContext ctx) {
    return super.visitExprBool(ctx);
  }

  @Override
  public Object visitExprCase(ExprCaseContext ctx) {
    return super.visitExprCase(ctx);
  }

  @Override
  public Object visitExprG(ExprGContext ctx) {
    return super.visitExprG(ctx);
  }

  @Override
  public Object visitFctDecl(FctDeclContext ctx) {
    return super.visitFctDecl(ctx);
  }

  @Override
  public Object visitInstr(InstrContext ctx) {
    return super.visitInstr(ctx);
  }

  @Override
  public Object visitProgram(ProgramContext ctx) {
    return super.visitProgram(ctx);
  }

  @Override
  public Object visitProgramMonde(ProgramMondeContext ctx) {
    return super.visitProgramMonde(ctx);
  }

  @Override
  public Object visitProgramMondeGlobalDecl(ProgramMondeGlobalDeclContext ctx) {
    return super.visitProgramMondeGlobalDecl(ctx);
  }

  @Override
  public Object visitProgramStrat(ProgramStratContext ctx) {
    return super.visitProgramStrat(ctx);
  }

  @Override
  public Object visitProgramStratGlobalDecl(ProgramStratGlobalDeclContext ctx) {
    return super.visitProgramStratGlobalDecl(ctx);
  }

  @Override
  public Object visitClauseDefault(ClauseDefaultContext ctx) {
    return super.visitClauseDefault(ctx);
  }

  @Override
  public Object visitClauseWhen(ClauseWhenContext ctx) {
    return super.visitClauseWhen(ctx);
  }
}
