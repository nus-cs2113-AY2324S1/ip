import java.util.Arrays;
import java.util.Scanner;
import java.util.ArrayList;

public class Duke {
    public static String CHATBOTNAME="Andrew Tate";
    public static ArrayList<Task> TASKS = new ArrayList<Task>();
    public static String LINE_DIVIDER = "____________________________________________________________";
    public static int FIRST_INDEX=0;
    public static int SECOND_INDEX=1;

    public static void printRemark(Task task){
        System.out.println(LINE_DIVIDER);
        System.out.println("Got it. I've add this task:");
        System.out.println(task);
        System.out.println("Now you have " + TASKS.size() + " tasks in the list");
        System.out.println(LINE_DIVIDER);
    }

    public static void handleTodo(String[] arguments){
        try{
            String taskDescription = String.join(" ", arguments);
            ToDo newToDo = new ToDo(taskDescription);
            TASKS.add(newToDo);
            printRemark(newToDo);
        }catch (Exception e){
            System.out.println(LINE_DIVIDER);
            System.out.println("Error with the arguments, please try again.");
            System.out.println(LINE_DIVIDER);
        }
    }

    public static void handleDeadline(String[] arguments){
        try{
            String argumentsString = String.join(" ", arguments);
            String[] argumentList = argumentsString.split(" /by ");
            String deadlineDescription = argumentList[FIRST_INDEX];
            String deadlineEndTime = argumentList[SECOND_INDEX];
            Deadline newDeadline = new Deadline(deadlineDescription, deadlineEndTime);
            TASKS.add(newDeadline);
            printRemark(newDeadline);

        } catch (Exception e){
            System.out.println(LINE_DIVIDER);
            System.out.println("Error with the arguments, please try again.");
            System.out.println(LINE_DIVIDER);
        }
    }

    public static void handleEvent(String[] arguments){
        try{
            String argumentsString = String.join(" ", arguments);
            String[] argumentList = argumentsString.split(" /from ");
            String eventDescription = argumentList[FIRST_INDEX];
            argumentList = argumentList[SECOND_INDEX].split(" /to ");
            String eventStartTime = argumentList[FIRST_INDEX];
            String eventEndTime = argumentList[SECOND_INDEX];
            Event newEvent = new Event(eventDescription, eventStartTime, eventEndTime);
            TASKS.add(newEvent);
            printRemark(newEvent);

        } catch (Exception e){
            System.out.println(LINE_DIVIDER);
            System.out.println("Error with the arguments, please try again.");
            System.out.println(LINE_DIVIDER);
        }
    }

    public static void markTask(String[] arguments){
        try{
            int taskIndex = Integer.parseInt(arguments[FIRST_INDEX]);
            Task taskToMark = TASKS.get(taskIndex-1);
            taskToMark.markAsDone();
            System.out.println(LINE_DIVIDER);
            System.out.println("Marked this task as done:");
            System.out.println(taskToMark);
            System.out.println(LINE_DIVIDER);

        }catch(Exception e){
            System.out.println(LINE_DIVIDER);
            System.out.println("Invalid index, or other error!");
            System.out.println(LINE_DIVIDER);
        }
    }

    public static void unmarkTask(String[] arguments){
        try{
            int taskIndex = Integer.parseInt(arguments[FIRST_INDEX]);
            Task taskToUnmark = TASKS.get(taskIndex-1);
            taskToUnmark.markAsUndone();
            System.out.println(LINE_DIVIDER);
            System.out.println("Marked this task as undone:");
            System.out.println(taskToUnmark);
            System.out.println(LINE_DIVIDER);

        }catch(Exception e){
            System.out.println(LINE_DIVIDER);
            System.out.println("Invalid index, or other error!");
            System.out.println(LINE_DIVIDER);
        }
    }

    public static void printList(){
        System.out.println(LINE_DIVIDER);
        for (int i = 0; i < TASKS.size(); i++) {
            Task task = TASKS.get(i);
            System.out.println(i+1 + "." + task.toString());
        }
        System.out.println(LINE_DIVIDER);
    }

    public static void main(String[] args){
        Scanner myScanner = new Scanner(System.in);
        System.out.println(LINE_DIVIDER);
        System.out.println("Hello! I'm " + CHATBOTNAME);
        System.out.println("What can I do for you?");
        System.out.println(LINE_DIVIDER);

        label:
        while (true){
            String commandGiven = myScanner.nextLine();
            String[] splitCommand = commandGiven.split("\\s+"); // split by space
            String actionCommand = splitCommand[FIRST_INDEX];

            switch (actionCommand) {
                case "todo":
                    handleTodo(Arrays.copyOfRange(splitCommand, SECOND_INDEX, splitCommand.length));
                    break;

                case "deadline":
                    handleDeadline(Arrays.copyOfRange(splitCommand, SECOND_INDEX, splitCommand.length));
                    break;

                case "event":
                    handleEvent(Arrays.copyOfRange(splitCommand, SECOND_INDEX, splitCommand.length));
                    break;

                case "bye":
                    break label;

                case "list":
                    printList();
                    break;

                case "mark":
                    markTask(Arrays.copyOfRange(splitCommand, SECOND_INDEX, splitCommand.length));
                    break;

                case "unmark":
                    unmarkTask(Arrays.copyOfRange(splitCommand, SECOND_INDEX, splitCommand.length));
                    break;

                default:
                    System.out.println(LINE_DIVIDER);
                    System.out.println("Invalid command");
                    System.out.println(LINE_DIVIDER);
                    break;
            }
        }
        System.out.println(LINE_DIVIDER);
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println(LINE_DIVIDER);
    }
}


