package be.unamur.info.b314.compiler.semantics.exception;

public class AlreadyGlobalDeclared extends RuntimeException {

  private static final String msg = "A global variable has already been defined with the name : ";

  public AlreadyGlobalDeclared(String name) {
    super(msg + name);
  }

  public AlreadyGlobalDeclared() {
    this("undefined");
  }
}
