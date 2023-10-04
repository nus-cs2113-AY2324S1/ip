package Commands;

import Storage.Storage;
import Task.TaskList;
import static Task.TaskList.list;

import java.io.IOException;
import Exceptions.DukeFormatException;


public class DeadlineCommand extends Command{
    String task;
    String deadline;

    public DeadlineCommand(String input) throws DukeFormatException {
        if (!input.contains("/by")) {
            throw new DukeFormatException("Ohno... Please check your format and include '/by'~");
        } else {
            String[] parts = input.split(" /by ");
            //check if task or deadline are null
            if (parts.length != 2 || parts[0].isEmpty() || parts[1].isEmpty()) {
                throw new DukeFormatException("Task or deadline cannot be empty... Please check your input again~");
            }
            task = parts[0].substring("deadline ".length());
            deadline = parts[1];
        }
    }

    @Override
    public void execute() throws IOException {
        TaskList.createDeadlineTasks(task, deadline);
        System.out.println("Got it. I've added this task:");
        System.out.println(task);
        System.out.println("Now you have " + (list.size()) + " tasks in the list.");
        Storage.saveListToFile();
    }
}