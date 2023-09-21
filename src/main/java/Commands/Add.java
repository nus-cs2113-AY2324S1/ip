package Commands;

import Data.Task;
import Data.TaskList;

import Ui.Ui;

public class Add extends Command{
    private final Task task;

    public Add(Task task) {
        this.task = task;
    }

    @Override
    public void execute(TaskList list, Ui ui) {
        list.add(task);
        String[] text = {"Added: " + task.getDescription(), "Now you have " + list.size() + " tasks in the list."};
        ui.printMultipleText(text);
    }
}
