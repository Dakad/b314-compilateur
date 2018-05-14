package be.unamur.info.b314.compiler.semantics.exception;

public class DuplicateVariable extends SemanticException{

  public DuplicateVariable(String msgError) {
    super(msgError);
  }
}
