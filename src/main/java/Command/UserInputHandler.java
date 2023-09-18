package Command;

import Tasks.Deadline;
import Tasks.Event;
import Tasks.Task;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import static Command.TaskManager.parseEventDescription;

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
            try {
                int index = TaskManager.extractIndex(userInput, 5);
                taskManager.markTaskAsDone(index);
            } catch (JarvisException e) {
                System.out.println(e.getMessage());
            }
        });

        commands.put("unmark", userInput -> {
            try {
                int index = TaskManager.extractIndex(userInput, 7);
                taskManager.unmarkTaskAsDone(index);
            } catch (JarvisException e) {
                System.out.println(e.getMessage());
            }
        });

        commands.put("todo", userInput -> {
            try {
                taskManager.addTodo(TaskManager.parseToDoDescription(userInput));
            } catch (JarvisException | IOException e) {
                System.out.println(e.getMessage());
            }
        });

        commands.put("deadline", userInput -> {
            try {
                List<String> deadline = TaskManager.parseDeadlineDescription(userInput);
                taskManager.addDeadline(deadline.get(0), deadline.get(1));
            } catch (JarvisException | IOException e) {
                System.out.println(e.getMessage());
            }
        });

        commands.put("event", userInput -> {
            try {
                List<String> event = TaskManager.parseEventDescription(userInput);
                taskManager.addEvent(event.get(0), event.get(1), event.get(2));
            } catch (JarvisException | IOException e) {
                System.out.println(e.getMessage());
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
                System.out.println(JarvisException.invalidCommand().getMessage());
            }

            System.out.println(LINE_BREAK);
            userInput = sc.nextLine();
        }
    }

    public static void loadTasksFromFile() {
        try {
            taskManager.loadTasksFromFile();
        } catch (IOException | JarvisException e) {
            System.out.println("Error loading tasks from file: " + e.getMessage());
        }
    }

    private static String getCommandWord(String userInput) {
        return userInput.split(" ")[0];
    }

}
