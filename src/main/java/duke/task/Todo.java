package duke.task;

/**
 * Type of task that represents a to-do object.
 */
public class Todo extends Task {
    public Todo(String description) {
        super(description);
    }

    @Override
    public String getFormattedTask() {
        return "[T] " + super.getFormattedTask();
    }

    @Override
    public String getSerializedString() {
        return "T|" + super.getSerializedString();
    }
}
