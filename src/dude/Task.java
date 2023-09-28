package dude;

public class Task {
    protected String description;
    protected boolean isDone;
    protected String type;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
        this.type = "[T]"; // Represents a generic task.
    }

    public String getStatusIcon() {
        return (isDone ? "[X]" : "[ ]");
    }

    public String getDescription() {
        return description;
    }

    public void setDone(boolean isDone) {
        this.isDone = isDone;
    }

    public String toFileFormat() {
        return type.charAt(1) + " | " + (isDone ? "1" : "0") + " | " + description;
    }

    public static Task fromFileFormat(String fileString) {
        String[] parts = fileString.split("\\s\\|\\s");
        Task task = new Task(parts[2]);
        if (parts[1].equals("1")) {
            task.setDone(true);
        }
        return task;
    }

    @Override
    public String toString() {
        return type + getStatusIcon() + " " + description;
    }
}
