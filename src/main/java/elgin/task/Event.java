package elgin.task;

public class Event extends Task {

    protected String from;
    protected String to;

    public Event(String description, String from, String to) {
        super(description);
        type = "E";
        this.from = from;
        this.to = to;
    }

    public Event(String description, String from, String to, boolean isDone) {
        super(description);
        type = "E";
        this.from = from;
        this.to = to;
        setIsDone(isDone);
    }

    @Override
    public String getDescription() {
        return description + " (from: " + getFrom() + " to: " + getTo() + ")";
    }

    public String getFrom() {
        return from;
    }

    public String getTo() {
        return to;
    }
}
