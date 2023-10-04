package Duke;

import java.util.ArrayList;

public class Task {
    
    protected String description;
    protected boolean isDone;
    private static int count = 0;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
        count++;
    }

    public String getTaskStatus() {
        return (isDone ? "X" : " ");
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

    // Delete Task
    public static void removeTask(ArrayList<Task> taskList, int taskIndex) {
        taskList.remove(taskIndex);
        count--;
    }
}