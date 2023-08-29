import java.util.Scanner;

public class Herbert {

    public static void greeting() {
        String logo = "(   )                           (   )                          (   )       \n"
                + " | | .-.     .--.    ___ .-.     | |.-.     .--.    ___ .-.     | |_       \n"
                + " | |/   \\   /    \\  (   )   \\    | /   \\   /    \\  (   )   \\   (   __)     \n"
                + " |  .-. .  |  .-. ;  | ' .-. ;   |  .-. | |  .-. ;  | ' .-. ;   | |        \n"
                + " | |  | |  |  | | |  |  / (___)  | |  | | |  | | |  |  / (___)  | | ___    \n"
                + " | |  | |  |  |/  |  | |         | |  | | |  |/  |  | |         | |(   )   \n"
                + " | |  | |  |  ' _.'  | |         | |  | | |  ' _.'  | |         | | | |    \n"
                + " | |  | |  |  .'.-.  | |         | '  | | |  .'.-.  | |         | ' | |    \n"
                + " | |  | |  '  `-' /  | |         ' `-' ;  '  `-' /  | |         ' `-' ;    \n"
                + "(___)(___)  `.__.'  (___)         `.__.    `.__.'  (___)         `.__.     \n";


        System.out.println(System.lineSeparator() + logo);
        System.out.println("___________________________________________________________________________");
        System.out.println("\tHello! I'm Herbert.\n\tWhat can I do for you?");
        System.out.println("___________________________________________________________________________");
        System.out.println();
    }

    public static void exit() {
        System.out.println("___________________________________________________________________________");
        System.out.println("\tBye. Hope to see you again soon!");
        System.out.println("___________________________________________________________________________");
    }

    public static void main(String[] args) {
        greeting();

        Scanner scan = new Scanner(System.in);

        String line;
        while (scan.hasNextLine()) {
            line = scan.nextLine();

            if (line.equalsIgnoreCase("bye")) {
                exit();
                break;
            }

            System.out.println("___________________________________________________________________________");
            System.out.println("\t" + line);
            System.out.println("___________________________________________________________________________");
            System.out.println();
        }
    }
}

