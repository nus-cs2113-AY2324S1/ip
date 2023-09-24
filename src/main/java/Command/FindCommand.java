package Command;

import Storage.Storage;
import Storage.TaskList;
import Ui.Ui;
import Soccat.Task;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class FindCommand extends Command{

    public static final String COMMAND_WORD = "find";
    public static final String INVALID_PROMPT = "Oops, please try deadline find <search word>!";
    public static final String FIND_FAIL = "Oops, no tasks found, please try a different search word!";

    private final String searchWord;

    public FindCommand(String searchWord) {
        this.searchWord = searchWord;
    }

    @Override
    public boolean execute(TaskList tasks, Ui ui, Storage storage) {
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
