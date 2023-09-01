public class Task {
    protected String description;
    protected boolean isDone;
    private static int totalTasks = 0;


    public static int getTotalTasks() {
        return totalTasks;
    }

    public static void incrementTotalTasks() {
        Task.totalTasks++;
    }

    public void setDone(boolean done){
        isDone = done;
    }

    public Task(String description) {
        this.description = description;
        this.isDone = false;
        incrementTotalTasks();
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    @Override
    public String toString() {
        return "[" + this.getStatusIcon() + "] " + this.description;
    }

    public void printAddedTask() {
        System.out.println("Got it. I've added this task:");
        System.out.println("    " + this);
    }
}
