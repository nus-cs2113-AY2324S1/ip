package dawson.task;

import java.time.format.DateTimeFormatter;

public abstract class Task {

    protected String description;
    protected boolean isDone;

    public abstract String encode();

    protected final DateTimeFormatter showDateTimeFormat = DateTimeFormatter
        .ofPattern("dd MMM yyyy, HH:mm");

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public String getDescription() {
        return description;
    }

    public boolean getIsDone() {
        return isDone;
    }

    public void markAsDone() {
        isDone = true;
    }

    public void unmark() {
        isDone = false;
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    @Override
    public String toString() {
        return String.format("[%s] %s", getStatusIcon(), description);
    }

}
