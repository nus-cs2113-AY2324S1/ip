package main.java.duke.util;

import main.java.duke.util.task.Deadline;
import main.java.duke.util.task.Event;
import main.java.duke.util.task.Task;
import main.java.duke.util.task.Todo;

import java.util.ArrayList;

public class TaskList {

    private ArrayList<Task> tasks;

    public TaskList() {
        this.tasks = new ArrayList<Task>();
    }

    public ArrayList<Task> getTasks() {
        return tasks;
    }

    public int size() {
        return tasks.size();
    }

    public boolean isEmpty() {
        return tasks.isEmpty();
    }

    public Task get(int i) {
        return tasks.get(i);
    }

    public ArrayList<Integer> find(String taskName) {
        ArrayList<Integer> foundTasksIndex = new ArrayList<Integer>();
        for (int i = 0; i < tasks.size(); i++) {
            if (tasks.get(i).getName().equals(taskName))
                foundTasksIndex.add(i);
        }
        return foundTasksIndex;
    }

    public Task removeTask(int selectedItem) {
        // check if integer given is in range of number of tasks
        Task removedTask = tasks.remove(selectedItem - 1);
        return removedTask;
    }

    public Deadline addDeadline(String deadlineName, String deadlineBy) {
        Deadline deadline = new Deadline(deadlineName, deadlineBy); // New Deadline object
        tasks.add(deadline); // Store user input into array
        return deadline;
    }

    public Event addEvent(String eventName, String eventFrom, String eventTo) {
        Event event = new Event(eventName, eventFrom, eventTo); // New Event object
        tasks.add(event); // Store user input into array
        return event;
    }

    public Todo addTodo(String todoName) {
        Todo todo = new Todo(todoName);
        tasks.add(todo); // Store user input into array
        return todo;
    }

    public void unmarkTask(int selectedItem) {
        // check if integer given is in range of number of tasks
        tasks.get(selectedItem - 1).setMarked(false); // unmark task
    }

    public void markTask(int selectedItem) {
        // check if integer given is in range of number of tasks
        tasks.get(selectedItem - 1).setMarked(true);
    }

}
