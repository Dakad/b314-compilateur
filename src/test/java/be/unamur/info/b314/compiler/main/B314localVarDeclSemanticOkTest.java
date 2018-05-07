package be.unamur.info.b314.compiler.main;

import org.junit.Test;
import org.junit.Rule;
import org.junit.rules.TemporaryFolder;
import org.junit.rules.TestRule;
import org.junit.rules.TestWatcher;
import org.junit.runner.Description;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class B314localVarDeclSemanticOkTest {

    private static final Logger LOG = LoggerFactory.getLogger(B314localVarDeclSemanticOkTest.class);

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
    // Serie localVarDecl OK
    //
    @Test
    public void testlocalVarDecl_fct_local_variable_same_name_as_global_ok() throws Exception{
        CompilerTestHelper.launchCompilation("/semantics/localVarDecl/ok/fct_local_variable_same_name_as_global.b314", testFolder.newFile(), true, "localVarDecl: fct_local_variable_same_name_as_global");
    }

    @Test
    public void testlocalVarDecl_fct_local_var_mixing_ok() throws Exception{
        CompilerTestHelper.launchCompilation("/semantics/localVarDecl/ok/fct_local_var_mixing.b314", testFolder.newFile(), true, "localVarDecl: fct_local_var_mixing");
    }

    @Test
    public void testlocalVarDecl_fct_local_var_same_names_and_different_types_than_global_vars_ok() throws Exception{
        CompilerTestHelper.launchCompilation("/semantics/localVarDecl/ok/fct_local_var_same_names_and_different_types_than_global_vars.b314", testFolder.newFile(), true, "localVarDecl: fct_local_var_same_names_and_different_types_than_global_vars");
    }

    @Test
    public void testlocalVarDecl_when_local_variable_diff_names_ok() throws Exception{
        CompilerTestHelper.launchCompilation("/semantics/localVarDecl/ok/when_local_variable_diff_names.b314", testFolder.newFile(), true, "localVarDecl: when_local_variable_diff_names");
    }

    @Test
    public void testlocalVarDecl_when_local_variable_same_name_as_global_ok() throws Exception{
        CompilerTestHelper.launchCompilation("/semantics/localVarDecl/ok/when_local_variable_same_name_as_global.b314", testFolder.newFile(), true, "localVarDecl: when_local_variable_same_name_as_global");
    }

    @Test
    public void testlocalVarDecl_when_local_var_diff_names_than_global_vars_ok() throws Exception{
        CompilerTestHelper.launchCompilation("/semantics/localVarDecl/ok/when_local_var_diff_names_than_global_vars.b314", testFolder.newFile(), true, "localVarDecl: when_local_var_diff_names_than_global_vars");
    }

}