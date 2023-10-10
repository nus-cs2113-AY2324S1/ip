package eggybyte.ip.data.task;

/**
 * The base class for all tasks.
 */
public class Task {
    public final String description;

    /**
     * Creating a new Task.
     */
    public Task(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return description;
    }
}