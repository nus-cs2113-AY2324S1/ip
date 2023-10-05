package eggybyte.ip.command;

import eggybyte.ip.data.exception.TipsException;
import eggybyte.ip.data.task.Task;

/**
 * Commands for Adding Tasks.
 * 
 * @see #COMMAND_WORD
 */
public class AddCommand extends Command {

    protected Task task;

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
    public AddCommand(String type, int validArgumentAmount) throws TipsException {
        super(type, validArgumentAmount);
    }

    @Override
    public String customFunction() {
        runningState.tasks.add(task);
        return task.toString();
    }

    @Override
    public CommandResult getCommandResult(String content) {
        return new CommandResult("Got it. I've added this task:\n  "
                + content
                + "\nNow you have " + runningState.tasks.size() + " tasks in the list.");
    }
}
