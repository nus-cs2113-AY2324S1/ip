import java.util.Arrays;
import java.util.Scanner;
import java.util.ArrayList;

public class Duke {
    public static String CHATBOTNAME="Andrew Tate";
    public static ArrayList<Task> TASKS = new ArrayList<Task>();
    public static String LINE_DIVIDER = "____________________________________________________________";
    public static int FIRST_INDEX=0;
    public static int SECOND_INDEX=1;
    public static String ASCII_ART =
            "⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⡿⠿⠿⠿⣿⣿⣿⣿⣿⣿⣿⣿⣿\n" +
            "⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⡿⠋⠁⠀⠀⣸⣷⣦⡉⢿⣿⣿⣿⣿⣿⣿\n" +
            "⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⡟⠀⡆⠀⠀⢺⣿⣿⣿⣿⣄⢻⣿⣿⣿⣿⣿\n" +
            "⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⡇⠀⠐⠸⠀⠘⣿⣿⣿⣿⣿⣧⢿⣿⣿⣿⣿\n" +
            "⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⠿⠇⠀⡀⠁⠀⠁⠈⠘⠉⠉⠉⠉⠈⢿⣿⣿⣿\n" +
            "⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⡀⠀⠀⢷⡀⠀⠀⠀⠘⣆⠀⠀⠀⠰⣾⣿⣿⣿\n" +
            "⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣧⡀⠀⠀⠻⣾⣶⠀⠀⠉⢴⣿⡏⢹⣿⣿⣿⣿\n" +
            "⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⠀⠀⠀⠘⠁⠀⢄⠨⣄⠻⢡⣾⣿⣿⣿⣿\n" +
            "⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⡿⠀⣇⠀⠀⠀⠀⠀⠶⠃⢠⣾⣿⣿⣿⣿⣿\n" +
            "⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⠟⠁⠀⠻⡷⠀⢀⡀⣤⣤⡖⣿⣿⣿⣿⣿⣿⣿\n" +
            "⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⠟⠁⠀⠀⠀⠀⠈⠻⢿⣿⣦⣿⢣⣿⣿⣿⣿⣿⣿⣿\n" +
            "⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⠋⠁⠀⠀⠀⠀⠀⠀⠀⠀⠀⠉⠙⠋⠀⠙⢿⣿⣿⣿⣿⣿\n" +
            "⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⡟⠁⠀⠀⠀⠀⠀⠀⠀⢀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠉⠻⣿⣿⣿\n" +
            "⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⡏⠀⠀⠀⠀⠀⠀⠀⠀⠀⡆⠰⣄⠀⠀⠀⠀⠀⠀⠀⠀⠀⠹⣿⣿\n" +
            "⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠹⡄⠹⠀⠀⠀⠀⠀⢶⣄⠀⠀⠀⠹⣿\n" +
            "⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⠇⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠁⠀⠀⠀⠀⠀⠀⣿⠟⠀⠀⠀⠀⣿\n" +
            "⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣿⡇⠀⠀⠀⠀⢿\n" +
            "⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣏⡀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢹⣿⠀⠀⠀⠀⢸\n" +
            "⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣷⠀⠁⠀⠠⠞⣻⡗⠶⣶⣤⠀⠀⠀⠀⠀⠀⠀⠀⠀⠋⠀⠀⠀⠀⢀\n" +
            "⣻⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⠀⠀⠀⠄⠒⠚⣿⣧⡹⣿⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢀⣼\n" +
            "⣻⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⠀⠀⢸⣦⠀⢀⡉⢿⣷⡿⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠐⣿⣿\n" +
            "⣿⣿⣿⣻⣿⣿⣿⣿⣿⣿⣿⣿⣿⡆⠀⠘⣿⣇⢸⣷⣦⡝⠁⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠹⣟";

    /* Pretty prints a remark after adding any tasks */
    public static void printRemark(Task task){
        System.out.println(LINE_DIVIDER);
        System.out.println("Got it. I've add this task:");
        System.out.println(task);
        System.out.println("Now you have " + TASKS.size() + " tasks in the list");
        System.out.println(LINE_DIVIDER);
    }

