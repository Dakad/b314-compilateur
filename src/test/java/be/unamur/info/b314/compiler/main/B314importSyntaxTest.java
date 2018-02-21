package be.unamur.info.b314.compiler.main;

import org.junit.Test;
import org.junit.Rule;
import org.junit.rules.TemporaryFolder;
import org.junit.rules.TestRule;
import org.junit.rules.TestWatcher;
import org.junit.runner.Description;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class B314importSyntaxTest {

    private static final Logger LOG = LoggerFactory.getLogger(B314importSyntaxTest.class);

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
    // Serie import OK
    //
    @Test
    public void testimport_good_ok() throws Exception{
        CompilerTestHelper.launchCompilation("/syntax/import/ok/good.b314", testFolder.newFile(), true, "import: good");
    }

    //
    // Serie import KO
    //
    @Test
    public void testimport_empty_ko() throws Exception {
        CompilerTestHelper.launchCompilation("/syntax/import/ko/empty.b314", testFolder.newFile(), false, "import: empty");
    }

}