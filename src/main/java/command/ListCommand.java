package command;

import task.TaskList;
import ui.Ui;

public class ListCommand extends Command {

    /**
     * Constructor
     */
    public ListCommand(){}

    /**
     * Execute by calling the printListMessage in Ui
     *
     * @param taskList the taskList of the current user
     * @param ui the ui for the current session
     */
    @Override
    public void execute(TaskList taskList, Ui ui) {
        Ui.printListMessage(taskList);
    }
}
