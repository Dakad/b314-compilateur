package be.unamur.info.b314.compiler.semantics.exception;

public class NotMatchingReturnType extends SemanticException{

  /**
   * @effects
   */
  public NotMatchingReturnType(String msgError) {
    super(msgError);
  }

}
