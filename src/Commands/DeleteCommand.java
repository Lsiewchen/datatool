package Commands;

import Receiver.Receiver;

import java.util.Stack;

public class DeleteCommand implements Command {
    private Receiver receiver;
    private int index;
    private String undo;

    public DeleteCommand(Receiver receiver, String payload){
        this.receiver = receiver;
        this.index = Integer.parseInt(payload) - 1;
    }

    @Override
    public void execute(Stack<Command> history) {
        history.push(this);
        this.undo = receiver.retrieveLine(index);
        receiver.delete(index);
    }

    @Override
    public void undo() {
        String[] datas = undo.split(" ");
        receiver.add(index, datas[0], datas[1], datas[2]);
    }
}
