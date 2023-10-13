package command;

import task.TaskList;
import ui.Ui;

/**
 * Represents a command. A command object corresponds to
 * a specific command requested by the user
 */
public abstract class Command {

    /**
     * Executes the command by calling methods in TaskList
     *
     * @param taskList the taskList of the current user
     * @param ui the ui for the current session
     */
    public void execute(TaskList taskList, Ui ui) {}

    /**
     * check whether this command is an exit command (user input "bye")
     *
     * default returns false, this method will be overridden in ExitCommand
     */
    public boolean isExit() {
        return false;
    }
}
