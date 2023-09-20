package lemon.task;

public class Event extends Task {
    protected String from;
    protected String to;

    public Event(String description, boolean isDone, String start, String end) {
        super(description, isDone);
        this.from = start;
        this.to = end;
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
