import java.util.Scanner;

public class itay {

    public static String parseString(String line){
        return line.trim();
    }
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);

        // greet
        System.out.println("Hello! I'm Itay");
        System.out.println("What can i do for you?");

        Scanner in = new Scanner(System.in);
        String line = in.nextLine();
        line = parseString(line);

        while(! line.equals("bye")) {
            System.out.println(line);
            line = in.nextLine();
            line = parseString(line);
        }

        System.out.println("Bye. Hope to see you again soon!");
    }
}
