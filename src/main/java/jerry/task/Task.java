package jerry.task;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;

/**
 * Represents a task in the task list.
 */
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

    /**
     * Retrieves a listing of every word in the description, in order.
     */
    public List<String> getWordsInDescription() {
        return Arrays.asList(description.split("\\s+"));
    }

    @Override
    public abstract String toString();


    /**
     * Computes the string to store the task state in a file
     */
    public abstract String encode();
}

