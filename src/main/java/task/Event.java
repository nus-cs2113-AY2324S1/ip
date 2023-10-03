package task;

public class Event extends Task {

    protected String from;
    protected String to;

    public Event(String description, String from, String to) {
        super(description);
        this.from = from;
        this.to = to;
    }

    // String[] in the format:
    // details[0] = description
    // details[1] = from
    // details[2] = to
    public Event(String[] details) {
        super(details[0]);
        this.from = details[1];
        this.to = details[2];
    }

    public String getCode() {
        return "E";
    }

    @Override
    public String toString() {
        return super.toString() + " (from: " + this.from + " to: " + this.to + ")";
    }
}
