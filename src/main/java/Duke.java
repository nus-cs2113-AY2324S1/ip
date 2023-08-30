import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        System.out.println("Hello! I'm KevBot");
        System.out.println("What can I do for you?");

        Scanner in = new Scanner(System.in);
        String line;
        while (in.hasNextLine()) {
            line = in.nextLine();
            if (line.equals("bye")) {
                break;
            }

            System.out.println(line);
        }
        System.out.println("Bye. Hope to see you again soon!");
    }
}
