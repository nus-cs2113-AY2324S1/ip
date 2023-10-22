package commands;

/**
 * The `Task` class represents a general task with a description and a completion status.
 */
public class Task {
    protected String description;
    protected boolean isDone;
    protected char type;

    private static int numberOfTasks = 0;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
        this.type = 'T';
        numberOfTasks += 1;
    }

    /**
     * Gets the status icon for the task.
     *
     * <p>This method returns a status icon character that represents whether the task is done
     * or not. If the task is done, it returns "X"; otherwise, it returns a space character (" ").
     * </p>
     *
     * @return A string containing the status icon for the task ("X" for done, " " for not done).
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " ");
    }

    public char getType() {
        return this.type;
    }

    public String getDescription() {
        return this.description;
    }


    public void markStatus() {
        this.isDone = true;
    }

    public void unmarkStatus() {
        this.isDone = false;
    }

    @Override
    public String toString() {
        return String.format(this.description);
    }
}

