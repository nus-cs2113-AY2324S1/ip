package lemon.task;

public class Event extends Task {
    private final String from;
    private final String to;

    public Event(String description, String start, String end, boolean isDone) {
        super(description, isDone);
        this.from = start;
        this.to = end;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + from + " to: " + to + ")";
    }

    public String toFile() {
        return "E" + super.toFile() + " | " + from + " | " + to;
    }
}
