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
            case "Bye":
            case "bye":
                printByeMessage();
                keyboard.close();
                return;

            case "List":
            case "list":
                printList(taskCount, list);
                break;

            case "Mark":
            case "mark":
                // command format e.g. mark 1
                taskNo = Integer.parseInt(commendSplits[1]);
                list[taskNo - 1].setDone(taskNo, taskCount, list);
                break;

            case "Unmark":
            case "unmark":
                taskNo = Integer.parseInt(commendSplits[1]);
                list[taskNo - 1].setNotDone(taskNo, taskCount, list);
                break;

            case "Todo":
            case "todo":
                // command format e.g. todo borrow book
                list[taskCount] = Todo.newTodoTask(command);
                createTaskSuccessMsg();
                break;

            case "Deadline":
            case "deadline":
                // command e.g. deadline return book /by Sunday
                list[taskCount] = Deadline.newDdl(command);
                createTaskSuccessMsg();
                break;

            case "Event":
            case "event":
                //command e.g. event project meeting /from Mon 2pm /to 4pm
                list[taskCount] = Event.newEventTask(command);
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
