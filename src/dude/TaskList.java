package dude;

import java.util.ArrayList;

import java.util.ArrayList;

/**
 * The `TaskList` class manages a list of tasks, providing operations to interact
 * with the tasks such as listing, adding, marking, and deleting tasks.
 */
public class TaskList {

    private ArrayList<Task> tasks;
    private Storage storage;
    private Ui ui;

    /**
     * Constructs a `TaskList` instance with the given list of tasks, storage, and user interface.
     *
     * @param tasks   The ArrayList of tasks to manage.
     * @param storage The Storage instance for saving and loading tasks.
     * @param ui      The Ui instance for user interactions.
     */
    public TaskList(ArrayList<Task> tasks, Storage storage, Ui ui) {
        this.tasks = tasks;
        this.storage = storage;
        this.ui = ui;
    }

    /**
     * Lists all tasks in the task list. If the list is empty, a message indicating no
     * tasks are present is shown.
     */
    public void listTasks() {
        if (tasks.isEmpty()) {
            ui.showMessage("You have no tasks in your list!");
        } else {
            ui.showTasks(tasks);
        }
    }

    /**
     * Adds a todo task to the task list based on user input.
     *
     * @param input The input command specifying the todo task.
     * @throws DudeException If the input validation or task addition fails.
     */
    public void addTodoTask(String input) throws DudeException {
        validateInput(input, 5, "☹ OOPS!!! The description of a todo cannot be empty.");

        String taskDescription = input.substring(5).trim();
        Task newTask = new Task(taskDescription);
        tasks.add(newTask);

        ui.showAddedTask(newTask, tasks.size());
        storage.saveToFile(tasks);
    }

    /**
     * Adds a deadline task to the task list based on user input.
     *
     * @param input The input command specifying the deadline task.
     * @throws DudeException If the input validation or task addition fails.
     */
    public void addDeadlineTask(String input) throws DudeException {
        validateInput(input, 9, "☹ OOPS!!! The description of a deadline cannot be empty.");

        int byIndex = input.indexOf("/by");
        if (byIndex == -1) {
            throw new DudeException("☹ OOPS!!! Please specify a deadline using /by.");
        }

        String taskDescription = input.substring(9, byIndex).trim();
        String by = input.substring(byIndex + 4).trim();

        Deadline newDeadline = new Deadline(taskDescription, by);
        tasks.add(newDeadline);

        ui.showAddedTask(newDeadline, tasks.size());
        storage.saveToFile(tasks);
    }

    /**
     * Adds an event task to the task list based on user input.
     *
     * @param input The input command specifying the event task.
     * @throws DudeException If the input validation or task addition fails.
     */
    public void addEventTask(String input) throws DudeException {
        validateInput(input, 6, "☹ OOPS!!! The description of an event cannot be empty.");

        int fromIndex = input.indexOf("/from");
        int toIndex = input.indexOf("/to");

        if (fromIndex == -1 || toIndex == -1) {
            throw new DudeException("☹ OOPS!!! Please specify event time using /from and /to.");
        }

        String taskDescription = input.substring(6, fromIndex).trim();
        String from = input.substring(fromIndex + 6, toIndex).trim();
        String to = input.substring(toIndex + 4).trim();

        Event newEvent = new Event(taskDescription, from, to);
        tasks.add(newEvent);

        ui.showAddedTask(newEvent, tasks.size());
        storage.saveToFile(tasks);
    }

    /**
     * Marks or unmarks a task as done based on user input.
     *
     * @param input The input command specifying the task to mark/unmark.
     * @param mark  True if the task should be marked as done, false if unmarked.
     * @throws DudeException If the input validation or task marking fails.
     */
    public void markOrUnmarkTask(String input, boolean mark) throws DudeException {
        int index = parseTaskIndex(input);
        Task task = tasks.get(index);
        task.setDone(mark);

        ui.showMarkedTask(task, mark);
        storage.saveToFile(tasks);
    }

    /**
     * Deletes a task from the task list based on user input.
     *
     * @param input The input command specifying the task to delete.
     * @throws DudeException If the input validation or task deletion fails.
     */
    public void deleteTask(String input) throws DudeException {
        int index = parseTaskIndex(input);
        Task removedTask = tasks.remove(index);

        ui.showRemovedTask(removedTask, tasks.size());
        storage.saveToFile(tasks);
    }

    /**
     * Parses the task index from user input and returns it.
     *
     * @param input The user input containing the task index.
     * @return The parsed task index.
     * @throws DudeException If the input format is invalid or the index is out of range.
     */
    private int parseTaskIndex(String input) throws DudeException {
        String[] arrOfInput = input.split(" ");
        if (arrOfInput.length < 2) {
            throw new DudeException("Please specify the task index.");
        }

        try {
            int index = Integer.parseInt(arrOfInput[1]) - 1;
            if (index < 0 || index >= tasks.size()) {
                throw new DudeException("Task index out of range.");
            }
            return index;
        } catch (NumberFormatException e) {
            throw new DudeException("Invalid task index format.");
        }
    }

    /**
     * Validates the input against a minimum length and displays an error message if
     * the input is invalid.
     *
     * @param input        The input to validate.
     * @param minLength    The minimum length of the input.
     * @param errorMessage The error message to display if validation fails.
     * @throws DudeException If the input validation fails.
     */
    private void validateInput(String input, int minLength, String errorMessage) throws DudeException {
        if (input.length() <= minLength || input.substring(minLength).trim().isEmpty()) {
            throw new DudeException(errorMessage);
        }
    }
}
