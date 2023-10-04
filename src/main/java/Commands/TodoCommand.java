package Commands;

import Storage.Storage;
import Task.TaskList;
import static Task.TaskList.list;

import java.io.IOException;
import Exceptions.DukeFormatException;


public class TodoCommand extends Command{
   String task;

   public TodoCommand(String task) throws DukeFormatException {
        task = this.task;
       if (task.isEmpty()) {
           throw new DukeFormatException("Task or deadline cannot be empty... Please check your input again~");
       }
   }

    @Override
    public void execute() throws IOException {
        TaskList.createTodoTasks(task);
        System.out.println("Got it. I've added this task:");
        System.out.println(task);
        System.out.println("Now you have " + (list.size()) + " tasks in the list.");
        Storage.saveListToFile();
    }
}
