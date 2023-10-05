package dawson.command;

import dawson.exception.DawsonException;
import dawson.task.TaskList;
import dawson.task.TodoTask;
import dawson.ui.Messages;

/**
 * Adds a Todo task.
 */
public class TodoCommand extends Command {

    private String payload;

    public TodoCommand(String payload) {
        this.payload = payload;
    }

    @Override
    public CommandResult execute(TaskList list) throws DawsonException {
        if (payload.equals("")) {
            String errorMsg = "The description of a todo cannot be empty!";
            throw new DawsonException(errorMsg);
        }

        TodoTask newTask = new TodoTask(payload);
        list.add(newTask);

        String[] msg = Messages.getAddSuccessMessage(newTask.toString(), list.getSize());
        return new CommandResult(msg);
    }

}