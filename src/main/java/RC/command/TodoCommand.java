package RC.command;

import RC.RCException;
import RC.TaskList;
import RC.task.Todo;

public class TodoCommand extends RCCommand {
    private String description;
    private TaskList tasks;

    public TodoCommand(String description, TaskList tasks) {
        this.description = description;
        this.tasks = tasks;
    }

    @Override
    public void execute() throws RCException {
        if (description.isEmpty()) {
            String errorMessage = "\tOOPS!!! The description of a todo cannot be empty.";
            throw new RCException(errorMessage);
        }

        tasks.add(new Todo(description));
    }
}
