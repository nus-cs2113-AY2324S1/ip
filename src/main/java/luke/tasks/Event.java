package luke.tasks;
import luke.user.LukeException;

/**
 * The Event class represents a task of type "Event" in the Luke application.
 * It extends the Task class and includes specific behavior for event tasks.
 */
public class Event extends Task {
    protected String startDate;
    protected String endDate;
    protected String eventGuide = "\tevent <description> /from <start date> /to <end date>";

    /**
     * Constructs an Event task with the specified task description, start date, and end date.
     *
     * @param taskDescription The description of the event task including start and end dates.
     * @throws LukeException If there are syntax or formatting errors in the task description.
     */
    public Event(String taskDescription) throws LukeException {
        super(taskDescription);

        int fromIndex = taskDescription.indexOf("/from ");
        int toIndex = taskDescription.indexOf("/to ");
        if (fromIndex == 0) {
            System.out.println("\tThere is a missing task description. Please follow this format:");
            printGuide();
            throw new LukeException();
        } else if (fromIndex < 0 || toIndex <= fromIndex) {
            System.out.println("\tThere is a syntax problem. Please follow this format:");
            printGuide();
            throw new LukeException();
        }

        description = taskDescription.substring(0, fromIndex);
        setDates(taskDescription.substring(fromIndex + 6, toIndex), taskDescription.substring(toIndex + 4));
    }

    /**
     * Gets the start date of the event.
     *
     * @return The start date of the event.
     */
    public String getStartDate() {
        return startDate;
    }

    /**
     * Gets the end date of the event.
     *
     * @return The end date of the event.
     */
    public String getEndDate() {
        return endDate;
    }

    /**
     * Parses and sets the start and end dates of the event from the provided date string.
     *
     * @param fromDateString The start date string.
     * @param toDateString   The end date string.
     * @throws LukeException If there are syntax or formatting errors in the date string.
     */
    public void setDates(String fromDateString, String toDateString) throws LukeException {
        if (fromDateString.isEmpty() || toDateString.isEmpty()) {
            System.out.println("\tThere is a missing date. Please follow this format:");
            printGuide();
            throw new LukeException();
        }

        startDate = fromDateString;
        endDate = toDateString;
    }

    /**
     * Displays a guide on how to format an event task when printing an error message.
     */
    @Override
    public void printGuide() {
        System.out.println(eventGuide);
    }

    /**
     * Returns a string representation of the event task for printing.
     *
     * @return A string representation of the event task.
     */
    @Override
    public String toString() {
        return "\t[E]" + getIsDone() + " " + getDescription() + "(from: " + getStartDate() + "to: " + getEndDate() + ")";
    }

    /**
     * Returns a string representation of the event task for memory storage.
     *
     * @return A string representation of the event task for memory storage.
     */
    @Override
    public String memoryString() {
        return "[E]" + getIsDone() + " " + getDescription() + "/from " + getStartDate() + "/to " + getEndDate();
    }
}
