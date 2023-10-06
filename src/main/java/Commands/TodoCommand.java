package Commands;

import Storage.Storage;
import Task.TaskList;
import static Task.TaskList.list;

import java.io.IOException;
import Exceptions.DukeFormatException;
import Task.Todo;

/**
 * Represent an intent to create a todo task
 */
public class TodoCommand extends Command{
   String task;

    /**
     * Constructor for 'TodoCommand" command with the task to create
     * @param inputTask from string input provided by user
     * @throws DukeFormatException if format provided by user is not what is expected
     */
   public TodoCommand(String inputTask) throws DukeFormatException {
       if (inputTask.isEmpty()) {
           throw new DukeFormatException("Task or deadline cannot be empty... Please check your input again~");
       }
       task = inputTask;
   }

    /**
     * Creates todo task in TaskList and saves to storage file
     * @throws IOException if file cannot be found
     */
    @Override
    public void execute() throws IOException {
        Todo todoTask = new Todo(task);
        list.add(todoTask);
        System.out.println("Got it. I've added this task:");
        System.out.println(todoTask);
        System.out.println("Now you have " + (list.size()) + " tasks in the list.");
        Storage.saveListToFile();
    }
}
