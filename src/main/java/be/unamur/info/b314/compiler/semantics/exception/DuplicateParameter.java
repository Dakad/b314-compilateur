package be.unamur.info.b314.compiler.semantics.exception;

public class DuplicateParameter extends RuntimeException {

  public DuplicateParameter(String msgError) {
    super(msgError);
  }
}
