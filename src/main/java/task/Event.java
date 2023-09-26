package task;
/**
 * Represents an event task with an additional 'from' and 'to' variable to store task duration
 */
public class Event extends Task {
    protected String from;
    protected String to;
    /**
     * Creates a new event task object
     *
     * @param description The details of the task
     * @param from Start of event
     * @param to End of event
     */
    public Event(String description, String from, String to) {
        super(description, false);
        this.from = from;
        this.to = to;
    }
    /**
     * Override the excess information output of the task
     */
    @Override
    public String getExcess() {
        return from + " | " + to;
    }
    /**
     * Override the type of the task to 'E'
     */
    @Override
    public String getType() {
        return "E";
    }
    /**
     * Override the full detail of the task
     */
    @Override
    public String getStatus() {
        return "[E]" + super.getStatus() + " (from: " + from + " to: " + to + ")";
    }

}
