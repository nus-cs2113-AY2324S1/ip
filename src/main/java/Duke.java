import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String logo = "__________________________________________\n";
        System.out.println(logo + "Hello I'm MatinBot\n" + "What can I do for you?\n" + logo);

        Scanner scanner = new Scanner(System.in);
        String userInput;

        do {
            userInput = scanner.nextLine();
            System.out.println(logo + userInput + "\n" + logo);
        } while (!userInput.equals("bye"));

        System.out.println("Bye. Hope to see you again soon!\n" + logo);
        scanner.close();
    }
}
