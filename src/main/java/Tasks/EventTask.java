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
     * Extracts the part of the description before "/from" if "/from" is found,
     * otherwise, it extracts the part of the description before "/to" if "/to" is found,
     * or returns the full description if neither "/from" nor "/to" is found.
     *
     * @param description The description of the event task.
     * @return The extracted part of the description based on the conditions.
     */
    private String eventDescription(String description) {
        int fromIndex = description.indexOf("/from");
        int toIndex = description.indexOf("/to");

        if (fromIndex != -1) {
            return description.substring(0, fromIndex).trim();
        } else if (toIndex != -1) {
            return description.substring(0, toIndex).trim();
        } else {
            return description.trim();
        }
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
            } else {
                // If there is "/from" but no "/to," return the portion after "/from."
                return description.substring(fromIndex + 5).trim();
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
    public String getEventDescription() {
        return eventDescription(super.getDescription());
    }

    public String toString() {
        return "[E]" + "[" + getStatusIcon() + "] " + this.getEventDescription() + " (from: " + this.from + " to: " + this.to + ")";
    }

}
