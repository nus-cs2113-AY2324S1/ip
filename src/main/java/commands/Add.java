package commands;

import dawson.Dawson;
import dawson.TaskList;
import tasks.Task;

public class Add extends Command {

    private String payload;
    private TaskList list;

    public Add(String payload, TaskList list) {
        this.payload = payload;
        this.list = list;
    }

    @Override
    public void execute() {
        if (payload.equals("")) {
            Dawson.printText("Empty string detected!");
            return;
        }

        Task newTask = new Task(payload);
        list.add(newTask);
        Dawson.printText("Added: " + payload);
    }

}
