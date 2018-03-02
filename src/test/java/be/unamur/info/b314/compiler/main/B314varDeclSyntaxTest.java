package be.unamur.info.b314.compiler.main;

import org.junit.Test;
import org.junit.Rule;
import org.junit.rules.TemporaryFolder;
import org.junit.rules.TestRule;
import org.junit.rules.TestWatcher;
import org.junit.runner.Description;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class B314varDeclSyntaxTest {

    private static final Logger LOG = LoggerFactory.getLogger(B314varDeclSyntaxTest.class);

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
    // Serie varDecl OK
    //
    @Test
    public void testvarDecl_fct_local_var_diff_name_ok() throws Exception{
        CompilerTestHelper.launchCompilation("/syntax/varDecl/ok/fct_local_var_diff_name.b314", testFolder.newFile(), true, "varDecl: fct_local_var_diff_name");
    }

    @Test
    public void testvarDecl_one_boolean_ok() throws Exception{
        CompilerTestHelper.launchCompilation("/syntax/varDecl/ok/one_boolean.b314", testFolder.newFile(), true, "varDecl: one_boolean");
    }

    @Test
    public void testvarDecl_when_local_variable_same_name_as_global_ok() throws Exception{
        CompilerTestHelper.launchCompilation("/syntax/varDecl/ok/when_local_variable_same_name_as_global.b314", testFolder.newFile(), true, "varDecl: when_local_variable_same_name_as_global");
    }

    @Test
    public void testvarDecl_variable_mixing_ok() throws Exception{
        CompilerTestHelper.launchCompilation("/syntax/varDecl/ok/variable_mixing.b314", testFolder.newFile(), true, "varDecl: variable_mixing");
    }

    @Test
    public void testvarDecl_fct_local_var_mixing_ok() throws Exception{
        CompilerTestHelper.launchCompilation("/syntax/varDecl/ok/fct_local_var_mixing.b314", testFolder.newFile(), true, "varDecl: fct_local_var_mixing");
    }

    @Test
    public void testvarDecl_one_integer_ok() throws Exception{
        CompilerTestHelper.launchCompilation("/syntax/varDecl/ok/one_integer.b314", testFolder.newFile(), true, "varDecl: one_integer");
    }

    @Test
    public void testvarDecl_nul_size_array_ok() throws Exception{
        CompilerTestHelper.launchCompilation("/syntax/varDecl/ok/nul_size_array.b314", testFolder.newFile(), true, "varDecl: nul_size_array");
    }

    @Test
    public void testvarDecl_array_in_all_shapes_ok() throws Exception{
        CompilerTestHelper.launchCompilation("/syntax/varDecl/ok/array_in_all_shapes.b314", testFolder.newFile(), true, "varDecl: array_in_all_shapes");
    }

    @Test
    public void testvarDecl_fct_local_variable_same_name_as_global_ok() throws Exception{
        CompilerTestHelper.launchCompilation("/syntax/varDecl/ok/fct_local_variable_same_name_as_global.b314", testFolder.newFile(), true, "varDecl: fct_local_variable_same_name_as_global");
    }

    @Test
    public void testvarDecl_one_square_ok() throws Exception{
        CompilerTestHelper.launchCompilation("/syntax/varDecl/ok/one_square.b314", testFolder.newFile(), true, "varDecl: one_square");
    }

    @Test
    public void testvarDecl_when_local_var_diff_names_than_global_vars_ok() throws Exception{
        CompilerTestHelper.launchCompilation("/syntax/varDecl/ok/when_local_var_diff_names_than_global_vars.b314", testFolder.newFile(), true, "varDecl: when_local_var_diff_names_than_global_vars");
    }

    @Test
    public void testvarDecl_fct_local_var_same_names_and_different_types_than_global_vars_ok() throws Exception{
        CompilerTestHelper.launchCompilation("/syntax/varDecl/ok/fct_local_var_same_names_and_different_types_than_global_vars.b314", testFolder.newFile(), true, "varDecl: fct_local_var_same_names_and_different_types_than_global_vars");
    }

    @Test
    public void testvarDecl_when_local_variable_diff_names_ok() throws Exception{
        CompilerTestHelper.launchCompilation("/syntax/varDecl/ok/when_local_variable_diff_names.b314", testFolder.newFile(), true, "varDecl: when_local_variable_diff_names");
    }

    //
    // Serie varDecl KO
    //
    @Test
    public void testvarDecl_missing_type_ko() throws Exception {
        CompilerTestHelper.launchCompilation("/syntax/varDecl/ko/missing_type.b314", testFolder.newFile(), false, "varDecl: missing_type");
    }

    @Test
    public void testvarDecl_fct_local_var_mixing_miss_declare_ko() throws Exception {
        CompilerTestHelper.launchCompilation("/syntax/varDecl/ko/fct_local_var_mixing_miss_declare.b314", testFolder.newFile(), false, "varDecl: fct_local_var_mixing_miss_declare");
    }

    @Test
    public void testvarDecl_array_with_erreur_ko() throws Exception {
        CompilerTestHelper.launchCompilation("/syntax/varDecl/ko/array_with_erreur.b314", testFolder.newFile(), false, "varDecl: array_with_erreur");
    }

    @Test
    public void testvarDecl_missing_id_ko() throws Exception {
        CompilerTestHelper.launchCompilation("/syntax/varDecl/ko/missing_id.b314", testFolder.newFile(), false, "varDecl: missing_id");
    }

    @Test
    public void testvarDecl_array_missing_second_integer_ko() throws Exception {
        CompilerTestHelper.launchCompilation("/syntax/varDecl/ko/array_missing_second_integer.b314", testFolder.newFile(), false, "varDecl: array_missing_second_integer");
    }

    @Test
    public void testvarDecl_fct_local_var_diff_name_missing_one_type_ko() throws Exception {
        CompilerTestHelper.launchCompilation("/syntax/varDecl/ko/fct_local_var_diff_name_missing_one_type.b314", testFolder.newFile(), false, "varDecl: fct_local_var_diff_name_missing_one_type");
    }

    @Test
    public void testvarDecl_arrat_missing_rbrack£_ko() throws Exception {
        CompilerTestHelper.launchCompilation("/syntax/varDecl/ko/arrat_missing_rbrack£.b314", testFolder.newFile(), false, "varDecl: arrat_missing_rbrack£");
    }

}