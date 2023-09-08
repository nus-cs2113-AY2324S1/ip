import java.util.Scanner;
public class Duke {
    public static String BOT_NAME = "Amy"; // a constant bot name
    public static Task[] taskList = new Task[100]; // contains tasks
    public static int numberOfTasks = 0; // number of tasks
    public static void addToList(Task task) {
        taskList[numberOfTasks] = task;
        numberOfTasks++;
        System.out.println("Got it. I've added this task: ");
        System.out.println("  " + task);
        System.out.println("Now you have " + numberOfTasks + " tasks in the list");
    }
    public static void listTasks() {
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < numberOfTasks; i++) {
            System.out.println((i + 1) + ". " + taskList[i]); // list if the user enters list
        }
    }
    public static void markTaskAsDone(int taskIndex) {
        if (taskIndex >= 1 && taskIndex <= numberOfTasks) {
            Task task = taskList[taskIndex - 1];
            task.markAsDone();
            System.out.println("Nice! I've marked this task as done:");
            System.out.println("  " + task);
        } else {
            System.out.println("Invalid task index. Please try again.");
        }
    }
    public static void unmarkTask(int taskIndex) {
        if (taskIndex >= 1 && taskIndex <= numberOfTasks) {
            Task task = taskList[taskIndex - 1];
            task.markAsNotDone();
            System.out.println("OK, I've marked this task as not done yet:");
            System.out.println("  " + task);
        } else {
            System.out.println("Invalid task index. Please try again.");
        }
    }
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        //System.out.println("Hello from\n" + logo);

        System.out.println("____________________________________________________________");
        System.out.println("Hello! I'm " + BOT_NAME);
        System.out.println("What can I do for you?");
        System.out.println("____________________________________________________________");

        Scanner scanner = new Scanner(System.in);
        while (true) {
            String userInput = scanner.nextLine(); // Read user input
            System.out.println("____________________________________________________________");
            if (userInput.equals("bye")) {
                break; // Exit the loop if the user enters "bye"
            } else if (userInput.equals("list")) {
                listTasks(); // list tasks
            } else if (userInput.startsWith("mark")) {
                // Extract the task index from the user input
                int taskIndex = Integer.parseInt(userInput.substring(5).trim());
                markTaskAsDone(taskIndex);
            } else if (userInput.startsWith("unmark")) {
                // Extract the task index from the user input
                int taskIndex = Integer.parseInt(userInput.substring(7).trim());
                unmarkTask(taskIndex);
            } else if (userInput.startsWith("todo")) {
                String description = userInput.substring(5).trim();
                Todo todo = new Todo(description);
                addToList(todo);
            }else if (userInput.startsWith("deadline")) {
                int inputIndex = userInput.indexOf(" /by ");
                String description = userInput.substring("deadline ".length(), inputIndex);
                String by = userInput.substring(inputIndex + " /by ".length());
                addToList(new Deadline(description, by));
            } else if (userInput.startsWith("event")) {
                String[] parts = userInput.substring(5).split("/from");
                String description = parts[0].trim();
                String[] dateTimeParts = parts[1].split("/to");
                String from = dateTimeParts[0].trim();
                String to = dateTimeParts[1].trim();
                Event event = new Event(description, from, to);
                addToList(event);
            }  else {
                System.out.println("Invalid command. Please try again.");
            }
            System.out.println("____________________________________________________________");
        }

        scanner.close();

        System.out.println("Bye. Hope to see you again soon!");
        System.out.println("____________________________________________________________");
    }
}
