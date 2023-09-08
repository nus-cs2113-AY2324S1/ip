public class Task {
    protected String description;
    protected boolean isDone;
    protected static int numTasks = 0;
    // defines constructor for the task class

    public String getStatusIcon() {
        return (isDone ? "X" : " ");
    }

    public static int getNumTasks() {
        return numTasks;
    }

    public String getDescription() {
        return this.description;
    }

    public void markAsDone() {
        this.isDone = true;
        System.out.println("Nice! I've marked this task as done:\n\t  " + this);
    }

    public Task(String description) {
        this.description = description;
        this.isDone = false;
        numTasks++;
    }

    public void unmarkTask() {
        this.isDone = false;
        System.out.println("Nice! I've marked this task as done:\n\t  " + this);
    }

    @Override
    public String toString() {
        return "[" + this.getStatusIcon() + "] " + this.getDescription();
    }

    public void printNewTask() {
        System.out.print("\tGot it. I've added this task:\n\t  " + this + "\n\tNow you have " + getNumTasks());
        if (getNumTasks() > 1) {
            System.out.println(" tasks in the list.");
        } else {
            System.out.println(" task in the list.");
        }
    }
}
