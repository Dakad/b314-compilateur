package be.unamur.info.b314.compiler.main;

import static org.hamcrest.Matchers.instanceOf;
import static org.hamcrest.Matchers.notNullValue;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.fail;

import be.unamur.info.b314.compiler.semantics.exception.NotMatchingType;
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

public class B314instr_set_toSemanticKoTest {

  private static final Logger LOG = LoggerFactory.getLogger(B314instr_set_toSemanticKoTest.class);

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
  // Serie instr_set_to KO
  //
  @Test
  public void testinstr_set_arena_case_to_boolean_ko() {
    String[] b314Files = {
        "and", "or", "eq", "gt", "lge", "lt", "lte", "or", "par", "true", "false"
    };

    for (String name : b314Files) {
      try {
        String file = "/semantics/instr_set_to/ko/arena_case_to_boolean_" + name + ".b314";
        CompilerTestHelper.getSymTable(file);
        fail("[Unthrowed] This .b314 is invalid. Should have thrown an Exception.\n" + file);
      } catch (RuntimeException e) {
        assertThat("Incorrect type of Exception throwned", e,
            instanceOf(NotMatchingType.class));
        assertThat("Must contain a detailed msg of the error", e.getMessage(), notNullValue());
      }
    }

  }

  @Test
  public void testinstr_set_to_undeclared_variable_ko() {
    String[] b314Files = {
        "var", "array", "array_2dim",
    };
    String file = "";

    for (String b314 : b314Files) {
      try {
        file = "/semantics/instr_set_to/ko/undeclared_" + b314 + ".b314";
        CompilerTestHelper.getSymTable(file);
        fail("[Unthrowed] This .b314 is invalid. Should have thrown an Exception.\n" + file);
      } catch (RuntimeException e) {
        assertThat("Incorrect type of Exception throwned", e,
            instanceOf(UndeclaredVariable.class));
        assertThat("Must contain a detailed msg of the error", e.getMessage(), notNullValue());
      }
    }

  }

  @Test
  public void testinstr_set_to_undeclared_function_ko() {
    String[] b314Files = {
        "call", "params", "params2",
    };
    String file = "";

    for (String b314 : b314Files) {
      try {
        file = "/semantics/instr_set_to/ko/undeclared_fct_" + b314 + ".b314";
        CompilerTestHelper.getSymTable(file);
        fail("[Unthrowed] This .b314 is invalid. Should have thrown an Exception.\n" + file);
      } catch (RuntimeException e) {
        assertThat("Incorrect type of Exception throwned\n" + file, e,
            instanceOf(UndeclaredFunction.class));
        assertThat("Must contain a detailed msg of the error", e.getMessage(), notNullValue());
      }
    }

  }


}