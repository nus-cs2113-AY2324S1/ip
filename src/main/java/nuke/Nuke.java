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

public class Nuke {
    private final Storage storage;
    private final TaskList tasks;
    private final Ui ui;
    private boolean isRunning;

    public static void main(String[] args) {
        new Nuke().run();
    }

    public Nuke() {
        ui = new Ui();
        ui.printWelcome();

        storage = new Storage();
        tasks = loadTasksFromStorage(storage, ui);

        isRunning = true;
    }

    public void run() {
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
            storage.saveTasks(tasks.getFormattedTasks());
        } catch (TaskSaveException e) {
            e.tasks = tasks.getTasks();
            ui.handleTaskSaveError(e);
        }
    }

    public void quit() {
        isRunning = false;
        ui.printBye();
    }

    public void addTask(Task task) {
        tasks.add(task);
        ui.printAddedTask(task.toString(), countTasks());
    }

    public void listTask() {
        ui.printListOfTasks(tasks.getTasks());
    }

    public void markTask(int idx) {
        String task = tasks.mark(idx);
        ui.printMarkedTask(task);
    }

    public void unmarkTask(int idx) {
        String task = tasks.unmark(idx);
        ui.printUnmarkedTask(task);
    }

    public void deleteTask(int idx) {
        String task = tasks.delete(idx);
        ui.printDeletedTask(task, countTasks());
    }

    public void addTodo(String name) {
        addTask(new Todo(name));
    }

    public void addDeadline(String name, String by) {
        addTask(new Deadline(name, by));
    }

    public void addEvent(String name, String from, String to) {
        addTask(new Event(name, from, to));
    }

    public int countTasks() {
        return tasks.size();
    }
}
