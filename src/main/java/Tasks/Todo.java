package tasks;

import exceptions.TaskEmptyDescriptionException;

public class Todo extends Task {
    public Todo (String description) throws TaskEmptyDescriptionException {
        super(description);
    }

    @Override
    public void printTask() {
        System.out.println("\t[T]" + getCompletedString() + getDescription());
    }
}
