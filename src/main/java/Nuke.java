import java.util.Scanner;

public class Nuke {
    public static String LOGO =
            "⠀⠀⠀⠀⠀ ⠀⠀⠀⠀⠀⢀⣀⣠⣤⣤⣤⣤⣀⣀\n" +
            "⠀⠀⠀⠀⠀⠀⠀⣠⡶⠟⠛⠉⠉⠉⠀⠈⠉⠉⠙⠛⠷⢦⡀\n" +
            "⠀⠀⠀⠀⢀⣴⠟⢁⣤⡀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣠⣄⡀⠻⣦⡀\n" +
            "⠀⠀⠀⣠⠟⢁⣴⣿⣿⣷⡀⠀⠀⠀⠀⠀⠀⠀⠀⣴⣿⣿⣿⣦⠈⢻⡆\n" +
            "⠀⠀⣰⠏⢠⣿⣿⣿⣿⣿⣿⡄⠀⠀⠀⠀⠀⠀⣼⣿⣿⣿⣿⣿⣷⠘⢷⡀\n" +
            "⠀⢰⡟⠀⣿⣿⣿⣿⣿⣿⣿⣿⡄⠀⠀⠀⠀⣼⣿⣿⣿⣿⣿⣿⣿⡆⣿⡆\n" +
            "⠀⣾⡇⢸⣿⣿⣿⣿⣿⣿⣿⣿⠟⣀⣤⣄⠈⢿⣿⣿⣿⣿⣿⣿⣿⠀⢻⡆\n" +
            "⠀⣿⠀⠘⠛⠛⠛⠛⠛⠛⠛⠃⢸⣿⣿⣿⣷⠀⠛⠛⠛⠛⠛⠛⠛⠃⠀⣿\n" +
            "⠀⢻⡇⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠻⣿⣿⠟⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢿\n" +
            "⠀⠸⣷⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣰⣶⣶⣶⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢸⡇\n" +
            "⠀⠀⠹⣇⠀⠀⠀⠀⠀⠀⠀⠀⠀⣼⣿⣿⣿⣿⣧⠀⠀⠀⠀⠀⠀⠀⠀⣰⠇\n" +
            "⠀⠀⠀⠘⢧⡀⠀⠀⠀⠀⠀⣠⣿⣿⣿⣿⣿⣿⣿⣇⠀⠀⠀⠀⢀⣴⠏\n" +
            "⠀⠀⠀⠀⠈⠻⢦⣀⠀⠀⢾⣿⣿⣿⣿⣿⣿⣿⣿⣿⠇⠀⣠⡾⠋\n" +
            "⠀⠀⠀⠀⠀⠀⠀⠉⠛⠶⣦⣤⣀⣉⣉⣉⣉⣩⣤⣴⠶⠛⠁\n" +
            "⠀⠀⠀⠀⠀⠀ ⠀⠀⠀⠀⠀⠈⠉⠉⠉⠉⠉⠉⠁";
    private static boolean running = true;

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        // Welcome message
        System.out.println(LOGO);
        System.out.println();
        System.out.println("[☢] Hello! I'm Nuke.");
        System.out.println("[☢] What can I do for you?");

        // Loop for user input
        while(running) {
            System.out.print("> ");
            String input = in.nextLine();
            if(input.equals("bye")) {
                System.out.println("[☢] Bye. Hope to see you again soon!");
                running = false;
            } else {
                System.out.println(input);
            }
        }
    }
}
