import java.util.ArrayList;
import java.util.Scanner;

public class Duke {

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
                System.out.println("Here are the tasks in your list:");
                for (int i = 0; i < tasks.size(); i++) {
                    System.out.println((i + 1) + ". " + tasks.get(i));
                }
            } else if (input.startsWith("todo")) {
                String taskDescription = input.substring(5);
                tasks.add(new Task(taskDescription));
                System.out.println("Got it. I've added this task:");
                System.out.println("  " + tasks.get(tasks.size() - 1));
                System.out.println("Now you have " + tasks.size() + " tasks in the list.");
            } else if (input.startsWith("deadline")) {
                String taskDescription = input.substring(9, input.indexOf("/by")).trim();
                String by = input.substring(input.indexOf("/by") + 4).trim();
                tasks.add(new Deadline(taskDescription, by));
                System.out.println("Got it. I've added this task:");
                System.out.println("  " + tasks.get(tasks.size() - 1));
                System.out.println("Now you have " + tasks.size() + " tasks in the list.");
            } else if (input.startsWith("event")) {
                String taskDescription = input.substring(6, input.indexOf("/from")).trim();
                String from = input.substring(input.indexOf("/from") + 6, input.indexOf("/to")).trim();
                String to = input.substring(input.indexOf("/to") + 4).trim();
                tasks.add(new Event(taskDescription, from, to));
                System.out.println("Got it. I've added this task:");
                System.out.println("  " + tasks.get(tasks.size() - 1));
                System.out.println("Now you have " + tasks.size() + " tasks in the list.");
            } else if (input.startsWith("mark") || input.startsWith("unmark")) {
                String[] arrOfInput = input.split(" ", 2);
                if (arrOfInput.length < 2) {
                    System.out.println("Please specify the task index.");
                } else {
                    try {
                        int index = Integer.parseInt(arrOfInput[1]) - 1;
                        if (index < 0 || index >= tasks.size()) { // Here, replace curPos with tasks.size()
                            System.out.println("Task index out of range.");
                        } else {
                            tasks.get(index).isDone = input.startsWith("mark");
                            String message = input.startsWith("mark") ?
                                    "Nice! I've marked this task as done:" :
                                    "OK, I've marked this task as not done yet:";
                            System.out.println(message);

                            // This line is the modified part: Using toString to print the task.
                            System.out.println("   " + tasks.get(index).toString());
                        }
                    } catch (NumberFormatException e) {
                        System.out.println("Invalid task index format.");
                    }
                }
            } else {
                // Add new task
                System.out.println("added: " + input);
                tasks.add(new Task(input));
            }
            drawLine();
            input = scan.nextLine();
        }
        scan.close();
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
