import java.util.Scanner;
public class Duke {
    private static Task[] tasks = new Task[100];
    private static int taskCount = 0;

    public static void addToTasks(String description, Type type) {
        switch (type) {
        case TODO:
            tasks[taskCount] = new ToDos(description);
            break;
        case DEADLINE:
            String[] deadlineTokens = description.split("/by");
            description = deadlineTokens[0].trim();
            String by = deadlineTokens[1].trim();
            tasks[taskCount] = new Deadlines(description, by);
            break;
        case EVENT:
            String[] eventTokens = description.split("/from");
            description = eventTokens[0].trim();
            eventTokens = eventTokens[1].split("/to");
            String from = eventTokens[0].trim();
            String to = eventTokens[1].trim();
            tasks[taskCount] = new Events(description, from, to);
            break;
        }
        taskCount++;
        System.out.println("\tGot it. I've added this task:");
        System.out.println("\t" + tasks[taskCount-1]);
        System.out.println("\tNow you have " + taskCount + " task(s) in the list.");
    }
    public static void printTasks() {
        if (taskCount == 0) {
            System.out.println("\tYou do not have any task in the list.");
            return;
        }
        System.out.println("\tHere are the tasks in your list:");
        for (int i=0; i<taskCount; i++) {
            System.out.print("\t");
            System.out.print(i+1 + ".");
            System.out.println(tasks[i]);
        }
    }
    public static void markAsDone(int number) {
        tasks[number-1].setDone(true);
        System.out.println("\tNice! I've marked this task as done:");
        System.out.println("\t" + tasks[number-1]);
    }
    public static void markAsUndone(int number) {
        tasks[number-1].setDone(false);
        System.out.println("\tNice! I've marked this task as not done yet:");
        System.out.println("\t" + tasks[number-1]);
    }

    public static void main(String[] args) {
        // Greetings
        String intro = "~~~~~~~~~~~~~~~~~~~\n"
                        + "Hello! I'm Wenny!\n"
                        + "How may I help you?\n"
                        + "~~~~~~~~~~~~~~~~~~~";
        System.out.println(intro);
        while (true) {
            Scanner scanner = new Scanner(System.in);
            String userInput = scanner.nextLine();
            String[] substr = userInput.split("\\s+");
            switch (substr[0]) {
            case "bye":
                printByeMessage();
                break;
            case "list":
                printTasks();
                break;
            case "mark":
                markAsDone(Integer.parseInt(substr[1]));
                break;
            case "unmark":
                markAsUndone(Integer.parseInt(substr[1]));
                break;
            case "todo":
                addToTasks(userInput.substring(5), Type.TODO);
                break;
            case "deadline":
                addToTasks(userInput.substring(9), Type.DEADLINE);
                break;
            case "event":
                addToTasks(userInput.substring(6), Type.EVENT);
                break;
            default:
                System.out.println("\tInvalid input, please try again");
            }
        }
    }
    public static void printByeMessage() {
        System.out.println("Bye. Hope to see you again soon!");
        System.exit(0);
    }

}
