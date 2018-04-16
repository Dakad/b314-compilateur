package be.unamur.info.b314.compiler.semantics.exception;

public class ReservedName extends RuntimeException {

  public ReservedName() {
    this("undefined");
  }

  public ReservedName(String name) {
    super("Used reserved keyword :"+name);
  }
}
