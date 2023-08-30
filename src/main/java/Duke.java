import java.util.Scanner;

public class Duke {

    public static void respond(String s) {
        System.out.println("____________________________________________________________\n" +
                s + "\n" +
                "____________________________________________________________");
    }
    public static void main(String[] args) {
//        String logo = " ____        _        \n"
//                + "|  _ \\ _   _| | _____ \n"
//                + "| | | | | | | |/ / _ \\\n"
//                + "| |_| | |_| |   <  __/\n"
//                + "|____/ \\__,_|_|\\_\\___|\n";
//        System.out.println("Hello from\n" + logo);
        respond("Hello! I'm Mark\nWhat can I do for you?");
        Scanner sc = new Scanner(System.in);
        String in = sc.nextLine();
        while(!in.equals("bye")) {
            respond(in);
            in = sc.nextLine();
        }
        respond("Bye. Hope to see you again soon!");
    }
}
