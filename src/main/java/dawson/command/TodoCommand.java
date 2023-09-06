package dawson.command;

import dawson.Dawson;
import dawson.TaskList;
import dawson.task.Todo;

public class TodoCommand extends Command {

    private String payload;
    private TaskList list;

    public TodoCommand(String payload, TaskList list) {
        this.payload = payload;
        this.list = list;
    }

    @Override
    public void execute() {
        if (payload.equals("")) {
            Dawson.printText("Empty string detected!");
            return;
        }

        Todo newTask = new Todo(payload);
        list.add(newTask);
    }

}