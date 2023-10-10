public class Task {
    private String description;
    private boolean isDone;
    public Task(String description) {
        setDescription(description);
        setDone(false);
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isDone() {
        return isDone;
    }

    public void setDone(boolean done) {
        isDone = done;
    }

    /**
     * Returns the format of the task to be printed out to user
     * @return String representation of the task
     */
    public String toString() {
        return (isDone ? "[X]" : "[ ]") + " " + description;
    }

    /**
     * Returns the format of the task to be saved into a file
     * @return String representation of the task
     */
    public String toSave() {
        return (isDone ? "1" : "0") + " | " + description;
    }
}
