import java.util.Scanner;

public class Chattie {
    public static void main(String[] args) {

        String line;
        Scanner in = new Scanner(System.in);

        System.out.println("\t____________________________________________________________");
        System.out.println("\tHello! I'm Chattie!");
        System.out.println("\tWhat can I do for you?");
        System.out.println("\t____________________________________________________________");

        do {
            line = in.nextLine();
            System.out.println("\t____________________________________________________________");
            System.out.println("\t" + line);
            System.out.println("\t____________________________________________________________");
        } while (!line.equals("bye"));

        System.out.println("Bye. Hope to see you again soon!");
        System.out.println("____________________________________________________________");
    }
}
