package be.unamur.info.b314.compiler.semantics;

import org.antlr.symtab.PrimitiveType;
import org.antlr.symtab.Type;

public enum PredefinedType implements Type {

  BOOLEAN,
  INTEGER,
  SQUARE,
  FUNCTION,
  SQUARE_ITEM,
  VARIABLE,
  ARRAY,
  VOID;

  private final PrimitiveType is;

  PredefinedType() {
    this(null);
  }

  PredefinedType(String type) {
    if (type == null)
      type = this.name();

    this.is = new PrimitiveType(type.toLowerCase());
  }

  PrimitiveType type() {
    return is;
  }

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
  public static PredefinedType get(Type type) {
    return get(type.getName());
  }

  /**
   * @effects Get the mathing {@link PredefinedType} for the provided type.
   * @return the {@link PredefinedType} <br>
   *         or {@link PredefinedType#VOID}
   */
  public static PredefinedType get(String text) {
    try {
      return PredefinedType.valueOf(text.toUpperCase());
    } catch (IllegalArgumentException | NullPointerException e) {
      return PredefinedType.VOID;
    }
  }


  @Override
  public String toString() {
    return getName();
  }
}
