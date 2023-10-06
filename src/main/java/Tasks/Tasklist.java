package tasks;

import java.util.ArrayList;

/**
 * Represents a list of tasks.
 */
public class Tasklist {
    private ArrayList<Task> taskList;

    public Tasklist() {
        taskList = new ArrayList<Task>();
    }

    public void addTask(Task task) {
        taskList.add(task);
    }

    public void deleteTask(int taskIndex) {
        taskList.remove(taskIndex - 1);
    }

    public Task getTask(int taskIndex) {
        return taskList.get(taskIndex - 1);
    }

    public int getTaskListSize() {
        return taskList.size();
    }

    public void markTaskAsDone(int taskIndex) {
        taskList.get(taskIndex - 1).markAsDone();
    }

    public void markTaskAsUndone(int taskIndex) {
        taskList.get(taskIndex - 1).markAsUndone();
    }
    
    public void printTaskList() {
        for (int i = 0; i < taskList.size(); i++) {
            System.out.print("\t" + (i + 1) + ".");
            taskList.get(i).printTask();
        }
    }

    /**
     * Search for tasks in the task list based on a keyword.
     * @param searchString The keyword to search for.
     * @return An ArrayList of tasks that match the keyword.
     */
    public ArrayList<Task> searchTasks(String searchString) {
        ArrayList<Task> searchResults = new ArrayList<Task>();
        for (int i = 0; i < taskList.size(); i++) {
            if (taskList.get(i).getDescription().contains(searchString)) {
                searchResults.add(taskList.get(i));
            }
        }
        return searchResults;
    }
}
