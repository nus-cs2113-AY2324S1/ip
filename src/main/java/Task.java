import java.util.ArrayList;

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
        System.out.println("\tNice! I've marked this task as done:\n\t  [" + this.getStatusIcon() + "] " + this.getDescription());
    }

    public void unmarkTask() {
        this.isDone = false;
        System.out.println("\tNice! I've marked this task as done:\n\t  [" + this.getStatusIcon() + "] " + this.getDescription());
    }
    public static void printTaskList(ArrayList<Task> tasks) {
        System.out.println("\tHere are the tasks in your list:");
        for (int i = 0; i < tasks.size(); i++) {
            System.out.println("\t" + (i + 1) + ".[" + tasks.get(i).getStatusIcon() + "] " + tasks.get(i).getDescription());
        }
    }

}
