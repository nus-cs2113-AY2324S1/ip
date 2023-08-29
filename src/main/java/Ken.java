import java.util.Scanner;

public class Ken {
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
        String[] storage = new String[100];
        int count = 0;
        while (true) {
            String input = scan.nextLine();
            switch(input) {
            case "list":
                System.out.println(line);
                for (int i = 0; i < count; i++) {
                    System.out.println((i + 1) + ". " + storage[i]);
                }
                System.out.println(line);
                break;
            case "bye":
                System.out.println(line
                        + "Bye. Hope to see you again soon!\n"
                        + line);
                return;
            default:
                storage[count] = input;
                System.out.println(line + "added: " + input + "\n" + line);
                count++;
                break;
            }

        }

    }
}
