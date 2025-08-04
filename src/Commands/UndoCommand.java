package Commands;

import Receiver.Receiver;

import java.util.Stack;

public class UndoCommand implements Command {
    private Receiver receiver;
    private Stack<Command> history;

    public UndoCommand(Receiver receiver){
        this.receiver = receiver;
    }

    @Override
    public void execute(Stack<Command> history) {
        history.pop().undo();
    }

    @Override
    public void undo() {}
}
