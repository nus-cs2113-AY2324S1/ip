package Soccat;

/**
 * Represents a deadline with <code>task</code>, <code>start</code> and <code>end</code>.
 * */

public class Event extends Task {

    public static final String TASK_CHAR = "E";
    public static final int NAME_IDX = 2;
    public static final int FROM_IDX = 3;
    public static final int TO_IDX = 4;
    protected String from;
    protected String to;

    public Event(String name, String from, String to) {
        super(name);
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
     * Converts an event object to a string,
     * delimited by the token <code>SPLIT_CHAR</code>
     *
     * @return string of parsed event.
     * */
    public String toTokenString() {
        String description = super.getName() + Task.SPLIT_CHAR + from + Task.SPLIT_CHAR + to;
        if (super.getDone()) {
            return TASK_CHAR + Task.SPLIT_CHAR + Task.DONE_CHAR + Task.SPLIT_CHAR + description + "\n";
        } else {
            return TASK_CHAR + Task.SPLIT_CHAR + Task.NOT_DONE_CHAR + Task.SPLIT_CHAR + description + "\n";
        }
    }

    @Override
    public String toString() {
        String event = " (from: " + from + " to: " + to + ")";
        return "[" + TASK_CHAR + "]" + super.toString() + event;
    }
}
