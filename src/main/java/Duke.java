import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        final String logo = "MudMud";

        Scanner in = new Scanner(System.in);
        String input = "";

        String[] tasks = new String[100];
        int tasksCount = 0;

        System.out.println("\t____________________________________________________________");
        System.out.println("\tOh hello! I'm " + logo + ".");
        System.out.println("\tPlease input something and I will repeat it for you.");
        System.out.println("\t____________________________________________________________");

        while (!input.equals("bye")) {
            input = in.nextLine();

            System.out.println("\t____________________________________________________________");
            if (input.equals("bye")) {
                System.out.println("\tGoodbye! I am going to sleep now.");
            } else if (input.equals("list")) {
                for (int i = 1; i <= tasksCount; i++) {
                    System.out.println("\t" + i + ". " + tasks[i - 1]);
                }
            } else {
                tasks[tasksCount] = input;
                tasksCount++;
                System.out.println("\tI have added \""  + input + "\" into the list.");
            }
            System.out.println("\t____________________________________________________________");
        }
    }
}