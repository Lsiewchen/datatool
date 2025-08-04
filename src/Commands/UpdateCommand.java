package Commands;

import Tools.Receiver;

public class UpdateCommand implements Command {
    private int index;
    private Receiver receiver;
    private String data1;
    private String data2;
    private String data3;
    private String undo;

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
//        this.undo = receiver.retrieveLine(index);
        System.out.println(index);
    }

    @Override
    public void execute() {
        receiver.update(index, data1, data2, data3);
    }
}
