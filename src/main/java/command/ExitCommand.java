package command;

import task.TaskList;
import storage.Storage;
import ui.Ui;

public class ExitCommand extends Command {

    /**
     * Executes by calling the saveList method in storage
     * After saving the data, it prints bye message and exit the program
     *
     * @param taskList the taskList of the current user
     * @param ui the ui for the current session
     */
    @Override
    public void execute(TaskList taskList, Ui ui) {
        Storage.saveList(taskList);
        Ui.printByeMessage();
        System.exit(0);
    }

    /**
     * Overrides the isExit() method which returns false
     *
     * @return true
     */
    @Override
    public boolean isExit() {
        return true;
    }
}
