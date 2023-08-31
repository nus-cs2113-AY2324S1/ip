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

        //
        Scanner scanner = new Scanner(System.in);
        String command;
        do {
            command = scanner.nextLine();
            System.out.println("> " + command);
            System.out.println("____________________________________________________________");
        } while (!command.equals("bye"));

        System.out.println("Bye. Hope to see you again soon!");
        System.out.println("____________________________________________________________");

        scanner.close();
    }
}

