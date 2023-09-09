public class Task {
    private String description;
    private boolean isDone;

    public Task(String taskName) {
        this.description = taskName;
        this.isDone = false;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String taskName) {
        this.description = taskName;
    }

    public boolean getIsDone() {
        return this.isDone;
    }

    public void setIsDone(boolean isDone) {
        this.isDone = isDone;
    }
    
    public void markAsDone() {
        this.isDone = true;
    }

    public void markAsUndone() {
        this.isDone = false;
    }

    public String getCompletedString() {
        if (this.isDone == true) {
            return "[X] ";
        } else {
            return "[ ] ";
        }
    }

    public void printTask() {
        System.out.println("\t" + getCompletedString() + this.description);
    }
}
