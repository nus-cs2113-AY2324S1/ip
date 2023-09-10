import java.util.Scanner;

public class Duke {

    public static int taskCount = 0;
    private static Task[] list = new Task[100];

    public static void main(String[] args) {
        printWelcomeMessage();
        Scanner keyboard = new Scanner(System.in);

        while (true) {
            String command = keyboard.nextLine();
            String[] commendSplits = command.split(" ");
            int spaceIndex, taskNo;

            switch (commendSplits[0]) {
            case "bye":
                printByeMessage();
                keyboard.close();
                return;

            case "list":
                printList(taskCount, list);
                break;

            case "mark":
                // command format e.g. mark 1
                taskNo = Integer.parseInt(commendSplits[1]);   //assume  saved in part[1], need further improve
                list[taskNo - 1].setDone(taskNo, taskCount, list);
                break;

            case "unmark":
                taskNo = Integer.parseInt(commendSplits[1]);   //assume  saved in part[1], need further improve
                list[taskNo - 1].setNotDone(taskNo, taskCount, list);
                break;

            case "todo":
                // command format e.g. todo borrow book
                String[] todoSplit = command.split(" ", 2);
                list[taskCount] = new Todo(todoSplit[1]);
                createTaskSuccessMsg();
                break;

            case "deadline":
                // command e.g. deadline return book /by Sunday
                String[] ddlSplit = command.split("/");
                spaceIndex = ddlSplit[0].indexOf(" ");
                String ddlTask = ddlSplit[0].substring(spaceIndex + 1).trim();
                list[taskCount] = new Deadline(ddlTask, ddlSplit[1].substring(3));
                createTaskSuccessMsg();
                break;

            case "event":
                //command e.g. event project meeting /from Mon 2pm /to 4pm
                String[] eventSplit = command.split("/");
                spaceIndex = eventSplit[0].indexOf(" ");
                String eventTask = eventSplit[0].substring(spaceIndex + 1).trim();
                String start = eventSplit[1].trim().substring(5); // Remove "/from " prefix
                String end = eventSplit[2].trim().substring(3); // Remove "/to " prefix
                list[taskCount] = new Event(eventTask, start, end);
                createTaskSuccessMsg();
                break;

            default:
                System.out.println("Sorry. This is not expected.");
                System.out.println("Please type in \"help\" to look for command format");
            }
        }

    }

    private static void createTaskSuccessMsg() {
        System.out.println("Got it. I've added this task:");
        System.out.println(list[taskCount]);
        taskCount++;
        System.out.println("Now you have " + taskCount + " tasks in the list.");
    }

    private static void printByeMessage() {
        System.out.println("Bye. Hope to see you again soon!");
    }

    private static void printWelcomeMessage() {
        System.out.println("Hello! I'm Oriento.");
        System.out.println("What can I do for you?");
    }

    private static void printList(int count, Task[] list) {
        for (int i = 0; i < count; i++) {
            //example 1.[T][X] read book
            System.out.println((i + 1) + "." + list[i]);
        }
    }

}
