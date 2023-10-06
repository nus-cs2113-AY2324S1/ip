package lemon.commands;

import lemon.exception.LemonException;
import lemon.file.Storage;
import lemon.task.TaskList;
import lemon.ui.UI;

/**
 * Represents an executable command in the chatbot.
 * Base implementation for various Command handling specific user actions.
 */
public class Command {
    public boolean isExit;

    /**
     * Returns whether executing this command should exit the chatbot.
     *
     * @return true if executing this command should exit the application, false otherwise.
     */
    public boolean isExit() {
        return isExit;
    }

    /**
     * Executes a specific command according to the user input on the TaskList, UI, and Storage.
     *
     * @param tasks List of tasks.
     * @param ui Manages interactions with the user.
     * @param storage Manages the loading from and saving to file.
     * @throws LemonException Any exception while executing the command.
     */
    public void execute(TaskList tasks, UI ui, Storage storage) throws LemonException {
    }
}
