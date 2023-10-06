package BotBuddy;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * The BotBuddy class represents the main application class for the BotBuddy program.
 */
public class BotBuddy {

    private Storage storage;
    private TaskList tasks;
    private Ui ui;
    private Parser parser;

    /**
     * Initializes the required objects and loads data from the storage file.
     *
     * @param filePath the relative file path of the storage file.
     * @param directoryName the name of the directory in which the storage file is located.
     */
    public BotBuddy(String filePath, String directoryName) {
        ui = new Ui();
        parser = new Parser();

        // check if storage file exists, if not try to create it
        try {
            storage = new Storage(filePath, directoryName, ui);
        } catch (IOException e) {
            ui.printCreateTaskFileErrorMsg();
            System.exit(1);
        }

        // try to read from storage file
        try {
            tasks = new TaskList(storage.load());
        } catch (FileNotFoundException e) {
            ui.printCreateTaskFileErrorMsg();
            System.exit(1);
        } catch (BotBuddyException e) {
            ui.printToUser(e.getMessage());
            System.exit(1);
        }

    }

    public static void main(String[] args) {
        new BotBuddy("data/taskfile.txt", "data").run();
    }

    /**
     * Runs the program until the bye command is given.
     */
    public void run() {
        ui.printWelcomeMsg();
        String command = "";
        String parameters = "";
        do {
            String userInput = ui.getUserInput();
            String[] inputArr = parser.parseInput(userInput);
            command = inputArr[0];
            if (inputArr.length == 2) {
                parameters = inputArr[1];
            } else {
                parameters = "";
            }

            try {
                parser.validateInput(command, parameters);
            } catch (BotBuddyException e) {
                ui.printToUser(e.getMessage());
                continue;
            }


            switch (command) {
            case "todo":
                addTodo(parameters);
                break;

            case "event":
                addEvent(parameters);
                break;

            case "deadline":
                addDeadline(parameters);
                break;

            case "list":
                listTasks();
                break;

            case "mark":
                markTask(parameters);
                break;

            case "unmark":
                unmarkTask(parameters);
                break;

            case "delete":
                deleteTask(parameters);
                break;

            case "find":
                findTask(parameters);
                break;

            case "bye":
                exitProgram();
                return;
            }

            // write to file here
            try {
                storage.store(tasks.getTaskArrayList());
            } catch (IOException e) {
                ui.printTaskFileWriteErrorMsg();
            }

        } while (!command.equals("bye"));
    }

    /**
     * Adds a todo and prints confirmation to the user.
     *
     * @param description Description of the todo.
     */
    public void addTodo(String description) {
        int noOfTasks = Task.getNoOfTasks();
        tasks.addTodoToTaskList(description);
        ui.printToUser("Got it, I've added this task:"
                + System.lineSeparator()
                + tasks.getTaskArrayList().get(noOfTasks));
    }

    /**
     * Adds an event and prints confirmation to the user.
     *
     * @param unparsedEventDetails String containing description, from date, and to date of event.
     */
    public void addEvent(String unparsedEventDetails) {
        int noOfTasks = Task.getNoOfTasks();
        String[] eventDetails = parser.parseEventDetails(unparsedEventDetails);
        tasks.addEventToTaskList(eventDetails);
        ui.printToUser("Got it, I've added this task:"
                + System.lineSeparator()
                + tasks.getTaskArrayList().get(noOfTasks));
    }

    /**
     * Adds a deadline and prints confirmation to the user.
     *
     * @param unparsedDeadlineDetails String containing description and by date of deadline.
     */
    public void addDeadline(String unparsedDeadlineDetails) {
        int noOfTasks = Task.getNoOfTasks();
        String[] deadlineDetails = parser.parseDeadlineDetails(unparsedDeadlineDetails);
        tasks.addDeadlineToTaskList(deadlineDetails);
        ui.printToUser("Got it, I've added this task:"
                + System.lineSeparator()
                + tasks.getTaskArrayList().get(noOfTasks));
    }

    /**
     * Checks if there are tasks in the task list and prints them to the user if they exist.
     */
    public void listTasks() {
        int noOfTasks = Task.getNoOfTasks();
        if (noOfTasks == 0) {
            ui.printToUser("There are currently no tasks!");
            return;
        }
        ui.printUnderscores();
        tasks.listTasksInTaskList(noOfTasks);
        ui.printUnderscores();
    }

    /**
     * Marks a task as done and prints confirmation to the user.
     *
     * @param taskToMark Task number to mark as done.
     */
    public void markTask(String taskToMark) {
        int taskIndex = Integer.parseInt(taskToMark) - 1;
        tasks.markTaskInTaskList(taskIndex);
        ui.printToUser("I've marked this task as done:"
                + System.lineSeparator()
                + tasks.getTaskArrayList().get(taskIndex));
    }

    /**
     * Unmarks a task as done and prints confirmation to the user.
     *
     * @param taskToUnmark Task number to unmark as done.
     */
    public void unmarkTask(String taskToUnmark) {
        int taskIndex = Integer.parseInt(taskToUnmark) - 1;
        tasks.unmarkTaskInTaskList(taskIndex);
        ui.printToUser("I've unmarked this task:"
                + System.lineSeparator()
                + tasks.getTaskArrayList().get(taskIndex));
    }

    /**
     * Deletes a task and prints confirmation to the user.
     *
     * @param taskToDelete Task number to delete.
     */
    public void deleteTask(String taskToDelete) {
        int taskIndex = Integer.parseInt(taskToDelete) - 1;
        String tempMessage = String.valueOf(tasks.getTaskArrayList().get(taskIndex));
        int noOfTasks = Task.getNoOfTasks();
        tasks.removeTaskFromTaskList(taskIndex);
        Task.setNoOfTasks(noOfTasks - 1);
        ui.printToUser("I've deleted this task:"
                + System.lineSeparator()
                + tempMessage);
    }

    /**
     * Searches for tasks and prints them to the user.
     *
     * @param searchString String to search for amongst tasks.
     */
    public void findTask(String searchString) {
        int noOfTasks = Task.getNoOfTasks();
        ui.printToUser("Here are the found tasks for '" + searchString + "':");
        ui.printUnderscores();
        tasks.findTasksInTaskList(searchString, noOfTasks);
        ui.printUnderscores();
    }

    /**
     * Prints the exit message.
     */
    public void exitProgram() {
        ui.printExitMsg();
    }

}
