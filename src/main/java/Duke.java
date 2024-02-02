import java.util.Scanner;
import java.util.Arrays;
public class Duke {
    public static void main(String[] args) {
//        String logo = " ____        _        \n"
//                + "|  _ \\ _   _| | _____ \n"
//                + "| | | | | | | |/ / _ \\\n"
//                + "| |_| | |_| |   <  __/\n"
//                + "|____/ \\__,_|_|\\_\\___|\n";
//        System.out.println("Hello from\n" + logo);

        Task[] listOfItems = new Task[100];

        System.out.println("____________________________________________________________");
        System.out.println("Hello! I'm Brennan!");
        System.out.println("What can I do for you?\n");

        //String that stores the input entered by the user
        String input;

        //variable stores the number of tasks being added
        int sizeOfAddedItems = 0;

        Scanner in = new Scanner(System.in);
        while (true) {
            input = in.nextLine();

            if (input.equals("bye")) {
                break;
            }

            else if (input.equals("list")) {
                System.out.println("____________________________________________________________");
                System.out.println("Here are the tasks in your list: ");
                for (int i = 0; i < sizeOfAddedItems; i++) {

                    System.out.println((i + 1) + ". " + "[" + listOfItems[i].getStatusIcon() + "]" + listOfItems[i].description);
                }
                System.out.println("____________________________________________________________");
            }

            else if (Arrays.asList(input.split( " ")).contains("mark")) {

                //Finding the index of the task that the user wants to mark
                String[] splitInput = input.split(" ");
                int indexTask = Integer.parseInt(splitInput[1]);

                listOfItems[indexTask - 1].markAsCompleted();

                System.out.println("____________________________________________________________");
                System.out.println("Nice! I've marked this task as done:");
                System.out.println((indexTask) + ". " + "[" + listOfItems[indexTask-1].getStatusIcon() + "]" + listOfItems[indexTask - 1].description);
                System.out.println("____________________________________________________________");

            }

            else if (Arrays.asList(input.split( " ")).contains("unmark")) {

                //Finding the index of the task that the user wants to mark
                String[] splitInput = input.split(" ");
                int indexTask = Integer.parseInt(splitInput[1]);

                listOfItems[indexTask - 1].markAsNotCompleted();

                System.out.println("____________________________________________________________");
                System.out.println(" OK, I've marked this task as not done yet:");
                System.out.println((indexTask) + ". " + "[" + listOfItems[indexTask-1].getStatusIcon() + "]" + listOfItems[indexTask - 1].description);
                System.out.println("____________________________________________________________");

            }

            else {
                listOfItems[sizeOfAddedItems] = new Task(input);

                System.out.println("____________________________________________________________");
                System.out.println(" added: " + input);
                System.out.println("____________________________________________________________");

                sizeOfAddedItems++;
            }

        }

        System.out.println("____________________________________________________________");
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println("____________________________________________________________");

    }
}
