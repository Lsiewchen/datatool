package Commands;

import Receiver.Receiver;

import java.util.Stack;

public class AddCommand implements Command {
    private Receiver receiver;
    private String data1, data2, data3;

    public AddCommand(Receiver receiver, String payload) {
        this.receiver = receiver;
        String[] datas =  payload.split(" ");
        this.data1 = datas[0];
        this.data2 = datas[1];
        this.data3 = datas[2];
    }

    @Override
    public void execute(Stack<Command> history) {
        history.push(this);
        receiver.add(data1, data2, data3);
    }

    @Override
    public void undo() {
        receiver.delete(receiver.getDataStoreSize()-1);
    }
}
