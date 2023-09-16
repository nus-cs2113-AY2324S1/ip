package RC.task;

public class Task {
    private String description;
    private boolean isDone;
    private static int numTasks = 0;
    public Task(String description) {
        this.description = description;
        this.isDone = false;
        numTasks++;
    }

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
    }

    public void unmarkTask() {
        this.isDone = false;
    }

    public void deleteTask() {
        numTasks--;
        System.out.print("\tNoted. I've removed the following task:\n\t  " + this + "\n\tNow you have " + getNumTasks());
        printNumTasks();
    }

    public static boolean isValidIndex(int index) {
        return index >= 0 && index < numTasks;
    }
    @Override
    public String toString() {
        return "[" + this.getStatusIcon() + "] " + this.getDescription();
    }

    public void printAddedTask() {
        System.out.print("\tGot it. I've added this task:\n\t  " + this + "\n\tNow you have " + getNumTasks());
        printNumTasks();
    }

    private static void printNumTasks() {
        if (getNumTasks() > 1) {
            System.out.println(" tasks in the list.");
        } else {
            System.out.println(" task in the list.");
        }
    }

    public String formatString() {
        int markTask = 0;
        if (this.getStatusIcon().equals("X")) {
            markTask = 1;
        }
        return markTask + " | " + this.getDescription();
    }
}
