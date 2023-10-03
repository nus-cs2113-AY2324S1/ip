import java.util.Scanner;

public class Ken {
    private static String[] taskDescriptions = new String[100];
    private static boolean[] taskDoneStatus = new boolean[100];
    private static String[] taskTypes = new String[100];
    private static String[] taskDates = new String[100];
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

    private static void printWelcomeMessage() {
        System.out.println(" ____________________________________________________________");
        System.out.println(" Hello! I'm Ken");
        System.out.println(" What would you like to do?");
        System.out.println("____________________________________________________________");
    }

    private static void printGoodbyeMessage() {
        System.out.println(" Bye for now!");
    }

    private static void listTasks() {
        System.out.println(" Here are the tasks in your list:");
        for (int i = 0; i < taskCount; i++) {
            char doneSymbol = taskDoneStatus[i] ? 'X' : ' ';
            String dateInfo = taskDates[i] != null ? taskDates[i] : "";
            System.out.println(" " + (i + 1) + ".[" + taskTypes[i] + "][" + doneSymbol + "] " + taskDescriptions[i] + dateInfo);
        }
    }

    private static void processUserCommand(String userInput) throws KenException {
        String[] parts = userInput.split(" ", 2);
        if (parts.length != 2) {
            // Handle cases when user input is not in the expected format
            if (parts.length == 1 && (parts[0].equalsIgnoreCase("todo") || parts[0].equalsIgnoreCase("deadline") || parts[0].equalsIgnoreCase("event"))) {
                // Handle the case where the taskType is provided without a space
                throw new KenException("Hey!! Description cannot be empty for a " + parts[0] + " task.");
            } else {
                throw new KenException("Hmm, what's that? Please use 'todo,' 'deadline,' 'event,' or 'mark [number].'");
            }
        } else {
            String taskType = parts[0].trim();
            String taskDescription = parts[1].trim();
            if (taskType.equalsIgnoreCase("todo") || taskType.equalsIgnoreCase("deadline") || taskType.equalsIgnoreCase("event")) {
                if (taskDescription.isEmpty()) {
                    throw new KenException("Hey!! Description cannot be empty for a " + taskType + " task.");
                }
                taskTypes[taskCount] = taskType.substring(0, 1).toUpperCase();
                String[] dateInfo = extractDateInfo(taskDescription);
                taskDescription = dateInfo[0];
                taskDates[taskCount] = dateInfo[1];
                taskDescriptions[taskCount] = taskDescription.trim();
                taskDoneStatus[taskCount] = false;
                taskCount++;
                System.out.println(" Got it. I've added this task:");
                String dateInfoText = taskDates[taskCount - 1] != null ? " " + taskDates[taskCount - 1] : "";
                System.out.println("   [" + taskTypes[taskCount - 1] + "][ ] " + taskDescriptions[taskCount - 1] + dateInfoText);
                System.out.println(" Now you have " + taskCount + " tasks in the list.");
            } else if (taskType.equalsIgnoreCase("mark")) {
                try {
                    int taskIndex = Integer.parseInt(taskDescription.trim()) - 1;
                    if (taskIndex >= 0 && taskIndex < taskCount) {
                        taskDoneStatus[taskIndex] = true;
                        System.out.println("Nice! I've marked this task as done:");
                        System.out.println("   [X] " + taskDescriptions[taskIndex] + (taskDates[taskIndex] != null ? taskDates[taskIndex] : ""));
                    } else {
                        throw new KenException("I can't find this task. Please provide a valid task number.");
                    }
                } catch (NumberFormatException e) {
                    throw new KenException("Wrong format!! Please provide the number of the task.");
                }
            } else {
                throw new KenException("Hmm, what's that? Please use 'todo,' 'deadline,' 'event,' or 'mark [number].'");
            }
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
