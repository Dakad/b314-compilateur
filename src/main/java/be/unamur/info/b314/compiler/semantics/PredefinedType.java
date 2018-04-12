package be.unamur.info.b314.compiler.semantics;

import org.antlr.symtab.PrimitiveType;

public enum PredefinedType {

  BOOLEAN,
  INTEGER,
  SQUARE,
  VOID;

  private PrimitiveType is;

  PredefinedType(){
    this.is = new PrimitiveType(this.name().toLowerCase());
  }

  PrimitiveType type(){
    return is;
  }



}
