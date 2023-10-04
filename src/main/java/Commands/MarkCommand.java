package Commands;

import Storage.Storage;
import static Task.TaskList.list;

import Exceptions.DukeIndexException;
import java.io.IOException;


public class MarkCommand extends Command{

    int taskIndex;

    public MarkCommand(String input) throws DukeIndexException {
        int index = Integer.parseInt(input)-1;
        if (index>=list.size()){
            throw new DukeIndexException("Invalid list item :<");
        }else {
            taskIndex = index;
        }
    }

    @Override
    public void execute() throws IOException {
        list.get(taskIndex).mark();
        System.out.println("Nice! I've marked this task as done: ");
        System.out.println(list.get(taskIndex));
        Storage.saveListToFile();
    }
}
