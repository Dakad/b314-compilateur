package be.unamur.info.b314.compiler.semantics.exception;

public class AlreadyDeclaredFunction extends RuntimeException {

  public AlreadyDeclaredFunction(String msgError) {
    super(msgError);
  }

}
