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
    public static final String DELETE_KEYWORD = "delete";
    public static final String MARK_KEYWORD = "mark";
    public static final String UNMARK_KEYWORD = "unmark";
    public static final String LIST_KEYWORD = "list";
    public static final String EXIT_KEYWORD = "bye";
    public static final String SPACE_DELIMITER = "\\s+";

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

    private static void executeAddTodo(String argument) {
        try {
            Todo newTodo = Todo.fromString(argument);
            taskList.addTask(newTodo);
            UserInterface.showTaskAddedConfirmation(newTodo, taskList);
            saveStateToDisk();
        } catch (InvalidTaskFormatException e) {
            UserInterface.showExceptionMessage(e);
        }
    }

    private static void executeAddDeadline(String argument) {
        try {
            Deadline newDeadline = Deadline.fromString(argument);
            taskList.addTask(newDeadline);
            UserInterface.showTaskAddedConfirmation(newDeadline, taskList);
            saveStateToDisk();
        } catch (InvalidTaskFormatException e) {
            UserInterface.showExceptionMessage(e);
        }
    }

    private static void executeAddEvent(String argument) {
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
        final String[] stringParts = rawUserInput.trim().split(SPACE_DELIMITER, 2);
        return stringParts.length == 2 ? stringParts : new String[] { stringParts[0] , "" };
    }

    private static void executeListAllTasks() {
        UserInterface.showListOfTasks(taskList);
    }

    private static void executeExitProgram() {
        UserInterface.showGoodbyeMessage();
        System.exit(0);
    }

    private static void executeMarkTask(String commandArgs) {
        markTaskAsDone(commandArgs);
        saveStateToDisk();
    }

    private static void executeUnmarkTask(String commandArgs) {
        markTaskAsUndone(commandArgs);
        saveStateToDisk();
    }

    private static void executeDeleteTask(String commandArgs) {
        try {
            Task task = taskList.removeTask(Integer.parseInt(commandArgs));
            UserInterface.showDeteteTaskConfirmation(task, taskList);
        } catch (NumberFormatException e) {
            System.out.println("\tPlease enter a valid task number.");
        } catch (TaskNotFoundException e) {
            UserInterface.showExceptionMessage(e);
        }
    }

    private static void executeUnknownCommand() {
        UserInterface.showUnknownCommandMessage();
    }

    private static void executeuteCommand(String userInputString) {
        final String[] commandTypeAndParams = splitCommandWordAndArgs(userInputString);
        final String commandType = commandTypeAndParams[0];
        final String commandArgs = commandTypeAndParams[1];
        switch (commandType) {
            case LIST_KEYWORD:
            executeListAllTasks();
            break;
            case MARK_KEYWORD:
            executeMarkTask(commandArgs);
            break;
            case UNMARK_KEYWORD:
            executeUnmarkTask(commandArgs);
            break;
            case TODO_KEYWORD:
            executeAddTodo(commandArgs);
            break;
            case DEADLINE_KEYWORD:
            executeAddDeadline(commandArgs);
            break;
            case DELETE_KEYWORD:
            executeDeleteTask(commandArgs);
            break;
            case EVENT_KEYWORD:
            executeAddEvent(commandArgs);
            break;
            case EXIT_KEYWORD:
            executeExitProgram();
            default:
            executeUnknownCommand();
            break;
        }
    }

    public static void main(String[] args) {
        loadStateFromDisk();
        UserInterface.showWelcomeMessage();
        while (true) {
            String userCommand = UserInterface.getUserInput();
            executeuteCommand(userCommand);
        }
    }
}
