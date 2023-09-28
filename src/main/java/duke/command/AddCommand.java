package duke.command;

public abstract class AddCommand extends Command {
    protected String dataToAdd;

    public AddCommand(String input) {
        super(false);
        dataToAdd = input;
    }

}
