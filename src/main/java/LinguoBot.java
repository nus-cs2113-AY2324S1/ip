import java.util.Scanner;
public class LinguoBot {

    private static void printLine() {
        System.out.println("-------------------------");
    }
    private static void printTaskList(Task[] taskList, int itemCount) {
        printLine();
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < itemCount; i++) {
            Task task = taskList[i];
            String taskType = task instanceof Todo ? "T" :
                    task instanceof Deadline ? "D" : "E";
            String taskStatus = task.getStatusIcon();
            String taskDetails = "";
            if (task instanceof Deadline) {
                taskDetails = "(by:" + ((Deadline)task).getDate() + ")";
            } else if (task instanceof Event) {
                taskDetails = "(from:" + ((Event)task).getFrom() + "to:" + ((Event)task).getTo() + ")";
            }
            System.out.println((i + 1) + ".[" + taskType + "][" + taskStatus + "]" + task.description + " " + taskDetails);
        }
        printLine();
    }

    private static void markTaskAsDone(Task[] taskList, int index) {
        if (index >= 0 && index < taskList.length && taskList[index] != null) {
            taskList[index].markAsDone();
            printLine();
            System.out.println("Nice! I've marked this task as done:");
            System.out.println("[" + taskList[index].getStatusIcon() + "] " + taskList[index].description);
            printLine();
        } else {
            printLine();
            System.out.println("Invalid task index.");
            printLine();
        }
    }

    private static void markTaskAsUndone(Task[] taskList, int index) {
        if (index >= 0 && index < taskList.length && taskList[index] != null) {
            taskList[index].markAsUndone();
            printLine();
            System.out.println("OK, I've marked this task as not done yet:");
            System.out.println("[" + taskList[index].getStatusIcon() + "] " + taskList[index].description);
            printLine();
        } else {
            printLine();
            System.out.println("Invalid task index.");
            printLine();
        }
    }

    public static void printTask(String line, Task[] taskList, int itemCount) {
        if (line.startsWith("todo")) {
            taskList[itemCount] = new Todo(line.substring(4));
        } else if (line.startsWith("deadline")) {
            int indexBy = line.indexOf("by");
            if (indexBy == -1) {
                // Handle the case when "by" is missing
                System.out.println("Invalid input. Please include 'by' for deadlines.");
            }
            taskList[itemCount] = new Deadline(line.substring(8, indexBy - 1), line.substring(indexBy + 2));
        } else if (line.startsWith("event")) {
            int indexFrom = line.indexOf("from");
            int indexTo = line.indexOf("to", indexFrom);
            if (indexFrom == -1 || indexTo == -1) {
                // Handle the case when "from" or "to" is missing
                System.out.println("Invalid input. Please include both 'from' and 'to' for events.");
            }
            taskList[itemCount] = new Event(line.substring(5, indexFrom - 1), line.substring(indexFrom + 4, indexTo), line.substring(indexTo + 2));
        }
        printLine();
        System.out.println(taskList[itemCount]);
        printLine();
    }

    public static void main(String[] args) {
        String logo = " \n" +
                "                                       \n" +
                " __    _                 _____     _   \n" +
                "|  |  |_|___ ___ _ _ ___| __  |___| |_ \n" +
                "|  |__| |   | . | | | . | __ -| . |  _|\n" +
                "|_____|_|_|_|_  |___|___|_____|___|_|  \n" +
                "            |___|                      \n";

        System.out.println("Hello I'm " + logo);
        System.out.println("What can I do for you?");

        Scanner in = new Scanner(System.in);

        Task[] taskList = new Task[100];
        int itemCount = 0;

        while (true) {
            String line = in.nextLine();

            if (line.equals("list")) {
                printTaskList(taskList, itemCount);
            } else if (line.startsWith("mark")) {
                int MARK_START_INDEX = 5;
                int taskIndex = Integer.parseInt(line.substring(MARK_START_INDEX)) - 1;
                markTaskAsDone(taskList, taskIndex);
            } else if (line.startsWith("unmark")) {
                int UNMARK_START_INDEX = 7;
                int taskIndex = Integer.parseInt(line.substring(UNMARK_START_INDEX)) - 1;
                markTaskAsUndone(taskList, taskIndex);
            } else if (line.contains("bye")) {
                printLine();
                System.out.println("Bye. Hope to see you again soon!");
                printLine();
                break;
            } else {
                printTask(line, taskList, itemCount);
                itemCount ++;
            }
        }
    }
}