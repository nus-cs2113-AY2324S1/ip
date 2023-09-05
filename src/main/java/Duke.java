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
                //theList[i] is the oldest String, start printing from theList[i - 1]
                int k = i - 1;
                for (int j = 1; theList[k] != null && j <= 100; j += 1) {
                    System.out.println(j + ". " + theList[k]);
                    k -= 1;
                    if (k < 0) {
                        k += 100;
                    }
                }
            }
            theList[i] = echo;
            System.out.println("added: " + echo);
            echo = in.nextLine();
            i += 1;
            if (i >= 100) {
                i = 0;
            }
        }

        System.out.println("Bye. Hope to see you again soon!");
        
        in.close();
    }
}
