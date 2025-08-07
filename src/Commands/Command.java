package Commands;

import CustomExceptions.InvalidFormatException;
import CustomExceptions.InvalidPayloadException;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * This interface represents an executable command within a command pattern structure.
 * Each command maintains the ability to execute its logic and reverse it through undo (if needed).
 */
public interface Command {


    /**
     * Executes the command logic delegated by the receiver.
     * @throws InvalidPayloadException if the payload is not suitable with the command
     * @throws InvalidFormatException if the format in the email field of the payload is invalid
     */
    void execute() throws InvalidPayloadException, InvalidFormatException;

    /**
     * Reverts the effects of a previously executed command from the following list (Add, Delete and Update).
     */
    void undo();

    /**
     * Abstract method that informs whether the command is undoable or not
     * @return true if the command can be undone, false otherwise
     */
    boolean canUndo();

    /**
     * Abstract that informs whether the command has been executed.
     * Prevents illegal undo operations injected to the stack
     * @return true if the command has been executed, false otherwise
     */
    boolean isExecuted();

    /**
     * Converts the parameter, title, to titlecase e.g. (Titlecase)
     * @param title String to be converted
     * @return String in titlecase
     */
    static String convertTitleCase(String title) {
        return  title.substring(0, 1).toUpperCase() + title.substring(1).toLowerCase();
    }

    /**
     * Checks if the input, email, format is of a valid format
     * @param email String to be checked
     * @throws InvalidFormatException if the String provided does not fulfil the requirements as mentioned in error message
     */
    static void isValidFormat(String email) throws InvalidFormatException {
        Pattern pattern = Pattern.compile
                ("^(\\w+(?:[.-]?\\w+)*@[a-zA-Z0-9]+(?:[.-]?[a-zA-Z0-9]+)*\\.[a-z]{2,3}|\\w+)$");
        Matcher matcher = pattern.matcher(email);
        if (!matcher.find()) {
            throw new InvalidFormatException("Format is invalid. Please provide an email address or Latin letters (case " +
                    "insensitive), digits 0 to 9 and\n" +
                    "underscores");
        }
    }

    /**
     * Checks if the index provided is of a valid format
     * @param index the index to be checked
     * @throws InvalidPayloadException if the index provided is not of a valid format i.e. not integer
     */
    static void isValidIndexFormat(String index) throws InvalidPayloadException {
        Pattern pattern = Pattern.compile
                ("^\\d+$");
        Matcher matcher = pattern.matcher(index);
        if (!matcher.find()) {
            throw new InvalidPayloadException("Index is invalid.");
        }
    }

    /**
     * Method to clean whitespace characters in the payload if present
     * by adding an escape character in front of them and make them appear
     * as a literal group of characters (e.g. \n instead of a new line)
     * @param payload to be cleaned
     * @return payload that has its whitespace characters replaced with an escape character in front of them
     */
    static String sanitizePayload(String payload) {
        return payload.replace("\n", "\\n")
                .replace("\t", "\\t")
                .replace("\r", "\\r")
                .replace("\f", "\\f");
    }
}
