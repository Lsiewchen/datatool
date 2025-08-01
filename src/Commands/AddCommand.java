package Commands;

import Tools.Receiver;

import java.util.List;

public class AddCommand implements Command {
    private int index;
    private Receiver receiver;
    private List<String> datas;

    public AddCommand(Receiver receiver, List<String> datas) {
        this.receiver = receiver;
        this.datas = datas;
        this.index = getLastIndex() + 1;
    }

    public int getLastIndex() {
        return 0;
    }

    @Override
    public void execute() {
        //change the parameter accordingly
    }
}
