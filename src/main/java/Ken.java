import java.util.Scanner;

public class Ken {
    // Initialize arrays to store tasks, their statuses, and their types
    private static String[] taskDescriptions = new String[100];
    private static boolean[] taskDoneStatus = new boolean[100]; // true for done, false for not done
    private static String[] taskTypes = new String[100]; // "T" for ToDo, "D" for Deadline, "E" for Event
    private static String[] taskDates = new String[100]; // Store date/time information
    private static int taskCount = 0;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Print the initial message
        printWelcomeMessage();

        // Read and process user commands
        while (true) {
            String userInput = scanner.nextLine();

            // Echo and process the user's command
            System.out.println(" ____________________________________________________________");
            if (userInput.equalsIgnoreCase("bye")) {
                printGoodbyeMessage();
                break; // Exit the loop
            } else if (userInput.equalsIgnoreCase("list")) {
                listTasks();
            } else {
                processUserCommand(userInput);
            }
            System.out.println("____________________________________________________________");
        }

        // Close the scanner when done
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

    private static void processUserCommand(String userInput) {
        String[] parts = userInput.split(" ", 2);
        if (parts.length != 2) {
            System.out.println(" Invalid input format. Please provide a valid task description.");
        } else {
            String taskType = parts[0].trim();
            String taskDescription = parts[1].trim();
            if (taskType.equalsIgnoreCase("todo") || taskType.equalsIgnoreCase("deadline") || taskType.equalsIgnoreCase("event")) {
                taskTypes[taskCount] = taskType.substring(0, 1).toUpperCase();
                String[] dateInfo = extractDateInfo(taskDescription); // Extract date/time information
                taskDescription = dateInfo[0]; // Remove date/time information from task description
                taskDates[taskCount] = dateInfo[1]; // Store date/time information separately
                taskDescriptions[taskCount] = taskDescription.trim();
                taskDoneStatus[taskCount] = false; // Initialize as not done
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
                        System.out.println(" Nice! I've marked this task as done:");
                        System.out.println("   [X] " + taskDescriptions[taskIndex] + (taskDates[taskIndex] != null ? taskDates[taskIndex] : ""));
                    } else {
                        System.out.println(" Task not found. Please provide a valid task number.");
                    }
                } catch (NumberFormatException e) {
                    System.out.println(" Invalid input. Please provide a valid task number.");
                }
            } else {
                System.out.println(" Invalid task type. Please use 'todo,' 'deadline,' 'event,' or 'mark [number].'");
            }
        }
    }

    private static String[] extractDateInfo(String description) {
        // Check if the description contains "/from" or "/by" and "/to" to determine task type
        if (description.contains("/from") && description.contains("/to")) {
            // Event task with both start and end dates
            String[] parts = description.split(" /from | /to ");
            if (parts.length > 1) {
                String startDate = parts[1];
                String endDate = parts[2];
                String dateInfo = " from: " + startDate + " to: " + endDate;
                return new String[]{parts[0], dateInfo};
            }
        } else if (description.contains("/by")) {
            // Deadline task with a single date
            String[] parts = description.split(" /by ");
            if (parts.length > 1) {
                String deadlineDate = parts[1];
                String dateInfo = " by: " + deadlineDate;
                return new String[]{parts[0], dateInfo};
            }
        }

        // If no date/time information found, return the description as is
        return new String[]{description, null};
    }
}

