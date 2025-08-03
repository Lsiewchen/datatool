package Commands;

import Tools.Receiver;

public class UpdateCommand implements Command {
    private int index;
    private Receiver receiver;
    private String data1;
    private String data2;
    private String data3;

    public UpdateCommand(Receiver receiver, int index, String data1) {
        this.receiver = receiver;
        this.index = index;
        this.data1 = data1;
    }

    public UpdateCommand(Receiver receiver, int index, String data1, String data2) {
        this(receiver, index, data1);
        this.data2 = data2;
    }

    public UpdateCommand(Receiver receiver, int index, String data1, String data2, String data3) {
        this(receiver, index, data1, data2);
        this.data3 = data3;
    }

    @Override
    public void execute() {
        receiver.update(index, data1, data2, data3);
    }
}
