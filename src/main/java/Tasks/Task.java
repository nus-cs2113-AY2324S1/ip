package tasks;

import exceptions.TaskEmptyDescriptionException;

/**
 * Represents a task that the user wants to keep track of.
 */
public class Task {
    private String description;
    private boolean isDone;

    public Task(String description) throws TaskEmptyDescriptionException {
        if (description == "") {
            throw new TaskEmptyDescriptionException();
        }
        this.description = description;
        this.isDone = false;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean getIsDone() {
        return this.isDone;
    }

    public void setIsDone(boolean isDone) {
        this.isDone = isDone;
    }
    
    public void markAsDone() {
        this.isDone = true;
    }

    public void markAsUndone() {
        this.isDone = false;
    }

    /**
     * Returns a string representation of the task's completion status.
     * @return A string representation of the task's completion status.
     */
    public String getCompletedString() {
        if (this.isDone == true) {
            return "[X] ";
        } else {
            return "[ ] ";
        }
    }

    /**
     * Print the task to the user.
     */
    public void printTask() {
        System.out.println("\t" + getCompletedString() + this.description);
    }
}
