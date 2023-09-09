package nuke;

import nuke.command.Command;
import nuke.command.exception.InvalidCommandArgumentException;
import nuke.command.exception.InvalidCommandTypeException;
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

        // Loop for user input
        while (isRunning) {
            String input = USER_IN.nextLine();
            runCommand(input);
            Ui.printBlankLine();
        }
    }

    private static void runCommand(String commandLine) {
        try {
            Command command = Parser.parseCommand(commandLine);
            command.run();
        } catch (InvalidCommandTypeException | InvalidCommandArgumentException ignored) {
            // Thrown by Parse.parseCommand() to prevent command.run().
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
        String[] taskList = tasks.stream().map(Task::toString).toArray(String[]::new);
        Ui.printListOfTasks(taskList);
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
}
