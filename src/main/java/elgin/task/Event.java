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

    public String getFrom() {
        return from;
    }

    public String getTo() {
        return to;
    }

    @Override
    public String toString() {
        return super.toString() + " (from: " + getFrom() + " to: " + getTo() + ")";
    }
}
