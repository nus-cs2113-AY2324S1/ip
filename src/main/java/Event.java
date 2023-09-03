public class Event extends Task {

    protected String from;
    protected String to;

    public Event(String name, String from, String to) {
        super(name);
        this.from = from;
        this.to = to;
    }

    @Override
    public String toString() {
        String event = super.toString() + " (from: " + from + " to: " + to + ")";
        if (super.getDone()) {
            return "[E][X] " + super.toString();
        } else {
            return "[E][ ] " + super.toString();
        }
    }
}
