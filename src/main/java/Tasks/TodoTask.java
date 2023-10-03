package Tasks;

import Tasks.Task;

/**
 * The TodoTask class represents a task of type "Todo."
 * It extends the Task class and inherits its properties and methods.
 */
public class TodoTask extends Task {
    /**
     * Constructs a TodoTask with the specified description.
     *
     * @param description The description of the task.
     */
    public TodoTask(String description) {
        super(description);
    }

    /**
     * Returns a string representation of the TodoTask.
     * The format is "[T] [status icon] description."
     *
     * @return A string representing the TodoTask.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
