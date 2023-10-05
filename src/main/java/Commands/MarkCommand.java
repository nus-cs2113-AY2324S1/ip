package Commands;

import Storage.Storage;
import static Task.TaskList.list;

import Exceptions.DukeIndexException;
import java.io.IOException;

/**
 * Represent an intent to mark task as done
 */
public class MarkCommand extends Command{

    int taskIndex;

    /**
     * Constructor for "MarkCommand" command with the task index to mark
     * @param input index provided by user
     * @throws DukeIndexException if index provided by user is not within range
     */
    public MarkCommand(String input) throws DukeIndexException {
        int index = Integer.parseInt(input)-1;
        if (index>=list.size()){
            throw new DukeIndexException("Invalid list item :<");
        }else {
            taskIndex = index;
        }
    }

    /**
     * Marks task as done and saves to storage file
     * @throws IOException if file cannot be found
     */
    @Override
    public void execute() throws IOException {
        list.get(taskIndex).mark();
        System.out.println("Nice! I've marked this task as done: ");
        System.out.println(list.get(taskIndex));
        Storage.saveListToFile();
    }
}
