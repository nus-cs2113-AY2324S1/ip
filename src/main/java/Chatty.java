import java.util.Scanner;
public class Chatty {
    /*public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
    }
     */
    public static void main(String[] args) {
        System.out.println("____________________________________________________________");
        System.out.println("Hello! I'm Chatty!\nWhat can I do for you?");
        System.out.println("____________________________________________________________\n");
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();
        String[] tasks = new String[100];
        int taskCount = 0;
        while (!input.equalsIgnoreCase("bye")){
            if (input.equalsIgnoreCase("list")) {
                System.out.println("____________________________________________________________");
                for (int i = 0; i < taskCount; i++) {
                    System.out.println((i + 1) + ". " + tasks[i]);
                }
                System.out.println("____________________________________________________________");
                input = scanner.nextLine();
                continue;
            }
            System.out.println("____________________________________________________________");
            tasks[taskCount]=input;
            taskCount++;
            System.out.println("added: "+input);
            System.out.println("____________________________________________________________\n");
            input = scanner.nextLine();
        }
        System.out.println("____________________________________________________________");
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println("____________________________________________________________");
    }
}
