package Commands;

import Receiver.Receiver;

import java.util.EmptyStackException;
import java.util.Stack;

/**
 * An implementation of the Command interface that enables
 * undoing of the most recent command using a command history stack.
 * This command interacts with the history to retrieve the last executed command
 * and calls its undo method, effectively reversing its effect.
 */
public class UndoCommand implements Command {

    /**
     * Variable containing the receiver that facilitates undo operations.
     */
    private Receiver receiver;
    /**
     * Variable containing the history of commands that has been done thus far.
     */
    private Stack<Command> history;

    /**
     * Constructor for UndoCommand with specified receiver.
     * @param receiver the receiver responsible for undo action
     */
    public UndoCommand(Receiver receiver){
        this.receiver = receiver;
    }

    /**
     * Executes the command by getting the latest command from the
     * history stack and then executing their relevant undo methods.
     *
     * @param history provides the command history stack from which
     * the most recent command is retrieved and undone
     */
    @Override
    public void execute(Stack<Command> history) {
        if (history.isEmpty()){
            throw new EmptyStackException();
        }
        history.pop().undo();
        System.out.println("undo");
    }

    /**
     * Since the undo method is meant to undo changes and not itself,
     * we do not implement the undo method for UndoCommand.
     */
    @Override
    public void undo() {}
}
