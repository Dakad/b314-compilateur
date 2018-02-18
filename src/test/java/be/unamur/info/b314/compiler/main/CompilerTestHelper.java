package be.unamur.info.b314.compiler.main;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.PrintStream;
import java.net.URISyntaxException;
import static org.hamcrest.Matchers.endsWith;
import static org.junit.Assert.assertThat;

/**
 *
 * @author Xavier Devroey - xavier.devroey@unamur.be
 */
class CompilerTestHelper {
    
    
    /**
     * Launch compilation and check OK/KO output according to the given parameters.
     *
     * @param input The name of the input B314 file (in src/test/resources).
     * @param ok True if the compiler should print 'OK' on stderr for the given
     * files.
     * @param message Message to print if the test failes.
     * @param outputFile Output file where PCode is written
     * @throws URISyntaxException If the given file does not exist in
     * src/test/resources.
     * @throws IOException If the method fails to create ta temporary output file in testFolder.
     */
    public static void launchCompilation(String input, File outputFile, boolean ok, String message) throws URISyntaxException, IOException {
        File inputFile = new File(CompilerTestHelper.class.getResource(input).toURI());
        ByteArrayOutputStream errContent = new ByteArrayOutputStream();
        System.setErr(new PrintStream(errContent));
        // Launch main method
        Main.main(new String[]{"-i", inputFile.getAbsolutePath(), "-o", outputFile.getAbsolutePath()});
        String expected;
        if (ok) {
            expected = String.format("OK%n"); // Using format to prevent EOL compatibility issues
        } else {
            expected = String.format("KO%n");
        }
        assertThat(message, errContent.toString(), endsWith(expected)); // Check that the output ends with OK/KO
    }

}
