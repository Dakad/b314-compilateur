package be.unamur.info.b314.compiler.semantics.exception;

public class CannotUseFunctionAsVariable extends RuntimeException {

  private static final String msg = "A function cannot be used as a variable : ";

  public CannotUseFunctionAsVariable(String name) {
    super(msg + name);
  }
}
