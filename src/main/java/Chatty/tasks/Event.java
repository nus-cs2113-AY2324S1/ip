/**
 * The Event class represents a task with an event date.
 */
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
    /**
     * Gets the description for the deadline task
     */
    @Override
    public String getDescription() {
        return "[E][" + getStatusIcon() + "] " + super.getDescription() + " (from: " + from + " to: " + to + ")";
    }

    @Override
    public String saveFormat() {
        return "E | " + (getIsDone() ? "1" : "0") + " | " + description + " | " + from + " to " + to;
    }
}
