package be.unamur.info.b314.compiler.main;

import org.junit.Test;
import org.junit.Rule;
import org.junit.rules.TemporaryFolder;
import org.junit.rules.TestRule;
import org.junit.rules.TestWatcher;
import org.junit.runner.Description;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class B314program_minimSemanticOkTest {

    private static final Logger LOG = LoggerFactory.getLogger(B314program_minimSemanticOkTest.class);

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
    // Serie program_minim OK
    //
    @Test
    public void testprogram_minim_empty_ok() throws Exception{
        CompilerTestHelper.launchCompilation("/semantics/program_minim/ok/empty.b314", testFolder.newFile(), true, "program_minim: empty");
    }

}