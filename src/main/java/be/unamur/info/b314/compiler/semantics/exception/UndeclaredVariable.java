package be.unamur.info.b314.compiler.semantics.exception;

public class UndeclaredVariable extends RuntimeException {

  private static final String msg = "Undeclared identifier : ";

  public UndeclaredVariable(String id) {
    super(msg + id);
  }
}
