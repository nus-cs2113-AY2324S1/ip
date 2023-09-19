package Chatty.tasks;

public class Event extends Task {
    private String from;
    private String to;


    public Event(String description, String from, String to) {
        super(description);
        this.from = from;
        this.to = to;
    }

    public String getFrom() {
        return from;
    }

    public String getTo() {
        return to;
    }

    @Override
    public String getDescription() {
        return "[E][" + getStatusIcon() + "] " + super.getDescription() + " (from: " + from + " to: " + to + ")";
    }
}
