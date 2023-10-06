package duke;
public class Event extends Task {
    protected String from;
    protected String to;

    public Event(String description, String from, String to) {
        super(description);
        this.from = from;
        this.to = to;
    }

    @Override
    public String getTaskType() {
        return "Event";
    }

    // Getter method for 'from' date
    public String getFrom() {
        return from;
    }

    // Getter method for 'to' date
    public String getTo() {
        return to;
    }

    @Override
    public String toFileString() {
        String doneStatus = isDone ? "1" : "0";
        return "E | " + doneStatus + " | " + description + " | " + from + "-" + to;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + from + " to: " + to + ")";
    }
}


