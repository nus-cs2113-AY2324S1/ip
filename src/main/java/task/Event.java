package task;

public class Event extends Task {
    protected String from;
    protected String to;
    public Event(String description, String from, String to) {
        super(description, false);
        this.from = from;
        this.to = to;
    }

    @Override
    public String getExcess() {
        return from + " | " + to;
    }

    @Override
    public String getType() {
        return "E";
    }
    @Override
    public String getStatus() {
        return "[E]" + super.getStatus() + " (from: " + from + " to: " + to + ")";
    }

}
