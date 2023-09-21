package Data;

/**
 * Class to represent an event task
 */
public class Event extends Task {
    protected String from;
    protected String to;

    /**
     * Constructor for Event task
     * @param description Description of the task
     * @param from Start date of the event
     * @param to End date of the event
     */
    public Event (String description, String from, String to) {
        super(description);
        this.from = from;
        this.to = to;
    }

    /**
     * Returns the string representation of the start date
     * @return String representation of the start date
     */
    public String getFrom() {
        return this.from;
    }

    /**
     * Returns the string representation of the end date
     * @return String representation of the end date
     */
    public String getTo() {
        return this.to;
    }

    /**
     * Returns the string representation of the task
     * @return String representation of the task
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + this.from + " to: " + this.to + ")";
    }
}
