package duke;

class Task {
    private final String description;
    private boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public void markAsDone() {
        this.isDone = true;
    }

    public void markAsNotDone() {
        this.isDone = false;
    }

    public String getDescription() {
        return this.description;
    }

    public String getStatusIcon() {
        return this.isDone ? "X" : " ";
    }

    public String toString() {
        String var10000 = this.getStatusIcon();
        return "[" + var10000 + "] " + this.description;
    }
}
