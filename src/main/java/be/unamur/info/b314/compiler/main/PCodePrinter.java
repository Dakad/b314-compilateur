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
        this.writer = new PrintWriter(outFile);
    }

    public PCodePrinter(String fileName) throws FileNotFoundException {
        this.writer = new PrintWriter(fileName);
    }

    public PCodePrinter(OutputStream out) {
        this.writer = new PrintWriter(out);
    }

    public void printLoadConstant(PCodeTypes type, int value) {
        this.writer.printf("ldc %s %d", type.representation, value).println();
    }

    public void printLoad(PCodeTypes type, int diff, int offset) {
        this.writer.printf("lod %s %d %d", type.representation, diff, offset).println();
    }

    public void printLoadAdress(PCodeTypes type, int diff, int offset) {
        this.writer.printf("lda %s %d %d", type.representation, diff, offset).println();
    }

    public void printIndexedFetch(PCodeTypes type) {
        this.writer.printf("ind %s", type.representation).println();
    }

    public void printStore(PCodeTypes type) {
        this.writer.printf("sto %s", type.representation).println();
    }

    public void printPop() {
        this.writer.println("pop");
    }

    public void printMarkStack(int diff) {
        this.writer.printf("mst %d", diff).println();
    }

    public void printCallUserProcedure(int paramCount, String name) {
        this.writer.printf("cup %d @%s", paramCount, name).println();
    }

    public void printSetStackPointer(int spValue) {
        this.writer.printf("ssp %d", spValue).println();
    }

    public void printReturnFromProcedure() {
        this.writer.println("retp");
    }

    public void printReturnFromFunction() {
        this.writer.println("retf");
    }

    public void printStop() {
        this.writer.println("stp");
    }

    public void printComments(String comment) {
        this.writer.printf("; %s", comment).println();
    }

    public void printDefineLabel(String label) {
        this.writer.printf("define @%s", label).println();
    }

    public void printUnconditionalJump(String label) {
        this.writer.printf("ujp @%s", label).println();
    }

    public void printFalseJump(String label) {
        this.writer.printf("fjp @%s", label).println();
    }

    public void printAdd(PCodeTypes type) {
        Preconditions.checkArgument(type.equals(PCodeTypes.Int) || type.equals(PCodeTypes.Adr));
        this.writer.printf("add %s", type.representation).println();
    }

    public void printSub(PCodeTypes type) {
        Preconditions.checkArgument(type.equals(PCodeTypes.Int) || type.equals(PCodeTypes.Adr));
        this.writer.printf("sub %s", type.representation).println();
    }

    public void printMul(PCodeTypes type) {
        Preconditions.checkArgument(type.equals(PCodeTypes.Int) || type.equals(PCodeTypes.Adr));
        this.writer.printf("mul %s", type.representation).println();
    }

    public void printDiv(PCodeTypes type) {
        Preconditions.checkArgument(type.equals(PCodeTypes.Int) || type.equals(PCodeTypes.Adr));
        this.writer.printf("div %s", type.representation).println();
    }

    public void printMod(PCodeTypes type) {
        Preconditions.checkArgument(type.equals(PCodeTypes.Int) || type.equals(PCodeTypes.Adr));
        this.writer.printf("mod %s", type.representation).println();
    }

    public void printEqualsValues(PCodeTypes type) {
        this.writer.printf("equ %s", type.representation).println();
    }

    public void printLess(PCodeTypes type) {
        Preconditions.checkArgument(type.equals(PCodeTypes.Int) || type.equals(PCodeTypes.Adr));
        this.writer.printf("les %s", type.representation).println();
    }

    public void printGreather(PCodeTypes type) {
        Preconditions.checkArgument(type.equals(PCodeTypes.Int) || type.equals(PCodeTypes.Adr));
        this.writer.printf("grt %s", type.representation).println();
    }

    public void printOr() {
        this.writer.println("or b");
    }

    public void printAnd() {
        this.writer.println("and b");
    }

    public void printNot() {
        this.writer.println("not b");
    }

    public void printRead() {
        this.writer.println("read");
    }

    public void printPrin() {
        this.writer.println("prin");
    }

    public void printCheck(int p, int q) {
        this.writer.printf("chk %d %d", p, q).println();
    }

    public void printIndexedAdressComputation(int q) {
        this.writer.printf("ixa %d", q).println();
    }

    public void flush() {
        this.writer.flush();
    }

    public void close() {
        this.writer.flush();
        this.writer.close();
    }

}
