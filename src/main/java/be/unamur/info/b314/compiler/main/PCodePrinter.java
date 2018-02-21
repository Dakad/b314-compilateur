package be.unamur.info.b314.compiler.main;

import com.google.common.base.Preconditions;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.OutputStream;
import java.io.PrintWriter;

/**
 * @author Xavier Devroey - xavier.devroey@unamur.be
 */
public class PCodePrinter {

    public enum PCodeTypes {
        Int('i'), Adr('a'), Bool('b');

        private final char representation;

        private PCodeTypes(char r) {
            representation = r;
        }

    }

    private final PrintWriter writer;

    public PCodePrinter(File outFile) throws FileNotFoundException {
        writer = new PrintWriter(outFile);
    }

    public PCodePrinter(String fileName) throws FileNotFoundException {
        writer = new PrintWriter(fileName);
    }

    public PCodePrinter(OutputStream out) {
        writer = new PrintWriter(out);
    }

    public void printLoadConstant(PCodeTypes type, int value) {
        writer.printf("ldc %s %d", type.representation, value).println();
    }

    public void printLoad(PCodeTypes type, int diff, int offset) {
        writer.printf("lod %s %d %d", type.representation, diff, offset).println();
    }

    public void printLoadAdress(PCodeTypes type, int diff, int offset) {
        writer.printf("lda %s %d %d", type.representation, diff, offset).println();
    }

    public void printIndexedFetch(PCodeTypes type) {
        writer.printf("ind %s", type.representation).println();
    }

    public void printStore(PCodeTypes type) {
        writer.printf("sto %s", type.representation).println();
    }

    public void printPop() {
        writer.println("pop");
    }

    public void printMarkStack(int diff) {
        writer.printf("mst %d", diff).println();
    }

    public void printCallUserProcedure(int paramCount, String name) {
        writer.printf("cup %d @%s", paramCount, name).println();
    }

    public void printSetStackPointer(int spValue) {
        writer.printf("ssp %d", spValue).println();
    }

    public void printReturnFromProcedure() {
        writer.println("retp");
    }

    public void printReturnFromFunction() {
        writer.println("retf");
    }

    public void printStop() {
        writer.println("stp");
    }

    public void printComments(String comment) {
        writer.printf("; %s", comment).println();
    }

    public void printDefineLabel(String label) {
        writer.printf("define @%s", label).println();
    }

    public void printUnconditionalJump(String label) {
        writer.printf("ujp @%s", label).println();
    }

    public void printFalseJump(String label) {
        writer.printf("fjp @%s", label).println();
    }

    public void printAdd(PCodeTypes type) {
        Preconditions.checkArgument(type.equals(PCodeTypes.Int) || type.equals(PCodeTypes.Adr));
        writer.printf("add %s", type.representation).println();
    }

    public void printSub(PCodeTypes type) {
        Preconditions.checkArgument(type.equals(PCodeTypes.Int) || type.equals(PCodeTypes.Adr));
        writer.printf("sub %s", type.representation).println();
    }

    public void printMul(PCodeTypes type) {
        Preconditions.checkArgument(type.equals(PCodeTypes.Int) || type.equals(PCodeTypes.Adr));
        writer.printf("mul %s", type.representation).println();
    }

    public void printDiv(PCodeTypes type) {
        Preconditions.checkArgument(type.equals(PCodeTypes.Int) || type.equals(PCodeTypes.Adr));
        writer.printf("div %s", type.representation).println();
    }

    public void printMod(PCodeTypes type) {
        Preconditions.checkArgument(type.equals(PCodeTypes.Int) || type.equals(PCodeTypes.Adr));
        writer.printf("mod %s", type.representation).println();
    }

    public void printEqualsValues(PCodeTypes type) {
        writer.printf("equ %s", type.representation).println();
    }

    public void printLess(PCodeTypes type) {
        Preconditions.checkArgument(type.equals(PCodeTypes.Int) || type.equals(PCodeTypes.Adr));
        writer.printf("les %s", type.representation).println();
    }

    public void printGreather(PCodeTypes type) {
        Preconditions.checkArgument(type.equals(PCodeTypes.Int) || type.equals(PCodeTypes.Adr));
        writer.printf("grt %s", type.representation).println();
    }

    public void printOr() {
        writer.println("or b");
    }

    public void printAnd() {
        writer.println("and b");
    }

    public void printNot() {
        writer.println("not b");
    }

    public void printRead() {
        writer.println("read");
    }

    public void printPrin() {
        writer.println("prin");
    }

    public void printCheck(int p, int q) {
        writer.printf("chk %d %d", p, q).println();
    }

    public void printIndexedAdressComputation(int q) {
        writer.printf("ixa %d", q).println();
    }

    public void flush() {
        writer.flush();
    }

    public void close() {
        writer.flush();
        writer.close();
    }

}
