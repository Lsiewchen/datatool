package Commands;

import Receiver.Receiver;

/**
 * An implementation of the Command interface that delegates
 * the list operation to the receiver for listing of employee
 * data.
 */
public class ListCommand implements Command {

    /**
     * Variable containing the receiver that handles the list operation.
     */
    private Receiver receiver;

    /**
     * Constructor for ListCommand with specified receiver
     * @param receiver the receiver responsible for the list action
     */
    public ListCommand(Receiver receiver) {
        this.receiver = receiver;
    }

    /**
     * Executes the list command by delegating to the receiver.
     *
     * The provided history stack is not used by this command, but is included to
     * maintain consistency with the command interface (requirements for Overriding).
     */
    @Override
    public void execute() {
        receiver.list();
    }

    /**
     * Since there are no changes to undo for ListCommand, we do not implement the undo command for ListCommand.
     */
    @Override
    public void undo() {}

    /**
     * Function that informs where the command is undoable to enable undo
     * @return false all the time because list operations need not be undone
     */
    @Override
    public boolean canUndo() {
        return false;
    }

    /**
     * Function that informs whether the command has been executed.
     * Prevents illegal undo operations injected to the stack
     * @return false as we do not need undo list operations
     */
    @Override
    public boolean isExecuted() {
        return canUndo();
    }
}
