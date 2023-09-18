package lemon.task;

public class Event extends Task {
    protected String from;
    protected String to;

    public Event(String description, boolean isDone, String start, String end) {
        super(description, isDone);
        this.from = start;
        this.to = end;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + from + " to: " + to + ")";
    }
}
