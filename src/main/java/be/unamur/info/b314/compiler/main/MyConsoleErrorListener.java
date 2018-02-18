
package be.unamur.info.b314.compiler.main;

import org.antlr.v4.runtime.ConsoleErrorListener;
import org.antlr.v4.runtime.RecognitionException;
import org.antlr.v4.runtime.Recognizer;

/**
 *
 * @author Xavier Devroey - xavier.devroey@unamur.be
 */
public class MyConsoleErrorListener extends ConsoleErrorListener {

    private boolean errorReported;

    @Override
    public void syntaxError(Recognizer<?, ?> recognizer, Object offendingSymbol, int line, int charPositionInLine, String msg, RecognitionException e) {
        errorReported = true;
        super.syntaxError(recognizer, offendingSymbol, line, charPositionInLine, msg, e); 
    }
    
    public boolean errorHasBeenReported(){
        return errorReported;
    }
}
