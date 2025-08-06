package Invoker;

import Commands.*;
import CustomExceptions.Exceptions.*;

import java.util.EmptyStackException;
import java.util.Stack;

public class Invoker {

    private static Command[] cmdToExecute;

    public static void setCommandsForExecution(Command[] cmd){
        cmdToExecute = cmd;
    }

    public static void executeCommand(Stack<Command> history) {
        for (Command command : cmdToExecute) {
            try {
                command.execute();
                if (command.canUndo()) {
                    history.push(command);
                }
            } catch (InvalidPayload | InvalidEmailFormat e) {
                System.out.println(e.getMessage());
            } catch (EmptyStackException e) {
                System.out.println("Error: Nothing to undo.");
            } catch (IndexOutOfBoundsException e) {
                System.out.println("Error: Invalid index entered.");
            }
        }
    }
}
