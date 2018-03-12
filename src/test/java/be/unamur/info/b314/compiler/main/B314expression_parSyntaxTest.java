package be.unamur.info.b314.compiler.main;

import org.junit.Test;
import org.junit.Rule;
import org.junit.rules.TemporaryFolder;
import org.junit.rules.TestRule;
import org.junit.rules.TestWatcher;
import org.junit.runner.Description;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class B314expression_parSyntaxTest {

    private static final Logger LOG = LoggerFactory.getLogger(B314expression_parSyntaxTest.class);

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
    // Serie expression_par OK
    //
    @Test
    public void testexpression_par_boolean_ok() throws Exception{
        CompilerTestHelper.launchCompilation("/syntax/expression_par/ok/boolean.b314", testFolder.newFile(), true, "expression_par: boolean");
    }

    @Test
    public void testexpression_par_boolean1_ok() throws Exception{
        CompilerTestHelper.launchCompilation("/syntax/expression_par/ok/boolean1.b314", testFolder.newFile(), true, "expression_par: boolean1");
    }

    @Test
    public void testexpression_par_boolean3_ok() throws Exception{
        CompilerTestHelper.launchCompilation("/syntax/expression_par/ok/boolean3.b314", testFolder.newFile(), true, "expression_par: boolean3");
    }

    @Test
    public void testexpression_par_boolean4_ok() throws Exception{
        CompilerTestHelper.launchCompilation("/syntax/expression_par/ok/boolean4.b314", testFolder.newFile(), true, "expression_par: boolean4");
    }

    @Test
    public void testexpression_par_entiere_items_ok() throws Exception{
        CompilerTestHelper.launchCompilation("/syntax/expression_par/ok/entiere_items.b314", testFolder.newFile(), true, "expression_par: entiere_items");
    }

    //
    // Serie expression_par KO
    //
}