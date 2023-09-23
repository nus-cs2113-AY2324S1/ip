package TaskList;

import java.time.LocalDate;
import java.util.ArrayList;

import Task.Deadline;
import Task.Event;
import Task.Task;
import Task.ToDo;

public class TaskList {
    private ArrayList<Task> taskList;

    public TaskList() {
        this.taskList = new ArrayList<Task>();
    }

    public ArrayList<Task> getTaskList() {
        return taskList;
    }

    public int getLength() {
        return taskList.size();
    }

    public Task markList(int index, boolean status) {
        Task task = taskList.get(index);
        task.setCompleted(status);
        taskList.set(index, task);
        return task;
    }

    public Deadline addDeadlineToList(String description, LocalDate by) {
            Deadline deadline = new Deadline(description, by);
            taskList.add(deadline);
            return deadline;
    }

    public Event addEventToList(String description, LocalDate from, LocalDate to) {
        Event event = new Event(description, from, to);
        taskList.add(event);
        return event;
    }

    public ToDo addToDoToList(String description) {
        ToDo todo = new ToDo(description);
        taskList.add(todo);
        return todo;
    }
    
    public Task deleteTaskFromList(int index) {
        Task deletedTask = taskList.get(index);
        taskList.remove(index);
        return deletedTask;
    }
}