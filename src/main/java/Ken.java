
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Ken {
    private static final String COMMAND_BYE = "bye";
    private static final String COMMAND_LIST = "list";
    private static final String COMMAND_TODO = "todo";
    private static final String COMMAND_DEADLINE = "deadline";
    private static final String COMMAND_EVENT = "event";
    private static final String COMMAND_DELETE = "delete";
    private static final String COMMAND_MARK = "mark";
    private static final String DATA_FILE_PATH = "data/ken.txt"; // Relative path to the data file

    private static List<String> taskDescriptions = new ArrayList<>();
    private static List<Boolean> taskDoneStatus = new ArrayList<>();
    private static List<String> taskTypes = new ArrayList<>();
    private static List<String> taskDates = new ArrayList<>();


    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Load tasks from the file when the chatbot starts up
        loadTasks();

        printWelcomeMessage();

        while (true) {
            String userInput = scanner.nextLine();

            System.out.println(" ____________________________________________________________");
            if (userInput.equalsIgnoreCase(COMMAND_BYE)) {
                printGoodbyeMessage();
                break;
            } else if (userInput.equalsIgnoreCase(COMMAND_LIST)) {
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

        for (int i = 0; !(i >= numTasks); i++) {
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

            if (taskType.equalsIgnoreCase(COMMAND_TODO) || taskType.equalsIgnoreCase(COMMAND_DEADLINE) || taskType.equalsIgnoreCase(COMMAND_EVENT)) {
                handleAddTask(taskType, taskDescription);
            } else if (taskType.equalsIgnoreCase(COMMAND_DELETE)) {
                handleDeleteTask(taskDescription);
            } else if (taskType.equalsIgnoreCase(COMMAND_MARK)) {
                handleMarkTask(taskDescription);
            } else {
                throw new KenException("Hmm, what's that? Please use 'todo,' 'deadline,' 'event,' 'delete [number],' or 'mark [number].'");
            }
        }
    }

    private static void handleInvalidCommand(String command) throws InvalidCommandException, EmptyDescriptionException {
        if (command.equalsIgnoreCase("delete")) {
            throw new InvalidCommandException("Please provide a task number to delete.");
        } else if (command.equalsIgnoreCase("todo") || command.equalsIgnoreCase("deadline") || command.equalsIgnoreCase("event")) {
            throw new EmptyDescriptionException("Hey!! Description cannot be empty for a " + command + " task.");
        } else {
            throw new InvalidCommandException("Hmm, what's that? Please use 'todo,' 'deadline,' 'event,' 'delete [number],' or 'mark [number].'");
        }
    }

    private static void handleAddTask(String taskType, String taskDescription) throws EmptyDescriptionException {
        if (taskDescription.isEmpty()) {
            throw new EmptyDescriptionException("Hey!! Description cannot be empty for a " + taskType + " task.");
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

        // Call saveTasks to save the tasks after adding
        saveTasks();
    }

    private static void deleteTask(int taskIndex) {
        if (taskIndex >= 0 && taskIndex < taskDescriptions.size()) {
            taskDescriptions.remove(taskIndex);
            taskDoneStatus.remove(taskIndex);
            taskTypes.remove(taskIndex);
            taskDates.remove(taskIndex);

            // Call saveTasks to save the tasks after deleting
            saveTasks();
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
                throw new TaskNotFoundException("I can't find this task. Please provide a valid task number.");
            }
        } catch (NumberFormatException e) {
            throw new TaskNotFoundException("Wrong format!! Please provide the number of the task.");
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

                    // Call saveTasks to save the tasks after marking
                    saveTasks();
                } else {
                    System.out.println("This task is already marked as done.");
                }
            } else {
                throw new TaskNotFoundException("I can't find this task. Please provide a valid task number.");
            }
        } catch (NumberFormatException e) {
            throw new TaskNotFoundException("Wrong format!! Please provide the number of the task.");
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



    private static void loadTasks() {
        File file = new File(DATA_FILE_PATH);

        if (file.exists()) {
            try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    // Parse the line and add tasks to the lists
                    // Format: T | 1 | read book | dateInfo (if available)
                    String[] parts = line.split(" \\| ");
                    if (parts.length >= 3) {
                        taskTypes.add(parts[0]);
                        taskDoneStatus.add(parts[1].equals("1"));
                        taskDescriptions.add(parts[2]);

                        // Check if dateInfo is available
                        if (parts.length >= 4) {
                            taskDates.add(parts[3]);
                        } else {
                            taskDates.add(null); // No dateInfo
                        }
                    }
                }
            } catch (IOException e) {
                System.out.println("Error loading tasks from the data file.");
            }
        }
    }


    private static void saveTasks() {
        File file = new File(DATA_FILE_PATH);

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
            for (int i = 0; i < taskDescriptions.size(); i++) {
                String taskType = taskTypes.get(i);
                String doneStatus = taskDoneStatus.get(i) ? "1" : "0";
                String description = taskDescriptions.get(i);
                writer.write(taskType + " | " + doneStatus + " | " + description);
                writer.newLine();
            }
        } catch (IOException e) {
            System.out.println("Error saving tasks to the data file.");
        }
    }


}

class KenException extends Exception {
    public KenException(String message) {
        super(message);
    }
}

class InvalidCommandException extends KenException {
    public InvalidCommandException(String message) {
        super(message);
    }
}

class EmptyDescriptionException extends KenException {
    public EmptyDescriptionException(String message) {
        super(message);
    }
}

class TaskNotFoundException extends KenException {
    public TaskNotFoundException(String message) {
        super(message);
    }
}
