package lemon.commands;

import lemon.file.Storage;
import lemon.task.TaskList;
import lemon.ui.UI;

/**
 * Represents an invalid command that the user inputs.
 * This command throws errors and exceptions to the user.
 */
public class InvalidCommand extends Command {
    /**
     * Executes the command to display error message for invalid command.
     * Displays the error message and a help list through the user interface.
     *
     * @param tasks List of tasks.
     * @param ui Manages interactions with the user.
     * @param storage Manages the loading from and saving to file.
     */
    @Override
    public void execute(TaskList tasks, UI ui, Storage storage) {
        ui.displayError("Uh-oh! Invalid command. Please try again!\n");
        ui.displayHelp();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
