import java.util.Scanner;

public class Fredbot {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        // System.out.println("Hello from\n" + logo);
        String greeting = "    ____________________________________________________________\n" +
                "     Hello! I'm Fredbot\n" +
                "     What can I do for you?\n" +
                "    ____________________________________________________________\n";
        String farewell = "    ____________________________________________________________\n" +
                "     Bye. Hope to see you again soon!\n" +
                "    ____________________________________________________________";
        System.out.println(greeting);
        String line;
        String divider = "    ____________________________________________________________";
        Scanner in = new Scanner(System.in);
        line = in.nextLine();
        while (!line.equals("bye")) {
            System.out.println(divider);
            System.out.println("    " + line);
            System.out.println(divider + '\n');
            line = in.nextLine();
        }
        System.out.println(farewell);
    }
}
