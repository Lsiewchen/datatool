package Invoker;

import Commands.*;

import java.util.Stack;

public class Invoker {

    private static Command[] cmdToExecute;

    public static void setCommandsForExecution(Command[] cmd){
        cmdToExecute = cmd;
    }

    public static void executeCommand(Stack<Command> history){
        for (Command command : cmdToExecute) {
            command.execute(history);
        }
    }
}
