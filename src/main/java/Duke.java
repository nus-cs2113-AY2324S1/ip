import javax.sound.midi.SysexMessage;
import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {

        partition();

        // name of the robot in ASCII art
        String name = " _____ _                       _     _       _         \n" +
                "/  __ \\ |                     | |   | |     | |        \n" +
                "| /  \\/ |__   ___  ___ _______| |__ | | ___ | | __ ____\n" +
                "| |   | '_ \\ / _ \\/ _ \\_  / _ \\ '_ \\| |/ _ \\| |/ /|_  /\n" +
                "| \\__/\\ | | |  __/  __// /  __/ |_) | | (_) |   <  / / \n" +
                " \\____/_| |_|\\___|\\___/___\\___|_.__/|_|\\___/|_|\\_\\/___|";

        // introduction and initial prompt for user input
        System.out.print("Hello! My name is:\n" + name + "\n\n" +
                "What can I do for you today? :)\n" + ">>");

        // Scanner object for getting user input from the terminal
        Scanner scanner = new Scanner(System.in);

        // string to monitor current user input
        String userInput = scanner.nextLine();

        // string array for storing all user inputs, and integer indexer to monitor size of array,
        // assume user inputs do not exceed 100
        String[] userInputs = new String[100];
        int userInputsIndex = 0;

        // exit if 'bye' command is given, else keep prompting for user input
        while(!userInput.equalsIgnoreCase("bye"))
        {

            // if list command is given, list out all previous user inputs,
            // else proceed to store it in the string array userInputs
            if (userInput.equalsIgnoreCase("list"))
            {
                // if list is empty, print 'no item' message instead of list items
                if(userInputsIndex == 0)
                {
                    System.out.println("No item stored in your list! :o");
                }
                else
                {
                    // custom message
                    System.out.println("Here are the item(s) in your list. :)");
                    // print out list items and number each item
                    for(int i = 0;i < userInputsIndex;i++)
                    {
                        System.out.println(i+1 + ". " + userInputs[i]);
                    }
                }
            }
            else
            {
                userInputs[userInputsIndex] = userInput; // Store user input into array
                userInputsIndex++; // Increase String array index\
            }
            partition();

            // prompt user for input and store it in userInput
            System.out.print("What do you want to do next? :o\n" + ">>");
            userInput = scanner.nextLine();
        }

        // program exit statement
        System.out.println(" Bye. Hope to see you again soon! :D");
        partition();
        System.out.println("                          -END-                             ");
    }

    // private function to print a stream of underscores for partitioning robot conversation
    private static void partition(){
        System.out.println("____________________________________________________________");
    }
}
