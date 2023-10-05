package eggybyte.ip.command;

import eggybyte.ip.data.exception.TipsException;

/**
 * Commands for Deleting Existing Tasks.
 * 
 * @see #COMMAND_WORD
 */
public class DeleteCommand extends Command {
    public static final String COMMAND_WORD = "delete";
    protected static final int validArgumentAmount = 1;
    private int index;

    /**
     * Creates a new Command.
     *
     * @param arguments The specified arguments will be used for creating command,
     *                  it will automatically check whethere the arguments are
     *                  valid.
     * @see #validArgumentAmount
     * @throws TipsException Any excption will be throw in this type, which contains
     *                       information about this exception and the possible
     *                       solution.
     */
    public DeleteCommand(String[] arguments) throws TipsException {
        super(COMMAND_WORD, validArgumentAmount);
        checkArguments(arguments);
        index = Integer.parseInt(arguments[0]);
    }

    @Override
    public String customFunction() throws TipsException {
        if (index - 1 >= runningState.tasks.size()) {
            throw new TipsException("Exceeded index!", "The index " + index + " has exceeded the range of the list!"
                    + "\nTry enter a valid index that is not bigger than the length of the list.");
        }
        index -= 1;
        String result = runningState.tasks.get(index).toString();
        runningState.tasks.remove(index);
        return result;
    }

    @Override
    public CommandResult getCommandResult(String content) {
        return new CommandResult("Noted. I've removed this task:\n  "
                + content
                + "\nNow you have " + runningState.tasks.size() + " tasks in the list.");
    }
}
