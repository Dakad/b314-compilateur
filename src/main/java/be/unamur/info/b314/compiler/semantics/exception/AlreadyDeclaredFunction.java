package be.unamur.info.b314.compiler.semantics.exception;

public class AlreadyDeclaredFunction extends RuntimeException {

  private static final String msg = "A global function has already been defined with the name : ";

  public AlreadyDeclaredFunction(String name) {
    super(msg + name);
  }

}
