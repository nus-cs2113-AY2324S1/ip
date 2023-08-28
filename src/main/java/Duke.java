import java.util.Scanner;

public class Duke {

    public static void echo(String in) {
        System.out.println("    _____________________________________\n"
        + in
        + "\n    _____________________________________");
    }

    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";

        System.out.println("Hello! I'm Axel!\n"
                + "What can I do for you?\n"
                + "    _____________________________________");

        Scanner in = new Scanner(System.in);
        String buf = in.nextLine();

        while(!buf.toLowerCase().equals("bye")){
            echo(buf);
            buf = in.nextLine();
        }

        System.out.println("Bye. Hope to see you again soon!\n"
                + "    _____________________________________");
    }
}
