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

    public static void addTasks(Task[] taskList, String input, String[] userInput, int taskCount){
        switch (userInput[0]) {
        case "todo":
            Task todo = new ToDo(input.substring(5));
            taskList[taskCount] = todo;
            addTaskCallback(taskList, taskCount);
            break;
        case "deadline":
            int slashIndex = input.indexOf('/');
            Deadline deadline = new Deadline(input.substring(9, slashIndex), input.substring(slashIndex).split("/by")[1]);
            taskList[taskCount] = deadline;
            addTaskCallback(taskList, taskCount);
            break;
        case "event":
            String[] parts = input.split("event | /from | /to ");
            Task event = new Event(parts[1], parts[2], parts[3]);
            taskList[taskCount] = event;
            addTaskCallback(taskList, taskCount);
        }

    }

    public static void main(String[] args) {
        printGreeting();

        Scanner scanner = new Scanner(System.in);
        Task[] taskList = new Task[100];
        int taskCount = 0;

        while (true) {
            String input = scanner.nextLine();
            if (input.startsWith("mark ")) {
                int indexPosition = Integer.parseInt(input.substring(5));
                markItem(taskList, indexPosition - 1, true);
            } else if (input.startsWith("unmark ")) {
                int indexPosition = Integer.parseInt(input.substring(7));
                markItem(taskList, indexPosition - 1, false);
            } else {
                if (input.equalsIgnoreCase("bye")) {
                    exitChatbot();
                    break;
                } else if (input.equalsIgnoreCase("list")) {
                    listItems(taskList, taskCount);
                } else {
                    String[] userInput = input.trim().split("\\s+");
                    addTasks(taskList, input, userInput, taskCount);
                    taskCount++;
                }
            }
        }
    }
}
