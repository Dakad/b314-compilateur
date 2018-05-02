package be.unamur.info.b314.compiler.main;

import org.junit.Test;
import org.junit.Rule;
import org.junit.rules.TemporaryFolder;
import org.junit.rules.TestRule;
import org.junit.rules.TestWatcher;
import org.junit.runner.Description;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class B314instr_set_toSemanticOkTest {

    private static final Logger LOG = LoggerFactory.getLogger(B314instr_set_toSemanticOkTest.class);

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
    // Serie instr_set_to OK
    //
    @Test
    public void testinstr_set_to_arena_case_to_item_ok() throws Exception{
        CompilerTestHelper.launchCompilation("/semantics/instr_set_to/ok/arena_case_to_item.b314", testFolder.newFile(), true, "instr_set_to: arena_case_to_item");
    }

}