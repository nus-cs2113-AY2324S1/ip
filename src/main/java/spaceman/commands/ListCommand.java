package spaceman.commands;

import spaceman.data.TaskList;
import spaceman.ui.Ui;

public class ListCommand extends Command {
    @Override
    public void execute(TaskList tasks) {
        Ui.showTaskList(tasks);
    }
}
