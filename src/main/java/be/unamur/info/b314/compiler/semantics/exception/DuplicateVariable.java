package be.unamur.info.b314.compiler.semantics.exception;

public class DuplicateVariable extends RuntimeException {

  private static final String msg = "A variable/parameter has been already defined for this "
      + "function with the identifier : ";

  public DuplicateVariable(String name) {
    super(msg + name);
  }
}
