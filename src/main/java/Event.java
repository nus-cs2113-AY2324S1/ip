/**
 * The Event class that represents the event task in the task list which has the start and end date.
 */
public class Event extends Task{
    private String from;
    private String to;

    /**
     * Creates an event task.
     *
     * @param name the name of the event
     *
     * @param from the start time of the event as a string
     *
     * @param to the end time of the event as a string
     */
    public Event(String name, String from, String to) {
        super(name);
        this.from = from;
        this.to = to;
    }

    public String getFrom() {
        return from;
    }

    public String getTo() {
        return to;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + from + " to: " + to + ")";
    }
}
