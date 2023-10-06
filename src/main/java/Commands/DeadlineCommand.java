package Commands;

import Storage.Storage;
import Task.Deadline;
import static Task.TaskList.list;

import java.io.IOException;
import Exceptions.DukeFormatException;

/**
 * Represent an intent to create deadline task
 */
public class DeadlineCommand extends Command{
    String task;
    String deadline;

    /**
     * Constructor for "DeadlineCommand" command with the task to create
     *
     * @param input string input provided by user
     * @throws DukeFormatException if format provided by user is not what is expected
     */
    public DeadlineCommand(String input) throws DukeFormatException {
        if (!input.contains(" /by")) {
            throw new DukeFormatException("Ohno... Please check your format and include '/by'~");
        } else {
            String[] parts = input.split(" /by ");
            if (parts[0].length() <= "deadline ".length()) {
                throw new DukeFormatException("Task cannot be empty :< Please provide a valid event description.");
            }
            task = parts[0].substring("deadline ".length());
            if (parts.length != 2 || task.isEmpty() || parts[1].isEmpty()) {
                throw new DukeFormatException("Task or deadline cannot be empty... Please check your input again~");
            }
            deadline = parts[1];
        }
    }

    /**
     * Creates deadline task in TaskList and saves to storage file
     * @throws IOException if file cannot be found
     */
    @Override
    public void execute() throws IOException {
        Deadline deadlineTask = new Deadline(task, deadline);
        list.add(deadlineTask);
        System.out.println("Got it. I've added this task:");
        System.out.println(deadlineTask);
        System.out.println("Now you have " + (list.size()) + " tasks in the list.");
        Storage.saveListToFile();
    }
}