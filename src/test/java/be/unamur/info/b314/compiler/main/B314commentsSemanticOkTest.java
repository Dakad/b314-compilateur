package be.unamur.info.b314.compiler.main;

import org.junit.Test;
import org.junit.Rule;
import org.junit.rules.TemporaryFolder;
import org.junit.rules.TestRule;
import org.junit.rules.TestWatcher;
import org.junit.runner.Description;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class B314commentsSemanticOkTest {

    private static final Logger LOG = LoggerFactory.getLogger(B314commentsSemanticOkTest.class);

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
    // Serie comments OK
    //
    @Test
    public void testcomments_empty_ok() throws Exception{
        CompilerTestHelper.launchCompilation("/semantics/comments/ok/empty.b314", testFolder.newFile(), true, "comments: empty");
    }

    @Test
    public void testcomments_original_no_comments_ok() throws Exception{
        CompilerTestHelper.launchCompilation("/semantics/comments/ok/original_no_comments.b314", testFolder.newFile(), true, "comments: original_no_comments");
    }

    @Test
    public void testcomments_original_comments_everywhere_ok() throws Exception{
        CompilerTestHelper.launchCompilation("/semantics/comments/ok/original_comments_everywhere.b314", testFolder.newFile(), true, "comments: original_comments_everywhere");
    }

    @Test
    public void testcomments_no_comments_multiple_lines_and_tab_ok() throws Exception{
        CompilerTestHelper.launchCompilation("/semantics/comments/ok/no_comments_multiple_lines_and_tab.b314", testFolder.newFile(), true, "comments: no_comments_multiple_lines_and_tab");
    }

    @Test
    public void testcomments_original_no_comments_multiple_lines_and_tab_ok() throws Exception{
        CompilerTestHelper.launchCompilation("/semantics/comments/ok/original_no_comments_multiple_lines_and_tab.b314", testFolder.newFile(), true, "comments: original_no_comments_multiple_lines_and_tab");
    }

    @Test
    public void testcomments_comments_everywhere_ok() throws Exception{
        CompilerTestHelper.launchCompilation("/semantics/comments/ok/comments_everywhere.b314", testFolder.newFile(), true, "comments: comments_everywhere");
    }

}