package Commands;

import java.util.Stack;

/**
 * This interface represents an executable command within a command pattern structure.
 * It supports a command history stack to facilitate rollback of commands.
 * Each command maintains the ability to execute its logic and reverse it through undo.
 */
public interface Command {

    /**
     * Executes the command logic by delegating to the receiver and records itself in
     * the provided history stack (if needed - Add, Delete and Update).
     * @param history
     */
    void execute(Stack<Command> history);

    /**
     * Reverts the effects of a previously executed command from the following list (Add, Delete and Update).
     */
    void undo();
}
