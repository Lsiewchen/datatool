import Commands.AddCommand;
import Commands.Command;
import Tools.Invoker;
import Tools.Receiver;

import java.util.ArrayList;
import java.util.Stack;

public class Client {
    private static Stack<Command> history = new Stack<Command>();
    public static void main(String[] args) {
        Receiver receiver = new Tools.Receiver();
        Command command = new AddCommand(receiver, new ArrayList<>());

        Command[] commands = new Command[]{};
        Invoker.setCommandsForExecution(commands);
        Invoker.executeCommand(history);
    }
}