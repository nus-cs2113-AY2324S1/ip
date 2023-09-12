import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        printWelcomeMessage();

        Scanner scanner = new Scanner(System.in);
        String userInput;
        List<Task> tasks = new ArrayList<>();

        while (true) {
            System.out.print("You: ");
            userInput = scanner.nextLine().trim();
            printLines();

            if (userInput.equalsIgnoreCase("bye")) {
                System.out.println("Bye! Have a nice day.");
                printLines();
                scanner.close();
                return;
            }

            String[] inputParts = userInput.split(" ", 2);
            String command = inputParts[0].toLowerCase();
            String argument = inputParts.length > 1 ? inputParts[1] : "";

            switch (command) {
                case "list":
                    if (tasks.isEmpty()) {
                        System.out.println("En: There are no tasks in your list.");
                    } else {
                        System.out.println("En: Here are the tasks in your list:");
                        for (int i = 0; i < tasks.size(); i++) {
                            System.out.println(" " + (i + 1) + "." + tasks.get(i));
                        }
                    }
                    printLines();
                    break;

                case "mark":
                    try {
                        int taskNumber = Integer.parseInt(argument);
                        if (taskNumber >= 1 && taskNumber <= tasks.size()) {
                            Task task = tasks.get(taskNumber - 1);
                            task.markDone();
                            System.out.println("En: I've marked this task as done:");
                            System.out.println("  " + task);
                        } else {
                            System.out.println("En: Invalid task number. Please enter a valid task number.");
                        }
                    } catch (NumberFormatException e) {
                        System.out.println("En: Invalid task number format. Please enter a valid task number.");
                    }
                    printLines();
                    break;

                default:
                    Task task = createTask(userInput);
                    if (task != null) {
                        tasks.add(task);
                        System.out.println("En: Yessir. I've added this task:");
                        System.out.println("  " + task);
                        System.out.println("En: Now you have " + tasks.size() + " tasks in the list.");
                        printLines();
                    } else {
                        System.out.println("En: I don't understand that command.");
                        printLines();
                    }
            }
        }
    }

    private static Task createTask(String userInput) {
        if (userInput.startsWith("todo ")) {
            return new ToDo(userInput.substring(5),false);
        } else if (userInput.startsWith("deadline ")) {
            String[] parts = userInput.substring(9).split("/by");
            if (parts.length == 2) {
                return new Deadline(parts[0].trim(), parts[1].trim(),false);
            }
        } else if (userInput.startsWith("event ")) {
            String[] parts = userInput.substring(6).split("/from");
            if (parts.length == 2) {
                String[] eventDetails = parts[1].split("/to");
                if (eventDetails.length == 2) {
                    return new Event(parts[0].trim(), eventDetails[0].trim(), eventDetails[1].trim(),false);
                }
            }
        }
        return null;
    }

    private static void printWelcomeMessage() {
        printLines();
        System.out.println("Hello! I'm En\nWhat can I do for you?");
        printLines();
    }

    public static void printLines() {
        for (int i = 0; i < 30; i++) {
            System.out.print('=');
        }
        System.out.println();
    }
}

