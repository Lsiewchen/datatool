package Commands;

import CustomExceptions.Exceptions.*;
import Receiver.Receiver;

import java.util.Stack;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * An implementation of the Command interface responsible for performing
 * an add operation via the receiver. This command processes a payload
 * into discrete data elements and stores them. When executed, it adds
 * the data to the receiver and registers itself in the command history
 * for future undo capability.
 */
public class AddCommand implements Command {

    /**
     * Variable containing the receiver responsible for handling the add operation.
     */
    private Receiver receiver;
    /**
     * Variable containing the segments of input data from the payload.
     */
    private String data1, data2, data3, payload;

    /**
     * Constructor for AddCommand with specified receiver and payload string.
     * Splits the input payload into three distinct data components used in the add operation.
     *
     * @param receiver the receiver responsible for executing this command’s logic
     * @param payload the raw input string containing space-separated values to be parsed
     */
    public AddCommand(Receiver receiver, String payload) {
        this.receiver = receiver;
        this.payload = payload;
    }

    /**
     * Executes the add command by pushing itself onto the command history stack
     * and delegating the add action to the receiver with parsed data.
     *
     * @param history the command history stack that logs this command
     *                for future undo reference
     */
    @Override
    public void execute(Stack<Command> history) {
//        if (!isValidEmailFormat(data3)) {
//            return;
//        }
//        receiver.add(data1, data2, data3);
//        history.push(this);
//        System.out.println("add");
        try{
            String[] datas =  payload.split(" ");
            if (datas.length != 3)
                throw new InvalidPayload("Incorrect payload!");
            this.data1 = convertTitleCase(datas[0]);
            this.data2 = convertTitleCase(datas[1]);
            this.data3 = datas[2];

            try{
                isValidEmailFormat(data3);
                receiver.add(data1, data2, data3);
                history.push(this);
                System.out.println("add");
            } catch (InvalidEmailFormat e) {
                System.out.println(e.getMessage());
            }

        } catch (InvalidPayload e) {
            System.out.println(e.getMessage());
        }

    }

    /**
     * Undoes the add command by deleting the most recently added entry
     * from the receiver’s data store, effectively reversing the add operation.
     */
    @Override
    public void undo() {
        receiver.delete(receiver.getDataStoreSize()-1); // calls delete on last index
    }

    private String convertTitleCase(String title) {
        return  title.substring(0, 1).toUpperCase() + title.substring(1);
    }

    private boolean isValidEmailFormat(String email) throws InvalidEmailFormat {
        Pattern pattern = Pattern.compile
                ("^\\w+(?:[.-]?\\w+)*@[a-zA-Z0-9]+(?:[.-]?[a-zA-Z0-9]+)*\\.[a-z]{2,3}$");
        Matcher matcher = pattern.matcher(email);

        if (!matcher.find()) {
            throw new InvalidEmailFormat("Email is in an invalid format.");
        }

        return matcher.find();
    }
}
