package Commands;

import Tools.Receiver;

import java.util.Stack;

public class UndoCommand implements Command {
    private Receiver receiver;
    private Stack<Command> history;

    public UndoCommand(Receiver receiver, Stack<Command> history){
        this.receiver = receiver;
        this.history = history;
    }

    @Override
    public void execute() {
        receiver.undo(history);
    }

    @Override
    public void undo() {}
}
