package duke.tasks;

import duke.Task;

/**
 * The `Todo` class represents a specific type of task in the Duke application, tasks with no specific date or time.
 * It extends the `Task` class and includes a description of the task.
 */
public class Todo extends Task {
    /**
     * Constructs a `Todo` task with the given description.
     *
     * @param description The description of the todo task.
     */
    public Todo(String description) {
        super(description);
    }

    /**
     * Overrides the `toString` method to provide a formatted representation of the `Todo` task.
     *
     * @return A string representation of the `Todo` task, including its description.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    /**
     * Gets the event time for a `Todo` task, which is empty.
     *
     * @return An empty string, as `Todo` tasks do not have a specific event time.
     */
    public String getEventTime() {
        return "";
    }
}
