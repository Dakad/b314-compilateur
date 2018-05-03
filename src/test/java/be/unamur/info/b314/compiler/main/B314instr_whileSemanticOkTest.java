package be.unamur.info.b314.compiler.main;

import org.junit.Test;
import org.junit.Rule;
import org.junit.rules.TemporaryFolder;
import org.junit.rules.TestRule;
import org.junit.rules.TestWatcher;
import org.junit.runner.Description;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class B314instr_whileSemanticOkTest {

    private static final Logger LOG = LoggerFactory.getLogger(B314instr_whileSemanticOkTest.class);

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
    // Serie instr_while OK
    //
    @Test
    public void testinstr_while_and_or_ok() throws Exception{
        CompilerTestHelper.launchCompilation("/semantics/instr_while/ok/and_or.b314", testFolder.newFile(), true, "instr_while: and_or");
    }

    @Test
    public void testinstr_while_array_ok() throws Exception{
        CompilerTestHelper.launchCompilation("/semantics/instr_while/ok/array.b314", testFolder.newFile(), true, "instr_while: array");
    }

    @Test
    public void testinstr_while_op_ok() throws Exception{
        CompilerTestHelper.launchCompilation("/semantics/instr_while/ok/op.b314", testFolder.newFile(), true, "instr_while: op");
    }

    @Test
    public void testinstr_while_op_var_ok() throws Exception{
        CompilerTestHelper.launchCompilation("/semantics/instr_while/ok/op_var.b314", testFolder.newFile(), true, "instr_while: op_var");
    }

    @Test
    public void testinstr_while_true_false_ok() throws Exception{
        CompilerTestHelper.launchCompilation("/semantics/instr_while/ok/true_false.b314", testFolder.newFile(), true, "instr_while: true_false");
    }

    @Test
    public void testinstr_while_var_ok() throws Exception{
        CompilerTestHelper.launchCompilation("/semantics/instr_while/ok/var.b314", testFolder.newFile(), true, "instr_while: var");
    }

}