import java.util.Scanner;

public class Duke {

    public static void echo(String line) {
        Scanner textIn = new Scanner(System.in);
        while (true) {
            String command = textIn.nextLine();
            if (command.equals("bye")) {
                break;
            }
            System.out.println(line);
            System.out.println(command);
            System.out.println(line);
        }
    }

    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        String line = "____________________________________________________________";
        System.out.println(line);
        System.out.println("Hello! I'm soccat!\nWhat can I do for you?");
        System.out.println(line);
        echo(line);
        System.out.println(line);
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println(line);
    }
}
