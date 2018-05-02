package be.unamur.info.b314.compiler.semantics.exception;

public class NotMatchingType extends RuntimeException {

  private static final String msg = "The type of the instruction is incorrect - Instr : ";

  /**
   * @effects
   */
  public NotMatchingType(String instr) {
    super(msg + instr);
  }

  /**
   * @effects
   */
  public NotMatchingType() {
    this("undefined");
  }
}
