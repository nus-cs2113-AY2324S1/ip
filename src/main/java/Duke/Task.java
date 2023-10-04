package Duke;

public class Task {
    
    protected String description;
    protected boolean isDone;
    private static int count = 0;
    protected String type;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
        count++;
    }

    public String getTaskType() {
        return type;
    }

    public String getTaskStatus() {
        return (isDone ? "X" : " ");
    }

    public String getDescriptionText() {
        return this.description;
    }

    // Method to return the task description
    public String getDescription() {
        return "[" + this.getTaskStatus() + "] " + this.description;
    }

    // Set the task status to done [X]
    public void markTask() {
        this.isDone = true;
    }

    // Set the task status to not done [ ]
    public void unMarkTask() {
        this.isDone = false;
    }

    // Return the total no. of Task
    public static int getTaskCount() {
        return count;
    }

    public String getStart() {
        return null;
    }

    public String getEnd() {
        return null;
    }

    public String getWhen() {
        return null;
    }

}