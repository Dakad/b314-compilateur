package be.unamur.info.b314.compiler.main;

import static org.hamcrest.Matchers.instanceOf;
import static org.hamcrest.Matchers.notNullValue;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.fail;

import be.unamur.info.b314.compiler.semantics.exception.NotBooleanCondition;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;
import org.junit.rules.TestRule;
import org.junit.rules.TestWatcher;
import org.junit.runner.Description;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class B314instr_whileSemanticKoTest {

    private static final Logger LOG = LoggerFactory.getLogger(B314instr_whileSemanticKoTest.class);

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
    // Serie instr_if_then_else OK
    //

    @Test
    public void testinstr_while_wrong_expr_type_ko() {
        String[] types = {"integer_"};
        String[] b314Files = {"array", "array_op", "op", "var"};
        String file = "";

        for (String type : types) {
            for (String name : b314Files) {
                try {
                    file = "/semantics/instr_while/ko/cond_" + type + name + ".b314";
                    CompilerTestHelper.getSymTable(file);
                    fail("[Unthrowed] This .b314 is invalid. Should have thrown an Exception.\n" + file);
                } catch (RuntimeException e) {
                    assertThat("Incorrect type of Exception throwned\n" + file, e,
                        instanceOf(NotBooleanCondition.class));
                    assertThat("Must contain a detailed msg of the error", e.getMessage(), notNullValue());
                }
            }
        }
    }


}