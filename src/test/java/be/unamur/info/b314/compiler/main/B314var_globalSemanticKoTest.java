package be.unamur.info.b314.compiler.main;

import static org.hamcrest.Matchers.instanceOf;
import static org.hamcrest.Matchers.notNullValue;
import static org.hamcrest.Matchers.nullValue;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.fail;

import be.unamur.info.b314.compiler.semantics.exception.AlreadyDeclaredVariable;
import be.unamur.info.b314.compiler.semantics.exception.NotPositiveSizeForArray;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;
import org.junit.rules.TestRule;
import org.junit.rules.TestWatcher;
import org.junit.runner.Description;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class B314var_globalSemanticKoTest {

  private static final Logger LOG = LoggerFactory.getLogger(B314var_globalSemanticKoTest.class);

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
  // Serie var_global KO
  //
  @Test(expected = AlreadyDeclaredVariable.class)
  public void testvar_global_duplicate_name_ko() {
      CompilerTestHelper.getSymTable("/semantics/var_global/ko/duplicate_name.b314");
  }

  @Test
  public void testvar_global_array_negatif_length_ko() {
    try {
      CompilerTestHelper.getSymTable("/semantics/var_global/ko/array_negatif_length.b314");

      fail("[Unthrowed] This .b314 is invalid");
    } catch (RuntimeException e) {
      assertThat("Incorrect type of Exception throwned", e,
          instanceOf(NotPositiveSizeForArray.class));
      assertThat("Must contain a detailed msg of the error", e.getMessage(), notNullValue());
    }

    try {
      CompilerTestHelper.getSymTable("/semantics/var_global/ko/array_negatif_length_2dim.b314");
      fail("[Unthrowed] This .b314 is invalid");
    } catch (RuntimeException e) {
      assertThat("Incorrect type of Exception throwned", e,
          instanceOf(NotPositiveSizeForArray.class));
      assertThat("Must contain a detailed msg of the error", e.getMessage(), notNullValue());
    }

    try {
      CompilerTestHelper.getSymTable("/semantics/var_global/ko/array_negatif_length_2dim_2.b314");
      fail("[Unthrowed] This .b314 is invalid");
    } catch (RuntimeException e) {
      assertThat("Incorrect type of Exception throwned", e,
          instanceOf(NotPositiveSizeForArray.class));
      assertThat("Must contain a detailed msg of the error", e.getMessage(), notNullValue());
    }
  }



  @Test
  public void testvar_global_array_null_length_ko() {
    try {
      CompilerTestHelper.getSymTable("/semantics/var_global/ko/array_null_length.b314");

      fail("[Unthrowed] This .b314 is invalid");
    } catch (RuntimeException e) {
      assertThat("Incorrect type of Exception throwned", e,
          instanceOf(NotPositiveSizeForArray.class));
      assertThat("Must contain a detailed msg of the error", e.getMessage(), notNullValue());
    }

    try {
      CompilerTestHelper.getSymTable("/semantics/var_global/ko/array_null_length_2dim.b314");

      fail("[Unthrowed] This .b314 is invalid");
    } catch (RuntimeException e) {
      assertThat("Incorrect type of Exception throwned", e,
          instanceOf(NotPositiveSizeForArray.class));
      assertThat("Must contain a detailed msg of the error", e.getMessage(), notNullValue());
    }
  }


}