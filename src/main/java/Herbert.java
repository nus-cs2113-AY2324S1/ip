import java.sql.SQLOutput;

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

        System.out.println("Hello from\n" + logo);

        System.out.println("___________________________________________________________________________");
        System.out.println(" Hello! I'm Herbert.\n What can I do for you?");
        System.out.println("___________________________________________________________________________");
        System.out.println(" Bye. Hope to see you again soon!");
        System.out.println("___________________________________________________________________________");


    }

    public static void main(String[] args) {
        greeting();
    }
}

