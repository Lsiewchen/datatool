package Commands;

import CustomExceptions.Exceptions.*;
import Receiver.Receiver;

/**
 * An implementation of the Command interface responsible for performing
 * a delete operation via the receiver. This command takes an index from
 * the payload, stores the deleted data for undo purposes, and removes
 * the corresponding entry from the receiver's datastore.
 */
public class DeleteCommand implements Command {

    /**
     * Variable containing the receiver responsible for handling operations.
     */
    private Receiver receiver;

    /**
     * Variable containing the index of the entry to be deleted.
     */
    private int index;

    /**
     * Variable containing the original data retrieved prior to deletion,
     * used to restore the entry during an undo operation.
     */
    private String oldData;

    /**
     * Variable used to contain the payload
     */
    private String payload;

    /**
     * Variable to indicate if the command has been executed or not
     * (used to prevent illegal undo situations)
     */
    private boolean isExecuted = false;

    /**
     * Constructor for DeleteCommand with specified receiver and payload.
     * Uses the payload to determine the target index for deletion.
     *
     * @param receiver the receiver responsible for executing the delete logic
     * @param payload a string representing the index to be deleted (1-based)
     */
    public DeleteCommand(Receiver receiver, String payload){
        this.receiver = receiver;
        this.payload = payload;
    }

    /**
     * Executes the delete command by retrieving the original data from the specified index,
     * and instructing the receiver to perform deletion.
     * @throws InvalidPayload if the index provided is of an incorrect format
     */
    @Override
    public void execute() throws InvalidPayload {
        Command.isValidIndexFormat(payload);
        this.index = Integer.parseInt(payload) - 1;
        this.oldData = receiver.retrieveLine(index); // stores data that was deleted
        receiver.delete(index);
        this.isExecuted = true;
        System.out.println("delete");
    }

    /**
     * Undoes the delete command by splitting the previously retrieved data
     * and reinserting it back into the receiver's datastore at the original index.
     */
    @Override
    public void undo() {
        String[] datas = oldData.split(" "); // prepare data to be added back
        receiver.add(index, datas[0], datas[1], datas[2]); // overloaded add method in receiver to insert at old index
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
