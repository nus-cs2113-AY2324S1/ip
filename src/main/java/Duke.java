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
        // Logo string
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
        // Initialize Scanner and ArrayList for tasks
        Scanner scan = new Scanner(System.in);
        String input = scan.nextLine();
        ArrayList<Task> tasks = new ArrayList<>();
        int curPos = 0; // Variable to keep track of the current task position

        // Main loop to process commands
        while (!(input.isEmpty())) {
            drawLine();
            if (input.equals("bye")) {
                byeDude();
                break;
            } else if (input.equals("list")) {
                System.out.println("Here are the tasks in your list:");
                for (int i = 0; i < curPos; i++) {
                    System.out.println((i + 1) + ". " + tasks.get(i).getStatusIcon() + " " + tasks.get(i).description);
                }
            } else if (input.startsWith("mark") || input.startsWith("unmark")) {
                // Split the input to separate command and task index
                String[] arrOfInput = input.split(" ", 2);
                if (arrOfInput.length < 2) {
                    System.out.println("Please specify the task index.");
                } else {
                    try {
                        // Convert user input index to zero-based index for ArrayList
                        int index = Integer.parseInt(arrOfInput[1]) - 1;
                        if (index < 0 || index >= curPos) {
                            System.out.println("Task index out of range.");
                        } else {
                            // Mark or unmark the task
                            tasks.get(index).isDone = input.startsWith("mark");
                            String message = input.startsWith("mark") ? "Nice! I've marked this task as done:" : "OK, I've marked this task as not done yet:";
                            System.out.println(message);
                            System.out.println("   " + tasks.get(index).getStatusIcon() + " " + tasks.get(index).description);
                        }
                    } catch (NumberFormatException e) {
                        System.out.println("Invalid task index format.");
                    }
                }
            } else {
                // Add new task
                System.out.println("added: " + input);
                tasks.add(new Task(input));
                curPos++;
            }
            drawLine();
            input = scan.nextLine();
        }
        // Close the Scanner to prevent resource leak
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
