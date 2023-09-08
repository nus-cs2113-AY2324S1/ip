public class Event extends Task {
    private String from;
    private String to;

    public Event(String from, String to, String description) {
        // call to the superclass (task) constructor
        super(description);
        this.from = from;
        this.to = to;
    }
    //to construct string representation of the object
    @Override
    public String toString() {
        return "[E]" + super.toString() + "(from: " + this.from + "to: " + this.to + " )";
    }
}