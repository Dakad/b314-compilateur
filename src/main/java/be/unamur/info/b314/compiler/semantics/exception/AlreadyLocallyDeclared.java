package be.unamur.info.b314.compiler.semantics.exception;

public class AlreadyLocallyDeclared extends RuntimeException {

  private static final String msg = "A local variable has been already defined with the name : ";

  public AlreadyLocallyDeclared(String name) {
    super(msg + name);
  }

  public AlreadyLocallyDeclared() {
    this("undefined");
  }
}
