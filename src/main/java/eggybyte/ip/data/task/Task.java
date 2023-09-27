package eggybyte.ip.data.task;

public class Task {
    public final String description;

    public Task(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return description;
    }
}