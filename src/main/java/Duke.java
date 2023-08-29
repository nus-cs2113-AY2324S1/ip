import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String logo = " _   __ ______  __    __\n"
                    + "| | / /|  ____||  \\  |  |\n"
                    + "| |/ / | |____ |   \\ |  |\n"
                    + "|   /  |  ____||    \\|  |\n"
                    + "| |\\ \\ | |____ |        |\n"
                    + "|_| \\_\\|______||__|\\____|";
        String line = "_______________________________________________\n";
        System.out.println(logo);

        System.out.println(line
                + "Hello! I'm Ken!\n"
                + "What can I do for you?\n"
                + line);

        Scanner scan = new Scanner(System.in);
        while (true) {
            String input = scan.nextLine();
            if (input.equals("bye")) {
                System.out.println(line
                    + "Bye. Hope to see you again soon!\n"
                    + line);
                return;
            }
            System.out.println(line + input + "\n" + line);

        }

    }
}
