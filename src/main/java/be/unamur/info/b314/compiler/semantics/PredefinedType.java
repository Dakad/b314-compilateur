package be.unamur.info.b314.compiler.semantics;

import org.antlr.symtab.PrimitiveType;

public enum PredefinedType {

  BOOLEAN,
  INTEGER,
  SQUARE,
  FUNCTION,
  CASE,
  CASE_ITEM,
  VARIABLE,
  VALUE,
  ARRAY,
  VOID;

  private PrimitiveType is;

  PredefinedType() {
    this(null);
  }

  PredefinedType(String type) {
    if (type == null) {
      type = this.name();
    }
    this.is = new PrimitiveType(type.toLowerCase());
  }

  PrimitiveType type() {
    return is;
  }


  public static PredefinedType get(String text) {
    try {
      return PredefinedType.valueOf(text.toUpperCase());
    } catch (IllegalArgumentException e) {
      return PredefinedType.VOID;
    }
  }
}
