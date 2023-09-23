package eggybyte.ip.command;

import eggybyte.ip.data.RunningState;
import eggybyte.ip.data.exception.TipsException;

/**
 * Represents an executable command.
 */
public class Command {
    protected static RunningState runningState;
    protected final String type;
    protected final int validArgumentAmount;

    public Command(String type, int validArgumentAmount) {
        this.type = type;
        this.validArgumentAmount = validArgumentAmount;
    }

    /**
     * Executes the command and returns the result.
     */
    public CommandResult execute() {
        try {
            return getCommandResult(customFunction());
        } catch (Exception exception) {
            return new CommandResult(exception);
        }
    }

    /**
     * Supplies the data the command will operate on.
     */
    public static void setRunningState(RunningState runningState) {
        Command.runningState = runningState;
    }

    public CommandResult getCommandResult(String content) throws Exception {
        throw new Exception("This method is to be implemented by child classes");
    }

    protected String customFunction() throws Exception {
        throw new Exception("This method is to be implemented by child classes");
    }

    protected void checkArguments(String[] arguments) throws TipsException {
        String helpMessage = "The valid argument amount of '" + type + "' command is " + validArgumentAmount
                + ", and you can check what each argument means in the user guide.";
        if (arguments.length < validArgumentAmount) {
            throw new TipsException("Not enough arguments input!", helpMessage);
        }
        if (arguments.length > validArgumentAmount) {
            throw new TipsException("Too many arguments input!", helpMessage);
        }
    }
}
