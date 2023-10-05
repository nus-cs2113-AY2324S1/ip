package Commands;

import Storage.Storage;
import Task.TaskList;
import static Task.TaskList.list;

import java.io.IOException;
import Exceptions.DukeFormatException;

/**
 * Represents an intent to create a new event task
 */
public class EventCommand extends Command{
    String task;
    String from;
    String to;

    /**
     * Constructor for "EventCommand" command with the task to create
     * @param input string input provided by user
     * @throws DukeFormatException if format provided by user is not what is expected
     */
    public EventCommand(String input) throws DukeFormatException {
        if (!input.contains("/from") || !input.contains("/to")) {
            throw new DukeFormatException("Uhoh... Please check your format and include '/from' and '/to'~");
        } else {
            String[] parts = input.split(" /");
            //check if task, to, from are null
            if (parts.length != 3 || parts[0].isEmpty() || parts[1].equals("from") || parts[2].equals("to")) {
                throw new DukeFormatException("Task.Task, from or to cannot be empty... Please check your input again~");
            }
            task = parts[0].substring("event ".length());
            from = parts[1].substring("from".length());
            to = parts[2].substring("to".length());
        }
    }

    /**
     * Creates event task in TaskList and saves to storage file
     * @throws IOException if file cannot be found
     */
    @Override
    public void execute() throws IOException {
        TaskList.createEventTasks(task, from, to);
        System.out.println("Got it. I've added this task:");
        System.out.println(task);
        System.out.println("Now you have " + (list.size()) + " tasks in the list.");
        Storage.saveListToFile();
    }
}