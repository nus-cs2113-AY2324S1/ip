import java.util.Scanner;

public class Zran {
    public static void main(String[] args) {
        System.out.println("    ____________________________________________________________");
        System.out.println("    Hello! I'm Zran!");
        System.out.println("    What can I do for you?");
        System.out.println("    ____________________________________________________________");
        echo();
    }

    public static void echo() {
        String input = "";
        Scanner in = new Scanner(System.in);
        String outputFormat = "    ____________________________________________________________\n" +
                "    %s\n    ____________________________________________________________";

        while(!(input = in.nextLine()).equals("bye")){
            System.out.printf((outputFormat) + "%n", input);
        }

        System.out.printf((outputFormat) + "%n", "Bye. Hope to see you again soon!");
    }
}
