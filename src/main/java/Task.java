public class Task {
    // Instance variables to store task description and status
    protected String description;
    protected boolean isDone;

    // Constructor to initialize a task with a description
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    // Method to get the status icon based on whether the task is done or not
    public String getStatusIcon() {
        return (isDone ? "[X]" : "[ ]");
    }
}

