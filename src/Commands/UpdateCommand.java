package Commands;

import CustomExceptions.Exceptions.*;
import Receiver.Receiver;

import java.util.Stack;

/**
 * An implementation of the Command interface responsible for performing
 * update operations on a specified index via the receiver. It also stores
 * the previous state of the updated entry to support undo functionality.
 */
public class UpdateCommand implements Command {

    /**
     * Variable containing the index of the entry to be updated.
     */
    private int index;

    /**
     * Variable containing the receiver responsible for executing the update logic.
     */
    private Receiver receiver;

    /**
     * Variable containing the incoming payload and the original data at the
     * specified index, used for undoing the update.
     */
    private String payload, oldData;

    /**
     * Variable containing the segments of input data from the payload.
     */
    private String data1, data2, data3;

    /**
     * Variable to indicate if the command has been executed or not
     * (used to prevent illegal undo situations)
     */
    private boolean isExecuted = false;

    /**
     * Constructor for UpdateCommand with specified receiver and payload string.
     * Extracts the index and data segments required for update from payload.
     *
     * @param receiver the receiver responsible for handling update operations
     * @param payload a string containing the index (1-based) and space-separated data elements
     */
    public UpdateCommand(Receiver receiver, String payload) {
        this.receiver = receiver;
        this.payload = Command.sanitizePayload(payload);
    }

    /**
     * Executes the update command by saving the current data and instructing the receiver
     * to perform the update operation with the parsed data.
     * @throws InvalidPayload if the provided payload has more inputs than expected
     * @throws InvalidFormat if the 4th entry in the payload i.e. email field is invalid
     */
    @Override
    public void execute() throws InvalidPayload, InvalidFormat {
        String[] datas =  payload.split(" "); // splitting of payload into individual segments

        if (datas.length > 4) { // ensure that the payload is valid, reject payloads that are too big
            throw new InvalidPayload("Incorrect payload!");
        }

        Command.isValidIndexFormat(datas[0]);
        this.index = Integer.parseInt(datas[0]) - 1; // changing from 1-based to zero-based
        this.oldData = receiver.retrieveLine(index); // retrieve index using the receiver method

        // Titlecase conversion for relevant data
        this.data1 = Command.convertTitleCase(datas[1]);
        this.data2 = datas.length > 2 ? Command.convertTitleCase(datas[2]) : this.data2;
        this.data3 = datas.length > 3 ?
                (datas[3].contains("@") ? datas[3] : Command.convertTitleCase(datas[3]))
                : this.data3;

        // Check if 4th entry in payload is valid format
        if (data3 != null) {
            Command.isValidFormat(data3);
        }

        // run update command if no exceptions caught above
        receiver.update(index, data1, data2, data3);

        // command is executed, change executed status to true
        this.isExecuted = true;
        System.out.println("update");
    }

    /**
     * Undoes the update command by restoring the previously saved data
     * to its original index in the receiver’s datastore.
     */
    @Override
    public void undo() {
        String[] datas = oldData.split(" ");
        receiver.update(index, datas[0], datas[1], datas[2]);
    }

    /**
     * This function returns true if the command is undoable if needed
     * @return boolean value of true if the command is undoable
     */
    @Override
    public boolean canUndo() {
        return true;
    }

    /**
     * This function returns true if the command has been executed if needed.
     * Used in case of illegal undo situations
     * @return boolean value of true if the command has been executed and false otherwise.
     */
    @Override
    public boolean isExecuted() {
        return isExecuted;
    }
}


