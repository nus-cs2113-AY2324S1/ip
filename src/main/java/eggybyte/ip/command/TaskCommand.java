package eggybyte.ip.command;

import eggybyte.ip.data.exception.TipsException;
import eggybyte.ip.data.task.Task;

/**
 * Commands for Adding General Tasks.
 * 
 * @see #COMMAND_WORD
 */
public class TaskCommand extends AddCommand {
    public static final String COMMAND_WORD = "task";
    protected static final int validArgumentAmount = 0;

    /**
     * Create a new Command.
     *
     * @param arguments The specified arguments will be used for creating command,
     *                  it will automatically check whethere the arguments are
     *                  valid.
     * @see #validArgumentAmount
     * @throws TipsException Any excption will be throw in this type, which contains
     *                       information about this exception and the possible
     *                       solution.
     */
    public TaskCommand(String description, String[] arguments) throws TipsException {
        super(COMMAND_WORD, validArgumentAmount);
        // checkArguments(arguments);
        task = new Task(description);
    }

    @Override
    public String customFunction() {
        runningState.tasks.add(task);
        return task.toString();
    }
}
