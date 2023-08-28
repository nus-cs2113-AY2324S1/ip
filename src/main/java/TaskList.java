import javax.swing.text.html.ListView;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;

public class TaskList {
    static List<Task> tasks = new ArrayList<>();

    public static void printHorizontalLines(int length) {
        for (int i = 0; i < length; i++) {
            System.out.print("-");
        }
        System.out.println();
    }

    public static void printList(int length){
        printHorizontalLines(length);
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < tasks.size(); i++) {
            Task task = tasks.get(i);
            String status = task.isDone() ? "[X]" : "[ ]";
            System.out.println((i + 1) + ". " + status + " " + task.getName());
        }
        printHorizontalLines(length);
    }

    public static boolean isValidIndex(int index) {
        return (index >= 1 && index <= tasks.size());
    }

    public static void markAsDone(int taskIndex, int length) {
        if (isValidIndex(taskIndex)) {
            Task task = tasks.get(taskIndex - 1);
            task.setDone(true);
            printHorizontalLines(length);
            System.out.println("Nice! I've marked this task as done:");
            System.out.println(" [X] " + task.getName());
            printHorizontalLines(length);
        } else {
            System.out.println("Invalid task index.");
        }
    }

    public static void unmark(int taskIndex, int  length) {
        if (isValidIndex(taskIndex)) {
            Task task = tasks.get(taskIndex - 1);
            task.setDone(false);
            printHorizontalLines(length);
            System.out.println("OK, I've marked this task as not done yet:");
            System.out.println(" [ ] " + task.getName());
            printHorizontalLines(length);
        } else {
            System.out.println("Invalid task index.");
        }
    }

    public static void main(String[] args) {
        int horizontalLineLength = 50;
        //String[] textList = new String[100];
        printHorizontalLines(horizontalLineLength);
        System.out.println("Hi! I'm Joshua");
        System.out.println("What can I do for you?");
        System.out.println("Enter any of the commands (list, mark, unmark, or bye):");
        printHorizontalLines(horizontalLineLength);

        Scanner scanner = new Scanner(System.in);

        while (true) {
            String input = scanner.nextLine();

            if (input.equals("list")) {
                printList(horizontalLineLength);
            } else if (input.startsWith("mark ")) {
                int taskIndex = Integer.parseInt(input.substring(5));
                markAsDone(taskIndex, horizontalLineLength);
            } else if (input.startsWith("unmark ")) {
                int taskIndex = Integer.parseInt(input.substring(7));
                unmark(taskIndex, horizontalLineLength);
            } else if (input.equals("bye")) {
                break;
            } else {
                tasks.add(new Task(input));
                System.out.println("Added: " + input);
            }
        }
    }
}
