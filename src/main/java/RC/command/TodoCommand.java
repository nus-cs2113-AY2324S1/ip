package RC.command;

import RC.RCException;
import RC.TaskList;
import RC.UI.Ui;
import RC.task.Todo;

/**
 * Represents a command to create and add a todo task to the task list.
 */
public class TodoCommand extends RCCommand {
    private String description;
    private static final String MESSAGE_EMPTY = "\tOOPS!!! The description of a todo cannot be empty.";

    public TodoCommand(String description) {
        this.description = description;
    }

    /**
     * Creates a todo task and adds it to the task list.
     *
     * @param taskList The task list where the created task will be added.
     * @param ui The user interface for displaying messages.
     * @throws RCException If the input is in the wrong format.
     */
    @Override
    public void execute(TaskList taskList, Ui ui) throws RCException {
        if (description.isEmpty()) {
            throw new RCException(MESSAGE_EMPTY);
        }

        taskList.add(new Todo(description), ui);
    }
}
