package be.unamur.info.b314.compiler.main;

import static org.hamcrest.Matchers.instanceOf;
import static org.junit.Assert.assertThat;

import be.unamur.info.b314.compiler.semantics.SymTableFiller;
import be.unamur.info.b314.compiler.semantics.exception.AlreadyGloballyDeclared;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;
import org.junit.rules.TestRule;
import org.junit.rules.TestWatcher;
import org.junit.runner.Description;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class B314var_globalSemanticTest {

  private static final Logger LOG = LoggerFactory.getLogger(B314var_globalSemanticTest.class);


  public SymTableFiller symbTab;

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
  //
  @Test
  public void testvar_global_variable_mixing_ok() throws Exception{
    symbTab = CompilerTestHelper.getSymTable("/semantics/var_global/ok/variable_mixing.b314");
    /*
      assertThat("Interpreter exist status was not 0", result.getExitStatus(), equalTo(0));
      assertThat("Wrong number of outputs, there was 1 turn", result.getOutLines(), hasSize(1));
     */
  }

  @Test
  public void testvar_global_all_integer_ok() throws Exception{
    symbTab = CompilerTestHelper.getSymTable("/semantics/var_global/ok/all_integer.b314");
  }

  @Test
  public void testvar_global_array_in_all_shapes_ok() throws Exception{
    symbTab = CompilerTestHelper.getSymTable("/semantics/var_global/ok/array_in_all_shapes.b314");
  }

  @Test
  public void testvar_global_all_boolean_ok() throws Exception{
    symbTab = CompilerTestHelper.getSymTable("/semantics/var_global/ok/all_boolean.b314");
  }

  @Test
  public void testvar_global_one_square_ok() throws Exception{
    symbTab = CompilerTestHelper.getSymTable("/semantics/var_global/ok/one_square.b314");
  }


}