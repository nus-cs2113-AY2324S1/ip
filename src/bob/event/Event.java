package bob.event;

import bob.task.Task;

public class Event extends Task {
    protected String from;
    protected String to;

    /**
     * Constructs new event.
     *
     * @param description description of event.
     * @param from start time of event.
     * @param to end time of event.
     */
    public Event(String description, String from, String to) {
        super(description);
        this.from = from;
        this.to = to;
    }

    @Override
    public String getTypeIcon() {
        return "E";
    }

    @Override
    public String getTask() {
        return String.format("[%s][%s] %s (from: %s to: %s)",
                getTypeIcon(), getStatusIcon(), description, from, to);
    }

    @Override
    public String getTaskForFile() {
        return String.format("%s | %b | %s | %s | %s", getTypeIcon(), isDone, description, from, to);
    }
}
