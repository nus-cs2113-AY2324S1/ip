import java.util.Scanner;

public class Alice {
    private static final String LINE = "____________________________________________________________\n";
    private static Task[] tasks = new Task[100];
    private static int numberOfTasks = 0;

    /**
     * For printing the hello message
     */
    public static void printHelloMessage() {
        System.out.println("Hi there! I am");
        String[] alice = {
                "  _   _    _    _   _   ",
                " / \\ / \\  / \\  / \\_/ \\",
                "( A )( L )( I )( C )( E )",
                " \\_/ \\_/  \\_/  \\_/ \\_/"
        };
        for (String line : alice) {
            System.out.println(line);
        }
        System.out.println("What can I do for you?");
        System.out.println(LINE);
    }

    /**
     * For printing the bye message
     */
    public static void printByeMessage() {
        System.out.println(" Bye for now... We will miss you:( See you again very soon!");
        System.out.println("   *****   ");
        System.out.println(" *       * ");
        System.out.println("*  O   O  *");
        System.out.println("*    ∆    *");
        System.out.println(" *       * ");
        System.out.println("   *****   ");
    }

    /**
     * List all tasks that have been added by user.
     */
    public static void listTasks() {
        int itemNumber;
        for (int i = 0; i < numberOfTasks; i++){
            itemNumber = i + 1;
            System.out.println(itemNumber + ". " + tasks[i].toString());
        }
        System.out.println(LINE);
    }

    /**
     * Add a new task to tasks array
     * @param newTask is a class
     */
    public static void addTask(Task newTask) {
        tasks[numberOfTasks] = newTask;
        numberOfTasks++;
        System.out.println(" Gotcha! I have added the following task:");
        System.out.println("   " + newTask.toString());
        System.out.println(" Total no. of tasks: " + numberOfTasks + " --- YOU'VE GOT THIS!\n" + LINE);
    }

    /**
     * Create a new to-do task in the correct format,
     * then, calls addTask to add task to tasks array
     * @param userInput input from user (eg. todo borrow book)
     */
    public static void addTodo(String userInput) {
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
    public static void addDeadline(String userInput) {
        final int LENGTH_OF_COMMAND = 9; //length of "deadline "

        String[] inputArray = userInput.split(" /");
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
    public static void addEvent(String userInput) {
        final int LENGTH_OF_COMMAND = 6; //length of "event "

        String[] inputArray = userInput.split(" /");
        String description = inputArray[0].substring(LENGTH_OF_COMMAND);
        String startDate = inputArray[1].strip();
        String endDate = inputArray[2].strip();

        Task newTask = new Event(description, startDate, endDate);
        addTask(newTask);
    }

    /**
     * Records down task from user input.
     * User is able to mark and unmark tasks, and also list all the tasks.
     */
    public static void main(String[] args) {
        printHelloMessage();
        int taskId;

        Scanner in = new Scanner(System.in);
        String userInput = in.nextLine();
        String actionOfInput;

        while (!(userInput.equals("bye"))){
            actionOfInput = userInput.split(" ")[0];
            switch (actionOfInput){
            case "list":
                listTasks();
                break;
            case "unmark":
                taskId = Integer.parseInt(userInput.split(" ")[1]) - 1;
                tasks[taskId].unmarkTask();
                break;
            case "mark":
                taskId = Integer.parseInt(userInput.split(" ")[1]) - 1;
                tasks[taskId].markTask();
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
            default:
                Task newTask = new Task(userInput);
                addTask(newTask);
            }
            userInput = in.nextLine();
        }
        printByeMessage();
    }
}