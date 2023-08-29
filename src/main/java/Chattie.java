import java.util.Scanner;

public class Chattie {
    public static void main(String[] args) {

        String line = " ";
        Scanner in = new Scanner(System.in);

        System.out.println("\t____________________________________________________________");
        System.out.println("\tHello! I'm Chattie!");
        System.out.println("\tWhat can I do for you?");
        System.out.println("\t____________________________________________________________");

        line = in.nextLine();
        while(!line.equals("bye")) {
            System.out.println("\t____________________________________________________________");
            System.out.println("\t" + line);
            System.out.println("\t____________________________________________________________");
            line = in.nextLine();
        }

        System.out.println("\t____________________________________________________________");
        System.out.println("\tBye. Hope to see you again soon!");
        System.out.println("\t____________________________________________________________");
    }
}
