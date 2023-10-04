package jerry.commands;

import jerry.task.Task;
import jerry.exceptions.TaskNotFoundException;
import jerry.common.Messages;

/**
 * Deletes a task identified using it's last displayed index from the task list.
 */
public class DeleteCommand extends Command {

    public static final String COMMAND_WORD = "delete";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Deletes the task identified by the index number used in the last task listing.\n"
            + "Parameters: INDEX\n"
            + "Example: " + COMMAND_WORD + " 1";

    public static final String MESSAGE_DELETE_TASK_SUCCESS = "Deleted task: %1$s";


    public DeleteCommand(int targetVisibleIndex) {
        super(targetVisibleIndex);
    }


    @Override
    public CommandResult execute() {
        try {
            final Task target = getTargetTask();
            taskList.removeTask(target);
            return new CommandResult(String.format(MESSAGE_DELETE_TASK_SUCCESS, target));

        } catch (IndexOutOfBoundsException ie) {
            return new CommandResult(Messages.MESSAGE_INVALID_TASK_DISPLAYED_INDEX);
        } catch (TaskNotFoundException tnfe) {
            return new CommandResult(Messages.MESSAGE_TASK_NOT_IN_TASKLIST);
        }
    }
}
