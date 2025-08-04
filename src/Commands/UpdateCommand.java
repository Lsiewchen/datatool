package Commands;

import Receiver.Receiver;

import java.util.Stack;

public class UpdateCommand implements Command {
    private int index;
    private Receiver receiver;
    private String data1;
    private String data2;
    private String data3;
    private String oldData;

    public UpdateCommand(Receiver receiver, String payload) {
        this.receiver = receiver;
        String[] datas =  payload.split(" ");
        this.index = Integer.parseInt(datas[0]) - 1;
        this.data1 = datas[1];
        if (datas.length > 2) {
            this.data2 = datas[2];
        }
        if (datas.length > 3) {
            this.data3 = datas[3];
        }
    }

    @Override
    public void execute(Stack<Command> history) {
        history.push(this);
        this.oldData = receiver.retrieveLine(index);
        receiver.update(index, data1, data2, data3);
    }

    @Override
    public void undo() {
        String[] datas = oldData.split(" ");
        receiver.update(index, datas[0], datas[1], datas[2]);
    }
}
