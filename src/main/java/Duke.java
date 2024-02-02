import java.util.Scanner;
public class Duke {
    public static void main(String[] args) {
//        String logo = " ____        _        \n"
//                + "|  _ \\ _   _| | _____ \n"
//                + "| | | | | | | |/ / _ \\\n"
//                + "| |_| | |_| |   <  __/\n"
//                + "|____/ \\__,_|_|\\_\\___|\n";
//        System.out.println("Hello from\n" + logo);

        String[] listOfItems = new String[100];

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
                for (int i = 0; i < sizeOfAddedItems; i++) {
                    System.out.println((i + 1) + ". " + listOfItems[i]);
                }
                System.out.println("____________________________________________________________");
            }
            else {
                listOfItems[sizeOfAddedItems] = input;

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
