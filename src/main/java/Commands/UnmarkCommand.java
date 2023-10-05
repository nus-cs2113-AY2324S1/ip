package Commands;

import Storage.Storage;
import static Task.TaskList.list;

import Exceptions.DukeIndexException;
import java.io.IOException;

/**
 * Represent an intent to mark task as undone
 */
public class UnmarkCommand extends Command{

    int taskIndex;
    /**
     * Constructor for "UnmarkCommand" command with the task index to ubmark
     * @param input index provided by user
     * @throws DukeIndexException if index provided by user is not within range
     */
    public UnmarkCommand(String input) throws DukeIndexException {
        int index = Integer.parseInt(input)-1;
        if (index>=list.size()){
            throw new DukeIndexException("Invalid list item :<");
        }else {
            taskIndex = index;
        }
    }

    /**
     * Marks task as undone and saves to storage file
     * @throws IOException if file cannot be found
     */
    @Override
    public void execute() throws IOException {
        list.get(taskIndex).unmark();
        System.out.println("OK, I've marked this task as not done yet: ");
        System.out.println(list.get(taskIndex));
        Storage.saveListToFile();
    }
}
