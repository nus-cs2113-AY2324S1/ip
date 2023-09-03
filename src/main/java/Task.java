public class Task {
    private String taskName;
    private boolean isDone;

    public Task(String taskName) {
        this.taskName = taskName;
        this.isDone = false;
    }

    public String getTaskName() {
        return this.taskName;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    public boolean getIsDone() {
        return this.isDone;
    }

    public void setIsDone(boolean isDone) {
        this.isDone = isDone;
    }
    
    public void markAsDone() {
        this.isDone = true;
        printTask();
    }

    public void markAsUndone() {
        this.isDone = false;
        printTask();
    }

    public void printTask() {
        if (this.isDone == true) {
            System.out.println("\t[X] " + this.taskName);
        } else {
            System.out.println("\t[ ] " + this.taskName);
        }
    }
}
