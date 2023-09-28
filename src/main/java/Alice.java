import exceptions.InvalidCommandException;
import exceptions.InvalidFormatException;
import tasks.Deadline;
import tasks.Event;
import tasks.Task;
import tasks.Todo;
import storage.FileManager;

import java.util.ArrayList;
import java.io.*;
import java.util.Scanner;

public class Alice {
    private static final String LINE = "\n____________________________________________________________\n";
    private static final String TAB_SPACE = "    ";
    private static ArrayList<Task> tasks= new ArrayList<>();
    private static FileManager file = new FileManager();
    private static int numberOfTasks;

    /**
     * For printing the hello message
     */
    public static void printHelloMessage() {
        System.out.println("    Hi there! I am");
        String alice = "     / \\ / \\  / \\  / \\_/ \\\n    ( A )( L )( I )( C )( E )\n     \\_/ \\_/  \\_/  \\_/ \\_/\n";
        System.out.println(alice);
        System.out.println("    What can I do for you?\n");
    }

    /**
     * For printing the bye message
     */
    public static void printByeMessage() {
        System.out.println("    Bye for now... We will miss you:( See you again very soon!");
        System.out.println("       *****   ");
        System.out.println("     *       * ");
        System.out.println("    *  O   O  *");
        System.out.println("    *    ∆    *");
        System.out.println("     *       * ");
        System.out.println("       *****   ");
    }

    /**
     * List all tasks that have been added by user.
     */
    public static void listTasks() {
        int itemNumber;
        for (int i = 0; i < tasks.size(); i++){
            itemNumber = i + 1;
            System.out.println(TAB_SPACE + itemNumber + ". " + tasks.get(i));
        }
        System.out.println(LINE);
    }

    /**
     * Add a new task to tasks array
     * @param newTask is a class
     */
    public static void addTask(Task newTask) {
        tasks.add(newTask);
        System.out.println("    Gotcha! I have added the following task:");
        System.out.println(TAB_SPACE + TAB_SPACE + newTask.toString());
        System.out.println("    Total no. of tasks: " + tasks.size() + " --- YOU'VE GOT THIS!\n" + LINE);
    }

    public static void deleteTask(String userInput) throws InvalidCommandException{
        int taskId = Integer.parseInt(userInput.split(" ")[1]);
        if (taskId > tasks.size() || taskId < 1) {
            throw new InvalidCommandException();
        }

        int taskPosition = taskId - 1;

        System.out.println("    Gotcha! I have removed the following task:");
        System.out.println(TAB_SPACE + TAB_SPACE + tasks.get(taskPosition));

        tasks.remove(taskPosition);
        System.out.println("    Total no. of tasks: " + tasks.size() + " --- YOU'VE GOT THIS!\n" + LINE);
    }

    /**
     * Create a new to-do task in the correct format,
     * then, calls addTask to add task to tasks array
     * @param userInput input from user (eg. todo borrow book)
     */
    public static void addTodo(String userInput) throws InvalidFormatException {
        if (userInput.split(" ").length < 2) {
            throw new InvalidFormatException();
        }
        final int LENGTH_OF_COMMAND = 5; //length of "size "

        String description = userInput.substring(LENGTH_OF_COMMAND);
        Task newTask = new Todo(description);
        addTask(newTask);
    }

    /**
     * Create a new deadline task in the correct format,
     * then, calls addTask to add deadline task to tasks array
     * @param userInput input from user (eg. deadline return book /by Sunday)
     */
    public static void addDeadline(String userInput) throws InvalidFormatException {
        final int LENGTH_OF_COMMAND = 9; //length of "deadline "

        String[] inputArray = userInput.split(" /");
        if (inputArray.length == 1) {
            throw new InvalidFormatException();
        }

        String description = inputArray[0].substring(LENGTH_OF_COMMAND);
        String date = inputArray[1];

        Task newTask = new Deadline(description, date);
        addTask(newTask);
    }

    /**
     * Create a new event task in the correct format,
     * then, calls addTask to add event task to tasks array
     * @param userInput input from user (eg. event project meeting /from Mon 2pm /to 4pm)
     */
    public static void addEvent(String userInput) throws InvalidFormatException{
        final int LENGTH_OF_COMMAND = 6; //length of "event "

        String[] inputArray = userInput.split(" /");
        if (inputArray.length < 3) {
            throw new InvalidFormatException();
        }

        String description = inputArray[0].substring(LENGTH_OF_COMMAND);
        String startDate = inputArray[1].strip();
        String endDate = inputArray[2].strip();

        Task newTask = new Event(description, startDate, endDate);
        addTask(newTask);
    }

    /**
     * Either marks or unmarks a task.
     * @param userInput Input from user.
     * @throws InvalidCommandException The task number specified is not within the list.
     */
    public static void changeStatus(String userInput) throws InvalidCommandException{
        int taskId;

        String[] userInputArray = userInput.split(" ");
        String actionOfInput = userInputArray[0];
        taskId = Integer.parseInt(userInputArray[1]) - 1;

        if (taskId+1 > tasks.size() || taskId+1 < 0) {
            throw new InvalidCommandException();
        }

        if (actionOfInput.equals("mark")){
            tasks.get(taskId).markTask();
        }
        if (actionOfInput.equals("unmark")) {
            tasks.get(taskId).unmarkTask();
        }
    }

    /**
     * Records down task from user input.
     * User is able to mark and unmark tasks, and also list all the tasks.
     */
    public static void main(String[] args) throws InvalidCommandException, InvalidFormatException, FileNotFoundException {
        printHelloMessage();
        tasks = file.retrieve();

        Scanner in = new Scanner(System.in);
        String userInput = in.nextLine();
        String actionOfInput;

        while (!(userInput.equals("bye"))){
            actionOfInput = userInput.split(" ")[0];

            try{
                switch (actionOfInput) {
                case "list":
                    listTasks();
                    break;
                case "mark":
                case "unmark":
                    changeStatus(userInput);
                    break;
                case "deadline":
                    addDeadline(userInput);
                    break;
                case "event":
                    addEvent(userInput);
                    break;
                case "todo":
                    addTodo(userInput);
                    break;
                case "delete":
                    deleteTask(userInput);
                    break;
                default:
                    throw new InvalidCommandException();
                }
            } catch (IndexOutOfBoundsException e){
                System.out.println("    You have an extra input OR you are missing an input!\n CORRECT IT BEFORE THE KNAVE OF HEART COMES!" + LINE);
            } catch (InvalidCommandException e){
                //Error message printed in InvalidCommandException class
            } catch (InvalidFormatException e) {
                //Error message printed in InvalidFormatException class
            }
            userInput = in.nextLine();
        }
        file.save(tasks);
        printByeMessage();
    }
}