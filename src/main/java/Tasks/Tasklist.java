package Tasks;

import java.util.ArrayList;

public class Tasklist {
    ArrayList<Task> taskList;

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
