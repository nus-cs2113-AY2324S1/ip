package python.task;

import java.util.ArrayList;
import java.util.List;

public class TaskList {
    final static private List<Task> tasks = new ArrayList<>();

    public static Task getTask(int taskNo) {
        return tasks.get(taskNo);
    }

    public static List<Task> getTasks() {
        return tasks;
    }

    public static int getNumberOfTasks() {
        return tasks.size();
    }

    public static void markTask(int taskNo) {
        tasks.get(taskNo).setDone(true);
    }

    public static void unmarkTask(int taskNo) {
        tasks.get(taskNo).setDone(false);
    }

    public static void deleteTask(int taskNo) {
        tasks.remove(taskNo);
    }

    public static void addTask(Task task) {
        tasks.add(task);
    }

    public static List<Task> findTask(String keyword) {
        List<Task> matchedTasks = new ArrayList<>();
        for (int taskNo = 0; taskNo < getNumberOfTasks(); taskNo++) {
            if (getTask(taskNo).getDescription().contains(keyword)) {
                matchedTasks.add(getTask(taskNo));
            }
        }
        return matchedTasks;
    }
}