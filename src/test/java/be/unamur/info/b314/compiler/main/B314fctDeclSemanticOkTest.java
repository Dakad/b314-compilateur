package be.unamur.info.b314.compiler.main;

import org.junit.Test;
import org.junit.Rule;
import org.junit.rules.TemporaryFolder;
import org.junit.rules.TestRule;
import org.junit.rules.TestWatcher;
import org.junit.runner.Description;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class B314fctDeclSemanticOkTest {

    private static final Logger LOG = LoggerFactory.getLogger(B314fctDeclSemanticOkTest.class);

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
    // Serie fctDecl OK
    //
    @Test
    public void testfctDecl_without_params_ok() throws Exception{
        CompilerTestHelper.launchCompilation("/semantics/fctDecl/ok/without_params.b314", testFolder.newFile(), true, "fctDecl: without_params");
    }

    @Test
    public void testfctDecl_with_params_ok() throws Exception{
        CompilerTestHelper.launchCompilation("/semantics/fctDecl/ok/with_params.b314", testFolder.newFile(), true, "fctDecl: with_params");
    }

    @Test
    public void testfctDecl_with_params_2_ok() throws Exception{
        CompilerTestHelper.launchCompilation("/semantics/fctDecl/ok/with_params_2.b314", testFolder.newFile(), true, "fctDecl: with_params_2");
    }

}