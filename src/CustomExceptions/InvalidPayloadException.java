package CustomExceptions;

/**
 * Custom exception used across command operations.
 * Thrown when a command receives too many arguments.
 */
public class InvalidPayloadException extends Exception {
    public InvalidPayloadException(String msg) {
        super(msg);
    }
}
