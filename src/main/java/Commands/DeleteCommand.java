package Commands;

import static Task.TaskList.list;
import Storage.Storage;

import Exceptions.DukeIndexException;
import java.io.IOException;


public class DeleteCommand extends Command{
    int taskIndex;

    public DeleteCommand(String input) throws DukeIndexException {
        int index = Integer.parseInt(input)-1;
        if (index>=list.size()){
            throw new DukeIndexException("Invalid list item :<");
        }else {
            taskIndex = index;
        }
    }

    @Override
    public void execute() throws IOException {
        System.out.println("Noted. I've removed this task:");
        System.out.println(list.get(taskIndex));
        list.remove(taskIndex);
        System.out.println("Now you have " + (list.size()) + " tasks in the list.");
        Storage.saveListToFile();
    }
}
