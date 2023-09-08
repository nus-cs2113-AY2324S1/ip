import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;
import java.text.ParseException;

public class Duke {

    private static final String HORIZONTAL_LINE = "--------------------------------------------";
    private static final String LOGO = "                                                  \n" + "                                                     \n" + "         $$\\  $$$$$$\\   $$$$$$\\   $$$$$$\\  $$\\   $$\\ \n" + "         \\__|$$  __$$\\ $$  __$$\\ $$  __$$\\ $$ |  $$ |\n" + "         $$\\ $$$$$$$$ |$$ |  \\__|$$ |  \\__|$$ |  $$ |\n" + "         $$ |$$   ____|$$ |      $$ |      $$ |  $$ |\n" + "         $$ |\\$$$$$$$\\ $$ |      $$ |      \\$$$$$$$ |\n" + "         $$ | \\_______|\\__|      \\__|       \\____$$ |\n" + "   $$\\   $$ |                              $$\\   $$ |\n" + "   \\$$$$$$  |                              \\$$$$$$  |\n" + "    \\______/                                \\______/ \n" + "   \n";
    private static final List<Task> tasks = new ArrayList<Task>();

    public static void printList() {
        System.out.println("\tHere are the tasks in your list:");
        for (int i = 0; i < tasks.size(); i++) {
            System.out.printf("\t\t%d. %s\n", i + 1, tasks.get(i));
        }
    }

    public static void showWelcomeMessage() {
        System.out.println(HORIZONTAL_LINE);
        System.out.println(LOGO);
        System.out.println("\tHi I'm Jerry !");
        System.out.println("\tWhat can I do for you ?\n");
    }

    private static void showGoodbyeMessage() {
        System.out.println(HORIZONTAL_LINE);
        System.out.println("\tBye hope to see you again\n");
        System.out.println(HORIZONTAL_LINE);
    }

    public static void confirmTaskAdded(Task newTask) {
        System.out.printf("\tGot it. I've added this task:\n\t\t %s\n\tNow you have %d tasks in the list.\n", newTask, tasks.size());
    }

    public static void toggleTaskStatus(String argument, Boolean isDone) {
        try {
            int index = Integer.parseInt(argument);
            if (isDone) {
                tasks.get(index - 1).markAsUndone();
                System.out.println("\tNice! I've marked this task as not done yet:");
            } else {
                tasks.get(index - 1).markAsDone();
                System.out.println("\tNice! I've marked this task as done:");
            }
            System.out.printf("\t\t %s\n", tasks.get(index - 1).toString());
        } catch (NumberFormatException e) {
            System.out.println("\tPlease enter a valid task number.");
        } catch (IndexOutOfBoundsException e) {
            if (tasks.isEmpty()) {
                System.out.println("\tYou haven't added any tasks yet.");
            } else if (tasks.size() == 1) {
                System.out.println("\tYou have added only one task yet.");
            } else {
                System.out.printf("\tThe task number must be between 1 and %d.\n", tasks.size());
            }
        }
    }

    private static void addTodo(String argument) {
        try {
            Todo newTodo = Todo.fromString(argument);
            tasks.add(newTodo);
            confirmTaskAdded(newTodo);
        } catch (ParseException e) {
            System.out.println(e.getMessage());
        }
    }

    private static void addDeadline(String argument) {
        try {
            Deadline newDeadline = Deadline.fromString(argument);
            tasks.add(newDeadline);
            confirmTaskAdded(newDeadline);
        } catch (ParseException e) {
            System.out.println(e.getMessage());
        }
    }

    private static void addEvent(String argument) {
        try {
            Event newEvent = Event.fromString(argument);
            tasks.add(newEvent);
            confirmTaskAdded(newEvent);
        } catch (ParseException e) {
            System.out.println(e.getMessage());
        }
    }

    private static String getUserInput() {
        Scanner in = new Scanner(System.in);
        String userInput = in.nextLine().trim();
        return userInput;
    }

    private static String[] splitCommandWordAndArgs(String rawUserInput) {
        final String[] split = rawUserInput.trim().split("\\s+", 2);
        return split.length == 2 ? split : new String[] { split[0] , "" }; // else case: no parameters
    }

    private static void executeCommand(String userInputString) {
            System.out.println(HORIZONTAL_LINE);
            // String[] parts = input.split(" ", 2);
            // String command = parts[0].toLowerCase();
            // String argument = null;
            // if (parts.length > 1) {
            //     argument = parts[1];
            // }
            final String[] commandTypeAndParams = splitCommandWordAndArgs(userInputString);
            final String commandType = commandTypeAndParams[0];
            final String commandArgs = commandTypeAndParams[1];
            switch (commandType) {
            case "list":
                printList();
                break;
            case "mark":
                toggleTaskStatus(commandArgs, false);
                break;
            case "unmark":
                toggleTaskStatus(commandArgs, true);
                break;
            case "todo":
                addTodo(commandArgs);
                break;
            case "deadline":
                addDeadline(commandArgs);
                break;
            case "event":
                addEvent(commandArgs);
                break;
            case "bye":
                showGoodbyeMessage();
                System.exit(0);
            default:
                System.out.println("\tUnknown command.");
                break;
            }
        System.out.println(HORIZONTAL_LINE);
    }

    public static void main(String[] args) {
        showWelcomeMessage();
        while (true) {
            String userCommand = getUserInput();
            executeCommand(userCommand);
        }
    }
}
