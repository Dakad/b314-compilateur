package be.unamur.info.b314.compiler.semantics.exception;

public class DuplicateParameter extends SemanticException{

  public DuplicateParameter(String msgError) {
    super(msgError);
  }
}
