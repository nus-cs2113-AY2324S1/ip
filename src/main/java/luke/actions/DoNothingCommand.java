package luke.actions;

/**
 * The DoNothingCommand class represents a null command.
 * It extends the Command class and is used when there are errors or invalid input in the user's command.
 */
public class DoNothingCommand extends Command {
    public DoNothingCommand(ActionType theAction, String parameters) {
        super(theAction, parameters);
    }
}

