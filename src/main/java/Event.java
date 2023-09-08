public class Event extends Todo {
    protected String from, to;

    public Event(String description, String from, String to) {
        super(description);
        this.from = from;
        this.to = to;
        isDone = false;
        type = "E";
    }

    @Override
    public String toString() {
        return super.toString() + " (from: " + from + " to:" + to + ")";
    }
}