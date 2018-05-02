package be.unamur.info.b314.compiler.semantics.exception;

public class NotBooleanCondition extends RuntimeException {

  private static final String msg = "The type of the condition statement is not boolean - Instr : ";

  /**
   * @effects Initialise the exception msg with the provided instruction and the pre-formatted msg.
   */
  public NotBooleanCondition(String instr) {
    super(msg + instr);
  }

}
