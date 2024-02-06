package chattie;

import chattie.commands.*;
import chattie.tasks.Deadline;
import chattie.tasks.Event;
import chattie.tasks.Task;
import chattie.tasks.Todo;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Deals with making sense of data or the user command
 */
public class Parser {
    //deals with making sense of the user command

    private static int COMMAND_TYPE_INDEX = 0;
    private static int TASK_TYPE_INDEX = 0;
    private static int ISDONE_INDEX = 1;
    private static int TASK_DESCRIPTION_INDEX = 2;
    private static int DETAILS_INDEX = 3;
    private static int FROM_INDEX = 0;
    private static int TO_INDEX = 1;

    /**
     * Takes in user input and creates a new command object that corresponds to input
     *
     * @param command String that user has entered
     * @return The type of command
     */
    public static Command parse(String command) {
        String[] commandArray = command.split(" ");
        String commandType = commandArray[COMMAND_TYPE_INDEX];

        switch(commandType) {
        case "list":
            return new ListCommand();
        case "mark":
            return new MarkCommand(command);
        case "unmark":
            return new UnmarkCommand(command);
        case "delete":
            return new DeleteCommand(command);
        case "todo":
            return new TodoCommand(command);
        case "deadline":
            return new DeadlineCommand(command);
        case "event":
            return new EventCommand(command);
        case "bye":
            return new ExitCommand();
        case "find":
            return new FindCommand(command);
        default:
            return new EchoCommand(command);
        }
    }

    /**
     * Converts string to a task object
     *
     * @param line Line in chattie.txt
     * @return Task object that corresponds to line in chattie.txt
     */
    public static Task parseTask(String line) {
        Task task;
        String[] taskArray = line.split(" \\| ");
        String taskType = taskArray[TASK_TYPE_INDEX].trim();
        boolean isDone = taskArray[ISDONE_INDEX].equals("1");
        String description = taskArray[TASK_DESCRIPTION_INDEX] + " ";
        if (taskType.equals("T")) {
            task = new Todo(description);
        } else if (taskType.equals("D")) {
            String by = taskArray[DETAILS_INDEX];
            task = new Deadline(description, by);
        } else {
            String[] fromTo = taskArray[DETAILS_INDEX].split("-");
            String from = fromTo[FROM_INDEX];
            String to = fromTo[TO_INDEX];
            task = new Event(description, from, to);
        }
        task.setDone(isDone);
        return task;
    }

    /**
     * Converts yyyy/mm/dd to MMM d yyyy
     *
     * @param by Date of deadline
     * @return Formatted date string
     */
    public static String parseDate(String by) {
        by = by.replace("/", "-");
        try {
            LocalDate date = LocalDate.parse(by);
            return date.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
        } catch (Exception e) {
            return by;
        }
    }
}
