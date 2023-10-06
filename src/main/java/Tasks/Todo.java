package tasks;

import exceptions.TaskEmptyDescriptionException;

/**
 * Represents a todo task.
 */
public class Todo extends Task {
    public Todo (String description) throws TaskEmptyDescriptionException {
        super(description);
    }

    /**
     * Print the Todo task to the user.
     */
    @Override
    public void printTask() {
        System.out.println("\t[T]" + getCompletedString() + getDescription());
    }
}
