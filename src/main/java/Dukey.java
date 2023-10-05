import Tasks.*;
import dukey.*;

import java.util.ArrayList;
import java.util.Scanner;

public class Dukey {
    private static void addTodo(String line, ArrayList<Task> tasks) {
        String[] words = line.split(" ");
        String description = null;
        try {
            description = words[1];
            tasks.add(new Todo(description));
            tasks.get(tasks.size() - 1).printNewTask();
        } catch(IndexOutOfBoundsException e) {
            System.out.println("_____________________________________________________");
            System.out.println(DukeyException.todoDescriptionError());
            System.out.println("_____________________________________________________");
        }
    }

    private static void deleteTask(String line, ArrayList<Task> tasks) {
        try {
            String[] words = line.split(" ");
            int index  = Integer.parseInt(words[1]) - 1;
            Task delete = tasks.get(index);
            tasks.get(index).printDeleteTask();
            tasks.remove(index);
        }
        catch(IndexOutOfBoundsException e) {
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
        System.out.println("_____________________________________________________");
        System.out.println("Here are the tasks in your list:");
        int index = 1;
        for (Task task : tasks) {
            System.out.println((index++) + "." + task);
        }
        System.out.println("_____________________________________________________");
    }


    public static void main(String[] args) {
        System.out.println("Hey! I'm Dukey, your virtual assistant!\nWhat can I do for you?\n");
        Scanner in = new Scanner(System.in);
        String line;
        ArrayList<Task> tasks = new ArrayList<>();
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
                    break;
                case "event":
                    addEvent(line, tasks);
                    break;
                case "todo":
                    addTodo(line, tasks);
                    break;
                case "delete":
                    deleteTask(line, tasks);
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
