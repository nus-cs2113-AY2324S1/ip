package dude;

/**
 * The `Task` class represents a generic task with a description and completion status.
 * It serves as the base class for different types of tasks, such as deadlines and events.
 */
public class Task {
    protected String description;
    protected boolean isDone;
    protected String type;

    /**
     * Constructs a new generic task with the specified description.
     *
     * @param description The description of the task.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
        this.type = "[T]"; // Represents a generic task.
    }

    /**
     * Returns a string representing the completion status of the task.
     *
     * @return A string containing "[X]" if the task is completed, "[ ]" otherwise.
     */
    public String getStatusIcon() {
        return (isDone ? "[X]" : "[ ]");
    }

    /**
     * Retrieves the description of the task.
     *
     * @return The description of the task.
     */
    public String getDescription() {
        return description;
    }

    /**
     * Sets the completion status of the task.
     *
     * @param isDone True if the task is completed, false otherwise.
     */
    public void setDone(boolean isDone) {
        this.isDone = isDone;
    }

    /**
     * Converts the task to a formatted string for saving to a file.
     *
     * @return A string representation of the task in file format.
     */
    public String toFileFormat() {
        return type.charAt(1) + " | " + (isDone ? "1" : "0") + " | " + description;
    }

    /**
     * Creates a `Task` object from a string in file format.
     *
     * @param fileString A string containing the task details in file format.
     * @return A `Task` object parsed from the file string.
     */
    public static Task fromFileFormat(String fileString) {
        String[] parts = fileString.split("\\s\\|\\s");
        Task task = new Task(parts[2]);
        if (parts[1].equals("1")) {
            task.setDone(true);
        }
        return task;
    }

    /**
     * Generates a string representation of the task, including its type, completion status, and description.
     *
     * @return A string representing the task.
     */
    @Override
    public String toString() {
        return type + getStatusIcon() + " " + description;
    }
}
