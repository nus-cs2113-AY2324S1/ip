package commands;

import dawson.Dawson;
import dawson.Task;
import dawson.TaskList;

public class Add extends Command {

    private String item;
    private TaskList list;

    public Add(String item, TaskList list) {
        this.item = item;
        this.list = list;
    }

    @Override
    public void execute() {
        if (item.equals("")) {
            Dawson.printText("Empty string detected!");
            return;
        }

        Task newTask = new Task(item);
        list.add(newTask);
        Dawson.printText("Added: " + item);
    }
    
}
