package RC.command;

import RC.RCException;
import RC.TaskList;
import RC.UI.Ui;
import RC.task.Deadline;

/**
 * Represents a command to create a deadline task and add it to the task list.
 */
public class DeadlineCommand extends RCCommand {
    private String input;
    private static final String BY_COMMAND = "/by";
    private static final String MESSAGE_MISSING_COMMAND = "\tOOPS!!! Please include /by followed by the " +
            "deadline. (eg. /by 2023-12-12 1200)";
    private static final String MESSAGE_EMPTY = "\tOOPS!!! Please ensure description and deadline are filled.";

    public DeadlineCommand(String input) {
        this.input = input;
    }

    /**
     * Creates a deadline task and adds it to the task list.
     *
     * @param taskList The task list where the deadline task will be added.
     * @param ui The user interface for displaying messages.
     * @throws RCException If the input is in the wrong format.
     */
    @Override
    public void execute(TaskList taskList, Ui ui) throws RCException {
        int splitIndex = input.indexOf(BY_COMMAND);
        if (splitIndex == -1) {
            throw new RCException(MESSAGE_MISSING_COMMAND);
        }

        String description = input.substring(0, splitIndex).trim();
        String by = input.substring(splitIndex + BY_COMMAND.length()).trim();
        if (description.isEmpty() || by.isEmpty()) {
            throw new RCException(MESSAGE_EMPTY);
        }

        taskList.add(new Deadline(description, by), ui);
    }
}
