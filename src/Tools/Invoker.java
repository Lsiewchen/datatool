package Tools;

import Commands.*;

import java.util.Stack;

public class Invoker { //sc

    private static Command[] cmdToExecute;

    public static void setCommandsForExecution(Command[] cmd){
        cmdToExecute = cmd;
    }

    public static void executeCommand(Stack<Command> history){
        for (Command command : cmdToExecute){
//            history.push(command);
            command.execute();
        }

    }
}
