package Command;

import Soccat.Task;
import Storage.TaskList;

import java.util.ArrayList;

public class ListCommand extends Command{

    public static final String COMMAND_WORD = "list";

    @Override
    public void execute(TaskList tasks) {
        ArrayList<Task> taskArrayList = tasks.getTasks();
        if (taskArrayList.isEmpty()) {
            System.out.println("Great, You have no tasks for now!");
        }
        for (int i = 0; i < taskArrayList.size(); i++) {
            // Add 1 to change array index to 1 based index
            int arrayIndex = i+1;
            System.out.println(arrayIndex + ". " + taskArrayList.get(i));
        }
    }
}
