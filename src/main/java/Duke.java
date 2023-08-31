import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        System.out.println("Hello! I'm Richard\n");
        System.out.println("What can I do for you ?\n");

        Scanner input = new Scanner(System.in);

        String user_input = input.nextLine();
        while (!user_input.equals("bye")) {
            System.out.println(user_input);
            user_input = input.nextLine();
        }

        System.out.println("Bye hope to see you again\n");
    }
}
