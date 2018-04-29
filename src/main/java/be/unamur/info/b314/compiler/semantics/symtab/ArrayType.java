package be.unamur.info.b314.compiler.semantics.symtab;

import org.antlr.symtab.Type;

/**
 * @overview This ArrayType is to add missing method in the initial ArrayType.
 */
public class ArrayType extends org.antlr.symtab.ArrayType {

  /**
   * @param elemType - The type of array's element
   * @param numElems - The total number of array's space.
   * @effects Initialize the array with the provided <i>elemType</i> <br>
   *          and the size of the array to the provided <i>numElems</i>.

   */
  public ArrayType(Type elemType, int numElems) {
    super(elemType, numElems);
  }

  /**
   * @param elemType - The type of array's element
   * @effects Initialize the array with the provided 'elemType' <br>
   *          and the size is set to -1.
   */
  public ArrayType(Type elemType) {
    this(elemType, -1);
  }

  /**
   * @return The total number of array's space <br>
   *         or <b>-1</b> if undefined.
   */
  public int getSize() {
    return super.numElems;
  }

  /**
   * @return the type of array's elements.
   */
  public Type getType(){
    return super.elemType;
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append(super.elemType).append("[").append(super.numElems).append("]");
    return sb.toString();
  }

}
