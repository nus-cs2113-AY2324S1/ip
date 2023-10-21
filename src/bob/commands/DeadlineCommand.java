package bob.commands;

import bob.BobException;
import bob.deadline.Deadline;
import bob.tasklist.TaskList;

import java.time.LocalDate;

/**
 * Adds a new deadline to the task list.
 */
public class DeadlineCommand extends Command {

    public static final String COMMAND_WORD = "deadline";

    private final Deadline newDeadline;

    /**
     * Creates a DeadlineCommand to add a new {@code Deadline}.
     *
     * @param deadline Deadline to add
     */
    public DeadlineCommand(Deadline deadline) {
        newDeadline = deadline;
    }

    @Override
    public String execute(TaskList taskList) {
        return taskList.handleCreateDeadline(newDeadline);
    }
}
