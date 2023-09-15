import java.util.Arrays;
import java.util.Scanner;
import duke.Task;
import duke.Deadline;
import duke.Event;
import duke.ToDo;
import duke.DukeException;

public class Duke {
    public static void printGreeting() {
        String logo = "______       _     _\n"
                + "| ___ \\     | |   | |\n"
                + "| |_/ / ___ | |__ | |__  _   _\n"
                + "| ___ \\/ _ \\| '_ \\| '_ \\| | | |\n"
                + "| |_/ | (_) | |_) | |_) | |_| |\n"
                + "\\____/ \\___/|_.__/|_.__/ \\__, |\n"
                + "                          __/ |\n"
                + "                         |___/";
        System.out.println("    Hello from\n" + logo);
        printLine();
        System.out.println("    Hello! I'm Bobby");
        System.out.println("    What can I do for you?");
        printLine();
    }

    public static void exitChatbot() {
        printLine();
        System.out.println("    Bye. Hope to see you again soon!");
        printLine();
    }

    public static void printLine() {
        System.out.println("    ____________________________________________________________");
    }

    public static void listItems(Task[] taskList, int taskCount) {
        printLine();
        for (int i = 0; i < taskCount; i++) {
            int indexNo = i + 1;
            switch (taskList[i].taskType){
            case "todo":
                System.out.println("    " + indexNo + "." + taskList[i].getTaskTypeIcon() + taskList[i].getStatusIcon() + " " + taskList[i].description);
                break;
            case "deadline":
                System.out.println("    " + indexNo + "." + taskList[i].getTaskTypeIcon() + taskList[i].getStatusIcon() + " " + taskList[i].description
                        + "(by:"+taskList[i].deadline+")");
                break;
            case "event":
                System.out.println("    " + indexNo + "." + taskList[i].getTaskTypeIcon() + taskList[i].getStatusIcon() + " " + taskList[i].description
                        + " (from: " + taskList[i].from + " to: " + taskList[i].to+")");
                break;
            }
        }
        printLine();
    }

    public static void markItem(Task[] taskList, int taskCount, boolean isMark) {
        printLine();
        if (isMark) {
            taskList[taskCount].isDone = true;
            System.out.println("    Nice! I've marked this task as done:");
            System.out.println("       " + taskList[taskCount].getStatusIcon() + " " + taskList[taskCount].description);
        } else {
            taskList[taskCount].isDone = false;
            System.out.println("    OK, I've marked this task as not done yet:");
            System.out.println("       " + taskList[taskCount].getStatusIcon() + " " + taskList[taskCount].description);
        }
        printLine();
    }

    public static void addTaskCallback(Task[] taskList, int taskCount) {
        printLine();
        System.out.println("    Got it. I've added this task:");
        switch (taskList[taskCount].taskType) {
        case "todo":
            System.out.print("      " + taskList[taskCount].getTaskTypeIcon() + taskList[taskCount].getStatusIcon() + " " + taskList[taskCount].description);
            break;
        case "deadline":
            System.out.print("      " + taskList[taskCount].getTaskTypeIcon() + taskList[taskCount].getStatusIcon() + " " + taskList[taskCount].description
            + "(by:"+taskList[taskCount].deadline+")");
            break;
        case "event":
            System.out.print("      " + taskList[taskCount].getTaskTypeIcon() + taskList[taskCount].getStatusIcon() + " " + taskList[taskCount].description
            + " (from: " + taskList[taskCount].from + " to: " + taskList[taskCount].to+")");
            break;
        }

        System.out.println();
        System.out.println("    Now you have " + (taskCount + 1) + " tasks in the list.");
        printLine();
    }

    public static void addTasks(Task[] taskList, String[] userInput, int taskCount){

        switch (userInput[0]) {
        case "todo":
            String todoDescription = String.join(" ", Arrays.copyOfRange(userInput, 1, userInput.length));
            Task todo = new ToDo(todoDescription);
            taskList[taskCount] = todo;
            addTaskCallback(taskList, taskCount);
            break;
        case "deadline":
            int byIndex = Arrays.asList(userInput).indexOf("/by");
            String deadlineDescription = String.join(" ", Arrays.copyOfRange(userInput, 1, byIndex)) + " ";
            String deadlineTime = String.join(" ", Arrays.copyOfRange(userInput, byIndex + 1, userInput.length));
            Deadline deadline = new Deadline(deadlineDescription, deadlineTime);
            taskList[taskCount] = deadline;
            addTaskCallback(taskList, taskCount);
            break;
        case "event":
            int fromIndex = Arrays.asList(userInput).indexOf("/from");
            int toIndex = Arrays.asList(userInput).indexOf("/to");
            String eventDescription = String.join(" ", Arrays.copyOfRange(userInput, 1, fromIndex)) + " ";
            String from = String.join(" ", Arrays.copyOfRange(userInput, fromIndex + 1, toIndex));
            String to = String.join(" ", Arrays.copyOfRange(userInput, toIndex + 1, userInput.length));
            Task event = new Event(eventDescription, from, to);
            taskList[taskCount] = event;
            addTaskCallback(taskList, taskCount);
            break;
        default:
        }

    }

    public void getInput(){
        Scanner scanner = new Scanner(System.in);
        int taskCount = 0;

        while (true) {
            String input = scanner.nextLine();
            String[] userInput = input.trim().split("\\s+");

            DukeException exceptionHandler = new DukeException(userInput);
            exceptionHandler.checkInput();

            if (!exceptionHandler.exception) {
                switch (userInput[0]) {
                case "mark":
                    markItem(Integer.parseInt(userInput[1]) - 1, true);
                    break;
                case "unmark":
                    markItem(Integer.parseInt(userInput[1]) - 1, false);
                    break;
                case "bye":
                    exitChatbot();
                    System.exit(0);
                    break;
                case "list":
                    listItems(taskCount);
                    break;
                default:
                    addTasks(userInput, taskCount);
                    taskCount++;
                    break;
                }
            }
        }
    }

    public static void main(String[] args) {
        Duke duke = new Duke();
        duke.printGreeting();
        duke.getInput();
    }
}