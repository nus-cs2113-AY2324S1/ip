package jerry.commands;

import jerry.task.Task;
import jerry.common.Messages;

public class MarkCommand extends Command {

    public static final String COMMAND_WORD = "mark";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Mark the specified task as done. "
            + "Parameters:  INDEX \n"
            + "Example: " + COMMAND_WORD
            + " 3";

    public static final String MESSAGE_SUCCESS = "Nice! I've marked this task as done: %1$s";

    public MarkCommand(int targetVisibleIndex) {
        super(targetVisibleIndex);
    }

    @Override
    public CommandResult execute() {
        try {
            final Task target = getTargetTask();
            target.markAsDone();
            return new CommandResult(String.format(MESSAGE_SUCCESS, target));

        } catch (IndexOutOfBoundsException ie) {
            return new CommandResult(Messages.MESSAGE_INVALID_TASK_DISPLAYED_INDEX);
        }
    }

}
