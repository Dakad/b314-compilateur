package be.unamur.info.b314.compiler.main;

import org.junit.Test;
import org.junit.Rule;
import org.junit.rules.TemporaryFolder;
import org.junit.rules.TestRule;
import org.junit.rules.TestWatcher;
import org.junit.runner.Description;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class B314clause_whenSemanticOkTest {

    private static final Logger LOG = LoggerFactory.getLogger(B314clause_whenSemanticOkTest.class);

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
    // Serie clause_when OK
    //
    @Test
    public void testclause_when_and_or_ok() throws Exception{
        CompilerTestHelper.launchCompilation("/semantics/clause_when/ok/and_or.b314", testFolder.newFile(), true, "clause_when: and_or");
    }

    @Test
    public void testclause_when_array_ok() throws Exception{
        CompilerTestHelper.launchCompilation("/semantics/clause_when/ok/array.b314", testFolder.newFile(), true, "clause_when: array");
    }

    @Test
    public void testclause_when_good_ok() throws Exception{
        CompilerTestHelper.launchCompilation("/semantics/clause_when/ok/good.b314", testFolder.newFile(), true, "clause_when: good");
    }

    @Test
    public void testclause_when_good_with_constant_ok() throws Exception{
        CompilerTestHelper.launchCompilation("/semantics/clause_when/ok/good_with_constant.b314", testFolder.newFile(), true, "clause_when: good_with_constant");
    }

    @Test
    public void testclause_when_good_with_fct_call_condition_ok() throws Exception{
        CompilerTestHelper.launchCompilation("/semantics/clause_when/ok/good_with_fct_call_condition.b314", testFolder.newFile(), true, "clause_when: good_with_fct_call_condition");
    }

    @Test
    public void testclause_when_good_with_fct_call_condition_skip_ok() throws Exception{
        CompilerTestHelper.launchCompilation("/semantics/clause_when/ok/good_with_fct_call_condition_skip.b314", testFolder.newFile(), true, "clause_when: good_with_fct_call_condition_skip");
    }

    @Test
    public void testclause_when_good_with_global_var_condition_ok() throws Exception{
        CompilerTestHelper.launchCompilation("/semantics/clause_when/ok/good_with_global_var_condition.b314", testFolder.newFile(), true, "clause_when: good_with_global_var_condition");
    }

    @Test
    public void testclause_when_good_with_rightexpr_conditiond_and_instruction_ok() throws Exception{
        CompilerTestHelper.launchCompilation("/semantics/clause_when/ok/good_with_rightexpr_conditiond_and_instruction.b314", testFolder.newFile(), true, "clause_when: good_with_rightexpr_conditiond_and_instruction");
    }

    @Test
    public void testclause_when_good_with_rightexpr_conditions_skip_ok() throws Exception{
        CompilerTestHelper.launchCompilation("/semantics/clause_when/ok/good_with_rightexpr_conditions_skip.b314", testFolder.newFile(), true, "clause_when: good_with_rightexpr_conditions_skip");
    }

    @Test
    public void testclause_when_op_ok() throws Exception{
        CompilerTestHelper.launchCompilation("/semantics/clause_when/ok/op.b314", testFolder.newFile(), true, "clause_when: op");
    }

    @Test
    public void testclause_when_op_var_ok() throws Exception{
        CompilerTestHelper.launchCompilation("/semantics/clause_when/ok/op_var.b314", testFolder.newFile(), true, "clause_when: op_var");
    }

    @Test
    public void testclause_when_true_false_ok() throws Exception{
        CompilerTestHelper.launchCompilation("/semantics/clause_when/ok/true_false.b314", testFolder.newFile(), true, "clause_when: true_false");
    }

    @Test
    public void testclause_when_var_ok() throws Exception{
        CompilerTestHelper.launchCompilation("/semantics/clause_when/ok/var.b314", testFolder.newFile(), true, "clause_when: var");
    }

}