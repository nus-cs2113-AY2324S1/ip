package Command;

import Storage.Storage;
import Storage.TaskList;
import Ui.Ui;

import java.util.ArrayList;

/**
 * Represents a command to search for tasks by keyword.
 * A <code>FindCommand</code> corresponds to a command
 * that tries to find tasks by <code>searchWord</code>.
 * */

public class FindCommand extends Command{

    public static final String COMMAND_WORD = "find";
    public static final String INVALID_PROMPT = "Oops, please try deadline find <search word>!";
    public static final String FIND_FAIL = "Oops, no tasks found, please try a different search word!";

    private final String searchWord;

    public FindCommand(String searchWord) {
        this.searchWord = searchWord;
    }

    /**
     * Executes the find command to display the list of
     * tasks found based on the search word.
     *
     * @param tasks The taskList object containing tasks
     * @param ui The ui object to display messages to users
     * @param taskFile The storage file for tasks to be stored
     * @return Boolean of whether to exit the application.
     * */
    @Override
    public boolean execute(TaskList tasks, Ui ui, Storage taskFile) {
        ui.displayLine();
        ArrayList<Integer> resultTaskIndexes = tasks.searchTasks(searchWord);
        if (resultTaskIndexes.isEmpty()) {
            ui.displayError(FIND_FAIL);
            return false;
        }
        ui.displaySelectedTasks(tasks, resultTaskIndexes);
        return false;
    }
}
