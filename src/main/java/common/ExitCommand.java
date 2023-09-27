package common;

import listWhisper.task.TaskList;
import storage.DataManager;
import ui.Ui;

public class ExitCommand extends Command {

    @Override
    public void execute(TaskList taskList, Ui ui) {
        DataManager.saveList(taskList);
        System.exit(0);
    }

    @Override
    public boolean isExit() {
        return true;
    }

    public void print() {
        Messages.printByeMessage();
    }
}
