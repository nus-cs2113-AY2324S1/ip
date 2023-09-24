package Command;

import Storage.Storage;
import Storage.TaskList;
import Ui.Ui;

/**
 * Represents a command to quit the application.
 * */
public class ExitCommand extends Command{

    public static final String COMMAND_WORD = "bye";

    /**
     * Display goodbye message and return exit value of
     * true to exit the application.
     *
     * @param tasks The taskList object containing tasks
     * @param ui The ui object to display messages to users
     * @param taskFile The storage file for tasks to be stored
     * @return Boolean of whether to exit the application.
     * */
    @Override
    public boolean execute(TaskList tasks, Ui ui, Storage taskFile) {
        ui.displayGoodbye();
        return true;
    }
}
