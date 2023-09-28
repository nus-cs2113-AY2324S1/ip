package dawson.command;

import dawson.TaskList;
import dawson.exception.DawsonException;
import dawson.task.Todo;

public class TodoCommand extends Command {

    private String payload;
    private TaskList list;

    public TodoCommand(String payload, TaskList list) {
        this.payload = payload;
        this.list = list;
    }

    @Override
    public void execute() throws DawsonException {
        if (payload.equals("")) {
            String errorMsg = "The description of a todo cannot be empty!";
            throw new DawsonException(errorMsg);
        }

        Todo newTask = new Todo(payload);
        list.add(newTask);
    }

}