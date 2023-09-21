package dude;

import java.util.ArrayList;
import java.util.Scanner;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.FileNotFoundException;

public class Dude {

    private static final String DIRECTORY_PATH = "./data";
    private static final String FILE_PATH = DIRECTORY_PATH + "/duke.txt";

    // Method to draw horizontal lines
    public static void drawLine() {
        for (int i = 0; i < 30; i++) {
            System.out.print("_");
        }
        System.out.println();
    }

    // Method to print the logo and introductory message
    public static void hiDude() {
        String logo = "###            #        \n"
                + "#  #           #        \n"
                + "#  #  #  #   ###   ##   \n"
                + "#  #  #  #  #  #  # ##  \n"
                + "#  #  #  #  #  #  ##    \n"
                + "###    ###   ###   ##   \n";

        System.out.println("Hello from\n" + logo);
        drawLine();
        System.out.println("Hello! I'm your best dude:)");
        System.out.println("What can I do for you?");
        drawLine();
    }

    // Method to handle the storage of tasks
    public static void storeDude() {
        Scanner scan = new Scanner(System.in);
        setupFile();
        ArrayList<Task> tasks = loadFromFile();

        String input = scan.nextLine();
        while (!input.isEmpty()) {
            drawLine();

            try {
                if (input.equals("bye")) {
                    byeDude();
                    break;
                } else if (input.equals("list")) {
                    listTasks(tasks);
                } else if (input.startsWith("todo")) {
                    addTodoTask(tasks, input);
                } else if (input.startsWith("deadline")) {
                    addDeadlineTask(tasks, input);
                } else if (input.startsWith("event")) {
                    addEventTask(tasks, input);
                } else if (input.startsWith("mark") || input.startsWith("unmark")) {
                    markOrUnmarkTask(tasks, input);
                } else if (input.startsWith("delete")) {
                    deleteTask(tasks, input);
                } else {
                    System.out.println("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
                }
            } catch (DudeException e) {
                System.out.println(e.getMessage());
            }

            drawLine();
            input = scan.nextLine();
        }
        scan.close();
    }


    private static void listTasks(ArrayList<Task> tasks) {
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < tasks.size(); i++) {
            System.out.println((i + 1) + ". " + tasks.get(i));
        }
    }

    private static void addTodoTask(ArrayList<Task> tasks, String input) throws DudeException {
        if (input.length() <= 5) {
            throw new DudeException("☹ OOPS!!! The description of a todo cannot be empty.");
        }

        String taskDescription = input.substring(5).trim();
        if (taskDescription.isEmpty()) {
            throw new DudeException("☹ OOPS!!! The description of a todo cannot be empty.");
        }
        tasks.add(new Task(taskDescription));
        printAddedTask(tasks);
        saveToFile(tasks);
    }


    private static void addDeadlineTask(ArrayList<Task> tasks, String input) throws DudeException {
        int byIndex = input.indexOf("/by");
        if (byIndex == -1) {
            throw new DudeException("☹ OOPS!!! Please specify a deadline using /by.");
        }

        String taskDescription = input.substring(9, byIndex).trim();
        if (taskDescription.isEmpty()) {
            throw new DudeException("☹ OOPS!!! The description of a deadline cannot be empty.");
        }

        String by = input.substring(byIndex + 4).trim();
        if (by.isEmpty()) {
            throw new DudeException("☹ OOPS!!! The deadline time cannot be empty.");
        }

        tasks.add(new Deadline(taskDescription, by));
        printAddedTask(tasks);
        saveToFile(tasks);
    }

    private static void addEventTask(ArrayList<Task> tasks, String input) throws DudeException {
        int fromIndex = input.indexOf("/from");
        int toIndex = input.indexOf("/to");

        if (fromIndex == -1 || toIndex == -1) {
            throw new DudeException("☹ OOPS!!! Please specify event time using /from and /to.");
        }

        String taskDescription = input.substring(6, fromIndex).trim();
        if (taskDescription.isEmpty()) {
            throw new DudeException("☹ OOPS!!! The description of an event cannot be empty.");
        }

        String from = input.substring(fromIndex + 6, toIndex).trim();
        if (from.isEmpty()) {
            throw new DudeException("☹ OOPS!!! The start time of an event cannot be empty.");
        }

        String to = input.substring(toIndex + 4).trim();
        if (to.isEmpty()) {
            throw new DudeException("☹ OOPS!!! The end time of an event cannot be empty.");
        }

        tasks.add(new Event(taskDescription, from, to));
        printAddedTask(tasks);
        saveToFile(tasks);
    }

