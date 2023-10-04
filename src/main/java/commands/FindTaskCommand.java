package commands;

import exceptions.UserInputValidation;
import exceptions.ZranExceptions;
import taskmanagement.Event;
import taskmanagement.Storage;
import taskmanagement.Task;
import taskmanagement.TaskList;
import userinputs.Ui;

import java.util.ArrayList;

/**
 * Find tasks in the task list that match a given keyword.
 * Extends the abstract Commands class.
 */
public class FindTaskCommand extends Commands{
    public FindTaskCommand(String input) {
        super(input);
    }

    /**
     * Executes the find task command, by calling the findTask function.
     *
     * @param tasks   The task list of class 'TaskList' where the task is to be deleted.
     * @param ui      The ui component of class 'UI' for displaying messages.
     * @param storage The storage component of class 'Storage' for saving task data.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ArrayList<Task> filteredTasks = new ArrayList<>();
        String key = input.substring(Commands.USER_FIND_COMMAND.length()).trim();
        filteredTasks = findTask(key, tasks.listItems.toArray(new Task[0]));
        if(!filteredTasks.isEmpty()){
            Ui.echo(filteredTasks);
        } else{
            System.out.println("    Oopsies! Looks like there are no tasks with " +
                    key + "!");
            Ui.showLine();
        }
    }

    /**
     * Filters tasks in the task list by a keyword.
     * @param key   The keyword to search for in task descriptions.
     * @param items The array of tasks to search within.
     * @return An ArrayList of tasks matching the given keyword.
     */
    public static ArrayList<Task> findTask(String key, Task... items) {
        // decided to experiment with the use of Varargs 
        ArrayList<Task> filteredTasks = new ArrayList<>();
        int index = 0;
        for (Task item : items) {
            if (item != null && item.getDescription().contains(key)) {
                filteredTasks.add(item);
            }
        }
        return filteredTasks;
    }
}
