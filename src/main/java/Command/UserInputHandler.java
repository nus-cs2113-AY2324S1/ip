package Command;

import Tasks.Deadline;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class UserInputHandler {

    @FunctionalInterface
    interface Command {
        void execute(String userInput) throws JarvisException;
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

        commands.put("todo", userInput -> {
            try {
                // Parsing user input and calling TaskManager method
                taskManager.addTodo(TaskManager.parseToDoDescription(userInput));
            }
            catch (JarvisException e) {
                // Catching the exception thrown by TaskManager and informing the user
                System.out.println(e.getMessage());
            }
        });

//        commands.put("deadline", userInput -> {
//            int lastIndex = userInput.lastIndexOf("/by");
//            String description = "", time = "";
//            if (lastIndex == -1) {
//                System.out.println(JarvisException.invalidDeadlineFormat().getMessage());
//            }
//            else {
//                description = userInput.substring(9, lastIndex).trim();
//                time = userInput.substring(lastIndex + 4).trim();
//                taskManager.addDeadline(description, time);
//            }
//        });

        commands.put("deadline", userInput -> {
            try {
                String deadline = String.valueOf(TaskManager.parseDeadlineDescription(userInput));
                taskManager.addDeadline(Deadline.getDescription(), Deadline.getTime());
            } catch (JarvisException e) {
                System.out.println(e.getMessage());
            }
        });


        commands.put("event", userInput -> {
            int lastIndexTo = userInput.lastIndexOf("/to");
            int lastIndexFrom = userInput.lastIndexOf("/from");
            String description = "", startTime = "", endTime="";

            if (lastIndexFrom != -1 && lastIndexTo != -1) {
                String[] parts = userInput.substring(6).trim().split("/from|/to", 3);
                description = parts[0].trim();
                startTime = parts[1].trim();
                endTime = parts[2].trim();
                if (description.isEmpty() || startTime.isEmpty() || endTime.isEmpty()) {
                    System.out.println(JarvisException.invalidEventFormat().getMessage());
                }
                else{
                    taskManager.addEvent(description, startTime, endTime);
                }
            }

        });
    }

    public static void processUserCommands() throws JarvisException {
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
