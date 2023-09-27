package linguobot.task;

/**
 * The <code>Event</code> class represents a type of task with an event schedule. It extends the <code>Task</code> class
 * and includes fields to store the start and end times of the event.
 */
public class Event extends Task {
    protected String from;
    protected String to;
    /**
     * Constructs a new <code>Event</code> task with a description, start time, and end time.
     *
     * @param description A description of the <code>Event</code> task.
     * @param from The start time of the <code>Event</code>.
     * @param to The end time of the <code>Event</code>.
     */
    public Event (String description, String from, String to) {
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
    public String toString() {
        return "[E][" + getStatusIcon() + "]" + description +
                " (from:" + getFrom() + "to:" + getTo() + ")";
    }

    @Override
    public String toFileString() {
        return "E | " + (isDone ? "1" : "0") + " | " + description +
        " | " + getFrom() + "to" + getTo();
    }
}
