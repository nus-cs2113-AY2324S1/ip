package BotBuddy;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class BotBuddy {

    private Storage storage;
    private TaskList tasks;
    private Ui ui;
    private Parser parser;

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

    public void addTodo(String parameters) {
        int noOfTasks = Task.getNoOfTasks();
        tasks.addTodoToTaskList(parameters);
        ui.printToUser("Got it, I've added this task:"
                + System.lineSeparator()
                + tasks.getTaskArrayList().get(noOfTasks));
    }

    public void addEvent(String parameters) {
        int noOfTasks = Task.getNoOfTasks();
        String[] eventDetails = parser.parseEventDetails(parameters);
        tasks.addEventToTaskList(eventDetails);
        ui.printToUser("Got it, I've added this task:"
                + System.lineSeparator()
                + tasks.getTaskArrayList().get(noOfTasks));
    }

    public void addDeadline(String parameters) {
        int noOfTasks = Task.getNoOfTasks();
        String[] deadlineDetails = parser.parseDeadlineDetails(parameters);
        tasks.addDeadlineToTaskList(deadlineDetails);
        ui.printToUser("Got it, I've added this task:"
                + System.lineSeparator()
                + tasks.getTaskArrayList().get(noOfTasks));
    }

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

    public void markTask(String parameters) {
        int taskToMark = Integer.parseInt(parameters) - 1;
        tasks.markTaskInTaskList(taskToMark);
        ui.printToUser("I've marked this task as done:"
                + System.lineSeparator()
                + tasks.getTaskArrayList().get(taskToMark));
    }

    public void unmarkTask(String parameters) {
        int taskToUnmark = Integer.parseInt(parameters) - 1;
        tasks.unmarkTaskInTaskList(taskToUnmark);
        ui.printToUser("I've unmarked this task:"
                + System.lineSeparator()
                + tasks.getTaskArrayList().get(taskToUnmark));
    }

    public void deleteTask(String parameters) {
        int taskToDelete = Integer.parseInt(parameters) - 1;
        String tempMessage = String.valueOf(tasks.getTaskArrayList().get(taskToDelete));
        int noOfTasks = Task.getNoOfTasks();
        tasks.removeTaskFromTaskList(taskToDelete);
        Task.setNoOfTasks(noOfTasks - 1);
        ui.printToUser("I've deleted this task:"
                + System.lineSeparator()
                + tempMessage);
    }

    public void findTask(String parameters) {
        int noOfTasks = Task.getNoOfTasks();
        ui.printToUser("Here are the found tasks for '" + parameters + "':");
        ui.printUnderscores();
        tasks.findTasksInTaskList(parameters, noOfTasks);
        ui.printUnderscores();
    }

    public void exitProgram() {
        ui.printExitMsg();
    }

}
