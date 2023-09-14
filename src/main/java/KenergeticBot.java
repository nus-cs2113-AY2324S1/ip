import java.util.Scanner;
import java.util.ArrayList;

public class KenergeticBot {

    public static void printGreetingMessage() {
        printLine();
        System.out.println("     Hello! I'm KenergeticBot\n" + "     What can I do for you?");
        printLine();
    }

    public static void printExitMessage() {
        printLine();
        System.out.println("     Bye. Hope to see you again soon!");
        printLine();
    }

    public static void printLine() {
        System.out.println("    ____________________________________________________________");
    }

    //Returns True if the text contains "mark", False if not
    public static boolean checkTextForMark(String item) {
        if (item.length() > 4 && item.startsWith("mark")) {
            return true;
        } else {
            return false;
        }
    }

    //Returns True if the text contains "unmark", False if not
    public static boolean checkTextForUnmark(String item) {
        if (item.length() > 6 && item.startsWith("unmark")) {
            return true;
        } else {
            return false;
        }
    }

    //Returns True if the text contains "todo", False if not
    public static boolean checkTextForTodo(String item) {
        if (item.length() > 3 && item.startsWith("todo")) {
            return true;
        } else {
            return false;
        }
    }

    //Returns True if the text contains "deadline", False if not
    public static boolean checkTextForDeadline(String item) {
        if (item.length() > 7 && item.startsWith("deadline")) {
            return true;
        } else {
            return false;
        }
    }

    //Returns True if the text contains "event", False if not
    public static boolean checkTextForEvent(String item) {
        if (item.length() > 4 && item.startsWith("event")) {
            return true;
        } else {
            return false;
        }
    }

    //Main logic for the bot's dialogue
    public static void botDialogue(ArrayList<Task> taskList) {
        Scanner input = new Scanner(System.in);
        String item = input.nextLine();
        if (item.equals("bye")) {
            printExitMessage();
            return;
        } else if (item.equals("list")) {
            list(taskList);
        } else if (checkTextForMark(item)) {
            String[] splitItem = item.split(" ");
            int listIndex = Integer.parseInt(splitItem[1]);
            mark(taskList, listIndex);
        } else if (checkTextForUnmark(item)) {
            String[] splitItem = item.split(" ");
            int listIndex = Integer.parseInt(splitItem[1]);
            unmark(taskList, listIndex);
        } else {
            try {
                add(taskList, item);
            } catch (KenergeticBotException e) { //exception thrown when user inputs command outside of the usual commands
                System.out.println(KenergeticBotException.INVALID_COMMAND); // unable to print sad face ˙◠˙
            }
        }
        botDialogue(taskList);
    }

    //Creates a "Todo" object and adds to the taskList
    public static void addTodo(ArrayList<Task> taskList, String item) throws KenergeticBotException {
        String formattedString = item.replace("todo", "").trim();
        if (formattedString.isEmpty()) {
            throw new KenergeticBotException();
        }
        String taskType = "[T]";
        Task newTask = new Todo(formattedString, taskType);
        taskList.add(newTask);
        System.out.println("     Got it. I've added this task:");
        System.out.printf("       %s\n", newTask);
        System.out.printf("     Now you have %d tasks in the list.\n", taskList.size());
    }

    //Creates a "Deadline" object and adds to the taskList
    public static void addDeadline(ArrayList<Task> taskList, String item) {
        String formattedString[] = item.replace("deadline", "").trim().split("/");
        String taskType = "[D]";
        String deadlineDate = "(" + formattedString[1].replace("by", "by:") + ")";
        Task newTask = new Deadline(formattedString[0], taskType, deadlineDate);
        taskList.add(newTask);
        System.out.println("     Got it. I've added this task:");
        System.out.printf("       %s\n", newTask);
        System.out.printf("     Now you have %d tasks in the list.\n", taskList.size());
    }

    //Creates a "Event" object and adds to the taskList
    public static void addEvent(ArrayList<Task> taskList, String item) {
        String formattedString[] = item.replace("event", "").split("/");
        String taskType = "[E]";
        String eventFrom = formattedString[1].replace("from", "from:");
        String eventTo = formattedString[2].replace("to", "to:");
        String dateTime = "(" + eventFrom + eventTo + ")";
        Task newTask = new Event(formattedString[0], taskType, dateTime);
        taskList.add(newTask);
        System.out.println("     Got it. I've added this task:");
        System.out.printf("       %s\n", newTask);
        System.out.printf("     Now you have %d tasks in the list.\n", taskList.size());
    }

    //Controls the logic for adding items to the list
    public static void add(ArrayList<Task> taskList, String item) throws KenergeticBotException {
        printLine();
        if (checkTextForTodo(item)) {
            try {
                addTodo(taskList, item);
            } catch (KenergeticBotException e) { //throws exception when the todo command is not followed with a description
                System.out.println(KenergeticBotException.MISSING_DESCRIPTION);
            }
        } else if (checkTextForDeadline(item)) {
            addDeadline(taskList, item);
        } else if (checkTextForEvent(item)) {
            addEvent(taskList, item);
        } else {
            throw new KenergeticBotException();
        }
        printLine();
    }

    public static void list(ArrayList<Task> taskList) {
        printLine();
        System.out.println("     Here are the tasks in your list:");
        for (int i = 0; i < taskList.size(); i++) {
            System.out.printf("     %d.%s\n", i+1, taskList.get(i));
        }
        printLine();
    }

    public static void mark(ArrayList<Task> taskList, int listIndex) {
        printLine();
        System.out.println("     Nice! I've marked this task as done:");
        taskList.get(listIndex - 1).mark();;
        System.out.printf("       %s\n", taskList.get(listIndex - 1));
        printLine();
    }

    public static void unmark(ArrayList<Task> taskList, int listIndex) {
        printLine();
        System.out.println("     OK, I've marked this task as not done yet:");
        taskList.get(listIndex - 1).unmark();
        System.out.printf("       %s\n", taskList.get(listIndex - 1));
        printLine();
    }

    public static void main(String[] args) {
        printGreetingMessage();
        ArrayList<Task> taskList = new ArrayList<Task>();
        botDialogue(taskList);
    }
}
