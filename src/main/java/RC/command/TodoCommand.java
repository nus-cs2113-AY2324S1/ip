package RC.command;

import RC.RCException;
import RC.TaskList;
import RC.task.Todo;

public class TodoCommand extends RCCommand {
    private String description;

    public static final String MESSAGE_EMPTY = "\tOOPS!!! The description of a todo cannot be empty.";
    public TodoCommand(String description) {
        this.description = description;
    }

    @Override
    public void execute(TaskList taskList) throws RCException {
        if (description.isEmpty()) {
            throw new RCException(MESSAGE_EMPTY);
        }

        taskList.add(new Todo(description));
    }
}
