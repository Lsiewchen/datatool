package Commands;

import CustomExceptions.Exceptions.*;

import java.util.Stack;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * This interface represents an executable command within a command pattern structure.
 * It supports a command history stack to facilitate rollback of commands.
 * Each command maintains the ability to execute its logic and reverse it through undo.
 */
public interface Command {

    /**
     * Executes the command logic by delegating to the receiver and records itself in
     * the provided history stack (if needed - Add, Delete and Update).
     */
    void execute() throws InvalidPayload, InvalidEmailFormat;

    /**
     * Reverts the effects of a previously executed command from the following list (Add, Delete and Update).
     */
    void undo();

    static String convertTitleCase(String title) {
        return  title.substring(0, 1).toUpperCase() + title.substring(1);
    }

    static void isValidEmailFormat(String email) throws InvalidEmailFormat {
        Pattern pattern = Pattern.compile
                ("^(\\w+(?:[.-]?\\w+)*@[a-zA-Z0-9]+(?:[.-]?[a-zA-Z0-9]+)*\\.[a-z]{2,3}|\\w+)$");
        Matcher matcher = pattern.matcher(email);
        if (!matcher.find()) {
            throw new InvalidEmailFormat("Email format is invalid.");
        }
    }
}
