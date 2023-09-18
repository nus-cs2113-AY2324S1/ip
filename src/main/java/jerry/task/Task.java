package jerry.task;

import java.io.Serializable;

public abstract class Task implements Serializable {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public Boolean isDone() {
        return this.isDone;
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " ");
    }

    public String getStatusInt() {
        return (isDone ? "1" : "0");
    }

    public void markAsDone() {
        this.isDone = true;
    }

    public void markAsUndone() {
        this.isDone = false;
    }

    public String getDescription() {
        return this.description;
    }

    @Override
    public abstract String toString();
}

