package jerry.commands;

import jerry.task.Task;
import jerry.exceptions.TaskNotFoundException;
import jerry.common.Messages;

/**
 * Mark a specified task as not done yet in the takslist
 */
public class UnmarkCommand extends Command {

    public static final String COMMAND_WORD = "unmark";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Mark the specified task as done. "
            + "Parameters:  INDEX \n"
            + "Example: " + COMMAND_WORD
            + " 3";

    public static final String MESSAGE_SUCCESS = "Ok! I've marked this task as undone: %1$s";

    public UnmarkCommand(int targetVisibleIndex) {
        super(targetVisibleIndex);
    }

    @Override
    public CommandResult execute() {
        try {
            final Task target = getTargetTask();
            target.markAsUndone();
            return new CommandResult(String.format(MESSAGE_SUCCESS, target));

        } catch (IndexOutOfBoundsException ie) {
            return new CommandResult(Messages.MESSAGE_INVALID_TASK_DISPLAYED_INDEX);
        }
    }

}
