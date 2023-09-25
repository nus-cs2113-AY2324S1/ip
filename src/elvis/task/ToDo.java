package elvis.task;

/**
 * Represents a ToDo task, which is a type of Task.
 * A ToDo task has a description and a completion status,
 * but does not have a deadline or event time.
 */
public class ToDo extends Task {

    /**
     * Constant representing the type of the task.
     * For ToDo tasks, this is always 'T'.
     */
    private final char taskType = 'T';

    /**
     * Constructs a new ToDo instance.
     *
     * @param description    The description of the ToDo task.
     * @param isDoneFromFile The completion status read from a file.
     */
    public ToDo(String description, int isDoneFromFile) {
        super(description, isDoneFromFile);
    }

    /**
     * Retrieves the type of the task.
     *
     * @return 'T' indicating that this is a ToDo task.
     */
    @Override
    public char getTaskType() {
        return taskType;
    }
}
