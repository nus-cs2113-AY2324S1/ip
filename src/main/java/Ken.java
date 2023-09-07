
import java.util.Scanner;

public class Ken {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        //Initialise array to store tasks
        String[] tasks = new String[100];
        boolean[] taskStatus = new boolean[100]; // true for done, false for not done
        int taskCount = 0;

        // Print the initial message
        System.out.println(" ____________________________________________________________");
        System.out.println(" Hello! I'm Ken");
        System.out.println(" What dyu want?");
        System.out.println("____________________________________________________________");

        // Read and echo user commands until "bye" is entered
        while (true) {
            String userInput = scanner.nextLine();

            // Echo the user's command
            System.out.println(" ____________________________________________________________");


            // Check if the user wants to exit
            if (userInput.equalsIgnoreCase("bye")) {
                System.out.println("____________________________________________________________");
                System.out.println(" Bye then!");
                System.out.println("____________________________________________________________");
                break; // Exit the loop
            } else if (userInput.equalsIgnoreCase("list")) {
                System.out.println(" Tasks ");
                for (int i = 0; i < taskCount; i++) {
                    char statusSymbol = taskStatus[i] ? 'X' : ' ';
                    System.out.println(" " + (i + 1) + ".[" + statusSymbol + "] " + tasks[i]);                }
            } else if (userInput.startsWith("mark " )) {
                try {
                    int taskIndex = Integer.parseInt(userInput.substring(5).trim()) - 1;
                    if (taskIndex >= 0 && taskIndex < taskCount) {
                        taskStatus[taskIndex] = true;
                        System.out.println(" Nice! I've marked this task as done:");
                        System.out.println("   [X] " + tasks[taskIndex]);
                    } else {
                        System.out.println(" Task not found. Please provide a valid task number.");
                    }
                } catch (NumberFormatException e) {
                    System.out.println(" Invalid input. Please provide a valid task number.");
                }
            } else if (userInput.startsWith("unmark ")) {
                try {
                    int taskIndex = Integer.parseInt(userInput.substring(7).trim()) - 1;
                    if (taskIndex >= 0 && taskIndex < taskCount) {
                        taskStatus[taskIndex] = false;
                        System.out.println(" OK, I've marked this task as not done yet:");
                        System.out.println("   [ ] " + tasks[taskIndex]);
                    } else {
                        System.out.println(" Task not found. Please provide a valid task number.");
                    }
                } catch (NumberFormatException e) {
                    System.out.println(" Invalid input. Please provide a valid task number.");
                }
            }
            else {
                //Add task to the array
                tasks[taskCount] = userInput;
                taskCount++;
                System.out.println(" added: " + userInput);
            }
        }
        // Close the scanner when done
        scanner.close();
    }
}