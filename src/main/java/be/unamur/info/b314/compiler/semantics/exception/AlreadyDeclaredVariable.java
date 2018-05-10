package be.unamur.info.b314.compiler.semantics.exception;

public class AlreadyDeclaredVariable extends RuntimeException {

  public AlreadyDeclaredVariable(String msgError) {
    super(msgError);
  }

  public AlreadyDeclaredVariable() {
    this("undefined");
  }
}
