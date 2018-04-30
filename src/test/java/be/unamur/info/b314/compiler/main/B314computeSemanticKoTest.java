package be.unamur.info.b314.compiler.main;

import static org.hamcrest.Matchers.instanceOf;
import static org.hamcrest.Matchers.notNullValue;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.fail;

import be.unamur.info.b314.compiler.semantics.exception.MissingRpar;
import be.unamur.info.b314.compiler.semantics.exception.BadWritteCompute;

import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;
import org.junit.rules.TestRule;
import org.junit.rules.TestWatcher;
import org.junit.runner.Description;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class B314computeSemanticKoTest {

    private static final Logger LOG = LoggerFactory.getLogger(B314computeSemanticKoTest.class);

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
    // Serie compute KO
    //
    @Test
    public void testcompute_missing_rpar_in_compute_ko() {
        try {
            CompilerTestHelper.getSymTable("/semantics/compute/ko/missing_rpar_in_compute.b314");
            //fail("[Unthrowed] This .b314 missing rpar");
        } catch (RuntimeException e) {
            assertThat("Incorrect type of Exception throwned", e,
                instanceOf(MissingRpar.class));
            assertThat("Must contain a detailed msg of the error", e.getMessage(), notNullValue());
        }
    }

    @Test
    public void testcompute_wrong_compute_ko() {
        try {
            CompilerTestHelper.getSymTable("/semantics/compute/ko/wrong_compute.b314");
            //fail("[Unthrowed] this .b314 wrong way to write compute");
        } catch (RuntimeException e) {
            assertThat("Incorrect type of Exception thowned", e,
                instanceOf(BadWritteCompute.class));
            assertThat("Must contain a detailed msg of the error", e.getMessage(), notNullValue());
        }
    }

    @Test
    public void testcompute_wrong_compute2_ko() {
        try {
            CompilerTestHelper.getSymTable("/semantics/compute/ko/wrong_compute2.b314");
            //fail("[Unthrowed] this .b314 wrong way to write compute");
        } catch (RuntimeException e) {
            assertThat("Incorrect type of Exception thowned", e,
                instanceOf(BadWritteCompute.class));
            assertThat("Must contain a detailed msg of the error", e.getMessage(), notNullValue());
        }
    }

}