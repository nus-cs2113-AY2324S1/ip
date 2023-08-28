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

    public static void main(String[] args) {
        String line;
        Scanner in = new Scanner(System.in);

        int listIdx = 0;
        Task[] listItems = new Task[100];
        int markIdx;

        printWelcome();

        line = in.nextLine().trim();
        while (line.equals("bye") == false) {
            println(LINE);

            if (line.equals("list")) {

                for (int i = 0; i < listIdx; i++) {
                    println(String.format("%d. %s", i+1, listItems[i].getTask()));
                }

            } else if (line.substring(0, 4).equals("mark")) {

                markIdx = Integer.parseInt(line.split(" ")[1]) - 1;
                listItems[markIdx].setIsDone(true);

                println("Nice! I've marked this task as done: ");
                println(listItems[markIdx].getTask());

            } else if (line.substring(0, 6).equals("unmark")) {

                markIdx = Integer.parseInt(line.split(" ")[1]) - 1;
                listItems[markIdx].setIsDone(false);

                println("Nice! I've marked this task as undone: ");
                println(listItems[markIdx].getTask());

            } else {

                listItems[listIdx] = new Task(line);
                listIdx++;
                println("added: " + line);

            }

            println(LINE);

            line = in.nextLine().trim();
        }

        printFarewell();
    }
}
