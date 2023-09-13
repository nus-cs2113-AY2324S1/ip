import java.util.Scanner;

import Objects.*;
import org.w3c.dom.events.Event;

public class Duke {

    /**
     * Prints a list of tasks with their status icons and descriptions.
     *
     * @param storageArray An array of Task objects representing the list of tasks.
     * @param num          The number of tasks to be printed.
     */

    public static void printList(Task[] storageArray, int num) {
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < num; i++) {
            System.out.println(
                    i + 1 + "." + "[" + storageArray[i].getType() + "]" + "[" + storageArray[i].getStatusIcon() + "]" + storageArray[i]);
        }
    }

    public static boolean isValidInputs(String methodName, int numberOfItems, String[] words) {
        switch (methodName) {
            case "mark", "unmark" -> {
                if (words.length == 1 || !words[1].matches("-?\\d+")) {
                    System.out.println(DukeException.invalidInputForType(words[0]));
                    return false;
                }
                System.out.println("mark " + words[1]);
                int index = Integer.parseInt(words[1]);
                // we need to check if it's out of bounds first
                if (index > numberOfItems || index <= 0) {
                    System.out.println(DukeException.outOfBoundsError());
                    return false;
                }
            }
            //im busy student... no time do, please give chance XD
            case "todo" -> {
                System.out.println("to do to be done");
            }
            case "deadline" -> {
                System.out.println("deadline to be done");
            }
            case "event" -> {
                System.out.println("event to be done");
            }
            default -> {
                System.out.println("invalid method name");
            }
        }
        return true;

    }

    public static void main(String[] args) {

        String logoRyan = " ____  _          \n"
                + "|  _ \\| | ___   __\n"
                + "| |_) | |/ _ \\ / _|\n"
                + "|  __/| | (_) | (__\n"
                + "|_|   |_|\\___/ \\___|\n";


        Task[] storageArray = new Task[100];
        int numOfItems = 0;

        String line = "Hello! I'm Ryan Loh \nWhat can I do for you?\nType anything to be added to your magic list :D\n";
        System.out.println(line + logoRyan);
        Scanner scanner = new Scanner(System.in);

        String userInput = scanner.nextLine();

        while (!userInput.equals("Bye")) {
            String[] words = userInput.split(" ");

            String initialWord = words[0];

            switch (initialWord) {
                case "list" -> printList(storageArray, numOfItems);
                case "mark" -> {
                    if (!isValidInputs(initialWord, numOfItems, words)) {
                        break;
                    }
                    int index = Integer.parseInt(words[1]);
                    Task currTask = storageArray[index - 1];
                    currTask.markStatus();
                    System.out.println("Nice! I've marked this task as done: ");
                    String message = String.format("[%s] %s", currTask.getStatusIcon(), currTask.getDescription());
                    System.out.println(message);


                }
                case "unmark" -> {
                    if (!isValidInputs(initialWord, numOfItems, words)) {
                        break;
                    }
                    // unmark an item
                    System.out.println("unmark " + words[1]);
                    int index = Integer.parseInt(words[1]);
                    // we need to check if it's out of bounds first

                    Task currTask = storageArray[index - 1];
                    currTask.unmarkStatus();
                    System.out.println("Nice! I've marked this task as done: ");
                    String message = String.format("[%s] %s", currTask.getStatusIcon(), currTask.getDescription());
                    System.out.println(message);


                }
                case "todo" -> {
                    String newWord = userInput.replace("todo ", "");
                    ToDos t = new ToDos(newWord);
                    storageArray[numOfItems] = t;
                    System.out.println("added: " + newWord);
                    numOfItems += 1;
                }
                case "deadline" -> {
                    String newWord = userInput.replace("deadline ", "");
                    String[] deadlineArray = newWord.split("/");
                    Deadlines t = new Deadlines(deadlineArray[0], deadlineArray[1]);
                    storageArray[numOfItems] = t;
                    System.out.println("added: " + deadlineArray[0]);
                    numOfItems += 1;

                }
                case "event" -> {
                    String newWord = userInput.replace("event ", "");
                    String[] firstSplit = newWord.split("/");
                    System.out.println(newWord);
                    Events t = new Events(firstSplit[0], firstSplit[1], firstSplit[2]);
                    storageArray[numOfItems] = t;

                    numOfItems += 1;
                    System.out.println("added: " + firstSplit[0] + ", you have now " + numOfItems + " tasks");


                }
                default -> {
                    Task t = new Task(userInput);
                    storageArray[numOfItems] = t;
                    System.out.println("added: " + userInput);
                    numOfItems += 1;
                }
            }

            userInput = scanner.nextLine();

        }
        String lineBreak = "------------------------------ \n";

        System.out.println(lineBreak + "Bye. Hope to see you again soon!\n");

        // Close the scanner when you're done with it
        scanner.close();

    }
}
