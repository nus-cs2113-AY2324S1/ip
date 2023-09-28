import java.util.ArrayList;
import java.util.List;

/**
 * The CommandHandler class processes user commands for task management.
 * It handles commands like adding, listing, marking, deleting, and finding tasks.
 */
public class CommandHandler {

    /**
     * Handles the user's input command and processes it.
     *
     * @param userInput The user's input command as a string.
     * @param tasks     The list of tasks to manage.
     */
    public void handleCommand(String userInput, List<Task> tasks) {
        String[] inputParts = userInput.split(" ", 2);
        String command = inputParts[0].toLowerCase();
        String argument = inputParts.length > 1 ? inputParts[1] : "";

        try {
            switch (command) {
                case "list":
                    handleListCommand(tasks);
                    break;

                case "mark":
                    handleMarkCommand(tasks, argument);
                    break;

                case "delete":
                    handleDeleteCommand(tasks, argument);
                    break;

                case "find":
                    handleFindCommand(tasks, argument);
                    break;

                default:
                    handleAddTaskMethod(userInput, tasks);
            }
        } catch (DukeException e) {
            System.out.println("Ahnge: ☹ OOPS!!! The command is not found.");
            printLines();
        }
    }

    /**
     * Handles the "find" command to search for tasks containing a keyword.
     *
     * @param tasks   The list of tasks to search within.
     * @param keyword The keyword to search for in task descriptions.
     */
    private static void handleFindCommand(List<Task> tasks, String keyword) {
        List<Task> matchingTasks = new ArrayList<>();
        for (Task task : tasks) {
            if (task.toString().toLowerCase().contains(keyword.toLowerCase())) {
                matchingTasks.add(task);
            }
        }
        if (matchingTasks.isEmpty()) {
            System.out.println("Ahnge: No matching tasks found.");
        } else {
            System.out.println("Ahnge: Here are the matching tasks in your list:");
            for (int i = 0; i < matchingTasks.size(); i++) {
                System.out.println(" " + (i + 1) + "." + matchingTasks.get(i));
            }
        }
        printLines();
    }

    /**
     * Handles the "delete" command to remove a task from the list.
     *
     * @param tasks    The list of tasks to delete from.
     * @param argument The task number or identifier to delete.
     */
    private static void handleDeleteCommand(List<Task> tasks, String argument) {
        try {
            int taskNumber = Integer.parseInt(argument);
            if (taskNumber >= 1 && taskNumber <= tasks.size()) {
                Task removedTask = tasks.remove(taskNumber - 1);
                System.out.println("Ahnge: Noted. I've removed this task:");
                System.out.println("  " + removedTask);
                System.out.println("Ahnge: Now you have " + tasks.size() + " tasks in the list.");
            } else {
                System.out.println("Ahnge: Invalid task number. Please enter a valid task number.");
            }
        } catch (NumberFormatException e) {
            System.out.println("Ahnge: Invalid task number format. Please enter a valid task number.");
        }
        printLines();
    }

    /**
     * Handles the addition of a task based on the user's input.
     *
     * @param userInput The user's input command as a string.
     * @param tasks     The list of tasks to add to.
     * @throws DukeException If the command is not understood or invalid.
     */
    private static void handleAddTaskMethod(String userInput, List<Task> tasks) throws DukeException {
        Task task = createTask(userInput);
        if (task != null) {
            tasks.add(task);
            System.out.println("Ahnge: Yessir. I've added this task:");
            System.out.println("  " + task);
            System.out.println("Ahnge: Now you have " + tasks.size() + " tasks in the list.");
            printLines();
        } else {
            throw new DukeException("Ahnge: I don't understand that command.");
        }
    }

    /**
     * Handles the "mark" command to mark a task as done.
     *
     * @param tasks    The list of tasks to mark.
     * @param argument The task number or identifier to mark as done.
     */
    private static void handleMarkCommand(List<Task> tasks, String argument) {
        try {
            int taskNumber = Integer.parseInt(argument);
            if (taskNumber >= 1 && taskNumber <= tasks.size()) {
                Task task = tasks.get(taskNumber - 1);
                task.markDone();
                System.out.println("Ahnge: I've marked this task as done:");
                System.out.println("  " + task);
            } else {
                System.out.println("Ahnge: Invalid task number. Please enter a valid task number.");
            }
        } catch (NumberFormatException e) {
            System.out.println("Ahnge: Invalid task number format. Please enter a valid task number.");
        }
        printLines();
    }

    /**
     * Handles the "list" command to display the list of tasks.
     *
     * @param tasks The list of tasks to display.
     */
    private static void handleListCommand(List<Task> tasks) {
        if (tasks.isEmpty()) {
            System.out.println("Ahnge: There are no tasks in your list.");
        } else {
            System.out.println("Ahnge: Here are the tasks in your list:");
            for (int i = 0; i < tasks.size(); i++) {
                System.out.println(" " + (i + 1) + "." + tasks.get(i));
            }
        }
        printLines();
    }

    /**
     * Creates a task object based on the user's input command.
     *
     * @param userInput The user's input command as a string.
     * @return A Task object created from the input command.
     * @throws DukeException If the input command is invalid.
     */
    private static Task createTask(String userInput) throws DukeException {
        if (userInput.startsWith("todo ")) {
            String description = userInput.substring(5).trim();
            if (description.isEmpty()) {
                throw new DukeException("Ahnge: ☹ OOPS!!! The description of a task cannot be empty.");
            }
            return new ToDo(description, false);
        } else if (userInput.startsWith("deadline ")) {
            String[] parts = userInput.substring(9).split("/by");
            if (parts.length == 2) {
                String description = parts[0].trim();
                String by = parts[1].trim();
                if (description.isEmpty() || by.isEmpty()) {
                    throw new DukeException("Ahnge: ☹ OOPS!!! The description and deadline cannot be empty.");
                }
                return new Deadline(description, by, false);
            }
        } else if (userInput.startsWith("event ")) {
            String[] parts = userInput.substring(6).split("/from");
            if (parts.length == 2) {
                String description = parts[0].trim();
                String[] eventDetails = parts[1].split("/to");
                if (eventDetails.length == 2) {
                    String from = eventDetails[0].trim();
                    String to = eventDetails[1].trim();
                    if (description.isEmpty() || from.isEmpty() || to.isEmpty()) {
                        throw new DukeException("Ahnge: ☹ OOPS!!! The description, start time, and end time cannot be empty.");
                    }
                    return new Event(description, from, to,false);
                }
            }
        }
        return null;
    }

    /**
     * Prints a line of equal signs to separate sections of output.
     */
    public static void printLines() {
        for (int i = 0; i < 30; i++) {
            System.out.print('=');
        }
        System.out.println();
    }
}
