package jerry;

import java.util.List;
import java.util.ArrayList;
import java.text.ParseException;

import jerry.task.Task;
import jerry.task.Todo;
import jerry.task.Deadline;
import jerry.task.Event;
import jerry.task.TaskList;
import jerry.userInterface.UserInterface;

public class Jerry {

    private static final TaskList taskList = new TaskList();
    private static final String HORIZONTAL_LINE = "--------------------------------------------";

    public static void toggleTaskStatus(String argument, Boolean isDone) {
        try {
            int index = Integer.parseInt(argument);
            if (isDone) {
                taskList.getTaskByIndex(index).markAsUndone();
                System.out.println("\tNice! I've marked this task as not done yet:");
            } else {
                taskList.getTaskByIndex(index).markAsDone();
                System.out.println("\tNice! I've marked this task as done:");
            }
            System.out.printf("\t\t %s\n", taskList.getTaskByIndex(index).toString());
        } catch (NumberFormatException e) {
            System.out.println("\tPlease enter a valid task number.");
        } catch (IndexOutOfBoundsException e) {
            if (taskList.isEmpty()) {
                System.out.println("\tYou haven't added any taskList yet.");
            } else if (taskList.size() == 1) {
                System.out.println("\tYou have added only one task yet.");
            } else {
                System.out.printf("\tThe task number must be between 1 and %d.\n", taskList.size());
            }
        }
    }

    private static void execAddTodo(String argument) {
        try {
            Todo newTodo = Todo.fromString(argument);
            taskList.addTask(newTodo);
            UserInterface.showTaskAddedConfirmation(newTodo, taskList);
        } catch (ParseException e) {
            System.out.println(e.getMessage());
        }
    }

    private static void execAddDeadline(String argument) {
        try {
            Deadline newDeadline = Deadline.fromString(argument);
            taskList.addTask(newDeadline);
            UserInterface.showTaskAddedConfirmation(newDeadline, taskList);
        } catch (ParseException e) {
            System.out.println(e.getMessage());
        }
    }

    private static void execAddEvent(String argument) {
        try {
            Event newEvent = Event.fromString(argument);
            taskList.addTask(newEvent);
            UserInterface.showTaskAddedConfirmation(newEvent, taskList);
        } catch (ParseException e) {
            System.out.println(e.getMessage());
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

    private static void executeCommand(String userInputString) {
            final String[] commandTypeAndParams = splitCommandWordAndArgs(userInputString);
            final String commandType = commandTypeAndParams[0];
            final String commandArgs = commandTypeAndParams[1];
            switch (commandType) {
            case "list":
                execListAllTasks();
                break;
            case "mark":
                toggleTaskStatus(commandArgs, false);
                break;
            case "unmark":
                toggleTaskStatus(commandArgs, true);
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
                System.out.println("\tUnknown command.");
                break;
            }
        System.out.println(HORIZONTAL_LINE);
    }

    public static void main(String[] args) {
        UserInterface.showWelcomeMessage();
        while (true) {
            String userCommand = UserInterface.getUserInput();
            executeCommand(userCommand);
        }
    }
}
