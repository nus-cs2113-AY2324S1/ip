package alan.data.task;

public class Event extends Task {
    protected String from;
    protected String to;

    public Event(String description, String from, String to) {
        super(description, TaskType.E);
        this.from  = from;
        this.to = to;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    @Override
    public String toString() {
        return "[" + super.getTaskType() + "]" + "[" + super.getStatusIcon() + "] " + super.toString() + " (from: " + from + " | to: "+ to + ")";
    }
}
