/**
 * The ToDo class represents a to-do task, which is a type of task without a specific deadline or event time.
 */
public class ToDo extends Task {
    /**
     * Creates a new to-do task with the specified description and completion status.
     *
     * @param description The description of the to-do task.
     * @param isDone      The completion status of the to-do task.
     */
    public ToDo(String description, boolean isDone) {
        super(description);
    }

    /**
     * Returns a string representation of the to-do task, including its type marker ('[T]') and description.
     *
     * @return A string representation of the to-do task.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}

