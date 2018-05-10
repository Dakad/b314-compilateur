package be.unamur.info.b314.compiler.main;

import static org.hamcrest.Matchers.instanceOf;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.fail;

import be.unamur.info.b314.compiler.semantics.exception.AlreadyDeclaredAsFunction;
import be.unamur.info.b314.compiler.semantics.exception.AlreadyDeclaredFunction;
import be.unamur.info.b314.compiler.semantics.exception.DuplicateVariable;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;
import org.junit.rules.TestRule;
import org.junit.rules.TestWatcher;
import org.junit.runner.Description;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class B314fctDeclSemanticKoTest {

  private static final Logger LOG = LoggerFactory.getLogger(B314fctDeclSemanticKoTest.class);

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
  // Serie fctDecl KO
  //

  @Test(expected = AlreadyDeclaredFunction.class)
  public void testfct_decl_duplicate_name_ko() {
    CompilerTestHelper.getSymTable("/semantics/fctDecl/ko/duplicate_name.b314");
  }

  @Test(expected = DuplicateVariable.class)
  public void testfct_decl_duplicate_param_ko() {
    CompilerTestHelper.getSymTable("/semantics/fctDecl/ko/duplicate_param.b314");
  }

  @Test(expected = DuplicateVariable.class)
  public void testfct_decl_duplicate_param2_ko() {
    CompilerTestHelper.getSymTable("/semantics/fctDecl/ko/duplicate_param2.b314");
  }

  @Test
  public void testfct_decl_name_as_param_ko() {
    try {
      CompilerTestHelper.getSymTable("/semantics/fctDecl/ko/fctname_as_param.b314");
      fail("[Unthrowed] This .b314 contains duplicate global variable");
    } catch (RuntimeException e) {
      assertThat("Incorrect type of Exception thrown", e,
          instanceOf(AlreadyDeclaredAsFunction.class));
    }
  }

}