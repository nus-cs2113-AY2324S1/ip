package command;

import task.TaskList;
import storage.Storage;
import ui.Ui;

public class ExitCommand extends Command {

    @Override
    public void execute(TaskList taskList, Ui ui) {
        Storage.saveList(taskList);
        Ui.printByeMessage();
        System.exit(0);
    }

    @Override
    public boolean isExit() {
        return true;
    }
}