    /* Pretty prints the error when exception is caught */
    public static void printErrorPrompt(Exception e){
        System.out.println(LINE_DIVIDER);
        System.out.println("Error: Invalid index or insufficient arguments provided!");
        System.out.println(LINE_DIVIDER);
    }
    public static void addTodoInList(String[] arguments){
        try{
            String taskDescription = String.join(" ", arguments);
            ToDo newToDo = new ToDo(taskDescription);
            TASKS.add(newToDo);
            printRemark(newToDo);
        }catch (Exception e){
            printErrorPrompt(e);
        }
    }

    public static void addDeadlineInList(String[] arguments){
        try{
            String argumentsString = String.join(" ", arguments);
            String[] argumentList = argumentsString.split(" /by ");
            String deadlineDescription = argumentList[FIRST_INDEX];
            String deadlineEndTime = argumentList[SECOND_INDEX];
            Deadline newDeadline = new Deadline(deadlineDescription, deadlineEndTime);
            TASKS.add(newDeadline);
            printRemark(newDeadline);

        } catch (Exception e){
            printErrorPrompt(e);
        }
    }

    public static void addEventInList(String[] arguments){
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
            printErrorPrompt(e);
        }
    }

    /* Marks the task from {@code arguments} as complete */
    public static void markTaskComplete(String[] arguments){
        try{
            int taskIndex = Integer.parseInt(arguments[FIRST_INDEX]);
            Task taskToMark = TASKS.get(taskIndex-1);
            taskToMark.markAsDone();
            System.out.println(LINE_DIVIDER);
            System.out.println("Marked this task as done:");
            System.out.println(taskToMark);
            System.out.println(LINE_DIVIDER);

        }catch(Exception e){
            printErrorPrompt(e);
        }
    }

    /* Marks the task from {@code arguments} as incomplete */
    public static void unmarkTaskIncomplete(String[] arguments){
        try{
            int taskIndex = Integer.parseInt(arguments[FIRST_INDEX]);
            Task taskToUnmark = TASKS.get(taskIndex-1);
            taskToUnmark.markAsUndone();
            System.out.println(LINE_DIVIDER);
            System.out.println("Marked this task as undone:");
            System.out.println(taskToUnmark);
            System.out.println(LINE_DIVIDER);

        }catch(Exception e){
            printErrorPrompt(e);
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

    public static void main(String[] args){
        Scanner myScanner = new Scanner(System.in);
        System.out.println(ASCII_ART);
        System.out.println(LINE_DIVIDER);
        System.out.println("Hello! I'm the top G " + CHATBOTNAME);
        System.out.println("What can I do for you?");
        System.out.println(LINE_DIVIDER);

        whileLoop:
        while (true){
            System.out.print("> ");
            String commandGiven = myScanner.nextLine();
            String[] arguments = commandGiven.split("\\s+"); // split by space
            String actionCommand = arguments[FIRST_INDEX];
            actionCommand = actionCommand.toLowerCase(); // make sure all same case to account for typing error

            switch (actionCommand) {
                case "todo":
                    // passing subarray of arguments without the 1st
                    addTodoInList(Arrays.copyOfRange(arguments, SECOND_INDEX, arguments.length));
                    break;

                case "deadline":
                    addDeadlineInList(Arrays.copyOfRange(arguments, SECOND_INDEX, arguments.length));
                    break;

                case "event":
                    addEventInList(Arrays.copyOfRange(arguments, SECOND_INDEX, arguments.length));
                    break;

                case "bye":
                    break whileLoop; // exit from while loop, as switch/case has its own break

                case "list":
                    printTaskList();
                    break;

                case "mark":
                    markTaskComplete(Arrays.copyOfRange(arguments, SECOND_INDEX, arguments.length));
                    break;

                case "unmark":
                    unmarkTaskIncomplete(Arrays.copyOfRange(arguments, SECOND_INDEX, arguments.length));
                    break;

                default:
                    System.out.println(LINE_DIVIDER);
                    System.out.println("Invalid command");
                    System.out.println(LINE_DIVIDER);
                    break;
            }
        }
        System.out.println(LINE_DIVIDER);
        System.out.println("Bye, hope to see you again soon!");
        System.out.println(LINE_DIVIDER);
    }
}


