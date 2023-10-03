package MySun.commands;

import MySun.ui.Ui;
import MySun.data.TaskList;

public class ListCommand extends Command {
    @Override
    public void execute(TaskList tasks) {
        Ui.showTaskList(tasks);
    }
}
