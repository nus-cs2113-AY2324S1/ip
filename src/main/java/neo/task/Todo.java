package neo.task;

/**
 * Represents a form of task of type todo.
 */
public class Todo extends Task {

    /**
     * Constructs a task with a string to describe the task.
     *
     * @param description This is the description of the task.
     */
    public Todo(String description) {
        super(description);
    }

    /**
     * Returns the todo in a format for saving into a .txt file.
     *
     * @return The formatted deadline.
     */
    @Override
    public String formatTask() {
        return "T / " + super.formatTask();
    }

    /**
     * Returns the todo in an easy-to-read format to the user.
     *
     * @return The formatted deadline.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
