package be.unamur.info.b314.compiler.semantics.exception;

import org.antlr.v4.runtime.ParserRuleContext;

/**
 * @overview ExceptionHandler is an handler for thrown exception. It sole purpose is to thrown pretty Semantic Error.
 * An ExceptionHandler is stateless, thus, immutable.
 *
 */
public class ExceptionHandler {

  private static final String ERR_MSG_ALREADY_DECLARED_FUNCTION = "Cannot use the function name as identifier for a parameter : ";

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
   * @throws AlreadyDeclaredAsFunction with the pretty msg.
   */
  public static void throwAlreadyDeclaredAsFunction(ParserRuleContext ctx) {
    String formatMsg = formatterMsg(ctx, ERR_MSG_ALREADY_DECLARED_FUNCTION);
    throw  new AlreadyDeclaredAsFunction(formatMsg);
  }

  /**
   *
   *@requires ctx - Context of the instruction causing the error. Must be not null
   *@requires name - name function declared
   * @throws AlreadyDeclaredAsFunction with the pretty msg.
   */
  public static void throwAlreadyDeclaredAsFunction(ParserRuleContext ctx, String name) {
    String formatMsg = formatterMsg(ctx, ERR_MSG_ALREADY_DECLARED_FUNCTION)+name;
    throw  new AlreadyDeclaredAsFunction(formatMsg);
  }

}
