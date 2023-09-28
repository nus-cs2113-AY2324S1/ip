class Task {
    private final String description;
    private boolean isDone;

    //initialize a task object with the given description
    public Task(String description) {
        this.description = description;
        this.isDone = false; //set initial completion to false
    }

    public void markAsDone() {
        isDone = true;
    }

    public void markAsNotDone() {
        isDone = false;
    }

    public String getDescription() {
        return description;
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " ");
    }

    @Override
    public String toString() {
        return "[" + getStatusIcon() + "] " + description;
    }
}