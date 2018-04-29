package be.unamur.info.b314.compiler.main;

import static org.hamcrest.Matchers.instanceOf;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

import be.unamur.info.b314.compiler.semantics.SymTableFiller;
import be.unamur.info.b314.compiler.semantics.symtab.ArrayType;
import java.util.Map;
import org.antlr.symtab.BaseSymbol;
import org.antlr.symtab.Symbol;
import org.antlr.symtab.VariableSymbol;
import org.hamcrest.collection.IsMapContaining;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestRule;
import org.junit.rules.TestWatcher;
import org.junit.runner.Description;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class B314var_globalSemanticTest {

  private static final Logger LOG = LoggerFactory.getLogger(B314var_globalSemanticTest.class);


  private SymTableFiller symTableFiller;

  private Map<String, ? extends Symbol> symbTab;

  @Rule
  public TestRule watcher = new TestWatcher() { // Prints message on logger before each test
    @Override
    protected void starting(Description description) {
      LOG.info(String.format("Starting test: %s()...",
          description.getMethodName()));
    }

    ;
  };

  //
  // Serie var_global OK

  @Test
  public void testvar_global_array_in_all_shapes_ok() throws Exception{
    symTableFiller = CompilerTestHelper.getSymTable("/semantics/var_global/ok/array_in_all_shapes.b314");
    symbTab = symTableFiller.getSymTable();

    assertThat("Must contain all glalvariables", symbTab.size() , is(6));

    assertThat("Must contain at least : myVar as square[5]", symbTab , IsMapContaining.hasKey("myVar"));
    BaseSymbol myVar = (BaseSymbol) symbTab.get("myVar");
    assertThat("Must be an Variable : myVar as square[5]", myVar , instanceOf(VariableSymbol.class));
    ArrayType myVarValue = (ArrayType) myVar.getType();
    assertThat("Must be an ArrayType : myVar as square[5]", myVarValue , instanceOf(ArrayType.class));
    assertThat("Must be correct size : myVar as square[5]", myVarValue.getSize() , is(5));
    assertThat("Must be correct size : myVar as square[5]", myVarValue.getType().getName() , is("square"));


    assertThat("Must contain at least : v3 as boolean [3,5]", symbTab , IsMapContaining.hasKey("v3"));
    BaseSymbol v3 = (BaseSymbol) symbTab.get("v3");
    assertThat("Must be an Variable : v3 as boolean [3,5]", v3 , instanceOf(VariableSymbol.class));
    ArrayType v3Value = (ArrayType) v3.getType();
    assertThat("Must be an ArrayType : v3 as boolean [3,5]", v3Value , instanceOf(ArrayType.class));
    assertThat("Must be correct size : v3 as boolean [3,5]", v3Value.getSize() , is(3));
    assertThat("Must be an ArrayType : v3 as boolean [3,5]", v3Value.getType() , instanceOf(ArrayType.class));
    ArrayType v3Value2Dim = (ArrayType) v3Value.getType();
    assertThat("Must be correct size : v3 as boolean [3,5]", v3Value2Dim, instanceOf(ArrayType.class));
    assertThat("Must be correct size : v3 as boolean [3,5]", v3Value2Dim.getSize() , is(5));
    assertThat("Must be a boolean : v3 as boolean [3,5]", v3Value2Dim.getType().getName() , is("boolean"));
  }

  @Test
  public void testvar_global_variable_mixing_ok() throws Exception{
    symTableFiller = CompilerTestHelper.getSymTable("/semantics/var_global/ok/variable_mixing.b314");
    /*
      assertThat("Interpreter exist status was not 0", result.getExitStatus(), equalTo(0));
      assertThat("Wrong number of outputs, there was 1 turn", result.getOutLines(), hasSize(1));
     */
  }

  @Test
  public void testvar_global_all_integer_ok() throws Exception{
    symTableFiller = CompilerTestHelper.getSymTable("/semantics/var_global/ok/all_integer.b314");
  }

  @Test
  public void testvar_global_all_boolean_ok() throws Exception{
    symTableFiller = CompilerTestHelper.getSymTable("/semantics/var_global/ok/all_boolean.b314");
  }

  @Test
  public void testvar_global_one_square_ok() throws Exception{
    symTableFiller = CompilerTestHelper.getSymTable("/semantics/var_global/ok/one_square.b314");
  }


}