package be.unamur.info.b314.compiler.main;

import static org.hamcrest.Matchers.endsWith;
import static org.junit.Assert.assertThat;

import be.unamur.info.b314.compiler.B314Parser;
import be.unamur.info.b314.compiler.B314Parser.RootContext;
import be.unamur.info.b314.compiler.semantics.SymTableFiller;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.PrintStream;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.net.URISyntaxException;

/**
 * @author Xavier Devroey - xavier.devroey@unamur.be
 */
class CompilerTestHelper {


  /**
   * Launch compilation and check OK/KO output according to the given parameters.
   *
   * @param input The name of the input B314 file (in src/test/resources).
   * @param ok True if the compiler should print 'OK' on stderr for the given files.
   * @param message Message to print if the test failes.
   * @param outputFile Output file where PCode is written
   * @throws URISyntaxException If the given file does not exist in src/test/resources.
   * @throws IOException If the method fails to create ta temporary output file in testFolder.
   */
  public static void launchCompilation(String input, File outputFile, boolean ok, String message)
      throws URISyntaxException, IOException {
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
    assertThat(message, errContent.toString(),
        endsWith(expected)); // Check that the output ends with OK/KO
  }


  public static SymTableFiller getSymTable(String input) {
    try {
      File inputFile = new File(CompilerTestHelper.class.getResource(input).toURI());
      ByteArrayOutputStream errContent = new ByteArrayOutputStream();
      System.setErr(new PrintStream(errContent));

      File outputFile = File.createTempFile(Long.toString(System.currentTimeMillis()),".tmp");
      outputFile.deleteOnExit();

      Constructor mainCons = Main.class.getDeclaredConstructor();
      mainCons.setAccessible(true);
      Object mainInstance = mainCons.newInstance();

      // Set private field Main.inputFile
      Field fInputFile = Main.class.getDeclaredField("inputFile");
      fInputFile.setAccessible(true);
      fInputFile.set(mainInstance, inputFile);

      // Set private field Main.outputFile
      Field fOutputFile = Main.class.getDeclaredField("outputFile");
      fOutputFile.setAccessible(true);
      fOutputFile.set(mainInstance, outputFile);

      // Call Main.parseInputFile()
      Method methParse = Main.class.getDeclaredMethod("parse");
      methParse.setAccessible(true);
      RootContext tree = (RootContext) methParse.invoke(mainInstance);

      // Call Main.fillSymTable() and return the result
      Method methFillSymTable = Main.class.getDeclaredMethod("fillSymTable", B314Parser.RootContext.class);
      methFillSymTable.setAccessible(true);
      return(SymTableFiller) methFillSymTable.invoke(mainInstance, tree);

    } catch (Exception e) {
      System.err.println(e);
      return null;
    }
  }


}
