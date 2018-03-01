package be.unamur.info.b314.compiler.main;

import org.junit.Test;
import org.junit.Rule;
import org.junit.rules.TemporaryFolder;
import org.junit.rules.TestRule;
import org.junit.rules.TestWatcher;
import org.junit.runner.Description;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class B314instructionsSyntaxTest {

    private static final Logger LOG = LoggerFactory.getLogger(B314instructionsSyntaxTest.class);

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
    // Serie instructions OK
    //
    @Test
    public void testinstructions_bool_array_constant_ok() throws Exception{
        CompilerTestHelper.launchCompilation("/syntax/instructions/ok/bool_array_constant.b314", testFolder.newFile(), true, "instructions: bool_array_constant");
    }

    @Test
    public void testinstructions_bool_array_right_expr_ok() throws Exception{
        CompilerTestHelper.launchCompilation("/syntax/instructions/ok/bool_array_right_expr.b314", testFolder.newFile(), true, "instructions: bool_array_right_expr");
    }

    @Test
    public void testinstructions_bool_constant_ok() throws Exception{
        CompilerTestHelper.launchCompilation("/syntax/instructions/ok/bool_constant.b314", testFolder.newFile(), true, "instructions: bool_constant");
    }

    @Test
    public void testinstructions_bool_right_expr_ok() throws Exception{
        CompilerTestHelper.launchCompilation("/syntax/instructions/ok/bool_right_expr.b314", testFolder.newFile(), true, "instructions: bool_right_expr");
    }

    @Test
    public void testinstructions_int_array_constant_ok() throws Exception{
        CompilerTestHelper.launchCompilation("/syntax/instructions/ok/int_array_constant.b314", testFolder.newFile(), true, "instructions: int_array_constant");
    }

    @Test
    public void testinstructions_int_array_right_expr_ok() throws Exception{
        CompilerTestHelper.launchCompilation("/syntax/instructions/ok/int_array_right_expr.b314", testFolder.newFile(), true, "instructions: int_array_right_expr");
    }

    @Test
    public void testinstructions_int_constant_ok() throws Exception{
        CompilerTestHelper.launchCompilation("/syntax/instructions/ok/int_constant.b314", testFolder.newFile(), true, "instructions: int_constant");
    }

    @Test
    public void testinstructions_int_right_expr_ok() throws Exception{
        CompilerTestHelper.launchCompilation("/syntax/instructions/ok/int_right_expr.b314", testFolder.newFile(), true, "instructions: int_right_expr");
    }

    @Test
    public void testinstructions_only_skip_ok() throws Exception{
        CompilerTestHelper.launchCompilation("/syntax/instructions/ok/only_skip.b314", testFolder.newFile(), true, "instructions: only_skip");
    }

    @Test
    public void testinstructions_square_array_constant_ok() throws Exception{
        CompilerTestHelper.launchCompilation("/syntax/instructions/ok/square_array_constant.b314", testFolder.newFile(), true, "instructions: square_array_constant");
    }

    @Test
    public void testinstructions_square_array_right_expr_ok() throws Exception{
        CompilerTestHelper.launchCompilation("/syntax/instructions/ok/square_array_right_expr.b314", testFolder.newFile(), true, "instructions: square_array_right_expr");
    }

    @Test
    public void testinstructions_square_constant_ok() throws Exception{
        CompilerTestHelper.launchCompilation("/syntax/instructions/ok/square_constant.b314", testFolder.newFile(), true, "instructions: square_constant");
    }

    @Test
    public void testinstructions_square_right_expr_ok() throws Exception{
        CompilerTestHelper.launchCompilation("/syntax/instructions/ok/square_right_expr.b314", testFolder.newFile(), true, "instructions: square_right_expr");
    }

    //
    // Serie instructions KO
    //
    @Test
    public void testinstructions_wrong_init_right_ko() throws Exception {
        CompilerTestHelper.launchCompilation("/syntax/instructions/ko/wrong_init_right.b314", testFolder.newFile(), false, "instructions: wrong_init_right");
    }

}