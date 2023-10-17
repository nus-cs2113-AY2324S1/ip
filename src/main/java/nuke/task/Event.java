package nuke.task;

import nuke.NukeDateTime;

import static nuke.task.TaskParser.TASK_FORMAT_SEPARATOR;

/**
 * Represents an event that has the start and the end.
 */
public class Event extends Task {
    private NukeDateTime from;
    private NukeDateTime to;

    /**
     * Constructs an event with the name, the start period, and the end period.
     *
     * @param name name of the task.
     * @param from start period of the task.
     * @param to end period of the task.
     */
    public Event(String name, String from, String to) {
        super(name);
        setFrom(from);
        setTo(to);
    }

    /**
     * Returns start period of the event.
     *
     * @return start period of the event
     */
    public String getFrom() {
        return from.toString();
    }

    /**
     * Sets start period of the event.
     *
     * @param from start period of the event
     */
    public void setFrom(String from) {
        this.from = new NukeDateTime(from);
    }

    /**
     * Returns end period of the event.
     *
     * @return end period of the event
     */
    public String getTo() {
        return to.toString();
    }

    /**
     * Sets end period of the event.
     *
     * @param to end period of the event
     */
    public void setTo(String to) {
        this.to = new NukeDateTime(to);
    }

    @Override
    public String getType() {
        return TYPE;
    }

    @Override
    public String toString() {
        return String.format("%s (from: %s to: %s)",
                super.toString(), getFrom(), getTo());
    }

    @Override
    public String formatData() {
        return super.formatData() + TASK_FORMAT_SEPARATOR
                + getFrom() + TASK_FORMAT_SEPARATOR
                + getTo();
    }

    public static final String TYPE = "E";
}
