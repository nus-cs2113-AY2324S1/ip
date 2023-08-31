import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;

public class Duke {


    public static List<String> saved_inputs = new ArrayList<String>();

    public static void print_saved_inputs() {
        for (int i = 0; i < saved_inputs.size(); i++) {
            System.out.printf("\t%d. %s\n", i + 1, saved_inputs.get(i));
        }
    }

    public static void main(String[] args) {
        System.out.println("\tHello! I'm Richard\n");
        System.out.println("\tWhat can I do for you ?\n");

        Scanner input = new Scanner(System.in);

        String user_input = input.nextLine();
        while (!user_input.equals("bye")) {
            if (user_input.equals("list")) {
                print_saved_inputs();
            } else {
                saved_inputs.add(user_input);
                System.out.printf("\tadded: %s\n", user_input);
            }
            user_input = input.nextLine();
        }

        System.out.println("\tBye hope to see you again\n");
    }
}
