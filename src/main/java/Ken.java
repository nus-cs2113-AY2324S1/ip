
import java.util.Scanner;

public class Ken {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

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
            System.out.println(" " + userInput);

            // Check if the user wants to exit
            if (userInput.equalsIgnoreCase("bye")) {
                System.out.println("____________________________________________________________");
                System.out.println(" Bye then!");
                System.out.println("____________________________________________________________");
                break; // Exit the loop
            }
        }

        // Close the scanner when done
        scanner.close();
    }
}