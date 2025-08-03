package Tools;

import Commands.Command;

import java.util.Stack;

public class Invoker { //sc

    private static Command[] cmdToExecute;

    public static void setCommandsForExecution(Command[] cmd){
        cmdToExecute = cmd;
    }

    public static void executeCommand(Stack<Command> history){
        for (Command command : cmdToExecute){
            command.execute();
        }
    }
}
