package Command;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class UserInputHandler {

    @FunctionalInterface
    interface Command {
        void execute(String userInput);
    }

    private static final String LINE_BREAK = "____________________________________________________________";
    private static final Scanner sc = new Scanner(System.in);
    private static final TaskManager taskManager = new TaskManager();

    private static final Map<String, Command> commands = new HashMap<>();
    static {
        commands.put("list", userInput -> taskManager.listTasks());

        commands.put("mark", userInput -> {
            int index = extractIndex(userInput, 5);
            if (index != -1) {
                taskManager.markTaskAsDone(index);
            }
        });

        commands.put("unmark", userInput -> {
            int index = extractIndex(userInput, 7);
            if (index != -1) {
                taskManager.unmarkTaskAsDone(index);
            }
        });

        commands.put("todo", userInput -> taskManager.addTodo(userInput.substring(5).trim()));

        commands.put("deadline", userInput -> {
            int lastIndex = userInput.lastIndexOf("/by");
            if (lastIndex != -1) {
                String description = userInput.substring(9, lastIndex).trim();
                String time = userInput.substring(lastIndex + 4).trim();
                taskManager.addDeadline(description, time);
            }
            else {
                taskManager.displayInvalidFormatMessage("deadline");
            }
        });

        commands.put("event", userInput -> {
            String[] parts = userInput.substring(6).trim().split("/from|/to", 3);
            if (parts.length >= 3) {
                String description = parts[0].trim();
                String startTime = parts[1].trim();
                String endTime = parts[2].trim();
                taskManager.addEvent(description, startTime, endTime);
            }
            else {
                taskManager.displayInvalidFormatMessage("event");
            }
        });
    }

    public static void processUserCommands() {
        String userInput = sc.nextLine();
        while (!userInput.equals("bye")) {
            System.out.println(LINE_BREAK);

            Command command = commands.getOrDefault(getCommandWord(userInput), null);
            if (command != null) {
                command.execute(userInput);
            }
            else {
                System.out.println("Unknown command. Please try again.");
            }

            System.out.println(LINE_BREAK);
            userInput = sc.nextLine();
        }
    }

    private static String getCommandWord(String userInput) {
        return userInput.split(" ")[0];
    }

    private static int extractIndex(String userInput, int startIndex) {
        try {
            if (startIndex < userInput.length()) {
                return Integer.parseInt(userInput.substring(startIndex).trim()) - 1; // Convert to 0-indexed
            }
            else {
                throw new NumberFormatException();
            }
        } catch (NumberFormatException e) {
            System.out.println("Invalid task number. Please try again.");
            return -1; // Invalid number
        }
    }
}
