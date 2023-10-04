import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Ken {
    private static List<String> taskDescriptions = new ArrayList<>();
    private static List<Boolean> taskDoneStatus = new ArrayList<>();
    private static List<String> taskTypes = new ArrayList<>();
    private static List<String> taskDates = new ArrayList<>();
    private static int taskCount = 0;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        printWelcomeMessage();

        while (true) {
            String userInput = scanner.nextLine();

            System.out.println(" ____________________________________________________________");
            if (userInput.equalsIgnoreCase("bye")) {
                printGoodbyeMessage();
                break;
            } else if (userInput.equalsIgnoreCase("list")) {
                listTasks();
            } else {
                try {
                    processUserCommand(userInput);
                } catch (KenException e) {
                    System.out.println("Error: " + e.getMessage());
                }
            }
            System.out.println("____________________________________________________________");
        }

        scanner.close();
    }
    private static void printKEN() {
        System.out.println("  K   K  EEEEE  N   N");
        System.out.println("  K  K   E      NN  N");
        System.out.println("  KKK    EEEE   N N N");
        System.out.println("  K  K   E      N  NN");
        System.out.println("  K   K  EEEEE  N   N");
    }

    private static void printWelcomeMessage() {
        System.out.println(" ____________________________________________________________");
        System.out.println(" Hello! I'm Ken");
        printKEN();
        System.out.println(" What would you like to do?");
        System.out.println("____________________________________________________________");
    }

    private static void printGoodbyeMessage() {
        System.out.println(" Bye for now!");
    }

    private static void listTasks() {
        System.out.println(" Here are the tasks in your list:");
        int numTasks = taskDescriptions.size(); // Get the number of tasks

        for (int i = 0; i < numTasks; i++) {
            char doneSymbol = taskDoneStatus.get(i) ? 'X' : ' ';
            String dateInfo = taskDates.get(i) != null ? taskDates.get(i) : "";
            System.out.println(" " + (i + 1) + ".[" + taskTypes.get(i) + "][" + doneSymbol + "] " + taskDescriptions.get(i) + dateInfo);
        }
    }

    private static void processUserCommand(String userInput) throws KenException {
        String[] parts = userInput.split(" ", 2);

        if (parts.length != 2) {
            handleInvalidCommand(parts[0]);
        } else {
            String taskType = parts[0].trim();
            String taskDescription = parts[1].trim();

            if (taskType.equalsIgnoreCase("todo") || taskType.equalsIgnoreCase("deadline") || taskType.equalsIgnoreCase("event")) {
                handleAddTask(taskType, taskDescription);
            } else if (taskType.equalsIgnoreCase("delete")) {
                handleDeleteTask(taskDescription);
            } else if (taskType.equalsIgnoreCase("mark")) {
                handleMarkTask(taskDescription);
            } else {
                throw new KenException("Hmm, what's that? Please use 'todo,' 'deadline,' 'event,' 'delete [number],' or 'mark [number].'");
            }
        }
    }

    private static void handleInvalidCommand(String command) throws KenException {
        if (command.equalsIgnoreCase("delete")) {
            throw new KenException("Please provide a task number to delete.");
        } else if (command.equalsIgnoreCase("todo") || command.equalsIgnoreCase("deadline") || command.equalsIgnoreCase("event")) {
            throw new KenException("Hey!! Description cannot be empty for a " + command + " task.");
        } else {
            throw new KenException("Hmm, what's that? Please use 'todo,' 'deadline,' 'event,' 'delete [number],' or 'mark [number].'");
        }
    }

    private static void handleAddTask(String taskType, String taskDescription) throws KenException {
        if (taskDescription.isEmpty()) {
            throw new KenException("Hey!! Description cannot be empty for a " + taskType + " task.");
        }

        taskTypes.add(taskType.substring(0, 1).toUpperCase());
        String[] dateInfo = extractDateInfo(taskDescription);
        taskDescription = dateInfo[0];
        taskDates.add(dateInfo[1]);
        taskDescriptions.add(taskDescription.trim());
        taskDoneStatus.add(false);

        System.out.println(" Got it. I've added this task:");
        String dateInfoText = taskDates.get(taskDescriptions.size() - 1) != null ? " " + taskDates.get(taskDescriptions.size() - 1) : "";
        System.out.println("   [" + taskTypes.get(taskDescriptions.size() - 1) + "][ ] " + taskDescriptions.get(taskDescriptions.size() - 1) + dateInfoText);
        System.out.println(" Now you have " + taskDescriptions.size() + " tasks in the list.");
    }

    private static void deleteTask(int taskIndex) {
        if (taskIndex >= 0 && taskIndex < taskDescriptions.size()) {
            taskDescriptions.remove(taskIndex);
            taskDoneStatus.remove(taskIndex);
            taskTypes.remove(taskIndex);
            taskDates.remove(taskIndex);

        } else {
                System.out.println("Task not found. Nothing was deleted.");
            }
        }


    private static void handleDeleteTask(String taskDescription) throws KenException {
        try {
            int taskIndex = Integer.parseInt(taskDescription.trim()) - 1;
            if (taskIndex >= 0 && taskIndex < taskDescriptions.size()) {
                String deletedTaskDescription = taskDescriptions.get(taskIndex);

                System.out.println("Got it. I've removed this task:");
                System.out.println("   [" + taskTypes.get(taskIndex) + "][ ] " + deletedTaskDescription + (taskDates.get(taskIndex) != null ? taskDates.get(taskIndex) : ""));
                deleteTask(taskIndex);
                System.out.println("Now you have " + taskDescriptions.size() + " tasks in the list.");
            } else {
                throw new KenException("I can't find this task. Please provide a valid task number.");
            }
        } catch (NumberFormatException e) {
            throw new KenException("Wrong format!! Please provide the number of the task.");
        }
    }

    private static void handleMarkTask(String taskDescription) throws KenException {
        try {
            int taskIndex = Integer.parseInt(taskDescription.trim()) - 1;
            if (taskIndex >= 0 && taskIndex < taskDescriptions.size()) {
                if (!taskDoneStatus.get(taskIndex)) {
                    taskDoneStatus.set(taskIndex, true);
                    System.out.println("Nice! I've marked this task as done:");
                    System.out.println(" [X] " + taskDescriptions.get(taskIndex) + (taskDates.get(taskIndex) != null ? taskDates.get(taskIndex) : ""));
                } else {
                    System.out.println("This task is already marked as done.");
                }
            } else {
                throw new KenException("I can't find this task. Please provide a valid task number.");
            }
        } catch (NumberFormatException e) {
            throw new KenException("Wrong format!! Please provide the number of the task.");
        }
    }


    private static String[] extractDateInfo(String description) {
        if (description.contains("/from") && description.contains("/to")) {
            String[] parts = description.split(" /from | /to ");
            if (parts.length > 1) {
                String startDate = parts[1];
                String endDate = parts[2];
                String dateInfo = " from: " + startDate + " to: " + endDate;
                return new String[]{parts[0], dateInfo};
            }
        } else if (description.contains("/by")) {
            String[] parts = description.split(" /by ");
            if (parts.length > 1) {
                String deadlineDate = parts[1];
                String dateInfo = " by: " + deadlineDate;
                return new String[]{parts[0], dateInfo};
            }
        }
        return new String[]{description, null};
    }
}

class KenException extends Exception {
    public KenException(String message) {
        super(message);
    }
}
