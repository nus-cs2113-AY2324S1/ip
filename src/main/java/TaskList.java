import java.util.ArrayList;
import java.util.List;

/**
 * The TaskList class manages the task-related data and provides methods to manipulate tasks.
 */
public class TaskList {
    /**
     * A list to store task descriptions.
     */
    public static List<String> taskDescriptions = new ArrayList<>();

    /**
     * A list to store task completion status (true for completed, false for incomplete).
     */
    public static List<Boolean> taskDoneStatus = new ArrayList<>();

    /**
     * A list to store task types (e.g., "T" for Todo, "D" for Deadline, "E" for Event).
     */
    public static List<String> taskTypes = new ArrayList<>();

    /**
     * A list to store task dates (optional for some task types).
     */
    public static List<String> taskDates = new ArrayList<>();

    /**
     * Parses a task description from a string and adds it to the task lists.
     *
     * @param line The string containing task information.
     */
    public void parseAndAddTask(String line) {
        String[] parts = line.split(" \\| ");
        if (parts.length >= 3) {
            taskTypes.add(parts[0]);
            taskDoneStatus.add(parts[1].equals("1"));
            taskDescriptions.add(parts[2]);

            if (parts[0].equals("T")) {
                taskDates.add(null); // Set the date to null for Todo tasks
            } else if (parts.length >= 4) {
                taskDates.add(parts[3]); // Set the date for Deadline and Event tasks
            } else {
                taskDates.add(null); // Handle the case where the date is missing for Deadline and Event tasks
            }
        }
    }

    /**
     * Retrieves the list of task descriptions.
     *
     * @return The list of task descriptions.
     */
    public List<String> getTaskDescriptions() {
        return taskDescriptions;
    }

    /**
     * Retrieves the list of task completion statuses.
     *
     * @return The list of task completion statuses.
     */
    public List<Boolean> getTaskDoneStatus() {
        return taskDoneStatus;
    }

    /**
     * Retrieves the list of task types.
     *
     * @return The list of task types.
     */
    public List<String> getTaskTypes() {
        return taskTypes;
    }

    /**
     * Retrieves the list of task dates.
     *
     * @return The list of task dates.
     */
    public List<String> getTaskDates() {
        return taskDates;
    }

    /**
     * Converts a task at the specified index into a formatted string.
     *
     * @param index The index of the task to be converted.
     * @return The formatted string representing the task.
     */
    public static String taskToString(int index) {
        String taskType = taskTypes.get(index);
        String doneStatus = taskDoneStatus.get(index) ? "[X]" : "[ ]";
        String description = taskDescriptions.get(index);
        String date = taskDates.get(index);

        switch (taskType) {
            case "T":
                return doneStatus + " " + description;
            case "D":
                if (date != null) {
                    return doneStatus + " " + description + " (" + date + ")";
                } else {
                    return doneStatus + " " + description;
                }
            case "E":
                if (date != null) {
                    String[] dateParts = date.split(" to ");
                    if (dateParts.length == 2) {
                        return doneStatus + " " + description + " (" + dateParts[0] + " to " + dateParts[1] + ")";
                    } else if (dateParts.length == 1) {
                        return doneStatus + " " + description + " (" + dateParts[0] + " )";
                    }
                } else {
                    return doneStatus + " " + description;
                }
            default:
                return "Unknown task type: " + taskType;
        }
    }
}
