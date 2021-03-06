package be.unamur.info.b314.compiler.semantics.exception;

public class UndeclaredFunction extends SemanticException{

  private static final String msg = "Undeclared function : ";

  public UndeclaredFunction(String header) {
    super(msg + header);
  }
}
