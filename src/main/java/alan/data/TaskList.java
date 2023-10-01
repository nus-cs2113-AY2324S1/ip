package alan.data;

import alan.data.task.Deadline;
import alan.data.task.Event;
import alan.data.task.Task;
import alan.data.task.Todo;

import java.util.ArrayList;

public class TaskList {
    private final ArrayList<Task> tasks;

    public TaskList() {
        tasks = new ArrayList<>();
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

    public void addTaskToTaskList(String taskType, String description, String[] splitTaskString) {
        switch (taskType) {
        case "T":
            tasks.add(new Todo(description));
            break;
        case "D":
            String by = splitTaskString[3];
            tasks.add(new Deadline(description, by));
            break;
        case "E":
            String date = splitTaskString[3];
            String[] splitDate = date.split("-");
            String from = splitDate[0];
            String to = splitDate[1];

            tasks.add(new Event(description, from, to));
            break;
        default:
            //todo handle invalid task type
            break;
        }
    }


}
