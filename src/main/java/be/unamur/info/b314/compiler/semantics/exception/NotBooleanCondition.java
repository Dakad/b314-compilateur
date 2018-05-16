package be.unamur.info.b314.compiler.semantics.exception;

public class NotBooleanCondition extends SemanticException{

  /**
   * @effects Initialise the exception msg with the provided instruction and the pre-formatted msg.
   */
  public NotBooleanCondition(String msgError) {
    super(msgError);
  }

}
