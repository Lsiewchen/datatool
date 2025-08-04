package Commands;

import Receiver.Receiver;

import java.util.Stack;

/**
 * An implementation of the Command interface responsible for performing
 * update operations on a specified index via the receiver. It also stores
 * the previous state of the updated entry to support undo functionality.
 */
public class UpdateCommand implements Command {

    /**
     * Variable containing the index of the entry to be updated (zero-based).
     */
    private int index;

    /**
     * Variable containing the receiver responsible for executing the update logic.
     */
    private Receiver receiver;

    /**
     * Variable containing the segments of input data from the payload.
     */
    private String data1, data2, data3;

    /**
     * Variable containing the original data at the specified index,
     * used for undoing the update.
     */
    private String oldData;

    /**
     * Constructor for UpdateCommand with specified receiver and payload string.
     * Extracts the index and data segments required for update from payload.
     *
     * @param receiver the receiver responsible for handling update operations
     * @param payload a string containing the index (1-based) and space-separated data elements
     */
    public UpdateCommand(Receiver receiver, String payload) {
        this.receiver = receiver;
        String[] datas =  payload.split(" ");
        this.index = Integer.parseInt(datas[0]) - 1;
        this.data1 = datas[1];

        if (datas.length > 2) {
            this.data2 = datas[2];
        }

        if (datas.length > 3) {
            this.data3 = datas[3];
        }
    }

    /**
     * Executes the update command by saving the current data,
     * pushing this command to the history stack, and instructing the receiver
     * to perform the update operation with the parsed data.
     *
     * @param history the command history stack for tracking commands and enabling undo
     */
    @Override
    public void execute(Stack<Command> history) {
        history.push(this);
        this.oldData = receiver.retrieveLine(index);
        receiver.update(index, data1, data2, data3);
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
}
