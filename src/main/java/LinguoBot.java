import java.util.Scanner;
public class LinguoBot {
    private static void printTaskList(Task[] taskList, int itemCount) {
        System.out.println("-------------------------");
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < itemCount; i++) {
            Task task = taskList[i];
            String taskType = task instanceof Todo ? "T" :
                    task instanceof Deadline ? "D" : "E";
            String taskStatus = task.getStatusIcon();
            String taskDescription = task.getDescription();
            String taskDetails = "";

            if (task instanceof Deadline) {
                taskDetails = "(by:" + ((Deadline)task).getDate() + ")";
            } else if (task instanceof Event) {
                taskDetails = "(from:" + ((Event)taskList[i]).getFrom() + "to:" + ((Event)taskList[i]).getTo() + ")";
//                        Event eventTask = (Event)taskList[i];
//                        taskDetails = "(from:" + eventTask.getFrom() + "to:" + eventTask.getTo() + ")";
            }
            System.out.println((i + 1) + ".[" + taskType + "][" + taskStatus + "]" + taskDescription + " " + taskDetails);
        }
        System.out.println("-------------------------");
    }

    private static void markTaskAsDone(Task[] taskList, int index) {
        if (index >= 0 && index < taskList.length && taskList[index] != null) {
            taskList[index].markAsDone();
            System.out.println("-------------------------");
            System.out.println("Nice! I've marked this task as done:");
            System.out.println("[" + taskList[index].getStatusIcon() + "] " + taskList[index].getDescription());
            System.out.println("-------------------------");
        } else {
            System.out.println("-------------------------");
            System.out.println("Invalid task index.");
            System.out.println("-------------------------");
        }
    }

    private static void markTaskAsUndone(Task[] taskList, int index) {
        if (index >= 0 && index < taskList.length && taskList[index] != null) {
            taskList[index].markAsUndone();
            System.out.println("-------------------------");
            System.out.println("OK, I've marked this task as not done yet:");
            System.out.println("[" + taskList[index].getStatusIcon() + "] " + taskList[index].getDescription());
            System.out.println("-------------------------");
        } else {
            System.out.println("-------------------------");
            System.out.println("Invalid task index.");
            System.out.println("-------------------------");
        }
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
//            Task newTask = new Task(line);

            if (line.equals("list")) {
                printTaskList(taskList, itemCount);
            } else if (line.startsWith("mark")) {
                int taskIndex = Integer.parseInt(line.substring(5)) - 1;
                markTaskAsDone(taskList, taskIndex);
            } else if (line.startsWith("unmark")) {
                int taskIndex = Integer.parseInt(line.substring(7)) - 1;
                markTaskAsUndone(taskList, taskIndex);
            } else if (line.contains("bye")) {
                System.out.println("-------------------------");
                System.out.println("Bye. Hope to see you again soon!");
                System.out.println("-------------------------");
                break;
            } else if (!line.contains("list") && !line.startsWith("mark") && !line.startsWith("unmark") && !line.contains("bye")) {
                if (line.startsWith("todo")) {
                    taskList[itemCount] = new Todo(line.substring(4));
                } else if (line.startsWith("deadline")) {
                    int index_by = line.indexOf("by");
                    if (index_by == -1) {
                        // Handle the case when "by" is missing
                        System.out.println("Invalid input. Please include 'by' for deadlines.");
                    }
                    taskList[itemCount] = new Deadline(line.substring(8, index_by - 1), line.substring(index_by + 2));
                } else if (line.startsWith("event")) {
                    int index_from = line.indexOf("from");
                    int index_to = line.indexOf("to", index_from);
                    if (index_from == -1 || index_to == -1) {
                        // Handle the case when "from" or "to" is missing
                        System.out.println("Invalid input. Please include both 'from' and 'to' for events.");
                    }
                    taskList[itemCount] = new Event(line.substring(5, index_from - 1), line.substring(index_from + 4, index_to), line.substring(index_to + 2));
                }
                System.out.println("-------------------------");
                System.out.println(taskList[itemCount]);
                System.out.println("-------------------------");
                itemCount++;
            }
        }
    }
}