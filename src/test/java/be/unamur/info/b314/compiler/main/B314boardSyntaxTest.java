package be.unamur.info.b314.compiler.main;

import org.junit.Test;
import org.junit.Rule;
import org.junit.rules.TemporaryFolder;
import org.junit.rules.TestRule;
import org.junit.rules.TestWatcher;
import org.junit.runner.Description;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class B314boardSyntaxTest {

    private static final Logger LOG = LoggerFactory.getLogger(B314boardSyntaxTest.class);

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
    // Serie board OK
    //
    @Test
    public void testboard_no_board_ok() throws Exception{
        CompilerTestHelper.launchCompilation("/syntax/board/ok/no_board.b314", testFolder.newFile(), true, "board: no_board");
    }

    @Test
    public void testboard_monde_ok() throws Exception{
        CompilerTestHelper.launchCompilation("/syntax/board/ok/monde.b314", testFolder.newFile(), true, "board: monde");
    }

    //
    // Serie board KO
    //
    @Test
    public void testboard_no_board_ko() throws Exception {
        CompilerTestHelper.launchCompilation("/syntax/board/ko/no_board.b314", testFolder.newFile(), false, "board: no_board");
    }

    @Test
    public void testboard_monde_ko() throws Exception {
        CompilerTestHelper.launchCompilation("/syntax/board/ko/monde.b314", testFolder.newFile(), false, "board: monde");
    }

}