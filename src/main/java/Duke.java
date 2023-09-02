import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String line = "____________________________________________________________";
        String logo = "Simon";
        System.out.println("\t" + line);
        System.out.println("\t" + "Hello I'm " + logo);
        System.out.println("\t" + "What can I do for you?");
        System.out.println("\t" + line);
        String userInput;
        Scanner in = new Scanner(System.in);

        userInput = in.nextLine();
        while (!userInput.equals("bye")) {
            System.out.println("\t" + line);
            System.out.println("\t" + userInput);
            System.out.println("\t" + line);
            userInput = in.nextLine();
        }
        System.out.println("\t" + line);
        System.out.println("\t" + "Bye. Hope to see you again soon!");
        System.out.println("\t" + line);
    }
}
