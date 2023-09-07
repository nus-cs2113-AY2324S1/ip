
import java.util.Scanner;

public class Ken {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        //Initialise array to store tasks
        String[] tasks = new String[100];
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
                    System.out.println(" " + (i + 1) + "." + tasks[i]);
                }
            } else {
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