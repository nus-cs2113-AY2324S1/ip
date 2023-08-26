import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Scanner;
import java.util.function.Function;
import java.util.stream.Collectors;

public class Duke {
    private static boolean ended = false;
    private final static Scanner scanner = new Scanner(System.in);
    private final static HashMap<String, Function<String, Void>> commands = new HashMap<>();

    public static void main(String[] args) {
        commands.put("bye", (e) -> bye());
        commands.put("list", (e) -> listTasksFlow());
        commands.put("mark", Duke::markTaskFlow);
        commands.put("unmark", Duke::unmarkTaskFlow);

        greet();
        while (!ended) {
            System.out.print("User: ");
            String userInput = getInput();
            String[] inputTokens = userInput.split(" ");

            Function<String, Void> command = commands.get(inputTokens.length > 0 ? inputTokens[0] : userInput);
            if (command == null) {
                addTaskFlow(userInput);
                continue;
            }

            userInput = Arrays.stream(inputTokens).skip(1).collect(Collectors.joining(" "));
            command.apply(userInput);
        }
    }

    public static String getInput() {
        return scanner.nextLine();
    }

    public static void addTaskFlow(String input) {
        ApplicationState.getAppState().addTask(input);
        printWrapped("added: " + input);
    }

    public static Void listTasksFlow() {
        ArrayList<Task> tasks = ApplicationState.getAppState().getTasks();
        int idx = 1;
        ProgramConstants.printSeparator();
        for (Task task : tasks) {
            System.out.printf("%d. %s\n", idx++, task.getNameWithStatus());
        }
        ProgramConstants.printSeparator();
        return null;
    }

    public static Void markTaskFlow(String taskNumberStr) {
        int taskNumber = parsePositiveNumber(taskNumberStr);
        if (taskNumber == -1) {
            printWrapped("Failed to mark task as done...");
            return null;
        }

        Task task = ApplicationState.getAppState().getTask(--taskNumber);
        if (task == null) {
            printWrapped("Failed to mark task as done...");
            return null;
        }

        task.markDone();
        String printOut = String.format("Nice I've marked this task as done:\n%s", task.getNameWithStatus());
        printWrapped(printOut);
        return null;
    }

    public static Void unmarkTaskFlow(String taskNumberStr) {
        int taskNumber = parsePositiveNumber(taskNumberStr);
        if (taskNumber == -1) {
            printWrapped("Failed to mark task as undone...");
            return null;
        }

        Task task = ApplicationState.getAppState().getTask(--taskNumber);
        if (task == null) {
            printWrapped("Failed to mark task as undone...");
            return null;
        }

        task.unmarkDone();
        String printOut = String.format("OK, I've marked this task as not done yet:\n%s", task.getNameWithStatus());
        printWrapped(printOut);
        return null;
    }

    public static int parsePositiveNumber(String number) {
        int taskNumber = -1;

        try {
            taskNumber = Integer.parseInt(number);
        } catch (NumberFormatException e) {
            return taskNumber;
        }

        if (taskNumber <= 0) return -1;
        return taskNumber;
    }

    public static void printWrapped(String printOut) {
        ProgramConstants.printSeparator();
        System.out.println(printOut);
        ProgramConstants.printSeparator();
    }

    public static void greet() {
        String greeting = String.format("Hello I'm %s\n", ProgramConstants.BOT_NAME) + "What can I do for you?";
        printWrapped(greeting);
    }

    public static Void bye() {
        printWrapped("Bye. Hope to see you again soon!");
        ended = true;
        return null;
    }
}
