package be.unamur.info.b314.compiler.semantics.exception;




/**
 *
 * @author Xavier Devroey - xavier.devroey@unamur.be
 */
public class SymbolNotFoundException extends Exception{

    public SymbolNotFoundException(String message) {
        super(message);
    }

    public SymbolNotFoundException(String message, Exception cause) {
        super(message, cause);
    }
    
}
