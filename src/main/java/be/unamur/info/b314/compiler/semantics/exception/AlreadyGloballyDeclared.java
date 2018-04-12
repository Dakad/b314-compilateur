package be.unamur.info.b314.compiler.semantics.exception;

public class AlreadyGloballyDeclared extends RuntimeException {

  private static final String msg = "A global variable has been already defined with the name : ";

  public AlreadyGloballyDeclared(String name) {
    super(msg + name);
  }

  public AlreadyGloballyDeclared() {
    this("undefined");
  }
}
