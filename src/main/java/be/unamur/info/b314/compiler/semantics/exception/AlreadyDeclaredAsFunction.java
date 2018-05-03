package be.unamur.info.b314.compiler.semantics.exception;

public class AlreadyDeclaredAsFunction extends RuntimeException {

  private static final String msg = "Cannot use the function name as identifier for a parameter";

  public AlreadyDeclaredAsFunction(String name) {
    super(msg + name);
  }

}
