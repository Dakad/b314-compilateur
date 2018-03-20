package be.unamur.info.b314.compiler.main;

import org.junit.Test;
import org.junit.Rule;
import org.junit.rules.TemporaryFolder;
import org.junit.rules.TestRule;
import org.junit.rules.TestWatcher;
import org.junit.runner.Description;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class B314expression_caseSyntaxTest {

    private static final Logger LOG = LoggerFactory.getLogger(B314expression_caseSyntaxTest.class);

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
    // Serie expression_case OK
    //
    @Test
    public void testexpression_case_constant_values_ok() throws Exception{
        CompilerTestHelper.launchCompilation("/syntax/expression_case/ok/constant_values.b314", testFolder.newFile(), true, "expression_case: constant_values");
    }

    @Test
    public void testexpression_case_operations_with_array_ok() throws Exception{
        CompilerTestHelper.launchCompilation("/syntax/expression_case/ok/operations_with_array.b314", testFolder.newFile(), true, "expression_case: operations_with_array");
    }

    @Test
    public void testexpression_case_operation_only_env_vars_ok() throws Exception{
        CompilerTestHelper.launchCompilation("/syntax/expression_case/ok/operation_only_env_vars.b314", testFolder.newFile(), true, "expression_case: operation_only_env_vars");
    }

    @Test
    public void testexpression_case_expression_gauche_ok() throws Exception{
        CompilerTestHelper.launchCompilation("/syntax/expression_case/ok/expression_gauche.b314", testFolder.newFile(), true, "expression_case: expression_gauche");
    }

    @Test
    public void testexpression_case_operations_ok() throws Exception{
        CompilerTestHelper.launchCompilation("/syntax/expression_case/ok/operations.b314", testFolder.newFile(), true, "expression_case: operations");
    }

    @Test
    public void testexpression_case_environnement_var_ok() throws Exception{
        CompilerTestHelper.launchCompilation("/syntax/expression_case/ok/environnement_var.b314", testFolder.newFile(), true, "expression_case: environnement_var");
    }

    //
    // Serie expression_case KO
    //
    @Test
    public void testexpression_case_wrong_environnement_var_ko() throws Exception {
        CompilerTestHelper.launchCompilation("/syntax/expression_case/ko/wrong_environnement_var.b314", testFolder.newFile(), false, "expression_case: wrong_environnement_var");
    }

    @Test
    public void testexpression_case_wrong_operation_only_env_vars_ko() throws Exception {
        CompilerTestHelper.launchCompilation("/syntax/expression_case/ko/wrong_operation_only_env_vars.b314", testFolder.newFile(), false, "expression_case: wrong_operation_only_env_vars");
    }

    @Test
    public void testexpression_case_wrong_operations_with_array_ko() throws Exception {
        CompilerTestHelper.launchCompilation("/syntax/expression_case/ko/wrong_operations_with_array.b314", testFolder.newFile(), false, "expression_case: wrong_operations_with_array");
    }

    @Test
    public void testexpression_case_wrong_expression_gauche_ko() throws Exception {
        CompilerTestHelper.launchCompilation("/syntax/expression_case/ko/wrong_expression_gauche.b314", testFolder.newFile(), false, "expression_case: wrong_expression_gauche");
    }

    @Test
    public void testexpression_case_wrong_operations_ko() throws Exception {
        CompilerTestHelper.launchCompilation("/syntax/expression_case/ko/wrong_operations.b314", testFolder.newFile(), false, "expression_case: wrong_operations");
    }

    @Test
    public void testexpression_case_wrong_constant_values_ko() throws Exception {
        CompilerTestHelper.launchCompilation("/syntax/expression_case/ko/wrong_constant_values.b314", testFolder.newFile(), false, "expression_case: wrong_constant_values");
    }

}