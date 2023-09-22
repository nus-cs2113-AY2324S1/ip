package TaskList;

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

    public Deadline addDeadlineToList(String[] argumentList) {
            Deadline deadline = new Deadline(argumentList[0], argumentList[1]);
            taskList.add(deadline);
            return deadline;
    }

    public Event addEventToList(String[] argumentList) {
        Event event = new Event(argumentList[0], argumentList[1], argumentList[2]);
        taskList.add(event);
        return event;
    }

    public ToDo addToDoToList(String arguments) {
        ToDo todo = new ToDo(arguments);
        taskList.add(todo);
        return todo;
    }
    
    public Task deleteTaskFromList(int index) {
        Task deletedTask = taskList.get(index);
        taskList.remove(index);
        return deletedTask;

    }
}