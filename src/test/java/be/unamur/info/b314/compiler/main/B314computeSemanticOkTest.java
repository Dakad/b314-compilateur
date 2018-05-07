package be.unamur.info.b314.compiler.main;

import org.junit.Test;
import org.junit.Rule;
import org.junit.rules.TemporaryFolder;
import org.junit.rules.TestRule;
import org.junit.rules.TestWatcher;
import org.junit.runner.Description;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class B314computeSemanticOkTest {

    private static final Logger LOG = LoggerFactory.getLogger(B314computeSemanticOkTest.class);

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
    // Serie compute OK
    //
    @Test
    public void testcompute_function_inside_compute_ok() throws Exception{
        CompilerTestHelper.launchCompilation("/semantics/compute/ok/function_inside_compute.b314", testFolder.newFile(), true, "compute: function_inside_compute");
    }

    @Test
    public void testcompute_function_with_one_compute_ok() throws Exception{
        CompilerTestHelper.launchCompilation("/semantics/compute/ok/function_with_one_compute.b314", testFolder.newFile(), true, "compute: function_with_one_compute");
    }

}