package taskmanagement;

public class Event extends Task {
    protected String from;
    protected String to;
    public Event(String description, String from, String to) {
        super(description);
        this.from = from;
        this.to = to;
        taskType = "E";
    }
    public Event(String description, String from, String to, Boolean isDone) {
        super(description);
        this.from = from;
        this.to = to;
        taskType = "E";
        this.isDone = isDone;
    }
    @Override
    public String toString() {
        return "[" + taskType + "]" + "[" + getStatusIcon() + "] " + description + " (from: " + from + " to: " + to + ")";
    }
    public String getTo(){ return to; }
    public String getFrom(){ return from; }
}
