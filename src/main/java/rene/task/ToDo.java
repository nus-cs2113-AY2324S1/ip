package rene.task;
/**
 * Represents todo tasks in the current task list
 */
public class ToDo extends Task {
    /**
     * Creates a new task of type todo.
     *
     * @param description Task description.
     */
    public ToDo(String description){
        super(description, TaskType.TODO);
    }
}
