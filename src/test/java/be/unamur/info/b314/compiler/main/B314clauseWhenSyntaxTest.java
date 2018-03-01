package be.unamur.info.b314.compiler.main;

import org.junit.Test;
import org.junit.Rule;
import org.junit.rules.TemporaryFolder;
import org.junit.rules.TestRule;
import org.junit.rules.TestWatcher;
import org.junit.runner.Description;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class B314clauseWhenSyntaxTest {

    private static final Logger LOG = LoggerFactory.getLogger(B314clauseWhenSyntaxTest.class);

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
    // Serie clauseWhen OK
    //
    @Test
    public void testclauseWhen_good_ok() throws Exception{
        CompilerTestHelper.launchCompilation("/syntax/clauseWhen/ok/good.b314", testFolder.newFile(), true, "clauseWhen: good");
    }

    @Test
    public void testclauseWhen_good_with_constant_ok() throws Exception{
        CompilerTestHelper.launchCompilation("/syntax/clauseWhen/ok/good_with_constant.b314", testFolder.newFile(), true, "clauseWhen: good_with_constant");
    }

    @Test
    public void testclauseWhen_good_with_fct_call_condition_ok() throws Exception{
        CompilerTestHelper.launchCompilation("/syntax/clauseWhen/ok/good_with_fct_call_condition.b314", testFolder.newFile(), true, "clauseWhen: good_with_fct_call_condition");
    }

    @Test
    public void testclauseWhen_good_with_fct_call_condition_skip_ok() throws Exception{
        CompilerTestHelper.launchCompilation("/syntax/clauseWhen/ok/good_with_fct_call_condition_skip.b314", testFolder.newFile(), true, "clauseWhen: good_with_fct_call_condition_skip");
    }

    @Test
    public void testclauseWhen_good_with_global_var_condition_ok() throws Exception{
        CompilerTestHelper.launchCompilation("/syntax/clauseWhen/ok/good_with_global_var_condition.b314", testFolder.newFile(), true, "clauseWhen: good_with_global_var_condition");
    }

    @Test
    public void testclauseWhen_good_with_rightexpr_conditiond_and_instruction_ok() throws Exception{
        CompilerTestHelper.launchCompilation("/syntax/clauseWhen/ok/good_with_rightexpr_conditiond_and_instruction.b314", testFolder.newFile(), true, "clauseWhen: good_with_rightexpr_conditiond_and_instruction");
    }

    @Test
    public void testclauseWhen_good_with_rightexpr_conditions_skip_ok() throws Exception{
        CompilerTestHelper.launchCompilation("/syntax/clauseWhen/ok/good_with_rightexpr_conditions_skip.b314", testFolder.newFile(), true, "clauseWhen: good_with_rightexpr_conditions_skip");
    }

    //
    // Serie clauseWhen KO
    //
    @Test
    public void testclauseWhen_multiple_when_clauses_after_default_ko() throws Exception {
        CompilerTestHelper.launchCompilation("/syntax/clauseWhen/ko/multiple_when_clauses_after_default.b314", testFolder.newFile(), false, "clauseWhen: multiple_when_clauses_after_default");
    }

    @Test
    public void testclauseWhen_multiple_when_clause_with_wrong_fct_call_ko() throws Exception {
        CompilerTestHelper.launchCompilation("/syntax/clauseWhen/ko/multiple_when_clause_with_wrong_fct_call.b314", testFolder.newFile(), false, "clauseWhen: multiple_when_clause_with_wrong_fct_call");
    }

    @Test
    public void testclauseWhen_multiple_when_with_rightexpr_conditions_and_instructions_wrong_ko() throws Exception {
        CompilerTestHelper.launchCompilation("/syntax/clauseWhen/ko/multiple_when_with_rightexpr_conditions_and_instructions_wrong.b314", testFolder.newFile(), false, "clauseWhen: multiple_when_with_rightexpr_conditions_and_instructions_wrong");
    }

    @Test
    public void testclauseWhen_multiple_when_with_rightexpr_conditions_and_instructions_wrong_typed_ko() throws Exception {
        CompilerTestHelper.launchCompilation("/syntax/clauseWhen/ko/multiple_when_with_rightexpr_conditions_and_instructions_wrong_typed.b314", testFolder.newFile(), false, "clauseWhen: multiple_when_with_rightexpr_conditions_and_instructions_wrong_typed");
    }

    @Test
    public void testclauseWhen_multiple_when_with_rightexpr_condition_wrong_type_A_ko() throws Exception {
        CompilerTestHelper.launchCompilation("/syntax/clauseWhen/ko/multiple_when_with_rightexpr_condition_wrong_type_A.b314", testFolder.newFile(), false, "clauseWhen: multiple_when_with_rightexpr_condition_wrong_type_A");
    }

    @Test
    public void testclauseWhen_multiple_when_with_rightexpr_condition_wrong_type_B_ko() throws Exception {
        CompilerTestHelper.launchCompilation("/syntax/clauseWhen/ko/multiple_when_with_rightexpr_condition_wrong_type_B.b314", testFolder.newFile(), false, "clauseWhen: multiple_when_with_rightexpr_condition_wrong_type_B");
    }

    @Test
    public void testclauseWhen_multiple_when_with_rightexpr_condition_wrong_type_C_ko() throws Exception {
        CompilerTestHelper.launchCompilation("/syntax/clauseWhen/ko/multiple_when_with_rightexpr_condition_wrong_type_C.b314", testFolder.newFile(), false, "clauseWhen: multiple_when_with_rightexpr_condition_wrong_type_C");
    }

}