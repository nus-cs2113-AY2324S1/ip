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
        String[] userList = new String[100];
        int listCounter = 0;

        Scanner in = new Scanner(System.in);
        userInput = in.nextLine();
        while (!userInput.equals("bye")) {
            switch (userInput) {
            case "list":
                System.out.println("\t" + line);
                for (int i = 0; i < listCounter; i++) {
                    System.out.println("\t" + (i + 1) + ". " + userList[i]);
                }
                System.out.println("\t" + line);
                userInput = in.nextLine();
                break;
            default:
                userList[listCounter] = userInput;
                listCounter++;
                System.out.println("\t" + line);
                System.out.println("\t" + "added: " + userInput);
                System.out.println("\t" + line);
                userInput = in.nextLine();
            }
        }
        System.out.println("\t" + line);
        System.out.println("\t" + "Bye. Hope to see you again soon!");
        System.out.println("\t" + line);
    }
}
