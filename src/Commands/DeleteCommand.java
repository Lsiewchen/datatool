package Commands;

import Receiver.Receiver;

import java.util.Stack;

/**
 * An implementation of the Command interface responsible for performing
 * a delete operation via the receiver. This command takes an index from
 * the payload, stores the deleted data for undo purposes, and removes
 * the corresponding entry from the receiver's datastore.
 */
public class DeleteCommand implements Command {

    /**
     * Variable containing the receiver responsible for handling delete and add operations.
     */
    private Receiver receiver;

    /**
     * Variable containing the zero-based index of the entry to be deleted.
     */
    private int index;

    /**
     * Variable containing the original data retrieved prior to deletion,
     * used to restore the entry during an undo operation.
     */
    private String oldData;

    /**
     * Constructor for DeleteCommand with specified receiver and payload.
     * Uses the payload to determine the target index for deletion.
     *
     * @param receiver the receiver responsible for executing the delete logic
     * @param payload a string representing the index to be deleted (1-based) - converted to zero-based for index
     */
    public DeleteCommand(Receiver receiver, String payload){
        this.receiver = receiver;
        this.index = Integer.parseInt(payload) - 1;
    }

    /**
     * Executes the delete command by retrieving the original data from the specified index,
     * pushing this command to the history stack, and instructing the receiver to perform deletion.
     *
     * @param history the command history stack that tracks this command for possible undo operations
     */
    @Override
    public void execute(Stack<Command> history) {

//        if (index < 0 || index > receiver.getDataStoreSize()-1) {
//            System.out.println("Invalid index entered");
//            return;
//        }

        try{
            this.oldData = receiver.retrieveLine(index); // stores data that was deleted
            receiver.delete(index);
            history.push(this);
            System.out.println("delete");
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Error: Invalid index entered");
        }
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
}
