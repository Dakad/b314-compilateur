package be.unamur.info.b314.compiler.semantics.exception;




/**
 *
 */
public class SemanticException extends RuntimeException{

    public SemanticException(String message) {
        super(message);
    }

    public SemanticException(String message, Exception cause) {
        super(message, cause);
    }
    
}
