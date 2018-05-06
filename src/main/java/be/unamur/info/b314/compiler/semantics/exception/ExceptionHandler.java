package be.unamur.info.b314.compiler.semantics.exception;

import org.antlr.v4.runtime.ParserRuleContext;

/**
 * @overview ExceptionHandler is an handler for thrown exception. It sole purpose is to thrown pretty Semantic Error.
 * An ExceptionHandler is stateless, thus, immutable.
 *
 */
public class ExceptionHandler {

  private static final String ERR_MSG_ALREADY_DECLARED_FUNCTION = "Cannot use the function name as identifier for a parameter : ";
  private static final String ERR_MSG_DUPLICATE_PARAMETER = "A parameter has been already defined for this function with the identifier : ";
  private static final String ERR_MSG_ALREADY_DECLARED_VARIABLE = "A global variable has already been defined with the name : ";
  private static final String ERR_MSG_NOT_POSITIVE_SIZE_FOR_ARRAY = "The array's size is null or not positive - Size : ";
  private static final String ERR_MSG_NOT_MATCHING_TYPE = "The type of the instruction is incorrect - Instr : ";
  private static final String ERR_MSG_UNDECLARED_VARIABLE = "Undeclared identifier : ";
  private static final String ERR_MSG_NOT_BOOLEAN_CONDITION = "The type of the condition statement is not boolean - Instr : ";

  /**
   *
   * @requires ctx - Must be defined
   * @requires msg - The instruction causing the error
   * @effects Extract different data about the instruction from the provided ctx <br>
   *          such as the current <i>line number</i>, <i>the start of incorrect instruction</i>
   * @return An pretty formatted error msg containing the line and the instruction causing the error.
   */
  private static String formatterMsg(ParserRuleContext ctx, String msg) {
    int lnNb = ctx.start.getLine();
    String msgError = "ERROR LINE : "+lnNb+". "+msg;
    return msgError;
  }



  /**
   *
   *@requires ctx - Context of the instruction causing the error. Must be not null
   * @throws AlreadyDeclaredFunction with the pretty msg.
   */
  public static void throwAlreadyDeclaredFunction(ParserRuleContext ctx) {
    String formatMsg = formatterMsg(ctx, ERR_MSG_ALREADY_DECLARED_FUNCTION);
    throw  new AlreadyDeclaredFunction(formatMsg);
  }

  /**
   *
   *@requires ctx - Context of the instruction causing the error. Must be not null
   *@requires name - name function declared
   * @throws AlreadyDeclaredFunction with the pretty msg.
   */
  public static void throwAlreadyDeclaredAsFunction(ParserRuleContext ctx, String name) {
    String formatMsg = formatterMsg(ctx, ERR_MSG_ALREADY_DECLARED_FUNCTION)+name;
    throw  new AlreadyDeclaredFunction(formatMsg);
  }



  /**
   *
   *@requires ctx - Context of the instruction causing the error. Must be not null
   * @throws DuplicateParameter with the pretty msg.
   */
  public static void throwDuplicateParameter(ParserRuleContext ctx) {
    String formatMsg = formatterMsg(ctx, ERR_MSG_DUPLICATE_PARAMETER);
    throw  new DuplicateParameter(formatMsg);
  }

  /**
   *
   *@requires ctx - Context of the instruction causing the error. Must be not null
   *@requires name - parameter
   * @throws DuplicateParameter with the pretty msg.
   */
  public static void throwDuplicateParameter(ParserRuleContext ctx, String name) {
    String formatMsg = formatterMsg(ctx, ERR_MSG_DUPLICATE_PARAMETER)+name;
    throw  new DuplicateParameter(formatMsg);
  }



  /**
   *
   *@requires ctx - Context of the instruction causing the error. Must be not null
   * @throws AlreadyDeclaredVariable with the pretty msg.
   */
  public static void throwAlreadyDeclaredVariable(ParserRuleContext ctx) {
    String formatMsg = formatterMsg(ctx, ERR_MSG_ALREADY_DECLARED_VARIABLE);
    throw  new AlreadyDeclaredVariable(formatMsg);
  }

