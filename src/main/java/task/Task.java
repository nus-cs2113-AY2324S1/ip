package task;

/**
 * Represents a general task.
 * It can be specified as todo, deadline or event
 */
public abstract class Task {
    private final String description;
    private boolean isDone;
    protected TaskType type;

    public Task(String description, TaskType type) {
        this.description = description;
        this.type = type;
        this.isDone = false;
    }

    public String getDescription() {
        return description;
    }

    public void setDone(boolean isDone) {
        this.isDone = isDone;
    }

    /**
     * Overrides the toString() from Object class
     *
     * returns a string in the format of T [x] description,
     * it will be overridden in other classes as their formats are different
     */
    @Override
    public String toString() {
        StringBuilder output = new StringBuilder("[");
        String simplifiedTaskType = String.valueOf(this.type.toString().charAt(0));
        output.append(simplifiedTaskType.toUpperCase()).append("]");

        if (isDone) {
            output.append("[X] ");
        } else {
            output.append("[ ] ");
        }
        return output.append(this.description).toString();
    }

    /**
     * Format task to input format to be saved in storage
     *
     * returns a string in the format of TaskType true description
     * It will be overridden in other classes as their formats are different
     */
    public String formatAsInput() {
        return this.type + " " + this.isDone + " " + this.description;
    }
}
