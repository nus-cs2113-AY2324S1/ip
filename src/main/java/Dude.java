import java.util.ArrayList;
import java.util.Scanner;

public class Dude {

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
        System.out.println("Hello! I'm your best Dude :)");
        System.out.println("What can I do for you?");
        drawLine();
    }

    // Method to handle the storage of tasks
    public static void storeDude() {
        Scanner scan = new Scanner(System.in);
        ArrayList<Task> tasks = new ArrayList<>();

        String input = scan.nextLine();
        while (!input.isEmpty()) {
            drawLine();

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
            } else {
                addGeneralTask(tasks, input);
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

    private static void addTodoTask(ArrayList<Task> tasks, String input) {
        String taskDescription = input.substring(5);
        tasks.add(new Task(taskDescription));
        printAddedTask(tasks);
    }

    private static void addDeadlineTask(ArrayList<Task> tasks, String input) {
        String taskDescription = input.substring(9, input.indexOf("/by")).trim();
        String by = input.substring(input.indexOf("/by") + 4).trim();
        tasks.add(new Deadline(taskDescription, by));
        printAddedTask(tasks);
    }

    private static void addEventTask(ArrayList<Task> tasks, String input) {
        String taskDescription = input.substring(6, input.indexOf("/from")).trim();
        String from = input.substring(input.indexOf("/from") + 6, input.indexOf("/to")).trim();
        String to = input.substring(input.indexOf("/to") + 4).trim();
        tasks.add(new Event(taskDescription, from, to));
        printAddedTask(tasks);
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
                System.out.println("Task index out of range.");
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
    }

    private static void addGeneralTask(ArrayList<Task> tasks, String input) {
        System.out.println("added: " + input);
        tasks.add(new Task(input));
    }

    private static void printAddedTask(ArrayList<Task> tasks) {
        System.out.println("Got it. I've added this task:");
        System.out.println("  " + tasks.get(tasks.size() - 1));
        System.out.println("Now you have " + tasks.size() + (tasks.size() == 1 ? " task" : " tasks") + " in the list.");
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

