package duke.commands;

import duke.inputProcess.Parser;
import duke.inputProcess.TaskList;

import java.time.format.DateTimeParseException;

/**
 * The `AddEvent` class is responsible for adding event tasks to the task list in the Hilary robot.
 * It parses user input and extracts the necessary information to create an event task. This class handles the
 * processing of user input for adding event tasks, including validating the input format, extracting task details,
 * and adding the task to the task list. It ensures that the start time of the event is before the end time.
 *
 * @author Cheung Ka Yuen
 * @version Final
 * @since 2023-10-24
 */
public class AddEvent {
    private final Parser parser;
    private final TaskList tasks;
    private static final String DATE_FORMAT_ERROR_MESSAGE = "The input format for event needs to be " +
            "\"event eventName /from dd/MM/yyyy HHmm /to dd/MM/yyyy HHmm\"";
    private static final String START_TIME_ERROR_MESSAGE = "Start time cannot be later than end time";
    private static final String MISSING_TIMING_MESSAGE = "OOPS!!! The event timing needs to be separated by \"/from\" and \"/to\"";


    /**
     * Constructs an `AddEvent` object with `Parser` and `TaskList`.
     *
     * @param parser The parser is to process user input.
     * @param tasks The task list where the event task will be added.
     */
    public AddEvent(Parser parser, TaskList tasks) {
        this.parser = parser;
        this.tasks = tasks;
    }

    /**
     * Adds an event task to the task list.
     * Parses the input, extracts the event name, start time, and end time for the event,
     * and adds the task to the list.
     * If the input format is incorrect or the start time is later than the end time,
     * appropriate error messages are displayed.
     */
    public void addEventTask() {
        try {
            Parser eventParse = parser.getEventInput();
            if (eventParse.getTaskTime2().isBefore(eventParse.getTaskTime1())) {
                System.out.println(START_TIME_ERROR_MESSAGE);
                return;
            }
            tasks.addEvent(parser.getTaskName(), parser.getTaskTime1(), parser.getTaskTime2());
            System.out.println("\tGot it. I've added this task:\n\t\t" + tasks.getByIndex(tasks.getSize() - 1) +
                    "\n\tNow you have " + tasks.getSize() + " tasks in the list");
        } catch (DateTimeParseException e) {
            System.out.println("\t" + DATE_FORMAT_ERROR_MESSAGE);
        } catch (IndexOutOfBoundsException e) {
            System.out.println("\t" + MISSING_TIMING_MESSAGE);
        }
    }
}
