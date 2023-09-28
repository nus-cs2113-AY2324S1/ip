package task;

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

    @Override
    public String toString() {
        StringBuilder output = new StringBuilder("[");
        output.append(this.type.toString().substring(0).toUpperCase()).append("]");

        if (isDone) {
            output.append("[X] ");
        } else {
            output.append("[ ] ");
        }
        return output.append(this.description).toString();
    }

    // format task to input format to be saved in storage
    public String formatAsInput() {
        return this.type + " " + this.isDone + " " + this.description;
    }
}
