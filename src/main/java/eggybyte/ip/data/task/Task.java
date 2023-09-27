package eggybyte.ip.data.task;

public class Task {
    protected final String description;

    public Task(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}