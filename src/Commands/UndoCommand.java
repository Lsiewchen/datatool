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
     * @param history the history of all the commands done thus far
     */
    public UndoCommand(Receiver receiver, Stack<Command> history){
        this.receiver = receiver;
        this.history = history;
    }

    /**
     * Executes the command by getting the latest command from the
     * history stack and then executing their relevant undo methods if able.
     */
    @Override
    public void execute() {
        if (history.isEmpty()){ // throws exception if there is nothing to undo
            throw new EmptyStackException();
        }
        while (!history.peek().isExecuted()) { // checks if the command was executed
            history.pop(); // removes the command if it was not executed and moves on to the next in the stack
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

    /**
     * Function that informs whether the command is undoable or not
     * @return false all the time because undo operations need not be undone
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
