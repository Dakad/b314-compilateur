package be.unamur.info.b314.compiler.semantics.symtab;

import java.util.List;
import org.antlr.symtab.Type;

/**
 * @overview
 */
public class B314FunctionType extends org.antlr.symtab.FunctionType {


  public B314FunctionType(PredefinedType returnType, List<Type> argumentTypes) {
    super(returnType, argumentTypes);
  }

  /**
   * @return the function's return type.
   */
  public PredefinedType getReturnType(){
    return (PredefinedType) super.returnType;
  }

}
