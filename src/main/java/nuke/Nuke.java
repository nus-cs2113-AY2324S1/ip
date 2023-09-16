package nuke;

import nuke.command.Command;
import nuke.command.CommandParser;
import nuke.command.exception.InvalidCommandArgumentException;
import nuke.command.exception.InvalidCommandTypeException;
import nuke.savefile.SaveManager;
import nuke.savefile.exception.TaskFileCopyException;
import nuke.savefile.exception.TaskLoadException;
import nuke.savefile.exception.TaskSaveException;
import nuke.task.Deadline;
import nuke.task.Event;
import nuke.task.Task;
import nuke.task.Todo;

import java.util.ArrayList;
import java.util.Scanner;

public class Nuke {
    private static boolean isRunning = true;
    private static final ArrayList<Task> tasks = new ArrayList<>();

    public static void main(String[] args) {
        final Scanner USER_IN = new Scanner(System.in);

        Ui.printWelcome();

        // Fail to run if
        // 1. loading tasks from the file fails and
        // 2. backing up the file fails and
        // 3. user does not ignore the error
        if (!loadTasksFromStorage(USER_IN)) {
            return;
        }

        Ui.printWelcomeAfter();

        // Loop for user input
        while (isRunning) {
            String input = USER_IN.nextLine();
            runCommand(input);
            Ui.printBlankLine();
        }

        saveTasksToStorage(USER_IN);
    }

    private static void runCommand(String commandLine) {
        try {
            Command command = CommandParser.parseCommand(commandLine);
            command.run();
        } catch (InvalidCommandTypeException | InvalidCommandArgumentException ignored) {
            // Thrown by Parse.parseCommand() to prevent command.run().
        }
    }

    private static boolean loadTasksFromStorage(final Scanner USER_IN) {
        ArrayList<Task> loadedTasks;
        try {
            loadedTasks = SaveManager.loadTasksFromStorage();
            tasks.addAll(loadedTasks);
        } catch (TaskLoadException e) {
            Ui.printTaskLoadError(e.backupFilePath);
        } catch (TaskFileCopyException e) {
            Ui.printTaskFileCopyError(e.filePath);

            String input = USER_IN.nextLine();
            if (!input.equalsIgnoreCase("ignore")) {
                return false;
            }
        }
        return true;
    }

    private static void saveTasksToStorage(final Scanner USER_IN) {
        try {
            SaveManager.saveTasksToStorage();
        } catch (TaskSaveException e) {
            Ui.printTaskSaveError(getTasks());
            boolean isQuitting = false;
            while (!isQuitting) {
                String input = USER_IN.nextLine();
                isQuitting = !input.isEmpty();
            }
        }
    }

    public static void quit() {
        isRunning = false;
        Ui.printBye();
    }

    public static void addTask(Task task) {
        tasks.add(task);
        Ui.printAddedTask(task.toString(), getNumberOfTasks());
    }

    public static void listTask() {
        Ui.printListOfTasks(getTasks());
    }

    public static void markTask(int idx) {
        Task task = tasks.get(idx);
        task.setDone(true);
        Ui.printMarkedTask(task.toString());
    }

    public static void unmarkTask(int idx) {
        Task task = tasks.get(idx);
        task.setDone(false);
        Ui.printUnmarkedTask(task.toString());
    }

    public static void deleteTask(int idx) {
        Task task = tasks.remove(idx);
        Ui.printDeletedTask(task.toString(), getNumberOfTasks());
    }

    public static void addTodo(String name) {
        addTask(new Todo(name));
    }

    public static void addDeadline(String name, String by) {
        addTask(new Deadline(name, by));
    }

    public static void addEvent(String name, String from, String to) {
        addTask(new Event(name, from, to));
    }

    public static int getNumberOfTasks() {
        return tasks.size();
    }

    public static String[] getTasks() {
        return tasks.stream().map(Task::toString).toArray(String[]::new);
    }

    public static String[] getFormattedTasks() {
        return tasks.stream().map(Task::formatData).toArray(String[]::new);
    }
}
