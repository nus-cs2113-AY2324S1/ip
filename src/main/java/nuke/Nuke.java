package nuke;

import nuke.command.Command;
import nuke.command.CommandParser;
import nuke.command.exception.InvalidCommandArgumentException;
import nuke.command.exception.InvalidCommandTypeException;
import nuke.storage.Storage;
import nuke.storage.exception.TaskFileCopyException;
import nuke.storage.exception.TaskLoadException;
import nuke.storage.exception.TaskSaveException;
import nuke.task.Deadline;
import nuke.task.Event;
import nuke.task.Task;
import nuke.task.TaskList;
import nuke.task.Todo;

/**
 * Represents main part of Nuke.
 */
public class Nuke {
    private final Storage storage;
    private final TaskList tasks;
    private final Ui ui;
    private boolean isRunning;

    public static void main(String[] args) {
        new Nuke().run();
    }

    private Nuke() {
        ui = new Ui();
        ui.printWelcome();

        storage = new Storage();
        tasks = loadTasksFromStorage(storage, ui);

        isRunning = true;
    }

    private void run() {
        ui.printGreetingQuestion();

        // Loop for user input
        while (isRunning) {
            String input = ui.scanNextLine();
            runCommand(input);
            ui.printBlankLine();
        }

        saveTasksToStorage(storage, ui);
    }

    private TaskList loadTasksFromStorage(Storage storage, Ui ui) {
        try {
            return new TaskList(storage.loadTasks());
        } catch (TaskLoadException e) {
            // Invoked when loading tasks from the file fails
            ui.printTaskLoadError(e);
            return new TaskList();
        } catch (TaskFileCopyException e) {
            // Invoked when
            // 1. loading tasks from the file fails and
            // 2. backing up the file fails
            // If user does not ignore the error, throw RuntimeException.
            ui.handleTaskFileCopyError(e);
            return new TaskList();
        }
    }

    private void runCommand(String commandLine) {
        try {
            Command command = CommandParser.parseCommand(commandLine);
            command.run(this);
        } catch (InvalidCommandTypeException e) {
            ui.printCommandTypeError(e);
        } catch (InvalidCommandArgumentException e) {
            ui.printCommandArgumentError(e);
        }
    }

    private void saveTasksToStorage(Storage storage, Ui ui) {
        try {
            storage.saveTasks(getFormattedTasks());
        } catch (TaskSaveException e) {
            e.tasks = getTasks();
            ui.handleTaskSaveError(e);
        }
    }

    /**
     * Makes Nuke to quit.
     */
    public void quit() {
        isRunning = false;
        ui.printBye();
    }

    /**
     * Adds the task.
     *
     * @param task task to add
     */
    public void addTask(Task task) {
        tasks.add(task);
        ui.printAddedTask(task.toString(), getNumberOfTasks());
    }

    /**
     * Lists all tasks.
     */
    public void listTask() {
        ui.printListOfTasks(getTasks());
    }

    /**
     * Marks the task of the index as done.
     *
     * @param idx index of the task to be marked done
     */
    public void markTask(int idx) {
        String task = tasks.mark(idx);
        ui.printMarkedTask(task);
    }

    /**
     * Marks the task of the index as not done.
     *
     * @param idx index of the task to be marked not done
     */
    public void unmarkTask(int idx) {
        String task = tasks.unmark(idx);
        ui.printUnmarkedTask(task);
    }

    /**
     * Deletes the task of the index.
     *
     * @param idx index of the task to be deleted
     */
    public void deleteTask(int idx) {
        String task = tasks.delete(idx);
        ui.printDeletedTask(task, getNumberOfTasks());
    }

    /**
     * Adds {@link Todo}.
     *
     * @param name name of {@link Todo} to be added
     */
    public void addTodo(String name) {
        addTask(new Todo(name));
    }

    /**
     * Adds {@link Deadline}.
     *
     * @param name name of {@link Deadline} to be added
     * @param by deadline of {@link Deadline} to be added
     */
    public void addDeadline(String name, String by) {
        addTask(new Deadline(name, by));
    }

    /**
     * Adds {@link Event}.
     *
     * @param name name of {@link Event} to be added
     * @param from start period of {@link Event} to be added
     * @param to end period of {@link Event} to be added
     */
    public void addEvent(String name, String from, String to) {
        addTask(new Event(name, from, to));
    }

    /**
     * Returns the number of the tasks.
     *
     * @return the number of the tasks
     */
    public int getNumberOfTasks() {
        return tasks.size();
    }

    /**
     * Returns string representations of all tasks.
     *
     * @return all tasks in form of {@link String}[].
     */
    public String[] getTasks() {
        return tasks.getTasks();
    }

    /**
     * Returns all tasks in form of formatted manner,
     * which is used to save in file.
     *
     * @return all tasks in form of formatted manner.
     */
    public String[] getFormattedTasks() {
        return tasks.getFormattedTasks();
    }
}
