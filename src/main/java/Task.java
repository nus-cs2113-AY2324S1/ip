public class Task {
    private String taskDesc;
    private boolean isDone;

    private static int numTask = 0;

    public Task(String taskDesc) {
        this.taskDesc = taskDesc;
        this.isDone = false;
    }

    public String getTaskDesc() {
        return taskDesc;
    }

    public void setTaskDesc(String taskDesc) {
        this.taskDesc = taskDesc;
    }

    public boolean getDone() {
        return isDone;
    }

    public void setDone(boolean isDone) {
        this.isDone = isDone;
    }

    public static int getNumTask() {
        return numTask;
    }

    public static void setNumTask(int numTask) {
        Task.numTask = numTask;
    }
}
