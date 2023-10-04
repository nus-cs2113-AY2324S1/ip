package Tasks;

/**
 * Represents a task that is an event, which is a subclass of Task.
 */
public class EventTask extends Task {
    private final String from;
    private final String to;

    /**
     * Constructs an EventTask with the given description.
     *
     * @param description The description of the event task, including start and end dates (if provided).
     */
    public EventTask(String description) {
        super(description);
        this.from = this.extractFromDate(description);
        this.to = this.extractToDate(description);
    }

    /**
     * Extracts the start date from the task description.
     *
     * @param description The description of the event task.
     * @return The extracted start date or "No Start Date" if not found.
     */
    private String extractFromDate(String description) {
        int fromIndex = description.indexOf("/from");
        if (fromIndex != -1) {
            int toIndex = description.indexOf("/to");
            if (toIndex != -1 && toIndex > fromIndex) {
                return description.substring(fromIndex + 5, toIndex).trim();
            }
        }
        return "No Start Date";
    }

    /**
     * Extracts the end date from the task description.
     *
     * @param description The description of the event task.
     * @return The extracted end date or "No End Date" if not found.
     */
    private String extractToDate(String description) {
        int toIndex = description.indexOf("/to");
        return toIndex != -1 ? description.substring(toIndex + 3).trim() : "No End Date";
    }

    /**
     * Gets the start date of the event.
     *
     * @return The start date string.
     */
    public String getFrom() {
        return from;
    }

    /**
     * Gets the end date of the event.
     *
     * @return The end date string.
     */
    public String getTo() {
        return to;
    }

    /**
     * Returns a string representation of the EventTask.
     *
     * @return A string representation in the format: "[E][TaskDescription] (from: [Start Date] to: [End Date])".
     */
    public String toString() {
        String superString = super.toString();
        return "[E]" + superString + " (from: " + this.from + " to: " + this.to + ")";
    }
}
