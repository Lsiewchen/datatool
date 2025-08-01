package Commands;

public class DeleteCommand implements Command {
    private int index;

    public DeleteCommand(int index){
        this.index = index;
    }

    @Override
    public void execute() {
        //change the parameter accordingly
    }
}
