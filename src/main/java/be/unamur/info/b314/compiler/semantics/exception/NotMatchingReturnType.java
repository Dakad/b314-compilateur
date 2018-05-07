package be.unamur.info.b314.compiler.semantics.exception;

public class NotMatchingReturnType extends RuntimeException {

  private static final String msg = "The return type of the function is incorrect - Return type : ";

  /**
   * @effects
   */
  public NotMatchingReturnType(String returnType) {
    super(msg + returnType);
  }

  /**
   * @effects
   */
  public NotMatchingReturnType() {
    this("undefined");
  }
}
