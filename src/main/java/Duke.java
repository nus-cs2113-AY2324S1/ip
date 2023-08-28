import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        System.out.println("\tHello! I'm RC\n\tWhat can I do for you?\n");
        Scanner in = new Scanner(System.in);
        String line = "";
        while (true) {
            line = in.nextLine();
            if (line.equals("bye")) {
                break;
            }
            System.out.println("\t" + line);
        }
        System.out.println("\tBye. Hope to see you again soon!\n");
    }
}
