package linguobot.task;

/**
 * The <code>Todo</code> class represents a type of task.
 * It extends the <code>Task</code> class and includes a description of the task.
 */
public class Todo extends Task{

    /**
     * Constructs a new `Todo` task with the provided description.
     *
     * @param description description of the task.
     */
    public Todo (String description) {
        super(description);
    }

    @Override
    public String toString() {
        return "[T][" + getStatusIcon() + "]" + description;
    }

    @Override
    public String toFileString() {
        return "T | " + (isDone ? "1" : "0") + " | " + description;
    }
}
