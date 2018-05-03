package be.unamur.info.b314.compiler.semantics.exception;

public class AlreadyDeclaredFunction extends RuntimeException {

  private static final String msg = "A global variable has already been defined with the name : ";

  public AlreadyDeclaredFunction(String name) {
    super(msg + name);
  }

  public AlreadyDeclaredFunction() {
    this("undefined");
  }
}
