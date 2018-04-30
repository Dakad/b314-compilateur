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

    assertThat("Must contain all global variables", symbTab.size() , is(6));

    assertThat("Must contain at least : myVar as square[5]", symbTab , IsMapContaining.hasKey("myVar"));
    BaseSymbol myVar = (BaseSymbol) symbTab.get("myVar");
    assertThat("Must be an Variable : myVar as square[5]", myVar , instanceOf(VariableSymbol.class));
    ArrayType myVarValue = (ArrayType) myVar.getType();
    assertThat("Must be an ArrayType : myVar as square[5]", myVarValue , instanceOf(ArrayType.class));
    assertThat("Must be correct size : myVar as square[5]", myVarValue.getSize() , is(5));
    assertThat("Must be an square array : myVar as square[5]", myVarValue.getType().getName() , is("square"));

    assertThat("Must contain at least : v3 as boolean [3,5]", symbTab , IsMapContaining.hasKey("v3"));
    BaseSymbol v3 = (BaseSymbol) symbTab.get("v3");
    assertThat("Must be an Variable : v3 as boolean [3,5]", v3 , instanceOf(VariableSymbol.class));
    ArrayType v3Value = (ArrayType) v3.getType();
    assertThat("Must be an ArrayType : v3 as boolean [3,5]", v3Value , instanceOf(ArrayType.class));
    assertThat("Must be correct size : v3 as boolean [3,5]", v3Value.getSize() , is(3));
    assertThat("Must be an ArrayType : v3 as boolean [3,5]", v3Value.getType() , instanceOf(ArrayType.class));
    ArrayType v3Value2Dim = (ArrayType) v3Value.getType();
    assertThat("Must be an ArrayType : v3 as boolean [3,5]", v3Value2Dim, instanceOf(ArrayType.class));
    assertThat("Must be correct size : v3 as boolean [3,5]", v3Value2Dim.getSize() , is(5));
    assertThat("Must be a boolean array : v3 as boolean [3,5]", v3Value2Dim.getType().getName() , is("boolean"));
  }

  @Test
  public void testvar_global_all_boolean_ok() throws Exception{
    symTableFiller = CompilerTestHelper.getSymTable("/semantics/var_global/ok/all_boolean.b314");
    symbTab = symTableFiller.getSymTable();

    assertThat("Must contain all global variables", symbTab.size() , is(3));

    assertThat("Must contain at least : b1 as boolean", symbTab , IsMapContaining.hasKey("b1"));
    BaseSymbol b1 = (BaseSymbol) symbTab.get("b1");
    assertThat("Must be an Variable : b1 as boolean", b1 , instanceOf(VariableSymbol.class));
    assertThat("Must be an typed as boolean : b1 as boolean", b1.getType().getName() , is("boolean"));

    assertThat("Must contain at least : b2 as boolean[5]", symbTab , IsMapContaining.hasKey("b2"));
    BaseSymbol b2 = (BaseSymbol) symbTab.get("b2");
    assertThat("Must be an Variable : b2 as boolean[5]", b2 , instanceOf(VariableSymbol.class));
    ArrayType b2Value = (ArrayType) b2.getType();
    assertThat("Must be an ArrayType : b2 as boolean[5]", b2Value , instanceOf(ArrayType.class));
    assertThat("Must be correct size : b2 as boolean[5]", b2Value.getSize() , is(2));
    assertThat("Must be boolean array : b2 as boolean[5]", b2Value.getType().getName() , is("boolean"));

    assertThat("Must contain at least : b3 as boolean [3,3]", symbTab , IsMapContaining.hasKey("b3"));
    BaseSymbol b3 = (BaseSymbol) symbTab.get("b3");
    assertThat("Must be an Variable : b3 as boolean [3,3]", b3 , instanceOf(VariableSymbol.class));
    ArrayType b3Value = (ArrayType) b3.getType();
    assertThat("Must be an ArrayType : b3 as boolean [3,3]", b3Value , instanceOf(ArrayType.class));
    assertThat("Must be correct size : b3 as boolean [3,3]", b3Value.getSize() , is(3));
    assertThat("Must be an ArrayType : b3 as boolean [3,3]", b3Value.getType() , instanceOf(ArrayType.class));
    ArrayType b3Value2Dim = (ArrayType) b3Value.getType();
    assertThat("Must be an ArrayType : b3 as boolean [3,3]", b3Value2Dim, instanceOf(ArrayType.class));
    assertThat("Must be correct size : b3 as boolean [3,3]", b3Value2Dim.getSize() , is(3));
    assertThat("Must be a boolean array: b3 as boolean [3,3]", b3Value2Dim.getType().getName() , is("boolean"));
  }


  @Test
  public void testvar_global_all_integer_ok() throws Exception{
    symTableFiller = CompilerTestHelper.getSymTable("/semantics/var_global/ok/all_integer.b314");
    symbTab = symTableFiller.getSymTable();

    assertThat("Must contain all global variables", symbTab.size() , is(3));

    assertThat("Must contain at least : i1 as integer", symbTab , IsMapContaining.hasKey("i1"));
    BaseSymbol i1 = (BaseSymbol) symbTab.get("i1");
    assertThat("Must be an Variable : i1 as integer", i1 , instanceOf(VariableSymbol.class));
    assertThat("Must be an typed as integer : i1 as integer", i1.getType().getName() , is("integer"));

    assertThat("Must contain at least : i2 as integer[2]", symbTab , IsMapContaining.hasKey("i2"));
    BaseSymbol i2 = (BaseSymbol) symbTab.get("i2");
    assertThat("Must be an Variable : i2 as integer[2]", i2 , instanceOf(VariableSymbol.class));
    ArrayType i2Value = (ArrayType) i2.getType();
    assertThat("Must be an ArrayType : i2 as integer[2]", i2Value , instanceOf(ArrayType.class));
    assertThat("Must be correct size : i2 as integer[2]", i2Value.getSize() , is(2));
    assertThat("Must be an integer array : i2 as integer[2]", i2Value.getType().getName() , is("integer"));

    assertThat("Must contain at least : i3 as integer [3,3]", symbTab , IsMapContaining.hasKey("i3"));
    BaseSymbol i3 = (BaseSymbol) symbTab.get("i3");
    assertThat("Must be an Variable : i3 as integer [3,3]", i3 , instanceOf(VariableSymbol.class));
    ArrayType i3Value = (ArrayType) i3.getType();
    assertThat("Must be an ArrayType : i3 as integer [3,3]", i3Value , instanceOf(ArrayType.class));
    assertThat("Must be correct size : i3 as integer [3,3]", i3Value.getSize() , is(3));
    assertThat("Must be an ArrayType : i3 as integer [3,3]", i3Value.getType() , instanceOf(ArrayType.class));
    ArrayType i3Value2Dim = (ArrayType) i3Value.getType();
    assertThat("Must be ArrayType : i3 as integer [3,3]", i3Value2Dim, instanceOf(ArrayType.class));
    assertThat("Must be correct size : i3 as integer [3,3]", i3Value2Dim.getSize() , is(3));
    assertThat("Must be a integer array : i3 as integer[3,3]", i3Value2Dim.getType().getName() , is("integer"));
  }


  @Test
  public void testvar_global_one_square_ok() throws Exception{
    symTableFiller = CompilerTestHelper.getSymTable("/semantics/var_global/ok/one_square.b314");
    symbTab = symTableFiller.getSymTable();

    assertThat("Must contain all global variables", symbTab.size() , is(3));

    assertThat("Must contain at least : s1 as square", symbTab , IsMapContaining.hasKey("s1"));
    BaseSymbol s1 = (BaseSymbol) symbTab.get("s1");
    assertThat("Must be an Variable : s1 as square[12]", s1 , instanceOf(VariableSymbol.class));
    assertThat("Must be an typed as square : s1 as square[12]", s1.getType().getName() , is("square"));

    assertThat("Must contain at least : s2 as square[12]", symbTab , IsMapContaining.hasKey("s2"));
    BaseSymbol s2 = (BaseSymbol) symbTab.get("s2");
    assertThat("Must be an Variable : s2 as square[12]", s2 , instanceOf(VariableSymbol.class));
    ArrayType s2Value = (ArrayType) s2.getType();
    assertThat("Must be an ArrayType : s2 as square[12]", s2Value , instanceOf(ArrayType.class));
    assertThat("Must be correct size : s2 as square[12]", s2Value.getSize() , is(12));
    assertThat("Must be an square array : s2 as square[12]", s2Value.getType().getName() , is("square"));

    assertThat("Must contain at least : s3 as square[12,3]", symbTab , IsMapContaining.hasKey("s3"));
    BaseSymbol s3 = (BaseSymbol) symbTab.get("s3");
    assertThat("Must be an Variable : s3 as square[12,3]", s3 , instanceOf(VariableSymbol.class));
    ArrayType s3Value = (ArrayType) s3.getType();
    assertThat("Must be an ArrayType : s3 as square[12,3]", s3Value , instanceOf(ArrayType.class));
    assertThat("Must be correct size : s3 as square[12,3]", s3Value.getSize() , is(12));
    assertThat("Must be an ArrayType : s3 as square[12,3]", s3Value.getType() , instanceOf(ArrayType.class));
    ArrayType s3Value2Dim = (ArrayType) s3Value.getType();
    assertThat("Must be ArrayType : s3 as square[12,3]", s3Value2Dim, instanceOf(ArrayType.class));
    assertThat("Must be correct size : s3 as square[12,3]", s3Value2Dim.getSize() , is(3));
    assertThat("Must be a square array : s3 as square[12,3]", s3Value2Dim.getType().getName() , is("square"));
  }


  @Test
  public void testvar_global_variable_mixing_ok() throws Exception{
    symTableFiller = CompilerTestHelper.getSymTable("/semantics/var_global/ok/variable_mixing.b314");
    symbTab = symTableFiller.getSymTable();

    assertThat("Must contain all global variables", symbTab.size() , is(6));


    assertThat("Must contain at least : v1 as integer", symbTab , IsMapContaining.hasKey("v1"));
    BaseSymbol v1 = (BaseSymbol) symbTab.get("v1");
    assertThat("Must be an Variable : v1 as integer", v1 , instanceOf(VariableSymbol.class));
    assertThat("Must be an typed as integer : v1 as integer", v1.getType().getName() , is("integer"));

    assertThat("Must contain at least : v2 as integer [2,2]", symbTab , IsMapContaining.hasKey("v2"));
    BaseSymbol v2 = (BaseSymbol) symbTab.get("v2");
    assertThat("Must be an Variable : v2 as integer [2,2]", v2 , instanceOf(VariableSymbol.class));
    ArrayType v2Value = (ArrayType) v2.getType();
    assertThat("Must be an ArrayType : v2 as integer [2,2]", v2Value , instanceOf(ArrayType.class));
    assertThat("Must be correct size : v2 as integer [2,2]", v2Value.getSize() , is(2));
    assertThat("Must be an ArrayType : v2 as integer [2,2]", v2Value.getType() , instanceOf(ArrayType.class));
    ArrayType v2Value2Dim = (ArrayType) v2Value.getType();
    assertThat("Must be ArrayType : v2 as integer [2,2]", v2Value2Dim, instanceOf(ArrayType.class));
    assertThat("Must be correct size : v2 as integer [2,2]", v2Value2Dim.getSize() , is(2));
    assertThat("Must be a integer array : v2 as integer[2,2]", v2Value2Dim.getType().getName() , is("integer"));

    assertThat("Must contain at least : v3 as boolean", symbTab , IsMapContaining.hasKey("v3"));
    BaseSymbol v3 = (BaseSymbol) symbTab.get("v3");
    assertThat("Must be an Variable : v3 as boolean", v3 , instanceOf(VariableSymbol.class));
    assertThat("Must be an typed as boolean : v3 as boolean", v3.getType().getName() , is("boolean"));

    assertThat("Must contain at least : v4 as boolean [2,3]", symbTab , IsMapContaining.hasKey("v4"));
    BaseSymbol v4 = (BaseSymbol) symbTab.get("v4");
    assertThat("Must be an Variable : v4 as boolean [2,3]", v4 , instanceOf(VariableSymbol.class));
    ArrayType v4Value = (ArrayType) v4.getType();
    assertThat("Must be an ArrayType : v4 as boolean [2,3]", v4Value , instanceOf(ArrayType.class));
    assertThat("Must be correct size : v4 as boolean [2,3]", v4Value.getSize() , is(2));
    assertThat("Must be an ArrayType : v4 as boolean [2,3]", v4Value.getType() , instanceOf(ArrayType.class));
    ArrayType v4Value2Dim = (ArrayType) v4Value.getType();
    assertThat("Must be an ArrayType : v4 as boolean [2,3]", v4Value2Dim, instanceOf(ArrayType.class));
    assertThat("Must be correct size : v4 as boolean [2,3]", v4Value2Dim.getSize() , is(3));
    assertThat("Must be a boolean array: v4 as boolean [2,3]", v4Value2Dim.getType().getName() , is("boolean"));

    assertThat("Must contain at least : v5 as square", symbTab , IsMapContaining.hasKey("v5"));
    BaseSymbol v5 = (BaseSymbol) symbTab.get("v5");
    assertThat("Must be an Variable : v5 as square[12]", v5 , instanceOf(VariableSymbol.class));
    assertThat("Must be an typed as square : v5 as square[12]", v5.getType().getName() , is("square"));

    assertThat("Must contain at least : v6 as square[42,42]", symbTab , IsMapContaining.hasKey("v6"));
    BaseSymbol v6 = (BaseSymbol) symbTab.get("v6");
    assertThat("Must be an Variable : v6 as square[42,42]", v6 , instanceOf(VariableSymbol.class));
    ArrayType v6Value = (ArrayType) v6.getType();
    assertThat("Must be an ArrayType : v6 as square[42,42]", v6Value , instanceOf(ArrayType.class));
    assertThat("Must be correct size : v6 as square[42,42]", v6Value.getSize() , is(42));
    assertThat("Must be an ArrayType : v6 as square[42,42]", v6Value.getType() , instanceOf(ArrayType.class));
    ArrayType v6Value2Dim = (ArrayType) v6Value.getType();
    assertThat("Must be ArrayType : v6 as square[42,42]", v6Value2Dim, instanceOf(ArrayType.class));
    assertThat("Must be correct size : v6 as square[42,42]", v6Value2Dim.getSize() , is(42));
    assertThat("Must be a square array : v6 as square[42,42]", v6Value2Dim.getType().getName() , is("square"));
  }


}