  /**
   *
   *@requires ctx - Context of the instruction causing the error. Must be not null
   *@requires name - name variable
   * @throws AlreadyDeclaredVariable with the pretty msg.
   */
  public static void throwAlreadyDeclaredVariable(ParserRuleContext ctx, String name) {
    String formatMsg = formatterMsg(ctx, ERR_MSG_ALREADY_DECLARED_VARIABLE)+name;
    throw  new AlreadyDeclaredVariable(formatMsg);
  }



  /**
   *
   *@requires ctx - Context of the instruction causing the error. Must be not null
   * @throws NotPositiveSizeForArray with the pretty msg.
   */
  public static void throwNotPositiveSizeForArray(ParserRuleContext ctx) {
    String formatMsg = formatterMsg(ctx, ERR_MSG_NOT_POSITIVE_SIZE_FOR_ARRAY);
    throw  new NotPositiveSizeForArray(formatMsg);
  }

  /**
   *
   *@requires ctx - Context of the instruction causing the error. Must be not null
   *@requires name - name array
   * @throws NotPositiveSizeForArray with the pretty msg.
   */
  public static void throwNotPositiveSizeForArray(ParserRuleContext ctx, String name) {
    String formatMsg = formatterMsg(ctx, ERR_MSG_NOT_POSITIVE_SIZE_FOR_ARRAY)+name;
    throw  new NotPositiveSizeForArray(formatMsg);
  }



  /**
   *
   *@requires ctx - Context of the instruction causing the error. Must be not null
   * @throws NotMatchingType with the pretty msg.
   */
  public static void throwNotMatchingType(ParserRuleContext ctx) {
    String formatMsg = formatterMsg(ctx, ERR_MSG_NOT_MATCHING_TYPE);
    throw  new NotMatchingType(formatMsg);
  }

  /**
   *
   *@requires ctx - Context of the instruction causing the error. Must be not null
   *@requires name - name type
   * @throws NotMatchingType with the pretty msg.
   */
  public static void throwNotMatchingType(ParserRuleContext ctx, String name) {
    String formatMsg = formatterMsg(ctx, ERR_MSG_NOT_MATCHING_TYPE)+name;
    throw  new NotMatchingType(formatMsg);
  }



  /**
   *
   *@requires ctx - Context of the instruction causing the error. Must be not null
   * @throws UndeclaredVariable with the pretty msg.
   */
  public static void throwUndeclaredVariable(ParserRuleContext ctx) {
    String formatMsg = formatterMsg(ctx, ERR_MSG_UNDECLARED_VARIABLE);
    throw  new UndeclaredVariable(formatMsg);
  }

  /**
   *
   *@requires ctx - Context of the instruction causing the error. Must be not null
   *@requires id - id du variable
   * @throws UndeclaredVariable with the pretty msg.
   */
  public static void throwUndeclaredVariable(ParserRuleContext ctx, String id) {
    String formatMsg = formatterMsg(ctx, ERR_MSG_UNDECLARED_VARIABLE)+id;
    throw  new UndeclaredVariable(formatMsg);
  }



  /**
   *
   *@requires ctx - Context of the instruction causing the error. Must be not null
   * @throws NotBooleanCondition with the pretty msg.
   */
  public static void throwNotBooleanCondition(ParserRuleContext ctx) {
    String formatMsg = formatterMsg(ctx, ERR_MSG_NOT_BOOLEAN_CONDITION);
    throw  new NotBooleanCondition(formatMsg);
  }

  /**
   *
   *@requires ctx - Context of the instruction causing the error. Must be not null
   *@requires instr - instruction
   * @throws NotBooleanCondition with the pretty msg.
   */
  public static void throwNotBooleanCondition(ParserRuleContext ctx, String instr) {
    String formatMsg = formatterMsg(ctx, ERR_MSG_NOT_BOOLEAN_CONDITION)+instr;
    throw  new NotBooleanCondition(formatMsg);
  }

}
