package be.unamur.info.b314.compiler.semantics.exception;

import org.antlr.v4.runtime.ParserRuleContext;

/**
 * @overview ExceptionHandler is an handler for thrown exception. It sole purpose is to thrown pretty Semantic Error.
 * An ExceptionHandler is stateless, thus, immutable.
 *
 */
public class ExceptionHandler {

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

}
