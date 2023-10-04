package Commands;

import Storage.Storage;
import static Task.TaskList.list;

import Exceptions.DukeIndexException;
import java.io.IOException;

public class UnmarkCommand extends Command{

    int taskIndex;

    public UnmarkCommand(String input) throws DukeIndexException {
        int index = Integer.parseInt(input)-1;
        if (index>=list.size()){
            throw new DukeIndexException("Invalid list item :<");
        }else {
            taskIndex = index;
        }
    }

    @Override
    public void execute() throws IOException {
        list.get(taskIndex).unmark();
        System.out.println("OK, I've marked this task as not done yet: ");
        System.out.println(list.get(taskIndex));
        Storage.saveListToFile();
    }
}
