package elgin.command;

import elgin.exception.DukeException;
import elgin.storage.Storage;
import elgin.task.TaskList;
import elgin.ui.Ui;


public class FindCommand extends Command {
    private String arguments;

    /**
     * Constructor for FindCommand.
     * Initialise the arguments to be lowercase for insensitive searching.
     *
     * @param arguments Arguments containing the keyword to search for.
     */
    public FindCommand(String arguments) {
        this.arguments = arguments.trim().toLowerCase();
    }

    /**
     * Executes to find tasks that contains the keyword and print them if found,
     * else print message to indicate no matched tasks.
     *
     * @param tasks TaskList of tasks.
     * @param ui Ui for standardised output of program.
     * @param storage Storage that stores and loads tasks from disk.
     * @throws DukeException If Keyword is empty.
     */
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        if (arguments.isEmpty()) {
            throw new DukeException("Please enter the keyword to search for.");
        }
        ui.formatPrint(tasks.findTasks(arguments));
    }
}
