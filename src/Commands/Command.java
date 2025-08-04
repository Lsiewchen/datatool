package Commands;

import java.util.Stack;

public interface Command {
    void execute(Stack<Command> history);
    void undo();
}
