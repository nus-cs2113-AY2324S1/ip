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

    public String toString() {
        String temp = "[ ]";
        if (isDone) {
            temp = "[X]";
        }
        return temp + " " + description;
    }
}
