import java.util.Scanner;

public class Duke {

    public static final String LINE = "\t____________________________________________________________";

    public static void printWelcome() {

        String bob = "\t   ___    ___  ___ \n"
                + "\t  / __\\  /___\\/ __\\\n"
                + "\t /__\\// //  //__\\/\\\n"
                + "\t/ \\/  \\/ \\_// \\/  \\\n"
                + "\t\\_____/\\___/\\_____/\n";

        System.out.println(LINE);
        System.out.println("\tHello! I'm\n" + bob);
        System.out.println("\tWhat can I do for you?");
        System.out.println(LINE);
    }

    public static void printFarewell() {
        System.out.println("\tBye. Hope to see you again soon!");
        System.out.println(LINE);
    }

    public static void main(String[] args) {
        String line;
        Scanner in = new Scanner(System.in);

        int listIdx = 0;
        String[] listItems = new String[100];

        printWelcome();

        line = in.nextLine().trim();
        while (line.equals("bye") == false) {
            System.out.println(LINE);
            if (line.equals("list")) {
                for (int i = 0; i < listIdx; i++) {
                    System.out.printf("%d. %s\n", i+1, listItems[i]);
                }
            } else {
                listItems[listIdx] = line;
                listIdx++;
                System.out.println("\tadded: " + line);
            }
            System.out.println(LINE);

            line = in.nextLine().trim();
        }

        printFarewell();
    }
}
