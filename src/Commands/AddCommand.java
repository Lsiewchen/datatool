package Commands;

import CustomExceptions.Exceptions.*;
import Receiver.Receiver;

/**
 * An implementation of the Command interface responsible for performing
 * an add operation via the receiver. This command processes a payload
 * into discrete data elements and stores them in the Array List via
 * the receiver.
 */
public class AddCommand implements Command {

    /**
     * Variable containing the receiver responsible for handling the add operation.
     */
    private Receiver receiver;
    /**
     * Variable containing the segments of input data from the payload and the actual payload.
     */
    private String data1, data2, data3, payload;

    /**
     * Variable to indicate if the command has been executed or not
     * (used to prevent illegal undo situations)
     */
    private boolean isExecuted = false;

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
     * Executes the add command by delegating the add action to the receiver with parsed data.
     * @throws InvalidPayload User inputs a payload that does fit for the Add command
     * @throws InvalidFormat User inputs an invalid input for data3 (Email field)
     */
    @Override
    public void execute() throws InvalidPayload, InvalidFormat {
        String[] datas = payload.split(" ");
        if (datas.length != 3) {
            throw new InvalidPayload("Incorrect payload format.");
        }
        this.data1 = Command.convertTitleCase(datas[0]);
        this.data2 = Command.convertTitleCase(datas[1]);
        this.data3 = datas[2].contains("@") ? datas[2] : Command.convertTitleCase(datas[2]);
        Command.isValidFormat(data3);
        receiver.add(data1, data2, data3);
        this.isExecuted = true;
        System.out.println("add");
    }

    /**
     * Undoes the add command by calling delete on the latest entry
     */
    @Override
    public void undo() {
        receiver.delete(receiver.getDataStoreSize()-1); // calls delete on last index
    }

    /**
     * Function that informs whether the command is undoable or not
     * @return true if the command can be undone, false otherwise
     */
    @Override
    public boolean canUndo() {
        return true;
    }

    /**
     * Function that informs whether the command has been executed.
     * Prevents illegal undo operations injected to the stack
     * @return true if the command has been executed, false otherwise
     */
    @Override
    public boolean isExecuted() {
        return isExecuted;
    }
}
