package duke.commands;

import java.time.format.DateTimeParseException;
import duke.inputProcess.Parser;
import duke.inputProcess.TaskList;

/**
 * The `AddDeadline` class is responsible for adding deadline tasks to the task list in the Hilary robot.
 * It parses user input and extracts the necessary information to create a deadline task. This class handles the
 * processing of user input for adding deadline tasks, including validating the input format, extracting task details,
 * and adding the task to the task list.
 *
 * @author Cheung Ka Yuen
 * @version Final
 * @since 2023-10-24
 */
public class AddDeadline {
    private final Parser parser;
    private TaskList tasks;
    private static final String DATE_FORMAT_ERROR_MESSAGE = "The input format for deadline event needs to be " +
            "\"deadline deadlineEvent /by dd/MM/yyyy HHmm\"";
    private static final String MISSING_BY_MESSAGE = "OOPS!!! The deadline needs to be separated by \"/by\"";


    /**
     * Constructs an `AddDeadline` object with `Parser` and `TaskList`.
     *
     * @param parser The parser is to process input from the user.
     * @param tasks The task list where the deadline task will be added.
     */
    public AddDeadline(Parser parser, TaskList tasks) {
        this.parser = parser;
        this.tasks = tasks;
    }

    /**
     * Adds a deadline task to the task list.
     * Parses the input, extracts the task name and deadline time, then adds the deadline task to the list.
     * If the input format is incorrect, appropriate error messages are displayed.
     */
    public void addDeadlineTask() {
        try {
            // Attempt to parse the deadline input and add the task to the list
            Parser deadlineParse = parser.getDeadlineInput();
            tasks.addDeadline(deadlineParse.getTaskName(), parser.getTaskTime1());
            System.out.println("\tGot it. I've added this task:\n\t\t" + tasks.getByIndex(tasks.getSize() - 1) +
                    "\n\tNow you have " + tasks.getSize() + " tasks in the list.");
        } catch (DateTimeParseException e) {
            System.out.println("\t" + DATE_FORMAT_ERROR_MESSAGE);
        } catch (IndexOutOfBoundsException e) {
            System.out.println("\t" + MISSING_BY_MESSAGE);
        }
    }
}
