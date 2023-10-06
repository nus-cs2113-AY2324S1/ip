package lemon.commands;

import lemon.file.Storage;
import lemon.task.TaskList;
import lemon.ui.UI;

/**
 * Represents a command to display valid commands accepted by the chatbot.
 * This command displays the functionalities and valid input format to the user.
 */
public class HelpCommand extends Command {
    public static final String COMMAND_WORD = "help";

    /**
     * Executes the command to display help list to the user.
     * Displays a list of help messages through the user interface.
     *
     * @param tasks List of tasks.
     * @param ui Manages interactions with the user.
     * @param storage Manages the loading from and saving to file.
     */
    @Override
    public void execute(TaskList tasks, UI ui, Storage storage) {
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
