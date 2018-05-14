package be.unamur.info.b314.compiler.semantics.exception;

public class NotMatchingType extends SemanticException{

  /**
   * @effects
   */
  public NotMatchingType(String msgError) {
    super(msgError);
  }

  /**
   * @effects
   */
  public NotMatchingType() {
    this("undefined");
  }
}
