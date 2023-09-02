import java.util.Scanner;  // Import the Scanner class
public class Duke {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);

        //Create a greeting message for the user
        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you?");

        //Get user input
        Scanner userScan = new Scanner(System.in);  // Create scanner object
        String userInput = userScan.nextLine();  // Get user input

        //Echo the arguments provided unless it is "bye" which quits the program
        while(!"bye".equalsIgnoreCase(userInput)){
            userInput = userScan.nextLine();  // Get user input again
            System.out.println(userInput); // Print user input
        }
        System.out.println("Bye. Hope to see you again soon!");
        
        //close the scanner
        userScan.close();

    }
}