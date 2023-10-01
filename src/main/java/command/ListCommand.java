package command;

import task.TaskList;
import utility.Ui;

public class ListCommand extends Command {
    /**
     * Lists out all current Tasks
      * @param tasks TaskList of current Tasks
     * @param ui Current User Interface
     */
    @Override
    public void execute(TaskList tasks, Ui ui) {
        tasks.printTasks();
    }
}
