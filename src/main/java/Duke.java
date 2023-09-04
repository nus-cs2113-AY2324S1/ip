import java.util.Scanner;

public class Duke {
    public static void printList(String[] storageArray, int num){
        for(int i = 0; i < num; i ++){
            System.out.println(i + 1 + "." + storageArray[i]);
        }
    }
    public static void main(String[] args) {

        String logoRyan = " ____  _          \n"
                + "|  _ \\| | ___   __\n"
                + "| |_) | |/ _ \\ / _|\n"
                + "|  __/| | (_) | (__\n"
                + "|_|   |_|\\___/ \\___|\n";

        System.out.println("Hello from\n" + logoRyan);

        String[] storageArray = new String[100];
        int numOfItems = 0;

        String line = "Hello! I'm Ryan Loh \nWhat can I do for you?\n";

        Scanner scanner = new Scanner(System.in);

        String userInput = scanner.nextLine();
        while (!userInput.equals("Bye")) {
            if (userInput.equals("list")) {
                System.out.println("Show the list");
                printList(storageArray,numOfItems);
            } else {
                storageArray[numOfItems] = userInput;
                System.out.println("added: " + storageArray[0]);
                numOfItems += 1;
            }

            // if the the string is list, we print out the entire list
            // if the string is bye, we exit the program
            // else we just add it into the text
            userInput = scanner.nextLine();

        }
        String lineBreak = "------------------------------ \n";

        System.out.println(line + lineBreak + "Bye. Hope to see you again soon!\n");

        // Close the scanner when you're done with it
        scanner.close();

    }
}
