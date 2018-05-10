package be.unamur.info.b314.compiler.semantics.exception;

public class UndeclaredVariable extends RuntimeException {

  public UndeclaredVariable(String msgError) {
    super(msgError);
  }
}
