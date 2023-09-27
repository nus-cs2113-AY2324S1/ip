package commands;

import data.TaskList;
import ui.Ui;

public class ListCommand extends Command {
    @Override
    public void execute(TaskList tasks) {
        Ui.printList(tasks);
    }
}
