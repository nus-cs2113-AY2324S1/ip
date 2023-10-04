package AMY.command;

public class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public void markAsDone() {
        isDone = true;
    }

    public void markAsNotDone() {
        isDone = false;
    }

    // mark done task with X
    public String getStatusIcon() {
        return (isDone ? "X" : " ");
    }

    public String getDescription() {
        return description;
    }

    // Convert the task to a file-readable string format
    public String toFileString() {
        String status = isDone ? "1" : "0";
        return String.format("%s | %s | %s", getTaskType(), status, description);
    }

    // Parse a task from a file string
    public static Task parseFromFileString(String fileString) {
        String[] parts = fileString.split("\\|");
        String taskType = parts[0].trim();
        String status = parts[1].trim();
        String description = parts[2].trim();

        Task task;

        if (taskType.equals("T")) {
            task = new Todo(description);
        } else if (taskType.equals("D")) {
            // Extract the deadline information (e.g., "June 6th")
            String by = parts[3].trim();
            task = new Deadline(description, by);
        } else if (taskType.equals("E")) {
            // Extract the event information (e.g., "Aug 6th 2-4pm")
            String fromTo = parts[3].trim();
            String[] dateTimeParts = fromTo.split("\\|");
            String from = dateTimeParts[0].trim();
            String to = dateTimeParts[1].trim();
            task = new Event(description, from, to);
        } else {
            // Handle unknown task types (optional)
            task = null;
        }

        if (task != null) {
            if (status.equals("1")) {
                task.markAsDone();
            }
        }

        return task;
    }

    protected String getTaskType() {
        return "T";
    }

    @Override
    public String toString() {
        return "[" + this.getStatusIcon() + "] " + description;
    }
}


