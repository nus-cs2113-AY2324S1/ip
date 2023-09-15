import task.Deadline;
import task.Event;
import task.Task;
import task.Todo;
import exceptionhandler.KenergeticBotException;

import java.util.Scanner;
import java.util.ArrayList;

import static command.booleanChecks.*;
import static command.commandList.*;
import static command.messages.*;
/*import static command.messages.printLine;
import static command.messages.printGreetingMessage;
import static command.messages.printExitMessage;*/


public class KenergeticBot {

    public static void main(String[] args) {
        printGreetingMessage();
        ArrayList<Task> taskList = new ArrayList<Task>();
        botDialogue(taskList);
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
                System.out.println(e.getMessage()); // unable to print sad face ˙◠˙
            }
        }
        botDialogue(taskList);
    }

    //Creates a "task.Todo" object and adds to the taskList
    public static void addTodo(ArrayList<Task> taskList, String item) throws KenergeticBotException {
        String formattedString = item.replace("todo", "").trim();
        if (formattedString.isEmpty()) {
            throw new KenergeticBotException(KenergeticBotException.TODO_MISSING_DESCRIPTION);
        }
        String taskType = "[T]";
        Task newTask = new Todo(formattedString, taskType);
        taskList.add(newTask);
        System.out.println("     Got it. I've added this task:");
        System.out.printf("       %s\n", newTask);
        System.out.printf("     Now you have %d tasks in the list.\n", taskList.size());
    }

    //Creates a "task.Deadline" object and adds to the taskList
    public static void addDeadline(ArrayList<Task> taskList, String item) throws KenergeticBotException {
        String[] formattedString = item.replace("deadline", "").trim().split("/");
        if (formattedString.length < 1) {
            throw new KenergeticBotException(KenergeticBotException.DEADLINE_MISSING_DESCRIPTION);
        } else if (formattedString.length < 2 || formattedString[1].replace("by", "").isEmpty()) { //|| formattedString[1].replace("by ", "")
            throw new KenergeticBotException(KenergeticBotException.DEADLINE_MISSING_DATE);
        }
        String taskType = "[D]";
        String deadlineDate = "(" + formattedString[1].replace("by", "by:") + ")";
        Task newTask = new Deadline(formattedString[0], taskType, deadlineDate);
        taskList.add(newTask);
        System.out.println("     Got it. I've added this task:");
        System.out.printf("       %s\n", newTask);
        System.out.printf("     Now you have %d tasks in the list.\n", taskList.size());
    }

    //Creates a "task.Event" object and adds to the taskList
    public static void addEvent(ArrayList<Task> taskList, String item) throws KenergeticBotException {
        String[] formattedString = item.replace("event", "").split("/");
        if (item.replace("event", "").isEmpty()) {
            throw new KenergeticBotException(KenergeticBotException.EVENT_MISSING_DESCRIPTION);
        }
        if (formattedString.length < 1) {
            throw new KenergeticBotException(KenergeticBotException.EVENT_MISSING_DESCRIPTION);
        } else if (formattedString.length < 2 || formattedString[1].replace("from", "").isEmpty()) {
            throw new KenergeticBotException(KenergeticBotException.EVENT_MISSING_START);
        } else if (formattedString.length < 3 || formattedString[2].replace("to", "").isEmpty()) {
            throw new KenergeticBotException(KenergeticBotException.EVENT_MISSING_END);
        }
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
                System.out.println(e.getMessage());
            }
        } else if (checkTextForDeadline(item)) {
            try {
                addDeadline(taskList, item);
            } catch (KenergeticBotException e) { //throws exception when the deadline command is not followed with a description
                System.out.println(e.getMessage());
            }
        } else if (checkTextForEvent(item)) {
            try {
                addEvent(taskList, item);;
            } catch (KenergeticBotException e) { //throws exception when the event command is not followed with a description
                System.out.println(e.getMessage());
            }
        } else {
            throw new KenergeticBotException(KenergeticBotException.INVALID_COMMAND);
        }
        printLine();
    }
}
