import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        final String logo = "MudMud";

        Scanner in = new Scanner(System.in);
        String input = "";

        System.out.println("\t____________________________________________________________");
        System.out.println("\tOh hello! I'm " + logo + ".");
        System.out.println("\tHow can I help you?");
        System.out.println("\t____________________________________________________________");

        while (!input.equals("bye")) {
            input = in.nextLine();

            System.out.println("\t____________________________________________________________");
            if (input.equals("bye")) {
                System.out.println("\tGoodbye! I am going to sleep now.");
            } else {
                System.out.println("\t" + input);
            }
            System.out.println("\t____________________________________________________________");

        }
    }
}