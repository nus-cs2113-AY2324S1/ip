package chattie.commands;

import chattie.TaskList;
import chattie.Ui;
import chattie.error.ChattieException;
import chattie.error.ErrorType;
import chattie.tasks.Task;

import java.util.ArrayList;

/**
 * Deals with find command
 */
public class FindCommand extends Command {

    private static String command;
    private static final int FIND_LENGTH = 5;

    public FindCommand(String command) {
        this.command = command;
    }

    /**
     * Filters the tasklist to match the query
     * @param tasks List of tasks
     * @param ui User interface
     * @throws ChattieException If no matching results
     */
    @Override
    public void execute(TaskList tasks, Ui ui) throws ChattieException {
        String query = command.substring(FIND_LENGTH);
        ArrayList<Task> filteredList = tasks.find(query);

        if (filteredList.isEmpty()) {
            throw new ChattieException(ErrorType.EMPTY_LIST);
        }

        ui.printFilteredList(filteredList);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
