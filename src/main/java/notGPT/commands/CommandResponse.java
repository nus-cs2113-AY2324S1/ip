package notGPT.commands;

import java.util.Arrays;
import notGPT.NotChatGPT;
import notGPT.exception.NotChatGPTExceptions;

/**
 * The CommandResponse class contains methods to handle user commands and provide responses.
 */
public class CommandResponse {

    /**
     * Handles the 'bye' command to exit the application.
     */
    public static void handleBye() {
        System.out.println("Bye Broski! Thank you for choosing notChatGPT :)");
        NotChatGPT.isRunning = false;
    }

    /**
     * Handles the 'list' command to display the list of tasks.
     */
    public static void handleList() {
        String[] tasks = NotChatGPT.taskList.getTasks();
        if (tasks.length == 0) {
            System.out.println("You have no tasks in your list lel");
        } else {
            System.out.println("Here are the tasks in your list:");
            for (int i = 0; i < tasks.length; i++) {
                if (tasks[i] != null) {
                    System.out.println((i + 1) + ". " + tasks[i]);
                }
            }
        }
    }

    /**
     * Handles the 'mark' command to mark a task as done.
     *
     * @param userInput An array of strings containing the user's input.
     */
    public static void handleMark(String[] userInput) {
        try {
            if (userInput.length < 2) {
                throw new NotChatGPTExceptions("Please specify the task number to mark!!");
            }
            int taskNumber = Integer.parseInt(userInput[1]);
            if (taskNumber < 1 || taskNumber > NotChatGPT.taskList.getTaskCount()) {
                throw new NotChatGPTExceptions("Task number is out of range!!");
            }
            if (NotChatGPT.taskList.getTaskByNumber(taskNumber).getIsDone()) {
                throw new NotChatGPTExceptions("Task is already marked as done!!");
            }
            NotChatGPT.taskList.markTaskAsDone(taskNumber);
            System.out.println("Nice! I've marked this task as done:");
            System.out.println(NotChatGPT.taskList.getTasks()[taskNumber - 1]);
        } catch (NumberFormatException e) {
            System.out.println("Error: Invalid task number. Please enter a valid number.");
        } catch (NotChatGPTExceptions e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    /**
     * Handles the 'unmark' command to unmark a task as not done.
     *
     * @param userInput An array of strings containing the user's input.
     */
    public static void handleUnmark(String[] userInput) {
        try {
            if (userInput.length < 2) {
                throw new NotChatGPTExceptions("Please specify the task number to unmark!!");
            }
            int taskNumber = Integer.parseInt(userInput[1]);
            if (taskNumber < 1 || taskNumber > NotChatGPT.taskList.getTaskCount()) {
                throw new NotChatGPTExceptions("Task number is out of range!!");
            }
            if (!NotChatGPT.taskList.getTaskByNumber(taskNumber).getIsDone()) {
                throw new NotChatGPTExceptions("Task is already marked as not done!!");
            }
            NotChatGPT.taskList.unmarkTaskAsDone(taskNumber);
            System.out.println("Aight! I've marked this task as not done yet.");
            System.out.println(NotChatGPT.taskList.getTasks()[taskNumber - 1]);
        } catch (NumberFormatException e) {
            System.out.println("Error: Invalid task number. Please enter a valid number.");
        } catch (NotChatGPTExceptions e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    /**
     * Handles the 'todo' command to add a new Todo task.
     *
     * @param userInput An array of strings containing the user's input.
     */
    public static void handleTodo(String[] userInput) {
        try {
            if (userInput.length < 2) {
                throw new NotChatGPTExceptions("Please specify the task description!! (cannot be empty lah)");
            }
            NotChatGPT.taskList.addTodo(String.join(" ", Arrays.copyOfRange(userInput, 1, userInput.length)));
            System.out.println("Got it. I've added this task:");
            System.out.println(NotChatGPT.taskList.getTasks()[NotChatGPT.taskList.getTaskCount() - 1]);
        } catch (NotChatGPTExceptions e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    /**
     * Handles the 'deadline' command to add a new Deadline task.
     *
     * @param userInput An array of strings containing the user's input.
     */
    public static void handleDeadline(String[] userInput) {
        try {
            int deadlineIndex = -1;
            for (int i = 1; i < userInput.length; i++) {
                if (userInput[i].equals("/by")) {
                    deadlineIndex = i;
                    break;
                }
            }
            if (userInput.length < 2 || deadlineIndex == 1) {
                throw new NotChatGPTExceptions("Please specify the deadline description!! (cannot be empty lah)");
            }
            if (deadlineIndex < 0) {
                throw new NotChatGPTExceptions("Invalid deadline format. Use '/by' to specify the deadline.");
            }
            if (deadlineIndex == userInput.length - 1) {
                throw new NotChatGPTExceptions("Please specify the deadline timing!!");
            }
            String deadlineName = String.join(" ", Arrays.copyOfRange(userInput, 1, deadlineIndex));
            String deadlineTime = String.join(" ", Arrays.copyOfRange(userInput, deadlineIndex + 1, userInput.length));
            NotChatGPT.taskList.addDeadline(deadlineName, deadlineTime);
            System.out.println("Got it. I've added this task:");
            System.out.println(NotChatGPT.taskList.getTasks()[NotChatGPT.taskList.getTaskCount() - 1]);
        } catch (NotChatGPTExceptions e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    /**
 * Handles the 'event' command to add a new Event task.
 *
 * @param userInput An array of strings containing the user's input.
 */
public static void handleEvent(String[] userInput) {
    try {
        int eventStartTimeIndex = -1;
        int eventEndTimeIndex = -1;
        for (int i = 1; i < userInput.length; i++) {
            if (userInput[i].equals("/from")) {
                eventStartTimeIndex = i;
            } else if (userInput[i].equals("/to")) {
                eventEndTimeIndex = i;
            }
        }
        if (userInput.length < 2 || eventStartTimeIndex == 1 || eventEndTimeIndex == 1) {
            throw new NotChatGPTExceptions("Please specify the event description!! (cannot be empty lah)");
        }
        if (eventStartTimeIndex < 0 || eventEndTimeIndex < 0 || eventEndTimeIndex == userInput.length - 1) {
            throw new NotChatGPTExceptions("Invalid event format. Use '/from' and '/to' to specify event timings.");
        }
        if (eventStartTimeIndex == userInput.length - 1 || eventStartTimeIndex == eventEndTimeIndex - 1) {
            throw new NotChatGPTExceptions("Please specify the event start time!!");
        }
        if (eventEndTimeIndex == userInput.length - 1) {
            throw new NotChatGPTExceptions("Please specify the event end time!!");
        }
        String eventName = String.join(" ", Arrays.copyOfRange(userInput, 1, eventStartTimeIndex));
        String eventStartTime = String.join(" ", Arrays.copyOfRange(userInput, eventStartTimeIndex + 1, eventEndTimeIndex));
        String eventEndTime = String.join(" ", Arrays.copyOfRange(userInput, eventEndTimeIndex + 1, userInput.length));
        NotChatGPT.taskList.addEvent(eventName, eventStartTime, eventEndTime);
        System.out.println("Got it. I've added this task:");
        System.out.println(NotChatGPT.taskList.getTasks()[NotChatGPT.taskList.getTaskCount() - 1]);
    } catch (NotChatGPTExceptions e) {
        System.out.println("Error: " + e.getMessage());
    }
}

/**
 * Handles the 'delete' command to remove a task from the list.
 *
 * @param userInput An array of strings containing the user's input.
 */
public static void handleDelete(String[] userInput) {
    try {
        if (userInput.length < 2) {
            throw new NotChatGPTExceptions("Please specify the task number to delete!!");
        }
        int taskNumber = Integer.parseInt(userInput[1]);
        if (taskNumber < 1 || taskNumber > NotChatGPT.taskList.getTaskCount()) {
            throw new NotChatGPTExceptions("Task number is out of range!! Please enter a valid number.");
        }
        System.out.println("Noted. I've removed this task:");
        System.out.println(NotChatGPT.taskList.getTasks()[taskNumber - 1]);
        NotChatGPT.taskList.deleteTask(taskNumber);
        System.out.println("Now you have " + NotChatGPT.taskList.getTaskCount() + " tasks in your list.");
    } catch (NumberFormatException e) {
        System.out.println("Error: Invalid task number. Please enter a valid number.");
    } catch (NotChatGPTExceptions e) {
        System.out.println("Error: " + e.getMessage());
    }
}

    /**
     * Handles the 'find' command to search for tasks containing a keyword.
     *
     * @param userInput An array of strings containing the user's input.
     */
    public static void handleFind(String[] userInput) {
        try {
            if (userInput.length < 2) {
                throw new NotChatGPTExceptions("Please specify the keyword to search for!!");
            }
            String keyword = String.join(" ", Arrays.copyOfRange(userInput, 1, userInput.length));
            String[] matchingTasks = NotChatGPT.taskList.findTasks(keyword);
            if (matchingTasks.length == 0) {
                System.out.println("No matching tasks found.");
            } else {
                System.out.println("Here are the matching tasks in your list:");
                for (int i = 0; i < matchingTasks.length; i++) {
                    System.out.println((i + 1) + ". " + matchingTasks[i]);
                }
            }
        } catch (NotChatGPTExceptions e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    /**
     * Responds to the user's command by calling the appropriate handler method.
     *
     * @param userInput An array of strings containing the user's input.
     */
    public static void respond(String[] userInput) {
        switch (userInput[0]) {
            case "bye":
                handleBye();
                break;
            case "list":
                handleList();
                break;
            case "mark":
                handleMark(userInput);
                break;
            case "unmark":
                handleUnmark(userInput);
                break;
            case "todo":
                handleTodo(userInput);
                break;
            case "deadline":
                handleDeadline(userInput);
                break;
            case "event":
                handleEvent(userInput);
                break;
            case "delete":
                handleDelete(userInput);
                break;
            case "find":
                handleFind(userInput);
                break;
            default:
                System.out.println("I'm sorry, but I don't know what that means :-(");
                break;
        }
    }
}
