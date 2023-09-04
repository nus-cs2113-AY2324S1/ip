import java.util.Scanner;

public class Duke {

    public static final String LINE = "____________________________________________________________";

    public static void println(String line) {
        System.out.println("\t" + line);
    }
    public static void printWelcome() {

        String bob = "\t   ___    ___  ___ \n"
                + "\t  / __\\  /___\\/ __\\\n"
                + "\t /__\\// //  //__\\/\\\n"
                + "\t/ \\/  \\/ \\_// \\/  \\\n"
                + "\t\\_____/\\___/\\_____/\n";

        println(LINE);
        println("Hello! I'm\n" + bob);
        println("What can I do for you?");
        println(LINE);
    }

    public static void printFarewell() {
        println("Bye. Hope to see you again soon!");
        println(LINE);
    }

    public static String getCommand(String input) {
        if (input.contains(" ")) {
            return input.substring(0, input.indexOf(" "));
        }

        return input;
    }

    public static void main(String[] args) {
        String line;
        Scanner in = new Scanner(System.in);

        String command;

        int listIdx = 0;
        Task[] listItems = new Task[100];
        int markIdx;

        printWelcome();

        line = in.nextLine().trim();
        while (line.equals("bye") == false) {
            println(LINE);

            command = getCommand(line);

            if (command.equals("list")) {

                for (int i = 0; i < listIdx; i++) {
                    println(String.format("%d. %s", i+1, listItems[i].getTask()));
                }

            } else if (command.equals("mark")) {

                markIdx = Integer.parseInt(line.split(" ")[1]) - 1;
                listItems[markIdx].setIsDone(true);

                println("Nice! I've marked this task as done: ");
                println(listItems[markIdx].getTask());

            } else if (command.equals("unmark")) {

                markIdx = Integer.parseInt(line.split(" ")[1]) - 1;
                listItems[markIdx].setIsDone(false);

                println("Nice! I've marked this task as undone: ");
                println(listItems[markIdx].getTask());

            } else if (command.equals("todo")) {

                String todo = line.substring(line.indexOf(" ")+1);
                listItems[listIdx] = new Todo(todo);
                listIdx++;

                println(listItems[listIdx-1].getTaskAdded(listIdx));

            } else if (command.equals("deadline")) {

                int byIdx = line.indexOf("/by");
                String description = line.substring(line.indexOf(" ")+1, byIdx-1);
                String deadline = line.substring(byIdx+ "/by ".length());

                listItems[listIdx] = new Deadline(description, deadline);
                listIdx++;

                println(listItems[listIdx-1].getTaskAdded(listIdx));

            } else if (command.equals("event")) {

                int fromIdx = line.indexOf("/from");
                int toIdx = line.indexOf("/to");

                String description = line.substring(line.indexOf(" ")+1, fromIdx-1);
                String start = line.substring(fromIdx+ "/from ".length(), toIdx-1);
                String end = line.substring(toIdx+ "/to ".length());

                listItems[listIdx] = new Event(description, start, end);
                listIdx++;

                println(listItems[listIdx-1].getTaskAdded(listIdx));

            } else {

                listItems[listIdx] = new Task(line);
                listIdx++;

                println(listItems[listIdx-1].getTaskAdded(listIdx));

            }

            println(LINE);

            line = in.nextLine().trim();
        }

        printFarewell();
    }
}
