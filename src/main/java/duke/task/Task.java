package duke.task;

import org.json.JSONObject;

/**
 * Represents a task
 */
public class Task {
    private boolean isDone;
    private String taskName;

    /**
     * Creates a new task with its name and the status on whether it is completed
     *
     * @param taskName Name of task
     * @param isDone Boolean representing whether it has been completed
     */
    public Task(String taskName, boolean isDone) {
        this.isDone = isDone;
        this.taskName = taskName;
    }

    /**
     * Returns a string representation of the task.
     *
     * @return A string representation of the task.
     */
    @Override
    public String toString() {
        if(!this.isDone) {
            return ("[ ] " + this.taskName);
        } else {
            return ("[X] " + this.taskName);
        }
    }


    /**
     * Returns the status on whether task is completed
     *
     * @return true if task is completed, false otherwise
     */
    public boolean getIsDone() {
        return this.isDone;
    }

    /**
     * Returns the name of the task
     *
     * @return Name of the task in a string
     */
    public String getTaskName() {
        return this.taskName;
    }

    /**
     * Marks the status of the completion of task as done
     */
    public void markAsDone() {
        this.isDone = true;
    }

    /**
     * Marks the status of the completion of task as undone
     */
    public void markAsUndone() {
        this.isDone = false;
    }

    /**
     * Converts the task to a JSON object.
     *
     * @return A JSON object representing the task.
     */
    public JSONObject toJSONObject() {
        return null;
    }
}
