package CustomExceptions;

/**
 * Container class for custom exceptions used across command operations.
 * Each nested class represents a specific failure mode encountered during
 * command execution.
 */

public class Exceptions {

    /**
     * Thrown when a command receives too many arguments.
     */
    public static class InvalidPayload extends Exception {
        public InvalidPayload(String msg) {
            super(msg);
        }
    }

    /**
     * Thrown when input data does not conform to the expected format.
     */
    public static class InvalidFormat extends Exception {
        public InvalidFormat(String msg) {
            super(msg);
        }
    }
}

