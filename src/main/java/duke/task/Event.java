package duke.task;

/**
 * Type of task that represents an event with start/end times.
 */
public class Event extends Task {
    private String from;
    private String to;
    public Event(String description, String from, String to) {
        super(description);
        this.from = from;
        this.to = to;
    }

    @Override
    public String getFormattedTask() {
        return "[E] " + super.getFormattedTask() + " (from: " + from + " to: " + to + ")";
    }

    @Override
    public String getSerializedString() {
        return "E|" + super.getSerializedString() + "|" + from + "|" + to;
    }
}
