import java.util.Scanner;

public class Duke {
    public static String line = "____________________________________________________________";
    public static String logo = "Simon";


    public static void printGreeting() {
        System.out.println("\t" + line);
        System.out.println("\t" + "Hello I'm " + logo);
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

    public static void markTask(String userInput, Task[] tasks) {
        //Find task number that user wants to mark
        int target = Integer.parseInt(userInput.substring(5)) - 1;
        tasks[target].markAsDone();
        System.out.println("\t" + line);
        System.out.println("\tNice! I've marked this task as done:");
        System.out.println("\t  [X] " + tasks[target].getDescription());
        System.out.println("\t" + line);
    }

    public static void unmarkTask(String userInput, Task[] tasks) {
        //Find task number that user wants to unmark
        int target = Integer.parseInt(userInput.substring(7)) - 1;
        tasks[target].unmarkAsDone();
        System.out.println("\t" + line);
        System.out.println("\tOkay, I've marked this task as not done yet:");
        System.out.println("\t  [] " + tasks[target].getDescription());
        System.out.println("\t" + line);
    }

    public static void addTodo(String userInput, Task[] tasks) {
        System.out.println("\t" + line);
        try {
            tasks[Task.getNumberOfTask()] = new Todo(userInput.substring(5));
            System.out.println("\t" + "Got it. I've added this task:");
            System.out.println("\t  " + tasks[Task.getNumberOfTask() - 1]);
            System.out.println("\tNow you have " + Task.getNumberOfTask() + " tasks in the list.");
        } catch (IndexOutOfBoundsException e) {
            System.out.println("\t☹ OOPS!!! The description of a todo cannot be empty.");
        }
        System.out.println("\t" + line);
    }

    public static void addEvent(String userInput, Task[] tasks) {
        //Might have to change in the future should space not be necessary in font of "/"
        int fromIndex = userInput.indexOf((" /from"));
        int toIndex = userInput.indexOf(" /to");
        String description = userInput.substring(6, fromIndex);
        String from = userInput.substring(fromIndex + 7, toIndex);
        String to = userInput.substring(toIndex + 5);
        tasks[Task.getNumberOfTask()] = new Event(description, from, to);
        System.out.println("\t" + line);
        System.out.println("\t" + "Got it. I've added this task:");
        System.out.println("\t  " + tasks[Task.getNumberOfTask() - 1]);
        System.out.println("\tNow you have " + Task.getNumberOfTask() + " tasks in the list.");
        System.out.println("\t" + line);
    }

    public static void addDeadline(String userInput, Task[] tasks) {
        int byIndex = userInput.indexOf((" /by"));
        String description = userInput.substring(9, byIndex);
        String by = userInput.substring(byIndex + 5);
        tasks[Task.getNumberOfTask()] = new Deadline(description, by);
        System.out.println("\t" + line);
        System.out.println("\t" + "Got it. I've added this task:");
        System.out.println("\t  " + tasks[Task.getNumberOfTask() - 1]);
        System.out.println("\tNow you have " + Task.getNumberOfTask() + " tasks in the list.");
        System.out.println("\t" + line);
    }

    public static void addTask(String userInput, Task[] tasks) {
        tasks[Task.getNumberOfTask()] = new Task(userInput);
        System.out.println("\t" + line);
        System.out.println("\t" + "added: " + userInput);
        System.out.println("\t" + line);
    }
    public static void main(String[] args) {

        //Print out greeting when user starts the program.
        printGreeting();

        String userInput;
        Task[] tasks = new Task[100];
        //Take in user input
        Scanner in = new Scanner(System.in);
        userInput = in.nextLine();

        while (!userInput.equals("bye")) {
            if (userInput.equals("list")) {
                //Print out current list of tasks
                printList(tasks);
                userInput = in.nextLine();
            }
            else if (userInput.startsWith("mark")) {
                //Mark the task that the user specified with task number
                markTask(userInput, tasks);
                userInput = in.nextLine();
            }
            else if (userInput.startsWith("unmark")) {
                //Unmark the task that the user specified with task number
                unmarkTask(userInput, tasks);
                userInput = in.nextLine();
            }
            else if (userInput.startsWith("todo")) {
                //Add Todo to list and prints out add message and number of tasks in list
                addTodo(userInput, tasks);
                userInput = in.nextLine();
            }
            else if (userInput.startsWith("event")) {
                //Add Event to list and prints out add message and number of tasks in list
                addEvent(userInput, tasks);
                userInput = in.nextLine();

            }
            else if (userInput.startsWith("deadline")) {
                //Add Deadline to list and prints out add message and number of tasks in list
                addDeadline(userInput, tasks);
                userInput = in.nextLine();
            }
            else {
                //If unable to understand user input
                printUnknownInputMessage();
                userInput = in.nextLine();
            }
        }
        //Exits loop when user inputs "bye"
        printFarewell();
    }
}