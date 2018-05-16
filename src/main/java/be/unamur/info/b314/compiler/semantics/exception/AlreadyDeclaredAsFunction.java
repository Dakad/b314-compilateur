package be.unamur.info.b314.compiler.semantics.exception;

public class AlreadyDeclaredAsFunction extends SemanticException{

  public AlreadyDeclaredAsFunction(String msgError) {
    super(msgError);
  }

}
