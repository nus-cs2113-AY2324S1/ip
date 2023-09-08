import java.util.Scanner;

public class Alice {
    private static final String LINE = "____________________________________________________________\n";
    private static Task[] tasks = new Task[100];
    private static int numberOfTask = 0;


    /**
     * List all tasks that have been added by user.
     */
    public static void listTasks() {
        int itemNumber;
        for (int i=0; i<numberOfTask; i++){
            itemNumber = i + 1;
            System.out.println(itemNumber + ". " + tasks[i].toString());
        }
        System.out.println(LINE);
    }

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
     * Add a new task to tasks array
     * @param newTask is a class
     */
    public static void addTask(Task newTask) {
        tasks[numberOfTask] = newTask;
        numberOfTask++;
        System.out.println(" Gotcha! I have added the following task:");
        System.out.println("   " + newTask.toString());
        System.out.println(" Total no. of tasks: " + numberOfTask + " --- YOU'VE GOT THIS!\n" + LINE);
    }

    /**
     * Create a new to-do task in the correct format,
     * then, calls addTask to add task to tasks array
     * @param userInput input from user (eg. todo borrow book)
     */
    public static void addTodo(String userInput) {
        int indexOfSplit = userInput.indexOf(" ");
        String description = userInput.substring(indexOfSplit);
        Task newTask = new Task(description);
        addTask(newTask);
    }

    /**
     * Create a new deadline task in the correct format,
     * then, calls addTask to add deadline task to tasks array
     * @param userInput input from user (eg. deadline return book /by Sunday)
     */
    public static void addDeadline(String userInput) {
        int indexOfSplit = userInput.indexOf((" /")); //used to split description from date
        String descriptionOfInput = userInput.substring(9, indexOfSplit);
        String dateOfInput = userInput.substring(indexOfSplit+2);
        Task newTask = new Deadline(descriptionOfInput, dateOfInput);
        addTask(newTask);
    }

    /**
     * Create a new event task in the correct format,
     * then, calls addTask to add event task to tasks array
     * @param userInput input from user (eg. event project meeting /from Mon 2pm /to 4pm)
     */
    public static void addEvent(String userInput) {
        int indexOfSplit = userInput.indexOf(" /"); //used to split description from date
        String descriptionOfInput = userInput.substring(6, indexOfSplit);
        String dateOfInput = userInput.substring(indexOfSplit+2);

        indexOfSplit = dateOfInput.indexOf(" /"); //used to split the 2 dates
        String startDate = dateOfInput.substring(0,indexOfSplit);
        String endDate = dateOfInput.substring(indexOfSplit+5);
        Task newTask = new Event(descriptionOfInput, startDate, endDate);
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
                tasks[taskId].setStatusIcon("unmark");
                break;
            case "mark":
                taskId = Integer.parseInt(userInput.split(" ")[1]) - 1;
                tasks[taskId].setStatusIcon("mark");
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
            }
            userInput = in.nextLine();
        }
        printByeMessage();
    }
}