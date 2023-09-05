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

        String[] theList = new String[100];
        Scanner in = new Scanner(System.in);
        int i = 0;

        String echo = in.nextLine();
        while (!echo.equals("bye")) {
            if (echo.equals("list")) {
                for (int j = 0; j < i; j += 1) {
                    System.out.println((j + 1) + ". " + theList[j]);
                }
            } else {
                theList[i] = echo;
                System.out.println("added: " + echo);
                i += 1;
            }
            echo = in.nextLine();
        }

        System.out.println("Bye. Hope to see you again soon!");
        
        in.close();
    }
}