    private static void markOrUnmarkTask(ArrayList<Task> tasks, String input) {
        String[] arrOfInput = input.split(" ", 2);
        if (arrOfInput.length < 2) {
            System.out.println("Please specify the task index.");
            return;
        }

        try {
            int index = Integer.parseInt(arrOfInput[1]) - 1;
            if (index < 0 || index >= tasks.size()) {
                System.out.println("dude.Task index out of range.");
                return;
            }
            tasks.get(index).isDone = input.startsWith("mark");
            String message = input.startsWith("mark") ?
                    "Nice! I've marked this task as done:" :
                    "OK, I've marked this task as not done yet:";
            System.out.println(message);
            System.out.println("   " + tasks.get(index));
        } catch (NumberFormatException e) {
            System.out.println("Invalid task index format.");
        }

        saveToFile(tasks);
    }

    private static void printAddedTask(ArrayList<Task> tasks) {
        System.out.println("Got it. I've added this task:");
        System.out.println("  " + tasks.get(tasks.size() - 1));
        System.out.println("Now you have " + tasks.size() + (tasks.size() == 1 ? " task" : " tasks") + " in the list.");
    }

    private static void deleteTask(ArrayList<Task> tasks, String input) {
        String[] arrOfInput = input.split(" ");
        if (arrOfInput.length < 2) {
            System.out.println("Please specify the task index to delete.");
            return;
        }

        try {
            int index = Integer.parseInt(arrOfInput[1]) - 1;
            if (index < 0 || index >= tasks.size()) {
                System.out.println("Task index out of range.");
                return;
            }

            Task removedTask = tasks.remove(index);
            System.out.println("Noted. I've removed this task:");
            System.out.println("  " + removedTask);
            System.out.println("Now you have " + tasks.size() + (tasks.size() == 1 ? " task" : " tasks") + " in the list.");
        } catch (NumberFormatException e) {
            System.out.println("Invalid task index format.");
        }

        saveToFile(tasks);
    }

    private static void setupFile() {
        // Check if the directory exists, if not, create it
        File directory = new File(DIRECTORY_PATH);
        if (!directory.exists()) {
            directory.mkdir();
        }

        // Check if the file exists, if not, create it
        File file = new File(FILE_PATH);
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                System.out.println("Error creating file: " + e.getMessage());
            }
        }
    }

    private static ArrayList<Task> loadFromFile() {
        ArrayList<Task> tasks = new ArrayList<>();
        try {
            File file = new File(FILE_PATH);
            Scanner fileReader = new Scanner(file);

            while (fileReader.hasNextLine()) {
                String line = fileReader.nextLine();
                // Assuming the first char denotes the type of task
                char taskType = line.charAt(0);
                switch (taskType) {
                case 'T':
                    tasks.add(Task.fromFileFormat(line));
                    break;
                case 'D':
                    tasks.add(Deadline.fromFileFormat(line));
                    break;
                case 'E':
                    tasks.add(Event.fromFileFormat(line));
                    break;
                }
            }

            fileReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("Error reading from file: " + e.getMessage());
        }
        return tasks;
    }

    private static void saveToFile(ArrayList<Task> tasks) {
        try {
            FileWriter fileWriter = new FileWriter(FILE_PATH);
            for (Task task : tasks) {
                fileWriter.write(task.toFileFormat() + "\n");
            }
            fileWriter.close();
        } catch (IOException e) {
            System.out.println("Error writing to file: " + e.getMessage());
        }
    }


    // Method to print the goodbye message
    public static void byeDude() {
        System.out.println("Bye. Hope to see you again soon!");
        drawLine();
    }

    // Main method
    public static void main(String[] args) {
        hiDude();
        storeDude();
    }
}

