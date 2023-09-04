import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        System.out.println("____________________________________________________________");
        System.out.println("Hello! I'm Calebra\nWhat can I do for you?");
        System.out.println("____________________________________________________________");


        Scanner input = new Scanner(System.in);
        String line = input.nextLine();
        while (!line.equals("bye")) {
            System.out.println("____________________________________________________________\n");
            System.out.println(line);
            System.out.println("____________________________________________________________\n");
            line = input.nextLine();
        }

        // When the user types "bye," exit the loop and display the goodbye message
        System.out.println("____________________________________________________________");
        System.out.println("Bye! Hope to see you again soon!");
        System.out.println("____________________________________________________________");

    }
}
