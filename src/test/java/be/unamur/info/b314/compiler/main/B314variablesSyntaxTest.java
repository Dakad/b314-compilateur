package be.unamur.info.b314.compiler.main;

import org.junit.Test;
import org.junit.Rule;
import org.junit.rules.TemporaryFolder;
import org.junit.rules.TestRule;
import org.junit.rules.TestWatcher;
import org.junit.runner.Description;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class B314variablesSyntaxTest {

    private static final Logger LOG = LoggerFactory.getLogger(B314variablesSyntaxTest.class);

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
    // Serie variables OK
    //
    @Test
    public void testvariables_boolean_ok() throws Exception{
        CompilerTestHelper.launchCompilation("/syntax/variables/ok/boolean.b314", testFolder.newFile(), true, "variables: boolean");
    }

    @Test
    public void testvariables_integer_ok() throws Exception{
        CompilerTestHelper.launchCompilation("/syntax/variables/ok/integer.b314", testFolder.newFile(), true, "variables: integer");
    }

    @Test
    public void testvariables_square_ok() throws Exception{
        CompilerTestHelper.launchCompilation("/syntax/variables/ok/square.b314", testFolder.newFile(), true, "variables: square");
    }

    //
    // Serie variables KO
    //
    @Test
    public void testvariables_arena_ko() throws Exception {
        CompilerTestHelper.launchCompilation("/syntax/variables/ko/arena.b314", testFolder.newFile(), false, "variables: arena");
    }

    @Test
    public void testvariables_bool_ko() throws Exception {
        CompilerTestHelper.launchCompilation("/syntax/variables/ko/bool.b314", testFolder.newFile(), false, "variables: bool");
    }

    @Test
    public void testvariables_int_ko() throws Exception {
        CompilerTestHelper.launchCompilation("/syntax/variables/ko/int.b314", testFolder.newFile(), false, "variables: int");
    }

    @Test
    public void testvariables_shoot_ko() throws Exception {
        CompilerTestHelper.launchCompilation("/syntax/variables/ko/shoot.b314", testFolder.newFile(), false, "variables: shoot");
    }

    @Test
    public void testvariables_use_ko() throws Exception {
        CompilerTestHelper.launchCompilation("/syntax/variables/ko/use.b314", testFolder.newFile(), false, "variables: use");
    }

}