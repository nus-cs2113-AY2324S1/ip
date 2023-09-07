import java.util.Arrays;
import java.util.Scanner;
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

    public static void echo(String input) {
        printLine();
        System.out.println("    " + input);
        printLine();
    }

    public static void listItems(Task[] itemList, int itemCount) {
        printLine();
        for (int i = 0; i < itemCount; i++) {
            int indexNo = i + 1;
            System.out.println("    " + indexNo + "." + itemList[i].getTaskTypeIcon() + itemList[i].getStatusIcon() + " " + itemList[i].description);
        }
        printLine();
    }

    public static void markItem(Task[] itemList, int itemCount, boolean isMark) {
        printLine();
        if (isMark) {
            itemList[itemCount].isDone = true;
            System.out.println("    Nice! I've marked this task as done:");
            System.out.println("       " + itemList[itemCount].getStatusIcon() + " " + itemList[itemCount].description);
        } else {
            itemList[itemCount].isDone = false;
            System.out.println("    OK, I've marked this task as not done yet:");
            System.out.println("       " + itemList[itemCount].getStatusIcon() + " " + itemList[itemCount].description);
        }
        printLine();
    }

    public static void addTaskCallback(Task[] itemList, int itemCount) {
        printLine();
        System.out.println("    Got it. I've added this task:");
        switch (itemList[itemCount].taskType) {
        case "todo":
            System.out.print("      " + itemList[itemCount].getTaskTypeIcon() + itemList[itemCount].getStatusIcon() + " " + itemList[itemCount].description);
            break;
        case "deadline":
            System.out.print("      " + itemList[itemCount].getTaskTypeIcon() + itemList[itemCount].getStatusIcon() + " " + itemList[itemCount].description
            + "(by:"+itemList[itemCount].deadline+")");
            break;
        case "event":
            System.out.print("      " + itemList[itemCount].getTaskTypeIcon() + itemList[itemCount].getStatusIcon() + " " + itemList[itemCount].description
            + " (from: " + itemList[itemCount].from + " to: " + itemList[itemCount].to+")");
            break;
        }

        System.out.println();
        System.out.println("    Now you have " + (itemCount + 1) + " tasks in the list.");
        printLine();
    }

    public static void main(String[] args) {
        printGreeting();

        Scanner scanner = new Scanner(System.in);
        Task[] itemList = new Task[100];
        int itemCount = 0;

        while (true) {
            String input = scanner.nextLine();
            if (input.startsWith("mark ")) {
                int indexPosition = Integer.parseInt(input.substring(5));
                markItem(itemList, indexPosition - 1, true);
            } else if (input.startsWith("unmark ")) {
                int indexPosition = Integer.parseInt(input.substring(7));
                markItem(itemList, indexPosition - 1, false);
            } else {
                if (input.equalsIgnoreCase("bye")) {
                    exitChatbot();
                    break;
                } else if (input.equalsIgnoreCase("list")) {
                    listItems(itemList, itemCount);
                } else {
                    String[] userInput = input.trim().split("\\s+");
                    String taskType = userInput[0];

                    switch (taskType) {
                    case "todo":
                        Task todo = new ToDo(input.substring(5));
                        itemList[itemCount] = todo;
                        addTaskCallback(itemList, itemCount);
                        itemCount++;
                        break;
                    case "deadline":
                        int slashIndex = input.indexOf('/');
                        task.description = input.substring(9, slashIndex);
                        task.taskType = taskType;
                        task.deadline = input.substring(slashIndex).split("/by")[1];
                        itemList[itemCount] = task;
                        addTaskCallback(itemList, itemCount);
                        itemCount++;
                        break;
                    case "event":
                        String[] parts = input.split("event | /from | /to ");
                        task.description = parts[1];
                        task.taskType = taskType;
                        task.from = parts[2];
                        task.to = parts[3];
                        itemList[itemCount] = task;
                        addTaskCallback(itemList, itemCount);
                        itemCount++;
                    }
                }
            }
        }
    }
}
//if (input.startsWith("todo"))