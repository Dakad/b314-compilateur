package be.unamur.info.b314.compiler.semantics.exception;

public class AlreadyDeclaredVariable extends SemanticException{

  public AlreadyDeclaredVariable(String msgError) {
    super(msgError);
  }

  public AlreadyDeclaredVariable() {
    this("undefined");
  }
}
