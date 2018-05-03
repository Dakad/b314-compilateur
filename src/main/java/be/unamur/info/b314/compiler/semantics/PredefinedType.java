package be.unamur.info.b314.compiler.semantics;

import org.antlr.symtab.PrimitiveType;
import org.antlr.symtab.Type;

public enum PredefinedType {

  BOOLEAN,
  INTEGER,
  SQUARE,
  FUNCTION,
  SQUARE_ITEM,
  VARIABLE,
  ARRAY,
  VOID;

  private PrimitiveType is;

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


  public static PredefinedType get(String text) {
    try {
      return PredefinedType.valueOf(text.toUpperCase());
    } catch (IllegalArgumentException | NullPointerException e) {
      return PredefinedType.VOID;
    }
  }

  public static PredefinedType get(Type type) {
    return get(type.getName());
  }
}
