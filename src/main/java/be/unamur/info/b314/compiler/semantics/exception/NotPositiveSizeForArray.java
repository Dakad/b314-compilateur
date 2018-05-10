package be.unamur.info.b314.compiler.semantics.exception;

public class NotPositiveSizeForArray extends RuntimeException {

  public NotPositiveSizeForArray(){
    super();
  }

  public NotPositiveSizeForArray(String msgError) {
    super(msgError);
  }
}
