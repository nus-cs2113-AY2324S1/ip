package alan.data;

import alan.data.task.Deadline;
import alan.data.task.Event;
import alan.data.task.Task;
import alan.data.task.Todo;

import java.util.ArrayList;
/**
 * Represents a list of the <code>Task</code> objects.
 */
public class TaskList {
    private final ArrayList<Task> tasks;

    public TaskList() {
        tasks = new ArrayList<>();
    }

    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    public ArrayList<Task> getTaskList() {
        return tasks;
    }

    public Task getSelectedTask(int selectedTaskIndex) {
        return tasks.get(selectedTaskIndex);
    }

    public int getLastTaskIndex() {
        return tasks.size() - 1;
    }

    public int getTaskListSize() {
        return tasks.size();
    }

    public void addToDo(String description) {
        tasks.add(new Todo(description));
    }

    public void addDeadline(String description, String by) {
        tasks.add(new Deadline(description, by));
    }

    public void addEvent(String description, String from, String to) {
        tasks.add(new Event(description, from, to));
    }

    public void markTask(int selectedTaskIndex, boolean isDone) {
        tasks.get(selectedTaskIndex).setDone(isDone);
    }

    public void removeTask(int selectedTaskIndex) {
        tasks.remove(selectedTaskIndex);
    }


}
