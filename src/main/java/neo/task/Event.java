package neo.task;

/**
 * Represents a form of task that has a starting date and due date.
 */
public class Event extends Task {

    protected String from;
    protected String to;

    /**
     * Constructs a task with a string to indicate the starting date and due date.
     *
     * @param description This is the description of the task.
     * @param from This is the description of the starting date.
     * @param to This is the description of the due date.
     */
    public Event(String description, String from, String to) {
        super(description);
        this.from = from;
        this.to = to;
    }

    /**
     * Returns the event in a format for saving into a .txt file.
     *
     * @return The formatted deadline.
     */
    @Override
    public String formatTask() {
        return "E / " + super.formatTask() + " / " + from + " / " + to;
    }

    /**
     * Returns the event in an easy-to-read format to the user.
     *
     * @return The formatted deadline.
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + dateTimeFormatter(from) + " to: " + dateTimeFormatter(to) + ")";
    }
}
