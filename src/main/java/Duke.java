import java.util.Scanner;

public class Duke {
    public static String line = "____________________________________________________________";
    public static String logo =
            "\t   _____ _____ __  __  ____  _   _ \n" +
            "\t  / ____|_   _|  \\/  |/ __ \\| \\ | |\n" +
            "\t | (___   | | | \\  / | |  | |  \\| |\n" +
            "\t  \\___ \\  | | | |\\/| | |  | | . ` |\n" +
            "\t  ____) |_| |_| |  | | |__| | |\\  |\n" +
            "\t |_____/|_____|_|  |_|\\____/|_| \\_|\n" +
            "\t                                   \n";


    public static void printGreeting() {
        System.out.println("\t" + line);
        System.out.println("\t" + "Hello I'm\n" + logo);
        System.out.println("\t" + "What can I do for you?");
        System.out.println("\t" + line);
    }

    public static void  printUnknownInputMessage() {
        System.out.println("\t" + line);
        System.out.println("\t" + " ☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
        System.out.println("\t" + line);
    }

    public static void printFarewell() {
        System.out.println("\t" + line);
        System.out.println("\t" + "Bye. Hope to see you again soon!");
        System.out.println("\t" + line);
    }

    public static void printList(Task[] tasks) {
        System.out.println("\t" + line);
        System.out.println("\tHere are the tasks in your list:");
        for (int i = 0; i < Task.getNumberOfTask(); i++) {
            System.out.println("\t" + (i + 1) + "." + tasks[i]);
        }
        System.out.println("\t" + line);
    }

    public static void markTask(String taskNumber, Task[] tasks) {
        //Convert task number to element in tasks array
        int target = Integer.parseInt(taskNumber) - 1;
        System.out.println("\t" + line);
        try {
            tasks[target].markAsDone();
            System.out.println("\tNice! I've marked this task as done:");
            System.out.println("\t  [X] " + tasks[target].getDescription());
        } catch (NullPointerException e) {
            System.out.println("\tSorry! There is no task associated with this number");
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("\tSorry! The tasks number inputted is out of bounds");
            System.out.println("\tPlease key in a number from 1-100");
        }
        System.out.println("\t" + line);
    }

    public static void unmarkTask(String taskNumber, Task[] tasks) {
        //Convert task number to element in tasks array
        int target = Integer.parseInt(taskNumber) - 1;
        System.out.println("\t" + line);
        try {
            tasks[target].unmarkAsDone();
            System.out.println("\tOkay, I've marked this task as not done yet:");
            System.out.println("\t  [] " + tasks[target].getDescription());
        } catch (NullPointerException e) {
            System.out.println("\tSorry! There is no task associated with this number");
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("\tSorry! The tasks number inputted is out of bounds");
            System.out.println("\tPlease key in a number from 1-100");
        }
        System.out.println("\t" + line);
    }

    public static void printAddTaskMessage(Task[] tasks) {
        System.out.println("\t" + "Got it. I've added this task:");
        System.out.println("\t  " + tasks[Task.getNumberOfTask() - 1]);
    }

    public static void printNumberOfTasks(Task[] tasks) {
        System.out.println("\tNow you have " + Task.getNumberOfTask() + " tasks in the list.");
    }

    public static void addTodo(String description, Task[] tasks) {
        tasks[Task.getNumberOfTask()] = new Todo(description);

        System.out.println("\t" + line);
        printAddTaskMessage(tasks);
        printNumberOfTasks(tasks);
        System.out.println("\t" + line);
    }

    public static void addEvent(String event, Task[] tasks) {
        System.out.println("\t" + line);
        try {
            //Split between 'description' and '/from and /to'
            String[] splitEvent = event.split(" /from ", 2);
            String description = splitEvent[0];
            //Split between '/from' and '/to'
            String[] time = splitEvent[1].split(" /to ");
            String from = time[0];
            String to = time[1];
            tasks[Task.getNumberOfTask()] = new Event(description, from, to);

            printAddTaskMessage(tasks);
            printNumberOfTasks(tasks);
        } catch (IndexOutOfBoundsException e) {
            System.out.println("\tPlease include when the time of your event in the following format:");
            System.out.println("\tevent [description] /from [start time] /to [end time]");
        }
        System.out.println("\t" + line);
    }

    public static void addDeadline(String deadline, Task[] tasks) {
        System.out.println("\t" + line);
        try {
            //Split between 'description' and '/by'
            String[] splitDeadline = deadline.split(" /by ");
            String description = splitDeadline[0];
            String by = splitDeadline[1];
            tasks[Task.getNumberOfTask()] = new Deadline(description, by);


            printAddTaskMessage(tasks);
            printNumberOfTasks(tasks);
            System.out.println("\t" + line);
        } catch (IndexOutOfBoundsException e) {
            System.out.println("\tPlease include when the deadline of your task is in the following format:");
            System.out.println("\tdeadline [description] /by [deadline]");
        }
        System.out.println("\t" + line);
    }

    /**
     * public static void addTask(String userInput, Task[] tasks) {
     *         tasks[Task.getNumberOfTask()] = new Task(userInput);
     *         System.out.println("\t" + line);
     *         System.out.println("\t" + "added: " + userInput);
     *         System.out.println("\t" + line);
     *         }
     */

    public static void processUserInput(String userInput, Task[] tasks) {
        String[] splitInput = userInput.split(" ");
        switch (splitInput[0]) {

        //If user types "list ..."
        case "list":
            //Print out current list of tasks
            printList(tasks);
            break;

        //If user types "mark 'n'", where n is a number referring to the task number
        case "mark":
            //Mark the task that the user specified with task number as done
            markTask(splitInput[1], tasks);
            break;

        //If user types "unmark 'n'", where n is a number referring to the task number
        case "unmark":
            //Unmark the task that the user specified with task number
            unmarkTask(splitInput[1], tasks);
            break;

        //If user types "todo ..."
        case "todo":
            //Add Todo to list and prints out add message and number of tasks in list
            try {
                addTodo(splitInput[1], tasks);
            } catch (IndexOutOfBoundsException e) {
                System.out.println("\t" + line);
                System.out.println("\t☹ OOPS!!! The description of a todo cannot be empty.");
                System.out.println("\t" + line);
            }
            break;

        //If user types "event ... /from ... /to ..."
        case "event":
            //Add Event to list and prints out add message and number of tasks in list
            try {
                addEvent(splitInput[1], tasks);
            } catch (IndexOutOfBoundsException e) {
                System.out.println("\t" + line);
                System.out.println("\t☹ OOPS!!! The description of an event cannot be empty.");
                System.out.println("\t" + line);
            }
            break;

        //If user types "deadline ... /by ..."
        case "deadline":
            //Add Deadline to list and prints out add message and number of tasks in list
            try {
                addDeadline(splitInput[1], tasks);
            } catch (IndexOutOfBoundsException e) {
                System.out.println("\t" + line);
                System.out.println("\t☹ OOPS!!! The description of a deadline cannot be empty.");
                System.out.println("\t" + line);
            }
            break;

        case "bye":
            //Prints farewell when user inputs "bye"
            printFarewell();
            break;

        default:
            //If unable to understand user input
            printUnknownInputMessage();
        }
    }
    public static void main(String[] args) {
        //Initialise variables
        String userInput = "";
        Task[] tasks = new Task[100];

        //Print out greeting when user starts the program.
        printGreeting();

        //Take in user input
        Scanner in = new Scanner(System.in);
        while (!userInput.equals("bye")) {
            userInput = in.nextLine();
            processUserInput(userInput, tasks);
        }
    }
}