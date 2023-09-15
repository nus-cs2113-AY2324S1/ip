package notGPT.commands;

import java.util.Arrays;
import notGPT.NotChatGPT;
import notGPT.exception.NotChatGPTExceptions;

public class CommandResponse {
    private static final String line = "____________________________________________________________\n";

    public static void handleBye() {
        System.out.println(line + "\nBye Broski! Thank you for choosing notChatGPT :)\n" + line);
        NotChatGPT.isRunning = false;
    }

    public static void handleList() {
        System.out.println(line + "\nHere are the tasks in your list:\n");
        String[] tasks = NotChatGPT.taskList.getTasks();
        for (int i = 0; i < tasks.length; i++) {
            if (tasks[i] != null) {
                System.out.println((i + 1) + ". " + tasks[i]);
            }
        }
        System.out.println(line);
    }

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
            System.out.println(line + "\nNice! I've marked this task as done:");
            System.out.println(NotChatGPT.taskList.getTasks()[taskNumber - 1] + "\n" + line);
        } catch (NumberFormatException e) {
            System.out.println(line + "\nError: Invalid task number. Please enter a valid number.\n" + line);
        } catch (NotChatGPTExceptions e) {
            System.out.println(line + "\nError: " + e.getMessage() + "\n" + line);
        }
    }

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
            System.out.println(line + "\nAight! I've marked this task as not done yet.");
            System.out.println(NotChatGPT.taskList.getTasks()[taskNumber - 1] + "\n" + line);
        } catch (NumberFormatException e) {
            System.out.println(line + "\nError: Invalid task number. Please enter a valid number.\n" + line);
        } catch (NotChatGPTExceptions e) {
            System.out.println(line + "\nError: " + e.getMessage() + "\n" + line);
        }
    }

    public static void handleTodo(String[] userInput) {
        try {
            if (userInput.length < 2) {
                throw new NotChatGPTExceptions("Please specify the task description!! (cannot be empty lah)");
            }
            NotChatGPT.taskList.addTodo(String.join(" ", Arrays.copyOfRange(userInput, 1, userInput.length)));
            System.out.println(line + "\nGot it. I've added this task:");
            System.out.println(NotChatGPT.taskList.getTasks()[NotChatGPT.taskList.getTaskCount() - 1] + "\n" + line);
        } catch (NotChatGPTExceptions e) {
            System.out.println(line + "\nError: " + e.getMessage() + "\n" + line);
        }
    }

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
            System.out.println(line + "\nGot it. I've added this task:");
            System.out.println(NotChatGPT.taskList.getTasks()[NotChatGPT.taskList.getTaskCount() - 1] + "\n" + line);
        } catch (NotChatGPTExceptions e) {
            System.out.println(line + "\nError: " + e.getMessage() + "\n" + line);
        }
    }

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
            System.out.println(line + "\nGot it. I've added this task:");
            System.out.println(NotChatGPT.taskList.getTasks()[NotChatGPT.taskList.getTaskCount() - 1] + "\n"+ line);
        } catch (NotChatGPTExceptions e) {
            System.out.println(line + "\nError: " + e.getMessage() + "\n" + line);
        }
    }

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
            default:
                System.out.println(line + "\nI'm sorry, but I don't know what that means :-(\n" + line);
                break;
        }
    }
}



