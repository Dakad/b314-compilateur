package be.unamur.info.b314.compiler.semantics.exception;

public class AlreadyDeclaredAsFunction extends RuntimeException {

  public AlreadyDeclaredAsFunction(String msgError) {
    super(msgError);
  }

}
