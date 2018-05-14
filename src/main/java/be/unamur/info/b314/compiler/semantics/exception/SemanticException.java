package be.unamur.info.b314.compiler.semantics.exception;




/**
 * @overview A SemanticException is a abstract representation of a semantic error during the parsing.
 * A SemanticException is mutable.
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
