public class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public String getTypeIcon() {
        return " ";
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " ");
    }

    public String getTaskAdded(int taskCount) {
        return String.format("Got it. I've added this task:\n" +
                "\t  %s\n" +
                "\tNow you have %d %s in the list",
                getTask(), taskCount, (taskCount == 1 ? "task" : "tasks"));
    }

    public String getTask() {
        return String.format("[%s][%s] %s", getTypeIcon(), getStatusIcon(), description);
    }

    public void setIsDone(boolean isDone) {
        this.isDone = isDone;
    }
}
