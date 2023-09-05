public class Event extends Task {

    private String from;
    private String to;

    public Event(String name, String from, String to) {
        super(name);
        this.from = from;
        this.to = to;
    }

    @Override
    public String addMessage() {
        return "Got it. I've added this task:\n" +
                "[E][ ] borrow book (from: " + from + " to: " + to + ")\n" +
                "Now you have " + numberOfTasks + " tasks in the list.";
    }

    @Override
    public String listText() {
        return "[E] " + super.listText() + " (from: " + from + " to: " + to + ")";
    }
}
