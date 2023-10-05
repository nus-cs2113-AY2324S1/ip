import Tasks.*;
import dukey.*;

import java.util.ArrayList;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;


public class Dukey {
    private static void addTodo(String line, ArrayList<Task> tasks) {
        String[] words = line.split(" ");
        String description = "";
        String filePath = "./docs/dukey.txt";
        try {
            for (int i = 1; i < words.length; i++) {
                description += words[i] + " ";
            }
            tasks.add(new Todo(description));
            tasks.get(tasks.size() - 1).printNewTask();
        } catch(IndexOutOfBoundsException e) {
            System.out.println("_____________________________________________________");
            System.out.println(DukeyException.todoDescriptionError());
            System.out.println("_____________________________________________________");
        }
    }

    private static void addEvent(String line, ArrayList<Task> tasks) {
        int startIndexOfFrom = line.indexOf("/from");
        int startIndexOfTo = line.indexOf("/to");
        final int beginIndex = 6;
        try {
        String from = line.substring(startIndexOfFrom + 6, startIndexOfTo);
        String to = line.substring(startIndexOfTo + 4);
        String description = line.substring(beginIndex, startIndexOfFrom);
        tasks.add(new Event(from, to, description));
        tasks.get(tasks.size() - 1).printNewTask();
        } catch(StringIndexOutOfBoundsException e) {
            System.out.println("_____________________________________________________");
            System.out.println(DukeyException.EventFormatError());
            System.out.println("_____________________________________________________");
        }
    }

    private static void addDeadline(String line, ArrayList<Task> tasks) {
        String[] words = line.split("/by");
        String[] words2 = line.split(" ");
    //    int indexOfDescriptionEnd = line.indexOf("/by");
        try {
            String description = words2[1];
            String by = words[1];
            tasks.add(new Deadline(description, by));
            tasks.get(tasks.size() - 1).printNewTask();
        } catch(ArrayIndexOutOfBoundsException e) {
            System.out.println("_____________________________________________________");
            System.out.println(DukeyException.deadlineDescriptionError());
            System.out.println("_____________________________________________________");
        }
    }

    private static void unmarkTask(String line, ArrayList<Task> tasks) {
        String[] words = line.split(" ");
        int taskNum = Integer.parseInt(words[1]) - 1;
        tasks.get(taskNum).unmarkTask();
    }

    private static void markTask(String line, ArrayList<Task> tasks) {
        String[] words = line.split(" ");
        int taskNum = Integer.parseInt(words[1]) - 1;
        tasks.get(taskNum).setDone();
    }


    public static void printTaskList(ArrayList<Task> tasks) {
        System.out.println("Here are the tasks in your list:");
        int index = 1;
        for (Task task : tasks) {
            System.out.println((index++) + "." + task);
        }
    }

    private static void printFileContents(String filePath) throws FileNotFoundException {
        File f = new File(filePath); // create a File for the given file path
        Scanner s = new Scanner(f); // create a Scanner using the File as the source
        while (s.hasNext()) {
            System.out.println(s.nextLine());
        }
    }

    private static void writeToFile(String filePath, String textToAdd) throws IOException {
        FileWriter fw = new FileWriter(filePath, true);
        fw.write(textToAdd);
        fw.close();
    }

    // appends the last index of the task array into the file
    private static void taskArrayToFile(String filePath, ArrayList<Task> tasks) throws IOException {
        FileWriter fw = new FileWriter(filePath, true);
        int index = tasks.size() - 1;
        Task lastTask = tasks.get(tasks.size() - 1);
        fw.write((index + 1) + "." + lastTask);
        fw.write("\n");
        fw.close();
    }
    private static void fileToTaskArray(String filePath, ArrayList<Task> tasks) throws IOException {
        File f = new File(filePath); // create a File for the given file path
        Scanner list = new Scanner(f); // create a Scanner using the File as the source
        while (list.hasNext()) {
            String line = list.nextLine();
            char type = line.charAt(3);
            switch (type) {
                case 'T':
                    addTodoFromFile(line, tasks);
                    break;
                case 'D':
                    addDeadlineFromFile(line, tasks);
                    break;
                case 'E':
                    addEventFromFile(line, tasks);
                    break;
                default:
                    break;
            }
        }
    }

    private static void addTodoFromFile(String line, ArrayList<Task> tasks) throws IOException {
        Task element = new Todo(line.substring(7));
        if (line.charAt(6) == 'X') {
            element.setDone();
        }
        tasks.add(element);
    }

    private static void addDeadlineFromFile(String line, ArrayList<Task> tasks) throws IOException {
        int endOfDescriptionIndex = line.indexOf("(by");
        String description = line.substring(8, endOfDescriptionIndex);
        int startOfDueIndex = line.indexOf(':');
        int endOfDueIndex = line.indexOf(')');
        String due = line.substring(startOfDueIndex + 1, endOfDueIndex);
        Task element = new Deadline(description, due);
        tasks.add(element);
    }

    private static void addEventFromFile(String line, ArrayList<Task> tasks) throws IOException {
        int endOfDescriptionIndex = line.indexOf("(from:");
        String description = line.substring(8, endOfDescriptionIndex);
        int startOfFromIndex = endOfDescriptionIndex + 7;
        int endOfFromIndex = line.indexOf("to:");
        String from = line.substring(startOfFromIndex, endOfFromIndex);
        String to = line.substring(endOfFromIndex + 4, line.indexOf(')'));
        Task element = new Event(from, to, description);
        tasks.add(element);
    }

    public static void main(String[] args) throws IOException {
        System.out.println("Hey! I'm Dukey, your virtual assistant!\nWhat can I do for you?\n");
        Scanner in = new Scanner(System.in);
        String line;
        ArrayList<Task> tasks = new ArrayList<>();
        String file2 = "./docs/dukey.txt";
        File file = new File(file2);
        // Check if the file or directory exists, and create it if it doesn't
        if (file.createNewFile()) {
            System.out.println("File created");
        } else {
            System.out.println("File already exists");
        }
        try {
            fileToTaskArray(file2, tasks);
        } catch (IOException e) {
            System.out.println("Something went wrong: " + e.getMessage());
        }
        while (true) {
            line = in.nextLine();
            String[] words = line.split(" ");
            String firstWord = words[0];
            switch (firstWord) {
                case "bye":
                    System.out.println("Bye. Hope to see you again soon!\n");
                    return;
                case "list":
                    printTaskList(tasks);
                    break;
                case "mark":
                    markTask(line, tasks);
                    break;
                case "unmark":
                    unmarkTask(line, tasks);
                    break;
                case "deadline":
                    addDeadline(line, tasks);
                    taskArrayToFile(file2, tasks);
                    break;
                case "event":
                    addEvent(line, tasks);
                    taskArrayToFile(file2, tasks);
                    break;
                case "todo":
                    addTodo(line, tasks);
                    taskArrayToFile(file2, tasks);
                    break;
                default:
                    if (line.trim().isEmpty()) {
                        System.out.println("________________________________");
                        System.out.println(DukeyException.EmptyInputError());
                        System.out.println("________________________________");
                    } else {
                        System.out.println("____________________________________________________________");
                        System.out.println(DukeyException.InvalidInputError());
                        System.out.println("____________________________________________________________");
                    }

            }
        }
    }
}
