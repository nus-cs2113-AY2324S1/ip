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

        // Task array for storing all user inputted tasks, and integer indexer to monitor size of array,
        // assume number of tasks do not exceed 100
        Task[] tasks = new Task[100];
        int tasksIndex = 0;

        // if 'bye' command is given exit program, else keep prompting for user input
        while(!userInput.equalsIgnoreCase("bye")) {

            // if 'list' command is given, list out all tasks
            if (userInput.equalsIgnoreCase("list")) {
                // if list is empty, print 'no item' message instead of tasks
                if(tasksIndex == 0) {
                    System.out.println("No item stored in your list! :o");
                }
                else {
                    // custom message
                    System.out.println("Here are the item(s) in your list. :)");
                    // print out tasks and number each task
                    for(int i = 0;i < tasksIndex;i++) {
                        System.out.println(i+1 + ". " + tasks[i]);
                    }
                }
            }
            // if 'mark' or 'unmark' command is given strictly with an integer, if integer is valid
            // mark or unmark the corresponding task in tasks
            else if(userInput.toLowerCase().matches("(mark|unmark) \\d+")) {
                // split userInput into command and integer
                String[] tokenizedUserInput = userInput.split(" ");
                int selectedItem = Integer.parseInt(tokenizedUserInput[1]);
                // check if integer given is in range of number of tasks
                if(selectedItem > 0 & selectedItem <= tasksIndex) {
                    // 'mark' case
                    if(tokenizedUserInput[0].equalsIgnoreCase("mark")) {
                        tasks[selectedItem-1].setMarked(true); // mark task
                        System.out.println("Task " + selectedItem + " marked!\n" +
                                tasks[selectedItem-1]);
                    }
                    // 'unmark' case
                    else {
                        tasks[selectedItem-1].setMarked(false); // unmark task
                        System.out.println("Task " + selectedItem + " unmarked!\n" +
                                tasks[selectedItem-1]);
                    }
                }
                // not in range of number of tasks
                else {
                    System.out.println("Invalid integer input! :(");
                }
            }
            // if not unique command, taken as adding a new Task
            else {
                userInput.split(" ");
                tasks[tasksIndex] = new Task(userInput); // Store user input into array
                tasksIndex++; // Increase String array index
            }

            partition();

            // prompt user for input and store it
            System.out.print("What do you want to do next? :o\n" + ">>");
            userInput = scanner.nextLine();
        }

        // program exit statement
        System.out.println(" Bye. Hope to see you again soon! :D");

        partition();
        System.out.println("                          -END-                             ");
    }

    // private function to print a stream of underscores for partitioning robot conversation
    private static void partition() {
        System.out.println("____________________________________________________________");
    }
}