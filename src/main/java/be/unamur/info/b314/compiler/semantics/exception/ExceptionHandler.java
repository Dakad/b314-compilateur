package be.unamur.info.b314.compiler.semantics.exception;

import be.unamur.info.b314.compiler.B314Parser.VarDeclContext;
import org.antlr.runtime.tree.ParseTree;
import org.antlr.v4.runtime.ParserRuleContext;

/**
 * @overview ExceptionHandler is an handler for thrown exception. It sole purpose is to thrown
 * pretty Semantic Error. An ExceptionHandler is stateless, thus, immutable.
 */
public class ExceptionHandler {

  private static final String ERR_MSG_ALREADY_DECLARED_AS_FUNCTION = "Cannot use the function name as identifier for a parameter";
  private static final String ERR_MSG_ALREADY_DECLARED_FUNCTION = "A global function has already been defined with the name";
  private static final String ERR_MSG_DUPLICATE_PARAMETER = "A parameter has been already defined for this function with the identifier";
  private static final String ERR_MSG_DUPLICATE_VARIABLE = "A variable/parameter has been already defined for this";
  private static final String ERR_MSG_ALREADY_DECLARED_VARIABLE = "A global variable has already been defined with the name";
  private static final String ERR_MSG_NOT_POSITIVE_SIZE_FOR_ARRAY = "The array's size is null or not positive";
  private static final String ERR_MSG_NOT_MATCHING_RETURN_TYPE = "The return type of the function is incorrect - Return type";
  private static final String ERR_MSG_NOT_MATCHING_TYPE = "The type of the instruction is incorrect";
  private static final String ERR_MSG_UNDECLARED_VARIABLE = "Undeclared identifier";
  private static final String ERR_MSG_NOT_BOOLEAN_CONDITION = "The type of the condition statement is not boolean";
  private static final String ERR_MSG_NOT_RETURN_VOID_FUNCTION = "Function has not return VOID.";


  /**
   * @return An pretty formatted error msg containing the line and the instruction causing the
   * error.
   * @requires ctx - Must be defined
   * @requires msg - The instruction causing the error
   * @effects Extract different data about the instruction from the provided ctx <br> such as the
   * current <i>line number</i>, <i>the start of incorrect instruction</i>
   */
  private static String formatterMsg(ParserRuleContext ctx, String msg) {
    int lnNb = ctx.start.getLine();
    StringBuilder msgError = new StringBuilder("\nERROR :\t");
    msgError.append(msg).append("\n");
    String instruction = "";
    for (org.antlr.v4.runtime.tree.ParseTree child : ctx.children) {
      instruction += child.getText() + " ";
    }
    msgError.append("LINE :\t").append(lnNb).append("\t");
    msgError.append(instruction).append("\n");
    return msgError.toString();
  }


  /**
   * @throws AlreadyDeclaredAsFunction with the pretty msg.
   * @requires ctx - Context of the instruction causing the error. Must be not null
   */
  public static void throwAlreadyDeclaredAsFunction(ParserRuleContext ctx) {
    String formatMsg = formatterMsg(ctx, ERR_MSG_ALREADY_DECLARED_AS_FUNCTION);
    throw new AlreadyDeclaredAsFunction(formatMsg);
  }


  /**
   * @throws AlreadyDeclaredFunction with the pretty msg.
   * @requires ctx - Context of the instruction causing the error. Must be not null
   */
  public static void throwAlreadyDeclaredFunction(ParserRuleContext ctx) {
    String formatMsg = formatterMsg(ctx, ERR_MSG_ALREADY_DECLARED_FUNCTION);
    throw new AlreadyDeclaredFunction(formatMsg);
  }


  /**
   * @throws DuplicateParameter with the pretty msg.
   * @requires ctx - Context of the instruction causing the error. Must be not null
   */
  public static void throwDuplicateParameter(ParserRuleContext ctx) {
    String formatMsg = formatterMsg(ctx, ERR_MSG_DUPLICATE_PARAMETER);
    throw new DuplicateParameter(formatMsg);
  }


  /**
   * @throws DuplicateVariable with the pretty msg.
   * @requires ctx - Context of the instruction causing the error. Must be not null
   */
  public static void throwDuplicateVariable(ParserRuleContext ctx) {
        String formatMsg = formatterMsg(ctx, ERR_MSG_DUPLICATE_VARIABLE);
        throw new DuplicateVariable(formatMsg);
  }


  /**
   * @throws AlreadyDeclaredVariable with the pretty msg.
   * @requires ctx - Context of the instruction causing the error. Must be not null
   */
  public static void throwAlreadyDeclaredVariable(ParserRuleContext ctx) {
    String formatMsg = formatterMsg(ctx, ERR_MSG_ALREADY_DECLARED_VARIABLE);
    throw new AlreadyDeclaredVariable(formatMsg);
  }


  /**
   * @throws NotPositiveSizeForArray with the pretty msg.
   * @requires ctx - Context of the instruction causing the error. Must be not null
   */
  public static void throwNotPositiveSizeForArray(ParserRuleContext ctx) {
    String formatMsg = formatterMsg(ctx, ERR_MSG_NOT_POSITIVE_SIZE_FOR_ARRAY);
    throw new NotPositiveSizeForArray(formatMsg);
  }


  /**
   * @throws NotMatchingReturnType with the pretty msg.
   * @requires ctx - Context of the instruction causing the error. Must be not null
   */
  public static void throwNotMatchingReturnType(ParserRuleContext ctx) {
    String formatMsg = formatterMsg(ctx, ERR_MSG_NOT_MATCHING_RETURN_TYPE);
    throw new NotMatchingReturnType(formatMsg);
  }


  /**
   * @throws NotMatchingType with the pretty msg.
   * @requires ctx - Context of the instruction causing the error. Must be not null
   */
  public static void throwNotMatchingType(ParserRuleContext ctx) {
    String formatMsg = formatterMsg(ctx, ERR_MSG_NOT_MATCHING_TYPE);
    throw new NotMatchingType(formatMsg);
  }


  /**
   * @throws UndeclaredVariable with the pretty msg.
   * @requires ctx - Context of the instruction causing the error. Must be not null
   */
  public static void throwUndesttclaredVariable(ParserRuleContext ctx) {
    String formatMsg = formatterMsg(ctx, ERR_MSG_UNDECLARED_VARIABLE);
    throw new UndeclaredVariable(formatMsg);
  }


  /**
   * @throws NotBooleanCondition with the pretty msg.
   * @requires ctx - Context of the instruction causing the error. Must be not null
   */
  public static void throwNotBooleanCondition(ParserRuleContext ctx) {
    String formatMsg = formatterMsg(ctx, ERR_MSG_NOT_BOOLEAN_CONDITION);
    throw new NotBooleanCondition(formatMsg);
  }


  /**
   * @throws NotReturnVoidFucntion with the pretty msg.
   * @requires ctx - Context of the instruction causing the error. Must be not null
   */
  public static void throwNotReturnVoidFucntion(ParserRuleContext ctx) {
    String formatMsg = formatterMsg(ctx, ERR_MSG_NOT_RETURN_VOID_FUNCTION);
    throw new NotReturnVoidFucntion(formatMsg);
  }

}
