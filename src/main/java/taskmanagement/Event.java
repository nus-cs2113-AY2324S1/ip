package taskmanagement;

import java.time.LocalDate;

public class Event extends Task {
//    protected String from;
//    protected String to;
    protected LocalDate from;
    protected LocalDate to;
    public Event(String description, LocalDate from, LocalDate to) {
        super(description);
        this.from = from;
        this.to = to;
        taskType = "E";
    }
    public Event(String description, LocalDate from, LocalDate to, Boolean isDone) {
        super(description);
        this.from = from;
        this.to = to;
        taskType = "E";
        this.isDone = isDone;
    }
    @Override
    public String toString() {
        return "[" + taskType + "]" + "[" + getStatusIcon() + "] " + description + " (from: " + from.format(formatter) + " to: " + to.format(formatter) + ")";
    }
    public LocalDate getTo(){ return to; }
    public LocalDate getFrom(){ return from; }
}
