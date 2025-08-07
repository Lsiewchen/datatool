package Invoker;

import Commands.*;
import CustomExceptions.InvalidFormatException;
import CustomExceptions.InvalidPayloadException;

import java.util.EmptyStackException;
import java.util.Stack;

/**
 * The invoker class is in charge of issuing request(s) by calling execute on the relevant commands.
 * If the command at hand is undoable, it will be added to the history stack.
 */
public class Invoker {

    /**
     * Variable to contain the commands to be executed
     */
    private static Command[] cmdToExecute;

    /**
     * Setter for the cmdToExecute variable
     * @param cmd commands to be executed
     */
    public static void setCommandsForExecution(Command[] cmd){
        cmdToExecute = cmd;
    }

    /**
     * Method to execute the commands in history. If the commands are undoable, stores the command to the stack.
     * In charge of catching errors that are propagated by the command execution.
     * @param history the stack of commands that are executed (should not contain commands that cannot be undone
     *                unless done by forced injection in Main/Driver class)
     */
    public static void executeCommand(Stack<Command> history) {
        for (Command command : cmdToExecute) {
            try {
                command.execute();
                if (command.canUndo()) {
                    history.push(command);
                }
            } catch (InvalidPayloadException | InvalidFormatException e) {
                System.out.println(e.getMessage());
            } catch (EmptyStackException e) {
                System.out.println("Error: Nothing to undo.");
            } catch (IndexOutOfBoundsException e) {
                System.out.println("Error: Invalid index entered.");
            }
        }
    }
}
