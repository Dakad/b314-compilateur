package be.unamur.info.b314.compiler.main;

import static com.google.common.base.Preconditions.checkArgument;

import be.unamur.info.b314.compiler.B314Lexer;
import be.unamur.info.b314.compiler.B314Parser;
import be.unamur.info.b314.compiler.semantics.exception.ParsingException;
import be.unamur.info.b314.compiler.semantics.SymTableFiller;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.RecognitionException;
import org.antlr.v4.runtime.misc.ParseCancellationException;
import org.antlr.v4.runtime.tree.ParseTreeWalker;
import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.Option;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author Xavier Devroey - xavier.devroey@unamur.be
 */
public class Main {

    private static final Logger LOG = LoggerFactory.getLogger(Main.class);

    private static final String NAME = "b314-compiler";
    private static final String HELP = "h";
    private static final String INPUT = "i";
    private static final String OUTPUT = "o";

    /**
     * Main method launched when starting compiler jar file.
     *
     * @param args Command line arguments.
     */
    public static void main(String[] args) {
        Main main = new Main();
        CommandLineParser parser = new DefaultParser();
        CommandLine line = null;
        try {
            line = parser.parse(main.options, args);
        } catch (ParseException ex) {
            LOG.error("Error while parsing command line!", ex);
            main.printHelpMessage();
        }
        // If help is requested, print help message and exit.
        if (line != null) {
            if (line.hasOption(HELP)) {
                main.printHelpMessage();
            } else {
                // Else start compilation
                try {
                    main.initialise(line);
                    main.compile(); // Call compile method (to be completed)
                    System.err.println("OK"); // Print OK on stderr
                } catch (Exception e) {
                    LOG.error("Exception occured during compilation!", e);
                    System.err.println("KO"); // Print KO on stderr if a problem occured
                }
            }
        }
    }

    /**
     * The command line options.
     */
    private final Options options;

    /**
     * The input B314 file.
     */
    private File inputFile;

    /**
     * The output PCode file.
     */
    private File outputFile;

  /**
   * The parser.
   */
  private B314Parser parser;
    
    private Main() {
        // Create command line options
        options = new Options();
        options.addOption(Option.builder(HELP)
                .desc("Prints this help message.")
                .build());

        options.addOption(Option.builder(INPUT)
                .desc("The B314 input file.")
                .hasArg()
                .build());

        options.addOption(Option.builder(OUTPUT)
                .desc("The PCOde output file.")
                .hasArg()
                .build());
    }

    /**
     * Prints help message with this options.
     */
    private void printHelpMessage() {
        HelpFormatter formatter = new HelpFormatter();
        formatter.setWidth(128);
        formatter.printHelp(String.format("java -jar %s.jar -%s | %s %s",
                NAME, HELP, INPUT, OUTPUT), options);
    }

    /**
     * Initialise the input compiler using the given input line.
     *
     * @throws Exception If one of the three required arguments is not provided.
     */
    private void initialise(CommandLine line) throws Exception {
        LOG.debug("Initialisation");
        // Check that the arguments are there
        if (!line.hasOption(INPUT)) {
            throw new ParseException(String.format("Option %s is mandatory!", INPUT));
        } else if (!line.hasOption(OUTPUT)) {
            throw new ParseException(String.format("Option %s is mandatory!", OUTPUT));
        }
        // Get given files and check they exist
        inputFile = new File(line.getOptionValue(INPUT));
        checkArgument(inputFile.exists() && inputFile.isFile(), "File %s not found!", inputFile.getName());
        LOG.debug("Input file set to {}", inputFile.getPath());

        outputFile = new File(line.getOptionValue(OUTPUT));
        if(!outputFile.exists()){
            outputFile.createNewFile();
        }
        checkArgument(outputFile.exists() && outputFile.isFile(), "File %s not created!", outputFile.getName());
        LOG.debug("Output file set to {}", outputFile.getPath());

        LOG.debug("Initialisation: done");
    }

    
    /**
     * Compiler Methods, this is where the MAGIC happens !!! \o/
     */
    private void compile() throws IOException, ParsingException {
        // Get AST : Abstract Syntax Tree
        LOG.debug("Parsing input");
        B314Parser.RootContext tree = parse(new ANTLRInputStream(new FileInputStream(inputFile)));
        LOG.debug("Parsing input: done");
        LOG.debug("AST is {}", tree.toStringTree(parser));

        // Build symbol table
        LOG.debug("Building symbol table");
        SymTableFiller symTable = fillSymTable(tree);
        LOG.debug("Building symbol table: done");

        // Print PCode
        LOG.debug("Printing PCode");
        printPCode(tree, symTable);
        LOG.debug("Printing PCode: done");
    }

    /**
     * Builds the abstract syntax tree from input.
     */
    private B314Parser.RootContext parse(ANTLRInputStream input) throws ParseCancellationException, ParsingException {
        // Create the token stream
        CommonTokenStream tokens = new CommonTokenStream(new B314Lexer(input));
        // Intialise parser
        this.parser = new B314Parser(tokens);
        // Set error listener to adoc implementation
        this.parser.removeErrorListeners();
        MyConsoleErrorListener errorListener = new MyConsoleErrorListener();
        parser.addErrorListener(errorListener);
        // Launch parsing
        B314Parser.RootContext tree;
        try {
            tree = parser.root();
        } catch (RecognitionException e) {
            throw new ParsingException("Error while retrieving parsing tree!", e);
        }
        if (errorListener.errorHasBeenReported()) {
            throw new ParsingException("Error while parsing input!");
        }
        return tree;
    }

    /**
     * Builds symbol table from AST.
     */
    private SymTableFiller fillSymTable(B314Parser.RootContext tree) {
        SymTableFiller filler = new SymTableFiller();
        ParseTreeWalker walker = new ParseTreeWalker();
        walker.walk(filler, tree);
        return filler;
    }

    /**
     * Print PCode from AST and symtable.
     */
    private void printPCode(B314Parser.RootContext tree, SymTableFiller symTable) throws FileNotFoundException {
        PCodePrinter printer = new PCodePrinter(outputFile);
        PCodeVisitor visitor = new PCodeVisitor(symTable, printer);
        tree.accept(visitor);
        printer.flush();
        printer.close();
    }

}
