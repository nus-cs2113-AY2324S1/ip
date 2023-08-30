import java.util.Scanner;

public class TUM {
    public static void main(String[] args) {
        System.out.println("____________________________________________________________");
        System.out.println(" Hello! I'm TUM");
        System.out.println(" What can I do for you?");
        System.out.println("____________________________________________________________");
        String line;
        Scanner in = new Scanner(System.in);
        line = in.nextLine();
        while (!line.equals("bye")) {
            System.out.println("____________________________________________________________");
            System.out.println(line);
            System.out.println("____________________________________________________________");
            line = in.nextLine();
        }
        System.out.println(" Bye. Hope to see you again soon!");
        System.out.println("____________________________________________________________");
    }
}
