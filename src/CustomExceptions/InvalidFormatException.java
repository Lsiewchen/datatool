package CustomExceptions;

/**
 * Custom exception used across command operations.
 * Thrown when input data does not conform to the expected format.
 */
public class InvalidFormatException extends Exception {
    public InvalidFormatException(String msg) {
        super(msg);
    }
}
