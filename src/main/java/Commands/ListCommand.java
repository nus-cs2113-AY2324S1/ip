package Commands;

import Task.TaskList;
import static Task.TaskList.list;

/**
 * Represent an intent to list all the tasks in TaskList
 */
public class ListCommand extends Command{

    /**
     * Iterates through list and prints tasks
     */
    @Override
    public void execute(){
        if (TaskList.size() == 0){
            System.out.println("Bop! You have no tasks...");
        } else {
            for (int i = 0; i < TaskList.size(); i++) {
                System.out.print((i + 1) + ". ");
                System.out.println(list.get(i));
            }
        }
    }
}
