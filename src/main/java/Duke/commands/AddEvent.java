package duke.commands;

import duke.inputProcess.Parser;
import duke.inputProcess.TaskList;

import java.time.format.DateTimeParseException;

/**
 * The `AddEvent` class is responsible for adding event tasks to the task list in the Duke robot.
 * It parses user input and extracts the necessary information to create an event task.
 */
public class AddEvent {
    private final Parser parser;
    private final TaskList tasks;

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
        Parser eventParse;
        try {
            try {
                // Attempt to parse the event input and add the task to the list
                eventParse = parser.getEventInput();
                if (eventParse.getTaskTime2().isBefore(eventParse.getTaskTime1())) {
                    System.out.println("\tStart time cannot be later than end time");
                    return;
                }
            } catch (DateTimeParseException e) {
                // Handle invalid date/time format
                System.out.println("\tThe input format for event needs to be \"event eventName /from dd/MM/yyyy HHmm /to dd/MM/yyyy HHmm\"");
                return;
            }
            tasks.addEvent(parser.getTaskName(), parser.getTaskTime1(), parser.getTaskTime2());
            System.out.println("\tGot it. I've added this task:\n\t\t" + tasks.getByIndex(tasks.getSize() - 1) +
                    "\n\tNow you have " + tasks.getSize() + " tasks in the list.");
        } catch (IndexOutOfBoundsException e) {
            // Handle missing "/from" and "/to" separators in the input
            System.out.println("\tOOPS!!! The event timing needs to be separated by \"/from\" and \"/to\"");
        }
    }
}
