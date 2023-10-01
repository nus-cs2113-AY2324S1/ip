package command;

import exception.FrankException;
import task.Task;
import task.TaskList;
import utility.Ui;
import java.io.IOException;
import java.util.ArrayList;

public class SearchCommand extends Command {
    private String searchWord;
    public SearchCommand (String command) throws FrankException {
        super(command);
        // After "search "
        try {
            searchWord = command.substring(7);
        } catch (StringIndexOutOfBoundsException e) {
            throw new FrankException("Brough you did not include a search term");
        }
    }
    @Override
    public void execute(TaskList tasks, Ui ui) throws FrankException, IOException {
        if(searchWord.isEmpty() || searchWord.equalsIgnoreCase(" ")){
            throw new FrankException("Brough you did not include a search term");
        }
        ArrayList<Integer> index = tasks.searchTasks(searchWord);
        ui.showSelectedTasks(tasks.getTaskData(), index);
    }

}
