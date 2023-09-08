public class Event extends Task {
    private String from;
    private String to;

    public Event(String from, String to, String description) {
        super(description);
        this.from = from;
        this.to = to;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + "(from: " + this.from + "to: " + this.to + " )";
    }
}