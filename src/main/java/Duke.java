import java.util.Scanner;

public class Duke {

    private static final String LINE = "____________________________________________________________";
    private static Task[] listItems = new Task[100];
    private static int listIdx = 0;

    public static void println(String line) {
        System.out.println("\t" + line);
    }
    public static void printWelcome() {

        String BOB = "\t   ___    ___  ___ \n"
                + "\t  / __\\  /___\\/ __\\\n"
                + "\t /__\\// //  //__\\/\\\n"
                + "\t/ \\/  \\/ \\_// \\/  \\\n"
                + "\t\\_____/\\___/\\_____/\n";

        println(LINE);
        println("Hello! I'm\n" + BOB);
        println("What can I do for you?");
        println(LINE);
    }

    public static void printFarewell() {
        println("Bye. Hope to see you again soon!");
        println(LINE);
    }

    public static void printList() {
        for (int i = 0; i < listIdx; i++) {
            println(String.format("%d. %s", i+1, listItems[i].getTask()));
        }
    }

    public static int getMarkIdx(String line) {
        return Integer.parseInt(line.split(" ")[1]) - 1;
    }

    public static void markItem(String line) {
        int markIdx = getMarkIdx(line);
        listItems[markIdx].setIsDone(true);

        println("Nice! I've marked this task as done: ");
        println(listItems[markIdx].getTask());
    }

    public static void unmarkItem(String line) {
        int markIdx = getMarkIdx(line);
        listItems[markIdx].setIsDone(false);

        println("Nice! I've marked this task as undone: ");
        println(listItems[markIdx].getTask());
    }

    public static void handleCreateTask(String line) {
        listItems[listIdx] = new Task(line);
        listIdx++;

        println(listItems[listIdx-1].getTaskAdded(listIdx));
    }

    public static void handleCreateTodo(String line) {
        String todo = line.substring(line.indexOf(" ")+1);
        listItems[listIdx] = new Todo(todo);
        listIdx++;

        println(listItems[listIdx-1].getTaskAdded(listIdx));
    }

    public static void handleCreateDeadline(String line) {
        int byIdx = line.indexOf("/by");

        // Extract task description and deadline from user input
        String description = line.substring(line.indexOf(" ")+1, byIdx-1);
        String deadline = line.substring(byIdx+ "/by ".length());

        listItems[listIdx] = new Deadline(description, deadline);
        listIdx++;

        println(listItems[listIdx-1].getTaskAdded(listIdx));
    }

    public static void handleCreateEvent(String line) {
        int fromIdx = line.indexOf("/from");
        int toIdx = line.indexOf("/to");

        // Extract task description, start time and end time from user input
        String description = line.substring(line.indexOf(" ")+1, fromIdx-1);
        String start = line.substring(fromIdx+ "/from ".length(), toIdx-1);
        String end = line.substring(toIdx+ "/to ".length());

        listItems[listIdx] = new Event(description, start, end);
        listIdx++;

        println(listItems[listIdx-1].getTaskAdded(listIdx));
    }

    public static void main(String[] args) {
        String line;
        Scanner in = new Scanner(System.in);

        printWelcome();

        line = in.nextLine();
        while (line.equals("bye") == false) {
            println(LINE);

            if (line.startsWith("list")) {
                printList();
            } else if (line.startsWith("mark")) {
                markItem(line);
            } else if (line.startsWith("unmark")) {
                unmarkItem(line);
            } else if (line.startsWith("todo")) {
                handleCreateTodo(line);
            } else if (line.startsWith("deadline")) {
                handleCreateDeadline(line);
            } else if (line.startsWith("event")) {
                handleCreateEvent(line);
            } else {
                handleCreateTask(line);
            }

            println(LINE);

            line = in.nextLine();
        }

        printFarewell();
    }
}
