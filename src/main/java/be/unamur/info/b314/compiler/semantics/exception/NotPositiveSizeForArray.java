package be.unamur.info.b314.compiler.semantics.exception;

public class NotPositiveSizeForArray extends RuntimeException {

  private static final String msg = "The array's size is null or not positive - Size : ";

  public NotPositiveSizeForArray(){
    super();
  }

  public NotPositiveSizeForArray(String s) {
    super(msg+s);
  }
}
