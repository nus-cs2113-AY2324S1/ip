package main.java.duke.util;

import main.java.duke.util.task.Deadline;
import main.java.duke.util.task.Event;
import main.java.duke.util.task.Task;
import main.java.duke.util.task.Todo;

import java.util.ArrayList;

public class TaskList {
    public static Task removeTask(ArrayList<Task> tasks, int selectedItem) {
        // check if integer given is in range of number of tasks
        Task removedTask = tasks.remove(selectedItem - 1);
        return removedTask;
    }

    public static Deadline addDeadline(String deadlineName, String deadlineBy, ArrayList<Task> tasks) {
        Deadline deadline = new Deadline(deadlineName, deadlineBy); // New Deadline object
        tasks.add(deadline); // Store user input into array
        return deadline;
    }

    public static Event addEvent(String eventName, String eventFrom, String eventTo, ArrayList<Task> tasks) {
        Event event = new Event(eventName, eventFrom, eventTo); // New Event object
        tasks.add(event); // Store user input into array
        return event;
    }

    public static Todo addTodo(String todoName, ArrayList<Task> tasks) {
        Todo todo = new Todo(todoName);
        tasks.add(todo); // Store user input into array
        return todo;
    }

    public static void unmarkTask(ArrayList<Task> tasks, int selectedItem) {
        // check if integer given is in range of number of tasks
        tasks.get(selectedItem - 1).setMarked(false); // unmark task
    }

    public static void markTask(ArrayList<Task> tasks, int selectedItem) {
        // check if integer given is in range of number of tasks
        tasks.get(selectedItem - 1).setMarked(true);
    }

    public static ArrayList<Task> emptyList() {
        // Task ArrayList for storing all user inputted tasks
        ArrayList<Task> tasks = new ArrayList<Task>();
        return tasks;
    }
}
