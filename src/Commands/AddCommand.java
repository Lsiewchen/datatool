package Commands;

import Tools.Receiver;

public class AddCommand implements Command {
    private Receiver receiver;
    private String data1, data2, data3;

    public AddCommand(Receiver receiver, String data1, String data2, String data3) {
        this.receiver = receiver;
        this.data1 = data1;
        this.data2 = data2;
        this.data3 = data3;
    }

    @Override
    public void execute() {
        receiver.add(data1, data2, data3);
    }
}
