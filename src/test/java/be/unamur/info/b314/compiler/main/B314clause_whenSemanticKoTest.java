package be.unamur.info.b314.compiler.main;

import static org.hamcrest.Matchers.instanceOf;
import static org.hamcrest.Matchers.notNullValue;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.fail;

import be.unamur.info.b314.compiler.semantics.exception.CannotUseFunctionAsVariable;
import be.unamur.info.b314.compiler.semantics.exception.NotBooleanCondition;
import be.unamur.info.b314.compiler.semantics.exception.UndeclaredFunction;
import be.unamur.info.b314.compiler.semantics.exception.UndeclaredVariable;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;
import org.junit.rules.TestRule;
import org.junit.rules.TestWatcher;
import org.junit.runner.Description;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class B314clause_whenSemanticKoTest {

  private static final Logger LOG = LoggerFactory.getLogger(B314clause_whenSemanticKoTest.class);

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

  //
  // Serie clause_when OK
  //
  @Test
  public void testclause_when_cond_integer_ko() throws Exception {
    String[] b314Files = {"op", "var"};
    String file = "";

    for (String name : b314Files) {
      try {
        file = "/semantics/clause_when/ko/cond_integer_" + name + ".b314";
        CompilerTestHelper.getSymTable(file);
        fail("[Unthrowed] This .b314 is invalid. Should have thrown an Exception.\n" + file);
      } catch (RuntimeException e) {
        assertThat("Incorrect type of Exception throwned\n" + file, e,
            instanceOf(NotBooleanCondition.class));
        assertThat("Must contain a detailed msg of the error", e.getMessage(), notNullValue());
      }
    }
  }

  @Test
  public void testclause_when_cond_square_ko() throws Exception {
    String[] b314Files = {"constant", "constant2", "op", "var"};
    String file = "";

    for (String name : b314Files) {
      try {
        file = "/semantics/clause_when/ko/cond_square_" + name + ".b314";
        CompilerTestHelper.getSymTable(file);
        fail("[Unthrowed] This .b314 is invalid. Should have thrown an Exception.\n" + file);
      } catch (RuntimeException e) {
        assertThat("Incorrect type of Exception throwned\n" + file, e,
            instanceOf(NotBooleanCondition.class));
        assertThat("Must contain a detailed msg of the error", e.getMessage(), notNullValue());
      }
    }
  }

  @Test(expected = UndeclaredVariable.class)
  public void testclause_when_cond_undecl_var_ko() throws Exception {
        CompilerTestHelper.getSymTable("/semantics/clause_when/ko/no_decl_var.b314");
  }

  @Test(expected = UndeclaredFunction.class)
  public void testclause_when_cond_undecl_fct_ko() throws Exception {
        CompilerTestHelper.getSymTable("/semantics/clause_when/ko/no_decl_fct.b314");
  }

  @Test(expected = UndeclaredFunction.class)
  public void testclause_when_cond_undecl_fct2_ko() throws Exception {
        CompilerTestHelper.getSymTable("/semantics/clause_when/ko/no_decl_fct2.b314");
  }


  @Test(expected = CannotUseFunctionAsVariable.class)
  public void testclause_when_cond_fct_as_var_ko() throws Exception {
        CompilerTestHelper.getSymTable("/semantics/clause_when/ko/fct_as_var.b314");
  }

}