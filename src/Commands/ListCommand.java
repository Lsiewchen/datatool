package Commands;

import Receiver.Receiver;

import java.util.Stack;

public class ListCommand implements Command {
    private Receiver receiver;

    public ListCommand(Receiver receiver) {
        this.receiver = receiver;
    }

    @Override
    public void execute(Stack<Command> history) {
        receiver.list();
    }

    @Override
    public void undo() {}
}
