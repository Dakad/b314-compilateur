package be.unamur.info.b314.compiler.semantics.exception;

public class AlreadyDeclaredVariable extends RuntimeException {

  private static final String msg = "A global variable has already been defined with the name : ";

  public AlreadyDeclaredVariable(String name) {
    super(msg + name);
  }

  public AlreadyDeclaredVariable() {
    this("undefined");
  }
}
