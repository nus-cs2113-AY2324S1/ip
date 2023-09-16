package duke.event;

import duke.task.Task;

public class Event extends Task {
    protected String from;
    protected String to;

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
}
