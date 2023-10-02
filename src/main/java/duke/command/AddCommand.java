package duke.command;

/**
 * Represents an add task command.
 */
public abstract class AddCommand extends Command {
    protected String dataToAdd;

    /**
     * Creates an add command.
     *
     * @param input The inputted data.
     */
    public AddCommand(String input) {
        super(false);
        dataToAdd = input;
    }

}
