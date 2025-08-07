package CustomExceptions;

public class Exceptions {
    // Index not Found (For update and delete)

    public class IndexNotFound extends Exception {
        IndexNotFound(String msg) {
            super(msg);
        }
    }

    // Too many arguments (For add and update)
    public static class InvalidPayload extends Exception {
        public InvalidPayload(String msg) {
            super(msg);
        }
    }


    // Empty List (For list)
    public class EmptyList extends Exception {
        EmptyList(String msg) {
            super(msg);
        }
    }


    // Empty history (For undo)
    public class NothingToUndo extends Exception {
        NothingToUndo(String msg) {
            super(msg);
        }
    }

    public static class InvalidEmailFormat extends Exception {
        public InvalidEmailFormat(String msg) {
            super(msg);
        }
    }
}

