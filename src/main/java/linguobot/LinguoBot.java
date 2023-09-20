package linguobot;

import linguobot.task.Task;
import linguobot.command.LinguoBotException;
import linguobot.command.CommandResponse;

import java.util.Scanner;
import java.util.ArrayList;
import java.io.FileNotFoundException;

import static linguobot.file.TaskFile.*;

public class LinguoBot {
    public static void main(String[] args) {
        String logo = " \n" +
                "                                       \n" +
                " __    _                 _____     _   \n" +
                "|  |  |_|___ ___ _ _ ___| __  |___| |_ \n" +
                "|  |__| |   | . | | | . | __ -| . |  _|\n" +
                "|_____|_|_|_|_  |___|___|_____|___|_|  \n" +
                "            |___|                      \n";

        System.out.println("Hello I'm " + logo);
        System.out.println("What can I do for you?");

        ArrayList<Task> taskList;

        try {
            taskList = loadTasksFromFile(); // Load tasks from the file
        } catch (FileNotFoundException e) {
            System.out.println("File not found");
            taskList = new ArrayList<>(); // Create a new task list if the file doesn't exist
        }

        Scanner in = new Scanner(System.in);

        while (true) {
            String line = in.nextLine();

            if (line.equals("list")) {
                CommandResponse.printTaskList(taskList);
            } else if (line.startsWith("mark")) {
                int MARK_START_INDEX = 5;
                int taskIndex = Integer.parseInt(line.substring(MARK_START_INDEX)) - 1;
                try {
                    CommandResponse.markTaskAsDone(taskList, taskIndex);
                } catch (LinguoBotException e) {
                    CommandResponse.printLine();
                    System.out.println("Error: " + e.getMessage());
                    CommandResponse.printLine();
                }
            } else if (line.startsWith("unmark")) {
                int UNMARK_START_INDEX = 7;
                int taskIndex = Integer.parseInt(line.substring(UNMARK_START_INDEX)) - 1;
                try {
                    CommandResponse.markTaskAsUndone(taskList, taskIndex);
                } catch (LinguoBotException e) {
                    CommandResponse.printLine();
                    System.out.println("Error: " + e.getMessage());
                    CommandResponse.printLine();
                }
            } else if (line.contains("delete")) {
                int DELETE_START_INDEX = 7;
                int taskIndex = Integer.parseInt(line.substring(DELETE_START_INDEX)) - 1;
                try {
                    CommandResponse.deleteTask(taskList, taskIndex);
                } catch (LinguoBotException e) {
                    CommandResponse.printLine();
                    System.out.println("Error: " + e.getMessage());
                    CommandResponse.printLine();
                }
            } else if (line.contains("bye")) {
                CommandResponse.printLine();
                System.out.println("Bye. Hope to see you again soon!");
                CommandResponse.printLine();
                break;
            } else {
                try {
                    CommandResponse.addTask(line, taskList);
                } catch (LinguoBotException e) {
                    CommandResponse.printLine();
                    System.out.println("Error: " + e.getMessage());
                    CommandResponse.printLine();
                }
            }
        }
    }

}
