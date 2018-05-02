package be.unamur.info.b314.compiler.main;

import org.junit.Test;
import org.junit.Rule;
import org.junit.rules.TemporaryFolder;
import org.junit.rules.TestRule;
import org.junit.rules.TestWatcher;
import org.junit.runner.Description;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class B314var_globalSemanticOkTest {

    private static final Logger LOG = LoggerFactory.getLogger(B314var_globalSemanticOkTest.class);

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
    // Serie var_global OK
    //
    @Test
    public void testvar_global_variable_mixing_ok() throws Exception{
        CompilerTestHelper.launchCompilation("/semantics/var_global/ok/variable_mixing.b314", testFolder.newFile(), true, "var_global: variable_mixing");
    }

    @Test
    public void testvar_global_all_integer_ok() throws Exception{
        CompilerTestHelper.launchCompilation("/semantics/var_global/ok/all_integer.b314", testFolder.newFile(), true, "var_global: all_integer");
    }

    @Test
    public void testvar_global_array_in_all_shapes_ok() throws Exception{
        CompilerTestHelper.launchCompilation("/semantics/var_global/ok/array_in_all_shapes.b314", testFolder.newFile(), true, "var_global: array_in_all_shapes");
    }

    @Test
    public void testvar_global_all_boolean_ok() throws Exception{
        CompilerTestHelper.launchCompilation("/semantics/var_global/ok/all_boolean.b314", testFolder.newFile(), true, "var_global: all_boolean");
    }

    @Test
    public void testvar_global_one_square_ok() throws Exception{
        CompilerTestHelper.launchCompilation("/semantics/var_global/ok/one_square.b314", testFolder.newFile(), true, "var_global: one_square");
    }

}