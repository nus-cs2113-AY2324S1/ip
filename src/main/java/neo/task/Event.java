package neo.task;


public class Event extends Task {

    protected String from;
    protected String to;


    public Event(String description, String from, String to) {
        super(description);
        this.from = from;
        this.to = to;
    }

    @Override
    public String formatTask() {
        return "E / " + super.formatTask() + " / " + from + " / " + to;
    }
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + dateTimeFormatter(from) + " to: " + dateTimeFormatter(to) + ")";
    }
}
