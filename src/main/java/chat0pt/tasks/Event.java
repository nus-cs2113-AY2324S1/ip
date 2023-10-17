package chat0pt.tasks;

public class Event extends Task {
    private final String from;
    private final String to;

    /**
     * Constructor for event
     * @param task Name of the task
     * @param from Start of event
     * @param to End of event
     */
    public Event(String task, String from, String to) {
        super(task);
        this.from = from;
        this.to = to;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from:" + from + " to:" + to + ")";
    }

    @Override
    public String toFile() {
        return "E#" + super.toFile() + "#" + from + "#" + to;
    }
}
