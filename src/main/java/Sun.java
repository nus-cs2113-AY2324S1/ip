import java.util.Scanner;

public class Sun {
    public static void main(String[] args) {
        String logo = " ____               \n"
                    + "| ___| _   _  ______ \n"
                    + "| \\__ | | | || /--\\ |\n"
                    + " \\___|| |_| || |  | |\n"
                    + "/____/ \\__,_||_|  |_|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println("____________________________________________________________");
        System.out.println("Hello! I'm Sun!");
        System.out.println("What can I do for you?");
        System.out.println("____________________________________________________________");
        Scanner scanner = new Scanner(System.in);
        String[] tasks = new String[100];
        int taskCount = 0;
        String command;
        do{
            command = scanner.nextLine();
            if (!command.equals("bye")) {
                if (command.equals("list")) {
                    System.out.println("____________________________________________________________");
                    for (int i = 0; i < taskCount; i++) {
                        System.out.println((i + 1) + ". " + tasks[i]);
                    }
                    System.out.println("____________________________________________________________");
                } else {
                    tasks[taskCount] = command;
                    taskCount++;
                    System.out.println("____________________________________________________________");
                    System.out.println("> added: " + command);
                    System.out.println("____________________________________________________________");
                }
            }
        } while (!command.equals("bye"));

        System.out.println("Bye. Hope to see you again soon!");
        System.out.println("____________________________________________________________");

        scanner.close();
    }
}

