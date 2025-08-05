package CustomExceptions;

public class Exceptions {
    // Index not Found (For update and delete)

    public class IndexNotFound extends Exception {
        IndexNotFound(String msg) {
            super(msg);
        }
    }

    /*
     *  if (index < 0 || index > dataStore.size()-1) {
     *   throw new IndexNotFound("Invalid index entered");
     * }
     * */

    // Too many arguments (For add and update)
    public static class InvalidPayload extends Exception {
        public InvalidPayload(String msg) {
            super(msg);
        }
    }

    /*
     * for add
     * if (datas.length > 3)
     *   throw new TooManyArguments("Too many arguments given");
     *
     * for update
     *
     * if (datas.length > 4)
     *   throw new TooManyArguments("Too many arguments given");
     * */

    // Empty List (For list)
    public class EmptyList extends Exception {
        EmptyList(String msg) {
            super(msg);
        }
    }

    /*
     * if (dataStore.size() == 0)
     *   throw new EmptyList("No entries in the registry, list is empty");
     * */


    // Empty history (For undo)
    public class NothingToUndo extends Exception {
        NothingToUndo(String msg) {
            super(msg);
        }
    }

    /*
     * try{
     *   history.pop().undo();
     * } catch (ArrayIndexOutOfBoundsException e) {
     *   System.out.println("Error! Nothing to undo.");
     * };
     * */

    public static class InvalidEmailFormat extends Exception {
        public InvalidEmailFormat(String msg) {
            super(msg);
        }
    }
}

