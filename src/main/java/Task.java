public class Task {
    private String taskDesc;
    private boolean done;

    private static int numTask = 0;

    public Task(String taskDesc) {
        this.taskDesc = taskDesc;
        this.done = false;
    }

    public String getTaskDesc() {
        return taskDesc;
    }

    public void setTaskDesc(String taskDesc) {
        this.taskDesc = taskDesc;
    }

    public boolean isDone() {
        return done;
    }

    public void setDone(boolean done) {
        this.done = done;
    }

    public static int getNumTask() {
        return numTask;
    }

    public static void setNumTask(int numTask) {
        Task.numTask = numTask;
    }
}
