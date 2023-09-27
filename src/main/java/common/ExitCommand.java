package common;

import listWhisper.task.TaskList;
import storage.Storage;
import ui.Ui;

public class ExitCommand extends Command {

    @Override
    public void execute(TaskList taskList, Ui ui) {
        Storage.saveList(taskList);
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
