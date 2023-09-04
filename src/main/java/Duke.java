//import scanner
import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";

        //message
        System.out.println("Hello! I'm Botbot");
        System.out.println("What can I do for you?");

        //scan input
        Scanner scanner = new Scanner(System.in);

        while(true) {
            String input = scanner.nextLine();
            //for command bye
            if (input.equals("bye")) {
                System.out.println("Bye! Hope to see you again soon!");
                break;
            } else {
                //Echo input
                System.out.println(input);
            }
        }

        //close scanner
        scanner.close();
    }
}
