package command;

import task.TaskList;
import utility.Ui;

public class ListCommand extends Command {
    @Override
    public void execute(TaskList tasks, Ui ui) {
        tasks.printTasks();
    }
}
