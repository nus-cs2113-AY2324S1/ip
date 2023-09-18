package jerry;

import jerry.task.Task;
import jerry.task.Todo;
import jerry.task.Deadline;
import jerry.task.Event;
import jerry.task.TaskList;
import jerry.userInterface.UserInterface;
import jerry.exceptions.InvalidTaskFormatException;
import jerry.exceptions.TaskNotFoundException;
import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.io.IOException;

public class Jerry {

    private static TaskList taskList;

    public static final Path FILE_PATH = Paths.get("./data/jerry.txt");
    public static final String TODO_KEYWORD = "todo";
    public static final String DEADLINE_KEYWORD = "deadline";
    public static final String EVENT_KEYWORD = "event";
    public static final String MARK_KEYWORD = "mark";
    public static final String UNMARK_KEYWORD = "unmark";
    public static final String LIST_KEYWORD = "list";
    public static final String EXIT_KEYWORD = "bye";

    private static void saveStateToDisk() {
        try {
            File file = FILE_PATH.toFile();
            file.getParentFile().mkdirs();
            taskList.serializeToFile(FILE_PATH);
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    private static void loadStateFromDisk() {
        try {
            if (FILE_PATH.toFile().exists()) {
                taskList = TaskList.deserializeFromFile(FILE_PATH);
            } else taskList = new TaskList();
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    public static void markTaskAsDone(String argument) {
        try {
            Task task = taskList.getTaskByIndex(Integer.parseInt(argument));
            task.markAsDone();
            UserInterface.showChangeTaskStatusConfirmation(task);
        } catch (NumberFormatException e) {
            System.out.println("\tPlease enter a valid task number.");
        } catch (TaskNotFoundException e) {
            UserInterface.showExceptionMessage(e);
        }
    }


    public static void markTaskAsUndone(String argument) {
        try {
            Task task = taskList.getTaskByIndex(Integer.parseInt(argument));
            task.markAsUndone();
            UserInterface.showChangeTaskStatusConfirmation(task);
        } catch (NumberFormatException e) {
            System.out.println("\tPlease enter a valid task number.");
        } catch (TaskNotFoundException e) {
            UserInterface.showExceptionMessage(e);
        }
    }

    private static void execAddTodo(String argument) {
        try {
            Todo newTodo = Todo.fromString(argument);
            taskList.addTask(newTodo);
            UserInterface.showTaskAddedConfirmation(newTodo, taskList);
            saveStateToDisk();
        } catch (InvalidTaskFormatException e) {
            UserInterface.showExceptionMessage(e);
        }
    }

    private static void execAddDeadline(String argument) {
        try {
            Deadline newDeadline = Deadline.fromString(argument);
            taskList.addTask(newDeadline);
            UserInterface.showTaskAddedConfirmation(newDeadline, taskList);
            saveStateToDisk();
        } catch (InvalidTaskFormatException e) {
            UserInterface.showExceptionMessage(e);
        }
    }

    private static void execAddEvent(String argument) {
        try {
            Event newEvent = Event.fromString(argument);
            taskList.addTask(newEvent);
            UserInterface.showTaskAddedConfirmation(newEvent, taskList);
            saveStateToDisk();
        } catch (InvalidTaskFormatException e) {
            UserInterface.showExceptionMessage(e);
        }
    }

    private static String[] splitCommandWordAndArgs(String rawUserInput) {
        final String[] split = rawUserInput.trim().split("\\s+", 2);
        return split.length == 2 ? split : new String[] { split[0] , "" }; // else case: no parameters
    }

    private static void execListAllTasks() {
        UserInterface.showListOfTasks(taskList);
    }

    private static void execExitProgram() {
        UserInterface.showGoodbyeMessage();
        System.exit(0);
    }

    private static void execMarkTask(String commandArgs) {
        markTaskAsDone(commandArgs);
        saveStateToDisk();
    }

    private static void execUnmarkTask(String commandArgs) {
        markTaskAsUndone(commandArgs);
        saveStateToDisk();
    }

    private static void execDeleteTask(String commandArgs) {
        try {
            Task task = taskList.removeTask(Integer.parseInt(commandArgs));
            UserInterface.showDeteteTaskConfirmation(task, taskList);
        } catch (NumberFormatException e) {
            System.out.println("\tPlease enter a valid task number.");
        } catch (TaskNotFoundException e) {
            UserInterface.showExceptionMessage(e);
        }
    }

    private static void execUnknownCommand() {
        UserInterface.showUnknownCommandMessage();
    }

    private static void executeCommand(String userInputString) {
        final String[] commandTypeAndParams = splitCommandWordAndArgs(userInputString);
        final String commandType = commandTypeAndParams[0];
        final String commandArgs = commandTypeAndParams[1];
        switch (commandType) {
            case LIST_KEYWORD:
            execListAllTasks();
            break;
            case MARK_KEYWORD:
            execMarkTask(commandArgs);
            break;
            case UNMARK_KEYWORD:
            execUnmarkTask(commandArgs);
            break;
            case TODO_KEYWORD:
            execAddTodo(commandArgs);
            break;
            case DEADLINE_KEYWORD:
            execAddDeadline(commandArgs);
            break;
            case EVENT_KEYWORD:
            execAddEvent(commandArgs);
            break;
            case EXIT_KEYWORD:
            execExitProgram();
            default:
            execUnknownCommand();
            break;
        }
    }

    public static void main(String[] args) {
        loadStateFromDisk();
        UserInterface.showWelcomeMessage();
        while (true) {
            String userCommand = UserInterface.getUserInput();
            executeCommand(userCommand);
        }
    }
}
