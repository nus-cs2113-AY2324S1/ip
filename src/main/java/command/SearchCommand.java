package command;

import exception.FrankException;
import task.Task;
import task.TaskList;
import utility.Ui;
import java.io.IOException;
import java.util.ArrayList;

public class SearchCommand extends Command {
    private String searchWord;

    /**
     * Constructor that creates a substring of everything
     * after the word "search " to make the searchWord
     *
     * @param command The command which includes search and the searchWord
     * @throws FrankException Unique Exceptions
     */
    public SearchCommand (String command) throws FrankException {
        super(command);
        // After "search "
        try {
            searchWord = command.substring(7);
        } catch (StringIndexOutOfBoundsException e) {
            throw new FrankException("Brough you did not include a search term");
        }
    }

    /**
     * Searches all tasks if they contain the searchWord and gathers their indexes
     * and prints them out
     *
     * @param tasks TaskList of current Tasks
     * @param ui Current User Interface
     * @throws FrankException Unique Exceptions
     * @throws IOException Input Exceptions
     */
    @Override
    public void execute(TaskList tasks, Ui ui) throws FrankException, IOException {
        if(searchWord.isEmpty() || searchWord.equalsIgnoreCase(" ")){
            throw new FrankException("Brough you did not include a search term");
        }
        ArrayList<Integer> index = tasks.searchTasks(searchWord);
        ui.showSelectedTasks(tasks.getTaskData(), index);
    }

}
