import java.util.Arrays;
import java.util.Scanner;
import java.util.ArrayList;

public class Duke {
    public static String CHATBOTNAME="Andrew Tate";
    public static ArrayList<Task> TASKS = new ArrayList<Task>();
    public static String LINE_DIVIDER = "____________________________________________________________";
    public static int FIRST_INDEX=0;
    public static int SECOND_INDEX=1;

    /* Pretty prints a remark after adding any tasks */
    public static void printRemark(Task task){
        System.out.println(LINE_DIVIDER);
        System.out.println("Got it. I've add this task:");
        System.out.println(task);
        System.out.println("Now you have " + TASKS.size() + " tasks in the list");
        System.out.println(LINE_DIVIDER);
    }

    public static void addTodoInList(String[] arguments) throws DukeException{
        if (arguments.length == 0){ // empty argument
            throw new DukeException("The description of a todo cannot be empty!");
        }
        String taskDescription = String.join(" ", arguments);
        ToDo newToDo = new ToDo(taskDescription);
        TASKS.add(newToDo);
        printRemark(newToDo);
    }

    public static void addDeadlineInList(String[] arguments) throws DukeException{
        try{
            String argumentsString = String.join(" ", arguments);
            String[] argumentList = argumentsString.split(" /by ");
            String deadlineDescription = argumentList[FIRST_INDEX];
            String deadlineEndTime = argumentList[SECOND_INDEX];
            Deadline newDeadline = new Deadline(deadlineDescription, deadlineEndTime);
            TASKS.add(newDeadline);
            printRemark(newDeadline);
        } catch(ArrayIndexOutOfBoundsException e){
            throw new DukeException("Insufficient arguments provided, try this (deadline submission /by date)");
        }

    }

    public static void addEventInList(String[] arguments) throws DukeException{
        try {
            String argumentsString = String.join(" ", arguments);
            String[] argumentList = argumentsString.split(" /from ");
            String eventDescription = argumentList[FIRST_INDEX];
            argumentList = argumentList[SECOND_INDEX].split(" /to ");
            String eventStartTime = argumentList[FIRST_INDEX];
            String eventEndTime = argumentList[SECOND_INDEX];
            Event newEvent = new Event(eventDescription, eventStartTime, eventEndTime);
            TASKS.add(newEvent);
            printRemark(newEvent);
        } catch(ArrayIndexOutOfBoundsException e){
            throw new DukeException("Insufficient arguments provided, try this (event tiktok hackathon /from date /to date)");
        }
    }

    /* Marks the task from {@code arguments} as complete */
    public static void markTaskComplete(String[] arguments) throws DukeException{
        try{
            int taskIndex = Integer.parseInt(arguments[FIRST_INDEX]);
            Task taskToMark = TASKS.get(taskIndex-1);
            taskToMark.markAsDone();
            System.out.println(LINE_DIVIDER);
            System.out.println("Marked this task as done:");
            System.out.println(taskToMark);
            System.out.println(LINE_DIVIDER);
        } catch(IndexOutOfBoundsException indexEx){
            throw new DukeException("Invalid index bro...");
        }
    }

    /* Marks the task from {@code arguments} as incomplete */
    public static void unmarkTaskIncomplete(String[] arguments) throws DukeException{
        try{
            int taskIndex = Integer.parseInt(arguments[FIRST_INDEX]);
            Task taskToUnmark = TASKS.get(taskIndex-1);
            taskToUnmark.markAsUndone();
            System.out.println(LINE_DIVIDER);
            System.out.println("Marked this task as undone:");
            System.out.println(taskToUnmark);
            System.out.println(LINE_DIVIDER);
        } catch(IndexOutOfBoundsException indexEx){
        throw new DukeException("Invalid index bro...");
    }
    }

    public static void printTaskList(){
        System.out.println(LINE_DIVIDER);
        for (int i = 0; i < TASKS.size(); i++) {
            Task task = TASKS.get(i);
            System.out.println(i+1 + "." + task.toString());
        }
        System.out.println(LINE_DIVIDER);
    }

    public static void handleCommandInLoop(String[] arguments, String actionCommand) throws DukeException, IndexOutOfBoundsException{
        switch (actionCommand) {
            case "todo":
                // passing subarray of arguments without the 1st, same for other commands as well
                addTodoInList(Arrays.copyOfRange(arguments, SECOND_INDEX, arguments.length));
                return;

            case "deadline":
                addDeadlineInList(Arrays.copyOfRange(arguments, SECOND_INDEX, arguments.length));
                return;

            case "event":
                addEventInList(Arrays.copyOfRange(arguments, SECOND_INDEX, arguments.length));
                return;

            case "list":
                printTaskList();
                return;

            case "mark":
                markTaskComplete(Arrays.copyOfRange(arguments, SECOND_INDEX, arguments.length));
                return;

            case "unmark":
                unmarkTaskIncomplete(Arrays.copyOfRange(arguments, SECOND_INDEX, arguments.length));
                return;

            default: // unknown command exception
                throw new DukeException("I don't know what that means...");
        }
    }

    public static void main(String[] args){
        System.out.println(LINE_DIVIDER);
        System.out.println("Hello! I'm the top G " + CHATBOTNAME);
        System.out.println("What can I do for you?");
        System.out.println(LINE_DIVIDER);
        Scanner myScanner = new Scanner(System.in);

        while (true) {
            System.out.print("> ");
            String commandGiven = myScanner.nextLine();
            String[] arguments = commandGiven.split("\\s+"); // split by space
            String actionCommand = arguments[FIRST_INDEX];
            actionCommand = actionCommand.toLowerCase(); // make sure all same case to account for typing error

            if (actionCommand.equals("bye")){
                break;
            }
            try {
                handleCommandInLoop(arguments, actionCommand);
            } catch (DukeException dukeEx) { // Invalid command/not supported
                System.out.println(dukeEx.toString());
            }
        }
        System.out.println(LINE_DIVIDER);
        System.out.println("Bye, hope to see you again soon!");
        System.out.println(LINE_DIVIDER);
    }
}


