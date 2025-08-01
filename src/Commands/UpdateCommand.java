package Commands;

import Tools.Receiver;

import java.util.List;

public class UpdateCommand implements Command {
    private int index;
    private Receiver receiver;
    private List<String> datas;

    public UpdateCommand(Receiver receiver, int index, List<String> datas) {
        this.receiver = receiver;
        this.index = index;
        this.datas = datas;
    }

    @Override
    public void execute() {
        //change the parameter accordingly
    }
}
