package eggybyte.ip.command;

import eggybyte.ip.data.RunningState;
import eggybyte.ip.data.exception.TipsException;

/**
 * Basic Command Class.
 */
public class Command {
    protected static RunningState runningState;
    protected final String type;
    protected final int validArgumentAmount;

    /**
     * Create a new Command.
     *
     * @param arguments The specified arguments will be used for creating command,
     *                  it will automatically check whethere the arguments are
     *                  valid.
     * @throws TipsException Any excption will be throw in this type, which contains
     *                       information about this exception and the possible
     *                       solution.
     */
    public Command(String type, int validArgumentAmount) throws TipsException {
        this.type = type;
        this.validArgumentAmount = validArgumentAmount;
    }

    /**
     * Executes the command.
     * 
     * @return Command result that contains a description and maybe an exception.
     */
    public CommandResult execute() {
        try {
            return getCommandResult(customFunction());
        } catch (Exception exception) {
            return new CommandResult(exception);
        }
    }

    /**
     * Running State setter to make clear which state the command is executing for.
     */
    public static void setRunningState(RunningState runningState) {
        Command.runningState = runningState;
    }

    /**
     * To combine the result after execution and give the user a standard
     * description.It must be implemented by child classes for customized use,
     * otherwise it may throw a tips exception.
     * 
     * @return A Command Result with a standard description or an unexpected
     *         exception.
     * @throws TipsException Any excption will be throw in this type, which contains
     *                       information about this exception and the possible
     *                       solution.
     */
    public CommandResult getCommandResult(String content) throws TipsException {
        throw new TipsException("This method is to be implemented by child classes.",
                "This is an internal error, please contact the programmer for further fix.");
    }

    /**
     * A function that do specified things in each child command class.It must be
     * implemented by child classes for customized use,otherwise it may throw a tips
     * exception.
     * 
     * @return A String that is raw to indicate the result, which maybe modified by
     *         the method getCommandResult().
     * @throws TipsException Any excption will be throw in this type, which contains
     *                       information about this exception and the possible
     *                       solution.
     */
    protected String customFunction() throws TipsException {
        throw new TipsException("This method is to be implemented by child classes.",
                "This is an internal error, please contact the programmer for further fix.");
    }

    /**
     * A function that used for checking the arguments' amount, to make sure no
     * exception about using an exceeded array element may happen while constructing
     * a new command.
     * 
     * @throws TipsException Any excption will be throw in this type, which contains
     *                       information about this exception and the possible
     *                       solution.
     */
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
