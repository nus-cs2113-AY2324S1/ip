package Duke.Task;

/**
 * A specific type of task that only contains task description.
 */
public class ToDo extends Task {
    public ToDo(String description) {
        super(description);
        taskType = "todo";
        symbol = "T";
    }


    @Override
    public String toString() {
        return "\t[T]" + super.toString();
    }
}
