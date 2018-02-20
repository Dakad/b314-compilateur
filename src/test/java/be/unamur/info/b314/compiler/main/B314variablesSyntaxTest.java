package be.unamur.info.b314.compiler.main;

import org.junit.Test;
import org.junit.Rule;
import org.junit.rules.TemporaryFolder;
import org.junit.rules.TestRule;
import org.junit.rules.TestWatcher;
import org.junit.runner.Description;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class B314variablesSyntaxTest {

    private static final Logger LOG = LoggerFactory.getLogger(B314variablesSyntaxTest.class);

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
    // Serie variables OK
    //
    @Test
    public void testvariables_empty_ok() throws Exception{
        CompilerTestHelper.launchCompilation("/syntax/variables/ok/empty.b314", testFolder.newFile(), true, "variables: empty");
    }

    //
    // Serie variables KO
    //
    @Test
    public void testvariables_empty_ko() throws Exception {
        CompilerTestHelper.launchCompilation("/syntax/variables/ko/empty.b314", testFolder.newFile(), false, "variables: empty");
    }

}