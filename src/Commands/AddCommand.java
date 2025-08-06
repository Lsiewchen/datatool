package Commands;

import CustomExceptions.Exceptions.*;
import Receiver.Receiver;

import java.util.Stack;

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
     * @param payload  the raw input string containing space-separated values to be parsed
     */
    public AddCommand(Receiver receiver, String payload) {
        this.receiver = receiver;
        this.payload = Command.sanitizePayload(payload);
    }

    /**
     * Executes the add command by pushing itself onto the command history stack
     * and delegating the add action to the receiver with parsed data.
     */
    @Override
    public void execute() throws InvalidPayload, InvalidEmailFormat {
        String[] datas = payload.split(" ");
        if (datas.length != 3) {
            throw new InvalidPayload("Incorrect payload format.");
        }
        this.data1 = Command.convertTitleCase(datas[0]);
        this.data2 = Command.convertTitleCase(datas[1]);
        this.data3 = datas[2].contains("@") ? datas[2] : Command.convertTitleCase(datas[2]);
        Command.isValidEmailFormat(data3);
        receiver.add(data1, data2, data3);
        System.out.println("add");
    }

    @Override
    public void undo() {
        receiver.delete(receiver.getDataStoreSize()-1); // calls delete on last index
    }

    @Override
    public boolean canUndo() {
        return true;
    }
}
