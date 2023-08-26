import java.util.Scanner;
public class Duke {
    public static void main(String[] args) {
        System.out.println("Hello I'm LinguoBot");
        System.out.println("What can I do for you?");

        String line;
        Scanner in = new Scanner(System.in);

        while (true) {
            line = in.nextLine();
            System.out.println("-------------------------");
            System.out.println(line);
            System.out.println("-------------------------");

            if (line.equals("bye")) {
                System.out.println("-------------------------");
                System.out.println("Bye. Hope to see you again soon!");
                System.out.println("-------------------------");
                break;
            }
        }
    }
}