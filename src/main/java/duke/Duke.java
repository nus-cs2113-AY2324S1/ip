package main.java.duke;

import main.java.duke.task.*;
import main.java.duke.util.*;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {

        // Task ArrayList for storing all user inputted tasks
        ArrayList<Task> tasks = new ArrayList<Task>();

        // IO file handling
        try {
            // file object with expected file path
            File dataFile = new File("./duke.txt");
            // attempt file creation, if file already exist, read from file
            if (!dataFile.createNewFile()) {
                Scanner fileScanner = new Scanner(dataFile);
                while (fileScanner.hasNext()) {
                    String line = fileScanner.nextLine();
                    String[] taskData = line.split("\\|");
                    switch (taskData[0]) {
                    case "T":
                        tasks.add(new Todo(taskData[2]));
                        break;
                    case "E":
                        tasks.add(new Event(taskData[2], taskData[3], taskData[4]));
                        break;
                    case "D":
                        tasks.add(new Deadline(taskData[2], taskData[3]));
                        break;
                    }
                    if (Integer.parseInt(taskData[1]) == 1) {
                        tasks.get(tasks.size() - 1).setMarked(true);
                    }
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        partition();

        // name of the robot in ASCII art
        String name = " _____ _                       _     _       _         \n" +
                "/  __ \\ |                     | |   | |     | |        \n" +
                "| /  \\/ |__   ___  ___ _______| |__ | | ___ | | __ ____\n" +
                "| |   | '_ \\ / _ \\/ _ \\_  / _ \\ '_ \\| |/ _ \\| |/ /|_  /\n" +
                "| \\__/\\ | | |  __/  __// /  __/ |_) | | (_) |   <  / / \n" +
                " \\____/_| |_|\\___|\\___/___\\___|_.__/|_|\\___/|_|\\_\\/___|";

        // introduction and initial prompt for user input
        System.out.print("Hello! My name is:\n" + name + "\n\n" +
                "What can I do for you today? :)\n" + ">>");

        // Scanner object for getting user input from the terminal
        Scanner scanner = new Scanner(System.in);

        // string to monitor current user input
        String userInput = scanner.nextLine();
        String[] userWords = userInput.split(" ");
        String userCommand = userWords[0];

        // if 'bye' command is given exit program, else keep prompting for user input
        while (!userCommand.equals("bye")) {
            // if 'list' command is given, list out all tasks
            if (userCommand.equals("list")) {
                // if list is empty, print 'no item' message instead of tasks
                if (tasks.isEmpty()) {
                    try {
                        throw new DukeException("Empty List");
                    } catch (DukeException e) {
                        System.out.println(e.getMessage());
                    }
                } else {
                    System.out.println("Here are the item(s) in your list. :)");
                    // print out tasks and number each task
                    for (int i = 0; i < tasks.size(); i++) {
                        System.out.println(i + 1 + ". " + tasks.get(i));
                    }
                }
            }
            // if "mark" command is given, mark the corresponding task in tasks
            else if (userCommand.equals("mark")) {
                try {
                    // split userInput into command and integer
                    int selectedItem = Integer.parseInt(userWords[1]);
                    // check if integer given is in range of number of tasks
                    tasks.get(selectedItem - 1).setMarked(true);
                    System.out.println("Task " + selectedItem + " marked!\n" +
                            tasks.get(selectedItem - 1));
                }catch (Exception e) {
                    System.out.println("Invalid integer input! :(");
                }
            }
            // if "unmark" command is given, unmark the corresponding task in tasks
            else if (userCommand.equals("unmark")) {
                try {
                    // split userInput into command and integer
                    int selectedItem = Integer.parseInt(userWords[1]);
                    // check if integer given is in range of number of tasks
                    tasks.get(selectedItem - 1).setMarked(false); // unmark task
                    System.out.println("Task " + selectedItem + " unmarked!\n" +
                            tasks.get(selectedItem - 1));
                }catch (Exception e) {
                    System.out.println("Invalid integer input! :(");
                }
            }
            // if "todo" command is given
            else if (userCommand.equals("todo")) {
                try {
                    // store information of todo: Name
                    if (userWords.length == 1) {
                        throw new DukeException("Todo Name Missing");
                    }
                    // store information of todo: Name
                    String[] todoNameWords = Arrays.copyOfRange(userWords, 1, userWords.length);
                    String todoName = String.join(" ", todoNameWords);
                    Todo todo = new Todo(todoName);
                    tasks.add(todo); // Store user input into array
                    System.out.println("I've added this task to your list:");
                    System.out.println(todo);
                    System.out.println("You now have " + tasks.size() + " task(s) in your list. :]");
                } catch (DukeException e) {
                    System.out.println(e.getMessage());
                }
            }
            // if "event" command is given
            else if (userCommand.equals("event")) {
                // find the index of "/from" and "/to" to separate the information
                int fromSplitIndex = 0;
                int toSplitIndex = 0;
                for(int i = 1; i < userWords.length; i++) {
                    if (userWords[i].equals("/from")) {
                        fromSplitIndex = i;
                    }
                    else if (userWords[i].equals("/to")) {
                        toSplitIndex = i;
                        break;
                    }
                }
                try {
                    if (fromSplitIndex == 1) {
                        throw new DukeException("Event Name Missing");
                    }
                    if (fromSplitIndex == toSplitIndex - 1) {
                        throw new DukeException("Event From Missing");
                    }
                    if (toSplitIndex == userWords.length - 1) {
                        throw new DukeException("Event To Missing");
                    }                // store information of event: Name, From and To
                    String[] eventNameWords = Arrays.copyOfRange(userWords, 1, fromSplitIndex);
                    String[] eventFromWords = Arrays.copyOfRange(userWords, fromSplitIndex + 1, toSplitIndex);
                    String[] eventToWords = Arrays.copyOfRange(userWords, toSplitIndex + 1, userWords.length);
                    String eventName = String.join(" ", eventNameWords);
                    String eventFrom = String.join(" ", eventFromWords);
                    String eventTo = String.join(" ", eventToWords);
                    Event event = new Event(eventName, eventFrom, eventTo); // New Event object
                    tasks.add(event); // Store user input into array
                    System.out.println("I've added this task to your list:");
                    System.out.println(event);
                    System.out.println("You now have " + tasks.size() + " task(s) in your list. :]");
                } catch (DukeException e) {
                    System.out.println(e.getMessage());
                }
            }
            // if "deadline" command is given
            else if (userCommand.equals("deadline")) {
                // find the index of "/by" to separate the information
                int splitIndex = 0;
                for(int i = 1; i < userWords.length; i++) {
                    if (userWords[i].equals("/by")) {
                        splitIndex = i;
                        break;
                    }
                }
                try {
                    if (splitIndex == 1) {
                        throw new DukeException("Deadline Name Missing");
                    }
                    if (splitIndex == userWords.length - 1) {
                        throw new DukeException("Deadline By Missing");
                    }
                    // store information of deadline: Name and By
                    String[] deadlineNameWords = Arrays.copyOfRange(userWords, 1, splitIndex);
                    String[] deadlineByWords = Arrays.copyOfRange(userWords, splitIndex + 1, userWords.length);
                    String deadlineName = String.join(" ", deadlineNameWords);
                    String deadlineBy = String.join(" ", deadlineByWords);
                    Deadline deadline = new Deadline(deadlineName, deadlineBy); // New Deadline object
                    tasks.add(deadline); // Store user input into array
                    System.out.println("I've added this task to your list:");
                    System.out.println(deadline);
                    System.out.println("You now have " + tasks.size() + " task(s) in your list. :]");
                } catch (DukeException e) {
                    System.out.println(e.getMessage());
                }
            }
            // if "delete" command is given, delete the corresponding task in the list
            else if (userCommand.equals("delete")) {
                try {
                    // split userInput into command and integer
                    int selectedItem = Integer.parseInt(userWords[1]);
                    // check if integer given is in range of number of tasks
                    Task removedTask = tasks.remove(selectedItem - 1);
                    System.out.println("Task " + selectedItem + " delete!\n" +
                            removedTask);
                }catch (Exception e) {
                    System.out.println("Invalid integer input! :(");
                }

            }
            // if not unique command, prompt user again
            else {
                System.out.println("Invalid command! :/");
            }

            partition();

            // prompt user for input and store it
            System.out.print("What do you want to do next? :o\n" + ">>");
            userInput = scanner.nextLine();
            userWords = userInput.split(" ");
            userCommand = userWords[0];
        }

        // program exit statement
        System.out.println(" Bye. Hope to see you again soon! :D");

        partition();
        System.out.println("                          -END-                             ");

        // writing to file
        try {
            File dataFile = new File("./duke.txt");
            // attempt file creation
            dataFile.createNewFile();

            // add all tasks to file
            FileWriter fileWriter = new FileWriter(dataFile);
            for(Task task : tasks) {
                fileWriter.write(task.toStringFile() + System.lineSeparator());
            }
            fileWriter.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    // private function to print a stream of underscores for partitioning robot conversation
    private static void partition() {
        System.out.println("____________________________________________________________");
    }
}