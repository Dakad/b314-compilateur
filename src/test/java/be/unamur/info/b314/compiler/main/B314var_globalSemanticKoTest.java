package be.unamur.info.b314.compiler.main;

import be.unamur.info.b314.compiler.semantics.SymTableFiller;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;
import org.junit.rules.TestRule;
import org.junit.rules.TestWatcher;
import org.junit.runner.Description;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class B314var_globalSemanticKoTest {

    private static final Logger LOG = LoggerFactory.getLogger(B314var_globalSemanticKoTest.class);

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
    // Serie var_global KO
    //
    @Test
    public void testvar_global_duplicate_name_ko() throws Exception{
        SymTableFiller symbtable = CompilerTestHelper
            .getSymTable("/semantics/var_global/ko/duplicate_name.b314");
    }


}