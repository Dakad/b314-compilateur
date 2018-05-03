package be.unamur.info.b314.compiler.main;

import static org.hamcrest.Matchers.instanceOf;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

import be.unamur.info.b314.compiler.semantics.PredefinedType;
import be.unamur.info.b314.compiler.semantics.SymTableFiller;
import be.unamur.info.b314.compiler.semantics.symtab.ArrayType;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.antlr.symtab.BaseScope;
import org.antlr.symtab.BaseSymbol;
import org.antlr.symtab.FunctionSymbol;
import org.antlr.symtab.ParameterSymbol;
import org.antlr.symtab.PrimitiveType;
import org.antlr.symtab.Symbol;
import org.antlr.symtab.SymbolWithScope;
import org.antlr.symtab.Type;
import org.antlr.symtab.VariableSymbol;
import org.hamcrest.collection.IsIn;
import org.hamcrest.collection.IsMapContaining;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;
import org.junit.rules.TestRule;
import org.junit.rules.TestWatcher;
import org.junit.runner.Description;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class B314fctDeclSemanticTest {

  private static final Logger LOG = LoggerFactory.getLogger(B314fctDeclSemanticTest.class);

  private SymTableFiller symTableFiller;

  private Map<String, ? extends Symbol> symbTab;

  @Rule
  public TemporaryFolder testFolder = new TemporaryFolder(); // Create a temporary folder for outputs deleted after tests

  @Rule
  public TestRule watcher = new TestWatcher() { // Prints message on logger before each test
    @Override
    protected void starting(Description description) {
      LOG.info(String.format("Starting test: %s()...",
          description.getMethodName()));
    }

    ;
  };


  private Map<String, ParameterSymbol> collectParams(List<? extends Symbol> symbols) {
    Map<String, ParameterSymbol> params = new HashMap<>(symbols.size());

    for (Symbol sb : symbols) {
      if(sb instanceof ParameterSymbol)
        params.putIfAbsent(sb.getName(), (ParameterSymbol) sb);
    }
    return params;
  }

  //
  // Serie fctDecl OK
  //
  @Test
  public void testfctDecl_without_params_ok() {
    // Exercice
    symTableFiller = CompilerTestHelper.getSymTable("/semantics/fctDecl/ok/without_params.b314");
    symbTab = symTableFiller.getSymTable();

    // Verify
    assertThat("Must contain all global variables and functions", symbTab.size(), is(2));

    assertThat("Must contain at least : f():boolean", symbTab, IsMapContaining.hasKey("f"));
    assertThat("Must be an function : f():boolean", symbTab.get("f"), instanceOf(FunctionSymbol.class));
    FunctionSymbol myFct = (FunctionSymbol) symbTab.get("f");

    PrimitiveType myFctType = (PrimitiveType) myFct.getType();
    assertThat("Must be an booleanType : f():boolean", myFctType.getName(),  is("boolean"));

    int nbFctParams = myFct.getNumberOfParameters();
    assertThat("Must be has any parameter : f():boolean", nbFctParams, is(0));
  }


  @Test
  public void testfctDecl_with_params_ok() {
    // Exercice
    symTableFiller = CompilerTestHelper.getSymTable("/semantics/fctDecl/ok/with_params.b314");
    symbTab = symTableFiller.getSymTable();

    // Verify
    assertThat("Must contain all global variables and functions", symbTab.size(), is(2));
        // Verify - Symbol
    assertThat("Must contain at least : f(integer):boolean", symbTab, IsMapContaining.hasKey("f"));
    assertThat("Must be an function : f(integer):boolean", symbTab.get("f"), instanceOf(FunctionSymbol.class));
        // Verify - Function
    FunctionSymbol myFct = (FunctionSymbol) symbTab.get("f");
    PrimitiveType myFctType = (PrimitiveType) myFct.getType();
    assertThat("Must be an booleanType : f(integer):boolean", myFctType.getName(),  is("boolean"));

    // Verify - Params
    int nbFctParams = myFct.getNumberOfParameters();
    assertThat("Must be has any parameter : f(integer):boolean", nbFctParams, is(1));

    Map<String, ParameterSymbol> myFctParams = collectParams(myFct.getSymbols());

    assertThat("Must contain at least paremeter i : f(integer):boolean", myFctParams, IsMapContaining.hasKey("i") );

    PrimitiveType paramType = (PrimitiveType)  myFctParams.get("i").getType();
    assertThat("Must contain at least paremeter i : f(integer):boolean", paramType.getName(), is("integer"));
  }

  @Test
  public void testfctDecl_with_params2_ok() {
    // Setup
    Object[][] configs = {
        {"f", "boolean",
            new String[]{"i", "boolean"}
        },
        {"fun", "void",
            new String[]{"i", "integer"},
            new String[]{"s", "square"}
        },
    };

    // Exercice

    symTableFiller = CompilerTestHelper.getSymTable("/semantics/fctDecl/ok/with_params_2.b314");
    symbTab = symTableFiller.getSymTable();

    assertThat("Must contain all global variables and functions", symbTab.size(), is(3));


    // Verify first function : f(integer)

    // Verify - Symbol
    assertThat("Must contain at least : f(integer):boolean", symbTab, IsMapContaining.hasKey("f"));
    assertThat("Must be an function : f(integer):boolean", symbTab.get("f"), instanceOf(FunctionSymbol.class));
    // Verify - Function
    FunctionSymbol myFct = (FunctionSymbol) symbTab.get("f");
    PrimitiveType myFctType = (PrimitiveType) myFct.getType();
    assertThat("Must be an booleanType : f(integer):boolean", myFctType.getName(),  is("boolean"));

    // Verify - Params
    int nbFctParams = myFct.getNumberOfParameters();
    assertThat("Must be has any parameter : f(integer):boolean", nbFctParams, is(1));

    Map<String, ParameterSymbol> myFctParams = collectParams(myFct.getSymbols());

    assertThat("Must contain at least paremeter i : f(integer):boolean", myFctParams, IsMapContaining.hasKey("i") );

    PrimitiveType paramType = (PrimitiveType)  myFctParams.get("i").getType();
    assertThat("Must contain at least paremeter i : f(integer):boolean", paramType.getName(), is("integer"));




    // Verify second function : fun(integer, square)

    // Verify - Symbol
    assertThat("Must contain at least : fun(integer, square):void", symbTab, IsMapContaining.hasKey("fun"));
    assertThat("Must be an function : fun(integer, square):void", symbTab.get("fun"), instanceOf(FunctionSymbol.class));
    // Verify - Function
     myFct = (FunctionSymbol) symbTab.get("fun");
     myFctType = (PrimitiveType) myFct.getType();
    assertThat("Must be an booleanType : fun(integer, square):void", myFctType.getName(),  is("void"));

    // Verify - Params
    nbFctParams = myFct.getNumberOfParameters();
    assertThat("Must be has any parameter : fun(integer, square):void", nbFctParams, is(2));

     myFctParams = collectParams(myFct.getSymbols());

    // Verify - first parameter i
    assertThat("Must contain at least parameter i : fun(integer, square):void", myFctParams, IsMapContaining.hasKey("i") );

    paramType = (PrimitiveType)  myFctParams.get("i").getType();
    assertThat("Must contain at least parameter i : fun(integer, square):void", paramType.getName(), is("integer"));

    // Verify - se cond parameter s
    assertThat("Must contain at least parameter s : fun(integer, square):void", myFctParams, IsMapContaining.hasKey("s") );

    paramType = (PrimitiveType)  myFctParams.get("s").getType();
    assertThat("Must contain at least parameter i : fun(integer, square):void", paramType.getName(), is("square"));


  }

}