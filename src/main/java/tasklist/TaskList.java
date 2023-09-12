package tasklist;

import java.util.ArrayList;
import java.util.List;

import tasks.Task;


/**
 *
 * The manager of the task list. I intend to shift the command functions in duke to this class.
 */
public class TaskList {

    private List<Task> tasks = new ArrayList<>();

    public void addTask(Task task) {
        tasks.add(task);
    }

    public List<Task> getTasks() {
        return tasks;
    }

    public void markTaskDone(int index) {
        if (isValidIndex(index)) {
            Task task = tasks.get(index - 1);
            task.setDone(true);
        }
    }

    public void unmarkTask(int index) {
        if (isValidIndex(index)) {
            Task task = tasks.get(index - 1);
            task.setDone(false);
        }
    }

    private boolean isValidIndex(int index) {
        return index >= 1 && index <= tasks.size();
    }
}