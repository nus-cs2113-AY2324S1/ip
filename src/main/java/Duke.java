import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String logo = " _           _        \n"
                + "| |    _   _| | _____ \n"
                + "| |   | | | | |/ / _ \\\n"
                + "| |___| |_| |   <  __/\n"
                + "|_____|\\__,_|_|\\_\\___|\n";
        System.out.println("Hello! I'm\n" + logo);
        System.out.println("What can I do for you?");

        Scanner in = new Scanner(System.in);

        String echo = in.nextLine();
        while (!echo.equals("bye")) {
            System.out.println(echo);
            echo = in.nextLine();
        }

        System.out.println("Bye. Hope to see you again soon!");
        
        in.close();
    }
}
