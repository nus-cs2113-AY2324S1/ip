package Duke.Task;

/**
 * Task object.
 */
public class Task {
    protected String description;
    protected boolean isDone;

    String taskType;
    String symbol;

    public Task(String description) {
        this.description = description;
        isDone = false;
        taskType = "Task";
        symbol = "t";
    }

    private String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    public String getDescription() {
        return this.description;
    }

    public void setDone() {
        this.isDone = true;
    }

    public void setUndone() {
        this.isDone = false;
    }

    public String getTaskType() {
        return taskType;
    }

    public String getSymbol(){
        return symbol;
    }

    /**
     * Encodes Task data for file saving.
     * @return Encoded Task data in String format.
     */
    public String convertToSaveFormat() {
        String doneMarker = getStatusIcon();
        return getSymbol() + " | " + doneMarker + " | " + getDescription();
    }

    /**
     * Override the printing of Task.
     * @return String format of Task for printing.
     */
    @Override
    public String toString() {
        String marker = getStatusIcon();
        return "[" + marker + "] " + getDescription();
    }
}