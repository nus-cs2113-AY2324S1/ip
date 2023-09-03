import java.util.Scanner;
public class LinguoBot {
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
            Task newTask = new Task(line);

            if (line.equals("list")) {
                System.out.println("-------------------------");
                System.out.println("Here are the tasks in your list:");
                for (int i = 0; i < itemCount; i++) {
                    String taskType = taskList[i] instanceof Todo ? "T" :
                            taskList[i] instanceof Deadline ? "D" : "E";
                    String taskStatus = taskList[i].getStatusIcon();
                    String taskDescription = taskList[i].getDescription();
                    String taskDetails = "";

                    if (taskList[i] instanceof Deadline) {
                        taskDetails = "(by:" + ((Deadline) taskList[i]).getDate() + ")";
                    } else if (taskList[i] instanceof Event) {
                        taskDetails = "(from:" + ((Event) taskList[i]).getFrom() + "to:" + ((Event) taskList[i]).getTo() + ")";
//                        Event eventTask = (Event) taskList[i];
//                        taskDetails = "(from:" + eventTask.getFrom() + "to:" + eventTask.getTo() + ")";
                    }

                    System.out.println((i + 1) + ".[" + taskType + "][" + taskStatus + "]" + taskDescription + " " + taskDetails);
                }
                System.out.println("-------------------------");
            }
            if (line.startsWith("mark")) {
                int taskIndex = Integer.parseInt(line.substring(5)) - 1;
                if (taskIndex >= 0 && taskIndex < itemCount) {
                    taskList[taskIndex].markAsDone();
                    System.out.println("-------------------------");
                    System.out.println("Nice! I've marked this task as done:");
                    System.out.println("[" + taskList[taskIndex].getStatusIcon() + "] " + taskList[taskIndex].getDescription());
                    System.out.println("-------------------------");
                } else {
                    System.out.println("-------------------------");
                    System.out.println("Invalid task index.");
                    System.out.println("-------------------------");
                }
            }
            if (line.startsWith("unmark")) {
                int taskIndex = Integer.parseInt(line.substring(7)) - 1;
                if (taskIndex >= 0 && taskIndex < itemCount) {
                    taskList[taskIndex].markAsUndone();
                    System.out.println("-------------------------");
                    System.out.println("OK, I've marked this task as not done yet:");
                    System.out.println("[" + taskList[taskIndex].getStatusIcon() + "] " + taskList[taskIndex].getDescription());
                    System.out.println("-------------------------");
                } else {
                    System.out.println("-------------------------");
                    System.out.println("Invalid task index.");
                    System.out.println("-------------------------");
                }
            }
            if (line.contains("bye")) {
                System.out.println("-------------------------");
                System.out.println("Bye. Hope to see you again soon!");
                System.out.println("-------------------------");
                break;
            }
            else if (!line.contains("list") && !line.startsWith("mark") && !line.startsWith("unmark") && !line.contains("bye")) {
                if (line.startsWith("todo")) {
                    taskList[itemCount] = new Todo(line.substring(4));
                } else if (line.startsWith("deadline")) {
                    int index = line.indexOf("by");
                    taskList[itemCount] = new Deadline(line.substring(8,index-1), line.substring(index + 2));
                } else if (line.startsWith("event")) {
                    int index = line.indexOf("from");
                    int index_v2 = line.indexOf("to");
                    taskList[itemCount] = new Event(line.substring(5,index-1),line.substring(index + 4, index_v2),line.substring(index_v2 + 2));
                }
                System.out.println("-------------------------");
                System.out.println(taskList[itemCount]);
                System.out.println("-------------------------");
                itemCount++;
            }
        }
    }
}