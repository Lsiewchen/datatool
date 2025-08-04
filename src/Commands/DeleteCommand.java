package Commands;

import Tools.Receiver;

public class DeleteCommand implements Command {
    private Receiver receiver;
    private int index;
    private String undo;

    public DeleteCommand(Receiver receiver, String payload){
        this.receiver = receiver;
        this.index = Integer.parseInt(payload) - 1;
        this.undo = receiver.retrieveLine(index);
    }

    @Override
    public void execute() {
        receiver.delete(index);
    }

    @Override
    public void undo() {
        String[] datas = undo.split(" ");
        receiver.add(index, datas[0], datas[1], datas[2]);
    }
}
