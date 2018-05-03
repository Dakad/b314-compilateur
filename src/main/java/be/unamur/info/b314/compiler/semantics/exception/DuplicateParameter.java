package be.unamur.info.b314.compiler.semantics.exception;

public class DuplicateParameter extends RuntimeException {

  private static final String msg = "A parameter has been already defined for this function with the identifier : ";

  public DuplicateParameter(String name) {
    super(msg + name);
  }
}
