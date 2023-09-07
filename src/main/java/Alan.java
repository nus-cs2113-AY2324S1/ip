import java.util.Scanner;

public class Alan {

    public static void printGreetingMessage() {
        printHorizontalLine();
        String man = " @/\n" +
                     "/| \n" +
                     "/ \\";

        System.out.println(man);
        System.out.println("Hello! I'm Alan");
        System.out.println("What can I do for you?");
        printHorizontalLine();
    }

    public static void printExitMessage() {
        System.out.println("Bye. Hope to see you again soon!");
    }

    public static void printHorizontalLine() {
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
    }

    public static void printTaskMessage(String taskTypeIcon, String taskIcon, String taskDescription) {
        System.out.println("\t[" + taskTypeIcon + "][" + taskIcon + "] " + taskDescription);
    }

    public static String getStringBetweenTwoCharacters (String input, char from, char to) {
        return input.substring(input.indexOf(from) + 1, input.indexOf(to));
    }

    public static void main(String[] args) {
        Task[] tasks = new Task[101];
        int currentTasksIndex = 1;

        printGreetingMessage();

        String userInput = " ";
        Scanner in = new Scanner(System.in);

        do {
            //Read user input
            System.out.print("Input: ");
            userInput = in.nextLine();

            printHorizontalLine();

            if (userInput.equals("bye")) {
                printExitMessage();
            } else if (userInput.equals("list")) {
                //print the tasks in the lists
                System.out.println("Here are the tasks in your list:");
                for (int i = 1; i < currentTasksIndex; i++) {
                    System.out.print((i) + ". ");
                    System.out.println(tasks[i]);
                }
            } else if (userInput.startsWith("mark")) {
                //mark tasks as done
                String[] words = userInput.split(" ");

                tasks[Integer.parseInt(words[1])].setDone(true);

                System.out.println("Alright! I've marked this task as done:");
                System.out.println(tasks[Integer.parseInt(words[1])]);
            } else if (userInput.startsWith("unmark")) {
                //unmark tasks as undone
                String[] words = userInput.split(" ");

                tasks[Integer.parseInt(words[1])].setDone(false);

                System.out.println("Ok, I've marked this task as not done yet:");
                System.out.println(tasks[Integer.parseInt(words[1])]);
            } else if (userInput.startsWith("todo")) {
                //add todo task to the list
                tasks[currentTasksIndex] = new Todo(userInput.replace("todo ", ""));
                System.out.println("added: " + tasks[currentTasksIndex]);
                System.out.println("Now you have " + currentTasksIndex + " in the list.");
                currentTasksIndex++;
            } else if (userInput.startsWith("deadline")) {
                //add deadline task to the list
                String filteredUserInput = userInput.replace("deadline ", "");
                String[] words = filteredUserInput.split(" /by ");
                tasks[currentTasksIndex] = new Deadline(words[0], words[1]);
                System.out.println("added: " + tasks[currentTasksIndex]);
                System.out.println("Now you have " + currentTasksIndex + " in the list.");
                currentTasksIndex++;
            } else if (userInput.startsWith("event")) {
                //add event to task
                String filteredUserInput = userInput.replace("event ", "");
                String[] words = filteredUserInput.split(" /");
                String description = words[0];
                String from = words[1].substring(5);
                String to = words[2].substring(3);

                System.out.println(from + "\n" + to);

                tasks[currentTasksIndex] = new Event(description, from, to);
                System.out.println("added: " + tasks[currentTasksIndex]);
                System.out.println("Now you have " + currentTasksIndex + " in the list.");
                currentTasksIndex++;
            }

            printHorizontalLine();

        } while(!userInput.equals("bye"));

    }
}
