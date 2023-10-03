package chat0pt.tasks;

public class Event extends Task {
    private final String from;
    private final String to;

    public Event(String tasks, String from, String to) {
        super(tasks);
        this.from = from;
        this.to = to;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from:" + from + " to:" + to + ")";
    }

    @Override
    public String toFile() {
        return "E#" + super.toFile() + "#" + from + "#" + to;
    }
}
