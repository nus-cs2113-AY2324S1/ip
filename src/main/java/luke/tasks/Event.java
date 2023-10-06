package luke.tasks;
import luke.user.LukeException;

/**
 * The Event class represents a task of type "Event" in the LukeTime application.
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

        int slashCut = taskDescription.indexOf("/");
        if (slashCut <= 0) {
            System.out.println("\tThere is a missing task description. Please follow this format:");
            printGuide();
            throw new LukeException();
        }

        description = taskDescription.substring(0, slashCut);

        String taskDuration = taskDescription.substring(slashCut + 1);
        setDates(taskDuration);
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
     * @param dates The date string containing start and end date information.
     * @throws LukeException If there are syntax or formatting errors in the date string.
     */
    public void setDates(String dates) throws LukeException {
        String[] words = dates.split(" ");
        if (!words[0].equals("from")) {
            System.out.println("\tThere is a syntax problem. Please follow this format:");
            printGuide();
            throw new LukeException();
        }

        dates = dates.substring(5);

        int slashCut = dates.indexOf("/");
        if (slashCut <= 0) {
            System.out.println("\tThere is a syntax problem. Please follow this format:");
            printGuide();
            throw new LukeException();
        }

        startDate = dates.substring(0, slashCut);
        dates = dates.substring(slashCut + 1);

        words = dates.split(" ");
        if (!words[0].equals("to")) {
            System.out.println("\tThere is a syntax problem. Please follow this format:");
            printGuide();
            throw new LukeException();
        }

        endDate = dates.substring(3);
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
        //super.toString();
        String isDoneString;

        if (isDone()) {
            isDoneString = "[X]";
        } else {
            isDoneString = "[ ]";
        }

        return "\t[E]" + isDoneString + " " + getDescription() + "(from: " + getStartDate() + "to: " + getEndDate() + ")";
    }

    /**
     * Returns a string representation of the event task for memory storage.
     *
     * @return A string representation of the event task for memory storage.
     */
    @Override
    public String memoryString() {
        String isDoneString;

        if (isDone()) {
            isDoneString = "[X]";
        } else {
            isDoneString = "[ ]";
        }

        return "[E]" + isDoneString + " " + getDescription() + "/from " + getStartDate() + "/to " + getEndDate();
    }
}
