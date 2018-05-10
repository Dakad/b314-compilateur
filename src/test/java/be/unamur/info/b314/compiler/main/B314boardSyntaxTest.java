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
    public void testboard_monde_without_declared_arena_ok() throws Exception{
        CompilerTestHelper.launchCompilation("/syntax/board/ok/monde_without_declared_arena.b314", testFolder.newFile(), true, "board: monde_without_declared_arena");
    }

    @Test
    public void testboard_monde_with_declared_arena_ok() throws Exception{
        CompilerTestHelper.launchCompilation("/syntax/board/ok/monde_with_declared_arena.b314", testFolder.newFile(), true, "board: monde_with_declared_arena");
    }

    @Test
    public void testboard_no_board_ok() throws Exception{
        CompilerTestHelper.launchCompilation("/syntax/board/ok/no_board.b314", testFolder.newFile(), true, "board: no_board");
    }

    //
    // Serie board KO
    //
    @Test
    public void testboard_double_declared_arena_ko() throws Exception {
        CompilerTestHelper.launchCompilation("/syntax/board/ko/double_declared_arena.b314", testFolder.newFile(), false, "board: double_declared_arena");
    }

    @Test
    public void testboard_monde_ko() throws Exception {
        CompilerTestHelper.launchCompilation("/syntax/board/ko/monde.b314", testFolder.newFile(), false, "board: monde");
    }

    @Test
    public void testboard_reserved_keyword_arena_ko() throws Exception {
        CompilerTestHelper.launchCompilation("/syntax/board/ko/reserved_keyword_arena.b314", testFolder.newFile(), false, "board: reserved_keyword_arena");
    }

}