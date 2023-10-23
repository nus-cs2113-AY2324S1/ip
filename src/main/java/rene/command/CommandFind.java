package rene.command;

import rene.storage.Storage;
import rene.tasklist.TaskList;
import rene.ui.Ui;
/**
 * Represents a command to search for a task.
 */
public class CommandFind extends Command{
    private String searchDetails;
    /**
     * Creates a new find command to search for a task.
     *
     * @param searchDetails User input containing details of what to search.
     */
    public CommandFind(String searchDetails){
        super((CommandType.FIND));
        this.searchDetails = searchDetails;
    }

    /**
     * Checks if specified task exists.
     * Marks the specified task as not done and updates storage data.
     *
     * @param ui User interface for interactions with user through CLI.
     * @param tasks Current task list in the program.
     * @param dataStorage Hard disk storage for storing task data.
     */
    @Override
    public void executeCommand(Ui ui, Storage dataStorage, TaskList tasks) {
        tasks.searchList(searchDetails);
    }
}
