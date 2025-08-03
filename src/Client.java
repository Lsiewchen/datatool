import Commands.AddCommand;
import Commands.Command;
import Commands.UpdateCommand;
import Tools.Invoker;
import Tools.Receiver;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class Client {
    private static Stack<Command> history = new Stack<Command>();

    //Add
    /*public static void main(String[] args) {
        Receiver receiver = new Tools.Receiver();
        //Test Local
        Command commandAdd1 = new AddCommand(receiver, "John", "Doe", "1abc-def.ghi@example.com"); //pass
        Command commandAdd2 = new AddCommand(receiver, "John", "doe", "2abc-_def@example.com"); //pass
        Command commandAdd3 = new AddCommand(receiver, "John%", "Doe", "3abc-def.ghi@example.com"); //pass
        Command commandAdd4 = new AddCommand(receiver, "John", "Doe#", "_4abc-def.ghi@example.com"); //pass
        Command commandAdd5 = new AddCommand(receiver, "John", "Doe", "5abc.-def@example.com"); //fail
        Command commandAdd6 = new AddCommand(receiver, "John", "Doe", "-6abcdef@example.com"); //fail
        Command commandAdd7 = new AddCommand(receiver, "John", "Doe", "7abc%def.@example.com"); //fail
        //Test Domain
        Command commandAdd8 = new AddCommand(receiver, "7ohn", "Doe", "8abcdef@example000.co"); //pass
        Command commandAdd9 = new AddCommand(receiver, "John", "003", "9abcdef@exam-ple.co"); //pass
        Command commandAdd10 = new AddCommand(receiver, "John", "Doe", "10abcdef@exam_ple.co"); //fail
        Command commandAdd11 = new AddCommand(receiver, "John", "Doe", "11abcdef@-example.co"); //fail

        Command[] commands = {commandAdd1, commandAdd2, commandAdd3, commandAdd4,
                commandAdd5, commandAdd6, commandAdd7, commandAdd8, commandAdd9,
                commandAdd10, commandAdd11};

        Invoker.setCommandsForExecution(commands);
        Invoker.executeCommand(history);
    }*/

    //Update
    /*public static void main(String[] args) {
        Receiver receiver = new Tools.Receiver();

        Command commandUpdate1 = new UpdateCommand(receiver, 1,"John", "update1", "1abc-def.ghi@example.com"); //pass
        Command commandUpdate2 = new UpdateCommand(receiver, 2,"John", "update2"); //pass
        Command commandUpdate3 = new UpdateCommand(receiver, 3,"update3"); //pass
        Command commandUpdate4 = new UpdateCommand(receiver, 5,"7ohn", "Doe", "8abc..def@example000.co"); //fail
        Command commandUpdate5 = new UpdateCommand(receiver, 7,"John", "Doe#", "_4abc-def.ghi@example.com"); //fail
        Command commandUpdate6 = new UpdateCommand(receiver, 6,"Update6", "003", "9abcdef@exam-ple.co"); //pass

        Command[] commands = {commandUpdate1, commandUpdate2, commandUpdate3, commandUpdate4, commandUpdate5, commandUpdate6};

        Invoker.setCommandsForExecution(commands);
        Invoker.executeCommand(history);
    }*/


}