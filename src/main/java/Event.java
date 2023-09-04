public class Event extends Task {
    protected String start;
    protected String end;

    public Event(String description, String start, String end) {
        super(description);
        this.start = start;
        this.end = end;
    }

    @Override
    public String getTypeIcon() {
        return "E";
    }

    @Override
    public String getTask() {
        return String.format("[%s][%s] %s (from: %s to: %s)",
                getTypeIcon(), getStatusIcon(), description, start, end);
    }
}
