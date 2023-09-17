package jerry;

import jerry.task.Task;
import jerry.task.Todo;
import jerry.task.Deadline;
import jerry.task.Event;
import jerry.task.TaskList;
import jerry.userInterface.UserInterface;
import jerry.exceptions.InvalidTaskFormatException;
import jerry.exceptions.TaskNotFoundException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.io.FileWriter;
import java.io.File;
import java.io.IOException;

public class Jerry {

    private static final TaskList taskList = new TaskList();

    public static final Path FILE_PATH = Paths.get("./data/jerry.txt");

    private static void saveStateToDisk() {
        try {
            File file = FILE_PATH.toFile();
            FileWriter myWriter = new FileWriter(FILE_PATH.toString());
            file.getParentFile().mkdirs();
            myWriter.write(taskList.serialize());
            myWriter.close();
        } catch (IOException e) {
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
        } catch (InvalidTaskFormatException e) {
            UserInterface.showExceptionMessage(e);
        }
    }

    private static void execAddDeadline(String argument) {
        try {
            Deadline newDeadline = Deadline.fromString(argument);
            taskList.addTask(newDeadline);
            UserInterface.showTaskAddedConfirmation(newDeadline, taskList);
        } catch (InvalidTaskFormatException e) {
            UserInterface.showExceptionMessage(e);
        }
    }

    private static void execAddEvent(String argument) {
        try {
            Event newEvent = Event.fromString(argument);
            taskList.addTask(newEvent);
            UserInterface.showTaskAddedConfirmation(newEvent, taskList);
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
    }

    private static void execUnmarkTask(String commandArgs) {
        markTaskAsUndone(commandArgs);
    }

    private static void execUnknownCommand() {
        UserInterface.showUnknownCommandMessage();
    }

    private static void executeCommand(String userInputString) {
        final String[] commandTypeAndParams = splitCommandWordAndArgs(userInputString);
        final String commandType = commandTypeAndParams[0];
        final String commandArgs = commandTypeAndParams[1];
        switch (commandType) {
            case "list":
            execListAllTasks();
            break;
            case "mark":
            execMarkTask(commandArgs);
            break;
            case "unmark":
            execUnmarkTask(commandArgs);
            break;
            case "todo":
            execAddTodo(commandArgs);
            break;
            case "deadline":
            execAddDeadline(commandArgs);
            break;
            case "event":
            execAddEvent(commandArgs);
            break;
            case "bye":
            execExitProgram();
            default:
            execUnknownCommand();
            break;
        }
        saveStateToDisk();
    }

    public static void main(String[] args) {
        UserInterface.showWelcomeMessage();
        while (true) {
            String userCommand = UserInterface.getUserInput();
            executeCommand(userCommand);
        }
    }
}
