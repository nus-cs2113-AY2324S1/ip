import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;

public class Duke {
    public static final String BOT_NAME = "JS";
    public static final String LINE_DIVIDER = "----------------------------------------";

    /**
     * Mark or unmark task status and updates in the ArrayList
     * 
     * @param taskList contains all the tasks stored.
     * @param position task number on the list.
     * @param status task marked as done or not done.
     */
    public static void markList(ArrayList<Task> taskList, String position, boolean status) {
        try {
            int index = Integer.parseInt(position) - 1;
            Task event = taskList.get(index);
            event.setCompleted(status);
            taskList.set(index, event);
            if(event.isCompleted()) {
                System.out.println("Nice! I've marked this task as done:");
                System.out.println(event);
            } else {
                System.out.println("OK, I've marked this task as not done yet:");
                System.out.println(event);
            }
        } catch (Exception exception) {
            System.out.println("Invalid Input");
        }
    }

    /**
     * Print all the tasks stored
     * 
     * @param taskList contains all the tasks stored.
     */
    public static void printList(ArrayList<Task> taskList) {
        System.out.println("Here are the task in your list:");
        Iterator<Task> taskListIter = taskList.iterator();
        for(int i = 1; taskListIter.hasNext() ; i++) {
            Task event = taskListIter.next();
            System.out.println(i + "." + event);
        }
    }

    /**
     * Prints the length of the list in a fixed format
     * 
     * @param taskList contains all the tasks stored.
     */
    public static void printListLength(ArrayList<Task> taskList) {
        System.out.println("Now you have " + taskList.size() + " task in the list");
    }

    
    /**
     * Prints out the new task added
     * 
     * @param taskList contains all the tasks stored.
     * @param task new task that is created
     */
    public static void printNewTask(ArrayList<Task> taskList, Task task) {
        System.out.println("Got it. I've added this task:");
        System.out.println(task);
        printListLength(taskList);
    }

    /**
     * Add deadline to the ArrayList
     * 
     * @param taskList contains all the tasks stored.
     * @param arugments arguments of the commands that is used
     */
    public static void addDeadlineToList(ArrayList<Task> taskList, String arguments) {
        try {
            String[] argumentsList = arguments.split(" /by ");
            Deadline newDeadline = new Deadline(argumentsList[0], argumentsList[1]);
            taskList.add(newDeadline);
            printNewTask(taskList, newDeadline);
        } catch (Exception exception) {
            System.out.println("Invalid Input");
        }
    }

    /**
     * Add Event to the ArrayList
     * 
     * @param taskList contains all the tasks stored.
     * @param arugments arguments of the commands that is used
     */
    public static void addEventToList(ArrayList<Task> taskList, String arguments) {
        try {
            String[] argumentsList = arguments.split(" /from ");
            String description = argumentsList[0];
            argumentsList = argumentsList[1].split(" /to ");
            Event newEvent = new Event(description, argumentsList[0], argumentsList[1]);
            taskList.add(newEvent);
            printNewTask(taskList, newEvent);
        } catch (Exception exception) {
            System.out.println("Invalid Input");
        }
    }

    /**
     * Add ToDo to the ArrayList
     * 
     * @param taskList contains all the tasks stored.
     * @param arugments arguments of the commands that is used
     */
    public static void addToDoToList(ArrayList<Task> taskList, String arguments) {
        try {
            if(arguments.isBlank()){
                System.out.println("Invalid Input");
            } else {
                ToDo newToDo = new ToDo(arguments);
                taskList.add(newToDo);
                printNewTask(taskList, newToDo);
            }
            
        } catch (Exception e) {
            System.out.println("Invalid Input");
        }
    }
    
    public static void main(String[] args) {
        String userInput, command;
        String arguments = "";
        ArrayList<Task> taskList = new ArrayList<Task>();
        System.out.println(LINE_DIVIDER);
        System.out.println("Hello! I'm " + BOT_NAME);
        System.out.println("What can I do for you?");
        System.out.println(LINE_DIVIDER);
        Scanner input = new Scanner(System.in);
        do {
            userInput = input.nextLine();
            command = userInput.split(" ", 2)[0];
            if(userInput.split(" ", 2).length != 1) {
                arguments = userInput.split(" ", 2)[1];
            }
            switch(command) {
            case "list":
                System.out.println(LINE_DIVIDER);
                printList(taskList);
                break;
            case "bye":
                break;
            case "todo":
                System.out.println(LINE_DIVIDER);
                addToDoToList(taskList, arguments);
                break;
            case "event":
                System.out.println(LINE_DIVIDER);
                addEventToList(taskList, arguments);
                break;
            case "deadline":
                System.out.println(LINE_DIVIDER);
                addDeadlineToList(taskList, arguments);
                break;
            case "unmark":
                System.out.println(LINE_DIVIDER);
                markList(taskList, arguments, false);
                break;
            case "mark":
                System.out.println(LINE_DIVIDER);
                markList(taskList, arguments, true);
                break;
            default:
                System.out.println(LINE_DIVIDER);
                System.out.println("Invalid command");
            }
            System.out.println(LINE_DIVIDER);
        } while (!(command.equals("bye")));
        input.close();
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println(LINE_DIVIDER);
    }
}
