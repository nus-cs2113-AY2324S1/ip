package Commands;

import static Task.TaskList.list;
import Storage.Storage;

import Exceptions.DukeIndexException;
import java.io.IOException;

/**
 * Represent an intent to delete a task from the list
 */
public class DeleteCommand extends Command{
    int taskIndex;

    /**
     * Constructor for "DeleteCommand" with the task number to be deleted
     * @param input The number of the task to be deleted in the form os String
     * @throws DukeIndexException if the index provided by user is not within range of list
     */
    public DeleteCommand(String input) throws DukeIndexException {
        int index = Integer.parseInt(input)-1;
        if (index>=list.size()){
            throw new DukeIndexException("Invalid list item :<");
        }else {
            taskIndex = index;
        }
    }

    /**
     * Deletes intended task from list and updates storage
     */
    @Override
    public void execute() throws IOException {
        System.out.println("Noted. I've removed this task:");
        System.out.println(list.get(taskIndex));
        list.remove(taskIndex);
        System.out.println("Now you have " + (list.size()) + " tasks in the list.");
        Storage.saveListToFile();
    }
}
