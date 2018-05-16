package be.unamur.info.b314.compiler.semantics.symtab;


/**
 * @overview A PredefenidType is
 */
public enum PredefinedType implements org.antlr.symtab.Type {

  BOOLEAN,
  INTEGER,
  SQUARE,
  FUNCTION,
  SQUARE_ITEM,
  VARIABLE,
  ARRAY,
  VOID;


  @Override
  public String getName() {
    return this.name().toLowerCase();
  }

  @Override
  public int getTypeIndex() {
    return this.name().hashCode();
  }

  /**
   * @requires type to be not null.
   * @effects Get the mathing {@link PredefinedType} for the provided type.
   * @return the {@link PredefinedType}.
   */
  public static PredefinedType get(org.antlr.symtab.Type type) {
    return get(type.getName());
  }

  /**
   * @effects Get the matching {@link PredefinedType} for the provided type.
   * @return the {@link PredefinedType} <br>
   *         or {@link PredefinedType#VOID}
   */
  public static PredefinedType get(String type) {
    try {
      return PredefinedType.valueOf(type.toUpperCase());
    } catch (IllegalArgumentException | NullPointerException e) {
      return PredefinedType.VOID;
    }
  }


  @Override
  public String toString() {
    return getName();
  }
}
