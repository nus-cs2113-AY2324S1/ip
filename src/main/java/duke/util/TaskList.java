package main.java.duke.util;

import main.java.duke.util.task.Deadline;
import main.java.duke.util.task.Event;
import main.java.duke.util.task.Task;
import main.java.duke.util.task.Todo;

import java.util.ArrayList;

/**
 * Class for storing and managing Task objects that are created by the user.
 */
public class TaskList {

    private ArrayList<Task> tasks;

    public TaskList() {
        this.tasks = new ArrayList<Task>();
    }

    /**
     * Get the current list of tasks.
     * @return the list of tasks that are stored in the system
     */
    public ArrayList<Task> getTasks() {
        return tasks;
    }

    /**
     * Get the number of tasks in the list.
     * @return the number of tasks in the list
     */
    public int size() {
        return tasks.size();
    }

    /**
     * Check if the list is empty.
     * @return true if the list is empty
     */
    public boolean isEmpty() {
        return tasks.isEmpty();
    }

    /**
     * Get the Task object at requested index.
     * @param index the index of the Task requested by the user
     * @return the Task object at index
     */
    public Task get(int index) {
        return tasks.get(index);
    }

    /**
     * Look up a name of a Task in list and find the ones with matching names.
     * @param taskName user indicated name to look for
     * @return a list of indexes of the Tasks in the list with names that match the input
     */
    public ArrayList<Integer> find(String taskName) {
        ArrayList<Integer> foundTasksIndex = new ArrayList<Integer>();
        for (int i = 0; i < tasks.size(); i++) {
            if (tasks.get(i).getName().equals(taskName))
                foundTasksIndex.add(i);
        }
        return foundTasksIndex;
    }

    /**
     * Remove the indicated Task in the list.
     * @param selectedItem the index of the Task to be removed
     * @return the removed Task in the list
     */
    public Task removeTask(int selectedItem) {
        // check if integer given is in range of number of tasks
        Task removedTask = tasks.remove(selectedItem - 1);
        return removedTask;
    }

    /**
     * Create a new Task subclass Deadline and store it in the list.
     * @param deadlineName the name of the Deadline object
     * @param deadlineBy the by date of the Deadline object
     * @return the created Deadline object
     */
    public Deadline addDeadline(String deadlineName, String deadlineBy) {
        Deadline deadline = new Deadline(deadlineName, deadlineBy); // New Deadline object
        tasks.add(deadline); // Store user input into array
        return deadline;
    }

    /**
     * Create a new Task subclass Event and store it in the list.
     * @param eventName the name of the Event object
     * @param eventFrom the from date of the Event object
     * @param eventTo the to date of the Event object
     * @return the created Event object
     */
    public Event addEvent(String eventName, String eventFrom, String eventTo) {
        Event event = new Event(eventName, eventFrom, eventTo); // New Event object
        tasks.add(event); // Store user input into array
        return event;
    }

    /**
     * Create a new Task subclass Todo and store it in the list.
     * @param todoName the name of the Todo object
     * @return the created Todo object
     */
    public Todo addTodo(String todoName) {
        Todo todo = new Todo(todoName);
        tasks.add(todo); // Store user input into array
        return todo;
    }

    /**
     * Mark the selected Task.
     * @param selectedItem the index of the selected task in the list
     */
    public void unmarkTask(int selectedItem) {
        // check if integer given is in range of number of tasks
        tasks.get(selectedItem - 1).setMarked(false); // unmark task
    }

    /**
     * Unmark the selected Task.
     * @param selectedItem the index of the selected task in the list
     */
    public void markTask(int selectedItem) {
        // check if integer given is in range of number of tasks
        tasks.get(selectedItem - 1).setMarked(true);
    }
}
