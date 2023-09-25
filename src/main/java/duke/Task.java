package duke;

/**
 * The {@code Task} class represents a task with a description and a completion status.
 * <p>
 * Each task has a description detailing what the task is about, and a boolean completion status indicating whether the task is done.
 * The {@code toString} method provides a string representation of the task, indicating its completion status and description.
 * </p>
 *
 * <p>
 * This class serves as the base class for different types of tasks that may have additional attributes and behaviors,
 * and it is designed to be extended by other specialized task classes.
 * </p>
 *
 * Key Methods Include:
 * <ul>
 *     <li>{@link #markAsDone()} - Marks the task as done.</li>
 *     <li>{@link #markAsUndone()} - Marks the task as undone.</li>
 *     <li>{@link #getCompletionStatus()} - Retrieves the completion status of the task.</li>
 *     <li>{@link #getDescription()} - Retrieves the description of the task.</li>
 *     <li>{@link #getTaskType()} - (Presumably) Retrieves the type of the task. However, the implementation is not provided here.</li>
 *     <li>{@link #toString()} - Provides a string representation of the task.</li>
 * </ul>
 *
 * @author  Ashok Balaji
 * @version 1.0
 * @since   2023-09-25
 */
public class Task {
    protected String description;
    protected boolean isDone;
    protected String taskType;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public String getDescription(){
        return this.description;
    }

    public void markAsDone(){
        this.isDone = true;
    }

    public void markAsUndone(){
        this.isDone = false;
    }

    public boolean getCompletionStatus(){
        return this.isDone;
    }

    public String getTaskType(){
        return this.taskType;
    }

    @Override
    public String toString(){
        return "[" + (isDone ? "X" : " ") + "] " + description; // mark done task with X
    }

}
