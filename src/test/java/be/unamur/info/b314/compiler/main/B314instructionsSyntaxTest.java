package be.unamur.info.b314.compiler.main;

import org.junit.Test;
import org.junit.Rule;
import org.junit.rules.TemporaryFolder;
import org.junit.rules.TestRule;
import org.junit.rules.TestWatcher;
import org.junit.runner.Description;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class B314instructionsSyntaxTest {

    private static final Logger LOG = LoggerFactory.getLogger(B314instructionsSyntaxTest.class);

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
    // Serie instructions OK
    //
    @Test
    public void testinstructions_only_skip_ok() throws Exception{
        CompilerTestHelper.launchCompilation("/syntax/instructions/ok/only_skip.b314", testFolder.newFile(), true, "instructions: only_skip");
    }

    //
    // Serie instructions KO
    //
    @Test
    public void testinstructions_wrong_ko() throws Exception {
        CompilerTestHelper.launchCompilation("/syntax/instructions/ko/wrong.b314", testFolder.newFile(), false, "instructions: wrong");
    }

}