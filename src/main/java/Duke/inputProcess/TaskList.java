package duke.inputProcess;

import duke.Task;
import duke.tasks.Deadline;
import duke.tasks.Todo;
import duke.tasks.Event;

import java.time.LocalDateTime;
import java.util.ArrayList;

/**
 * The `TaskList` class represents a collection of tasks in the Duke application.
 * It provides methods to add different types of tasks (Todo, Event, and Deadline) and retrieve task information.
 */
public class TaskList {
    private static ArrayList<Task> list;

    /**
     * Constructs a new `TaskList` object, initializing an empty array list of tasks.
     */
    public TaskList() {
        list = new ArrayList<>();
    }

    /**
     * Adds a Todo task to the task list with the given description.
     *
     * @param userInput The description of the Todo task to be added.
     */
    public void addTodo(String userInput) {
        list.add(new Todo(userInput));
    }

    /**
     * Adds an Event task to the task list with the given description, start time, and end time.
     *
     * @param eventToAdd The description of the Event task.
     * @param startTime The start time of the Event task.
     * @param endTime The end time of the Event task.
     */
    public void addEvent(String eventToAdd, LocalDateTime startTime, LocalDateTime endTime) {
        list.add(new Event(eventToAdd, startTime, endTime));
    }

    /**
     * Adds a Deadline task to the task list with the given description and deadline time.
     *
     * @param deadlineToAdd The description of the Deadline task.
     * @param deadlineTime The deadline time of the Deadline task.
     */
    public void addDeadline(String deadlineToAdd, LocalDateTime deadlineTime) {
        list.add(new Deadline(deadlineToAdd, deadlineTime));
    }

    /**
     * Retrieves a task from the task list by its index.
     *
     * @param index The index of the task to retrieve.
     * @return The task at the specified index.
     */
    public Task getByIndex(int index) {
        return list.get(index);
    }

    /**
     * Gets the number of tasks in the task list.
     *
     * @return The number of tasks in the list.
     */
    public int getSize() {
        return list.size();
    }

    /**
     * Get the list of tasks.
     *
     * @return The list of tasks in the `TaskList`.
     */
    public ArrayList<Task> getList() {
        return list;
    }
}
