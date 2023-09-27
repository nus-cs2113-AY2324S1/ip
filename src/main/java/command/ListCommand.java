package command;

import task.TaskList;
import ui.Ui;

public class ListCommand extends Command {
    public ListCommand(){}

    @Override
    public void execute(TaskList taskList, Ui ui) {
        Ui.printListMessage(taskList);
    }
}
