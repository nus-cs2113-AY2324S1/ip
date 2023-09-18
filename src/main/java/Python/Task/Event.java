package Python.Task;

public class Event extends Task {
    protected String from, to;

    public Event(String description, String from, String to) {
        super(description);
        this.from = from;
        this.to = to;
    }

    public String getTypeIcon() {
        return "[E]";
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

    @Override
    public String toDiskSaveFormat() {
        return super.toDiskSaveFormat() + " | " + getFrom() + " | " + getTo();
    }
}
