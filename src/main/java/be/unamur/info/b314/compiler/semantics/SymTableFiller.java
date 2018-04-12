package be.unamur.info.b314.compiler.semantics;

import be.unamur.info.b314.compiler.B314BaseListener;
import be.unamur.info.b314.compiler.B314Parser.ArrayContext;
import be.unamur.info.b314.compiler.B314Parser.IntValContext;
import be.unamur.info.b314.compiler.B314Parser.LocalVarDeclContext;
import be.unamur.info.b314.compiler.B314Parser.RootContext;
import be.unamur.info.b314.compiler.B314Parser.ScalarContext;
import be.unamur.info.b314.compiler.B314Parser.TypeContext;
import be.unamur.info.b314.compiler.B314Parser.VarDeclContext;
import be.unamur.info.b314.compiler.semantics.exception.AlreadyGloballyDeclared;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import org.antlr.symtab.ArrayType;
import org.antlr.symtab.LocalScope;
import org.antlr.symtab.Scope;
import org.antlr.symtab.Symbol;
import org.antlr.symtab.SymbolTable;
import org.antlr.symtab.Type;
import org.antlr.symtab.VariableSymbol;
import org.antlr.v4.runtime.tree.ParseTree;

/**
 * @overview SymTableFiller has to fills a symbol table using ANTLR listener for B314 langage.
 * SymTableFiller is mutable.
 * @specfield symbolTable : Holds all scopes and symbols of the parsed .B314
 * @specfield currentScope : Represents the last Scope entered
 * @inv symbolTable must contains at least the global scope and the predefined types such as
 * Boolean, Integer, Square.
 */
public class SymTableFiller extends B314BaseListener {

  private final SymbolTable symTable;

  private Scope currentScope;


  public SymTableFiller() {
    this.symTable = new SymbolTable();
    this.definePrimitiveType();
  }

  private void definePrimitiveType() {
    this.symTable.definePredefinedSymbol(PredefinedType.BOOLEAN.type());
    this.symTable.definePredefinedSymbol(PredefinedType.INTEGER.type());
    this.symTable.definePredefinedSymbol(PredefinedType.SQUARE.type());
    this.symTable.definePredefinedSymbol(PredefinedType.VOID.type());
  }

  /**
   * @return a read-only view of the Symbol table.
   * @throws UnsupportedOperationException if attemps to modifiy the Map in any way.
   */
  public Map<String, ? extends Symbol> getSymTable() {
    return Collections.unmodifiableMap(symTable.GLOBALS.getMembers());
  }


  /**
   * @return the number of global variables declared
   */
  public int countVariables() {
    return symTable.GLOBALS.getNumberOfSymbols();
  }

  private void pushScope(Scope scope) {
    currentScope = scope;
  }

  private void popScope() {
    currentScope = currentScope.getEnclosingScope();
  }

  @Override
  public void enterRoot(RootContext ctx) {
    pushScope(symTable.GLOBALS);
  }

  @Override
  public void exitRoot(RootContext ctx) {
    popScope();
  }

  @Override
  public void enterType(TypeContext ctx) {
    ParseTree type = ctx.getChild(0);
    if (type == null) {
      return;
    }

    Type predefType = null;
    String varName = ((VarDeclContext) ctx.getParent()).name.getText();
    VariableSymbol var =  (VariableSymbol) currentScope.getSymbol(varName);

    if (type instanceof ScalarContext) {
      predefType = PredefinedType.get(type.getText()).type();
      var.setType(predefType);
    } else {
      ArrayContext typeArray = (ArrayContext) type;
      // First, get the type of this array vaWr.
      predefType = PredefinedType.get(typeArray.scalar().getText()).type();

      // Init the array
      ArrayType array = this.createArrayType(typeArray.elt, predefType);
      var.setType(array);
    }
  }

  @Override
  public void enterArray(ArrayContext ctx) {
    super.enterArray(ctx);
  }

  private ArrayType createArrayType(List<IntValContext> arraySizes, Type type) {
    if (arraySizes.isEmpty()) {
      return null;
    }

    if (arraySizes.size() > 1) { // ? Is multi-dimension array ?
      ArrayType nestedArray;
      for (int i = arraySizes.size() - 1, sizeNested; i > 0; --i) {
        sizeNested = Integer.parseInt(arraySizes.get(i).INTEGER().getText());
        nestedArray = new ArrayType(type, sizeNested);
        type = nestedArray;
      }
    }
    int size = Integer.parseInt(arraySizes.get(0).INTEGER().getText());
    return new ArrayType(type, size);
  }


  @Override
  public void enterVarDecl(VarDeclContext ctx) {
    String name = ctx.name.getText();
    try {
      symTable.GLOBALS.getSymbol(name);
    } catch (IllegalArgumentException e) {
      throw new AlreadyGloballyDeclared(name);
    }
    VariableSymbol var = new VariableSymbol(name);
    currentScope.define(var);
  }


  @Override
  public void enterLocalVarDecl(LocalVarDeclContext ctx) {
    LocalScope local = new LocalScope(currentScope);
    currentScope.nest(local);
    pushScope(local);
  }

  @Override
  public void exitLocalVarDecl(LocalVarDeclContext ctx) {
    popScope();
  }

  @Override
  public int hashCode() {
    return super.hashCode();
  }

  @Override
  public boolean equals(Object obj) {
    return super.equals(obj);
  }

  @Override
  public String toString() {
    return getSymTable().toString();
  }
}
