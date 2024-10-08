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

    @Test
    public void testexpression_par_entiere_operation_ok() throws Exception{
        CompilerTestHelper.launchCompilation("/syntax/expression_par/ok/entiere_operation.b314", testFolder.newFile(), true, "expression_par: entiere_operation");
    }

    //
    // Serie expression_par KO
    //
    @Test
    public void testexpression_par_add_var_wrong_type_ko() throws Exception {
        CompilerTestHelper.launchCompilation("/syntax/expression_par/ko/add_var_wrong_type.b314", testFolder.newFile(), false, "expression_par: add_var_wrong_type");
    }

    @Test
    public void testexpression_par_operations_with_arrays_wrong_array_type_in_expression_ko() throws Exception {
        CompilerTestHelper.launchCompilation("/syntax/expression_par/ko/operations_with_arrays_wrong_array_type_in_expression.b314", testFolder.newFile(), false, "expression_par: operations_with_arrays_wrong_array_type_in_expression");
    }

    @Test
    public void testexpression_par_sub_compute_wrong_type_condition_ko() throws Exception {
        CompilerTestHelper.launchCompilation("/syntax/expression_par/ko/sub_compute_wrong_type_condition.b314", testFolder.newFile(), false, "expression_par: sub_compute_wrong_type_condition");
    }

    @Test
    public void testexpression_par_undeclared_var_ko() throws Exception {
        CompilerTestHelper.launchCompilation("/syntax/expression_par/ko/undeclared_var.b314", testFolder.newFile(), false, "expression_par: undeclared_var");
    }

    @Test
    public void testexpression_par_wrong_boolean_ko() throws Exception {
        CompilerTestHelper.launchCompilation("/syntax/expression_par/ko/wrong_boolean.b314", testFolder.newFile(), false, "expression_par: wrong_boolean");
    }

    @Test
    public void testexpression_par_wrong_entiere_ko() throws Exception {
        CompilerTestHelper.launchCompilation("/syntax/expression_par/ko/wrong_entiere.b314", testFolder.newFile(), false, "expression_par: wrong_entiere");
    }

    @Test
    public void testexpression_par_wrong_type_in_sub_expression_ko() throws Exception {
        CompilerTestHelper.launchCompilation("/syntax/expression_par/ko/wrong_type_in_sub_expression.b314", testFolder.newFile(), false, "expression_par: wrong_type_in_sub_expression");
    }

}