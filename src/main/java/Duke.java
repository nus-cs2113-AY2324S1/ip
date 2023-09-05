import java.util.Scanner;
import java.util.Arrays;

public class Duke {
    public static void main(String[] args) {
        String logo = "__________________________________________\n";
        System.out.println(logo + "Hello I'm MatinBot\n" + "What can I do for you?\n" + logo);

        Scanner scanner = new Scanner(System.in);
        String[] toAdd = new String[100];
        int count = 0;
        String userInput;

        do {
            userInput = scanner.nextLine();
            System.out.println(logo);

            if (userInput.equals("list")) {
                String[] taskList = Arrays.copyOf(toAdd, count);
                for (int i = 0; i < taskList.length; i++) {
                    System.out.println((i + 1) + ". " + taskList[i]);
                }
            } else {
                toAdd[count] = userInput;
                System.out.println("added: " + userInput);
                count++;
            }
            System.out.println(logo);
        } while (!userInput.equals("bye"));

        System.out.println("Bye. Hope to see you again soon!\n" + logo);
        scanner.close();
    }
}
