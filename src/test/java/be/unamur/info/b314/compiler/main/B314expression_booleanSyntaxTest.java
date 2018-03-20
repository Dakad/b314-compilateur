package be.unamur.info.b314.compiler.main;

import org.junit.Test;
import org.junit.Rule;
import org.junit.rules.TemporaryFolder;
import org.junit.rules.TestRule;
import org.junit.rules.TestWatcher;
import org.junit.runner.Description;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class B314expression_booleanSyntaxTest {

    private static final Logger LOG = LoggerFactory.getLogger(B314expression_booleanSyntaxTest.class);

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
    // Serie expression_boolean OK
    //
    @Test
    public void testexpression_boolean_constant_values_ok() throws Exception{
        CompilerTestHelper.launchCompilation("/syntax/expression_boolean/ok/constant_values.b314", testFolder.newFile(), true, "expression_boolean: constant_values");
    }

    @Test
    public void testexpression_boolean_operations_with_array_ok() throws Exception{
        CompilerTestHelper.launchCompilation("/syntax/expression_boolean/ok/operations_with_array.b314", testFolder.newFile(), true, "expression_boolean: operations_with_array");
    }

    @Test
    public void testexpression_boolean_parin_expression_ok() throws Exception{
        CompilerTestHelper.launchCompilation("/syntax/expression_boolean/ok/parin_expression.b314", testFolder.newFile(), true, "expression_boolean: parin_expression");
    }

    @Test
    public void testexpression_boolean_operation_only_env_vars_ok() throws Exception{
        CompilerTestHelper.launchCompilation("/syntax/expression_boolean/ok/operation_only_env_vars.b314", testFolder.newFile(), true, "expression_boolean: operation_only_env_vars");
    }

    @Test
    public void testexpression_boolean_expression_gauche_ok() throws Exception{
        CompilerTestHelper.launchCompilation("/syntax/expression_boolean/ok/expression_gauche.b314", testFolder.newFile(), true, "expression_boolean: expression_gauche");
    }

    @Test
    public void testexpression_boolean_operations_ok() throws Exception{
        CompilerTestHelper.launchCompilation("/syntax/expression_boolean/ok/operations.b314", testFolder.newFile(), true, "expression_boolean: operations");
    }

    @Test
    public void testexpression_boolean_environnement_var_ok() throws Exception{
        CompilerTestHelper.launchCompilation("/syntax/expression_boolean/ok/environnement_var.b314", testFolder.newFile(), true, "expression_boolean: environnement_var");
    }

    //
    // Serie expression_boolean KO
    //
    @Test
    public void testexpression_boolean_or_env_var_wrong_type_ko() throws Exception {
        CompilerTestHelper.launchCompilation("/syntax/expression_boolean/ko/or_env_var_wrong_type.b314", testFolder.newFile(), false, "expression_boolean: or_env_var_wrong_type");
    }

    @Test
    public void testexpression_boolean_var_wrong_type_ko() throws Exception {
        CompilerTestHelper.launchCompilation("/syntax/expression_boolean/ko/var_wrong_type.b314", testFolder.newFile(), false, "expression_boolean: var_wrong_type");
    }

    @Test
    public void testexpression_boolean_greathethan_env_var_wrong_type_ko() throws Exception {
        CompilerTestHelper.launchCompilation("/syntax/expression_boolean/ko/greathethan_env_var_wrong_type.b314", testFolder.newFile(), false, "expression_boolean: greathethan_env_var_wrong_type");
    }

    @Test
    public void testexpression_boolean_env_var_wrong_ko() throws Exception {
        CompilerTestHelper.launchCompilation("/syntax/expression_boolean/ko/env_var_wrong.b314", testFolder.newFile(), false, "expression_boolean: env_var_wrong");
    }

    @Test
    public void testexpression_boolean_operation_ko_ko() throws Exception {
        CompilerTestHelper.launchCompilation("/syntax/expression_boolean/ko/operation_ko.b314", testFolder.newFile(), false, "expression_boolean: operation_ko");
    }

    @Test
    public void testexpression_boolean_operation_with_arrays_missing_index_ko() throws Exception {
        CompilerTestHelper.launchCompilation("/syntax/expression_boolean/ko/operation_with_arrays_missing_index.b314", testFolder.newFile(), false, "expression_boolean: operation_with_arrays_missing_index");
    }

    @Test
    public void testexpression_boolean_undeclared_var_ko() throws Exception {
        CompilerTestHelper.launchCompilation("/syntax/expression_boolean/ko/undeclared_var.b314", testFolder.newFile(), false, "expression_boolean: undeclared_var");
    }

}