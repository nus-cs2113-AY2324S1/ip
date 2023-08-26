import java.util.ArrayList;
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

    private static ArrayList<String> tasks = new ArrayList<>();

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
            } else if(input.equals("list")) {
                for(int i = 0; i < tasks.size(); i++) {
                    System.out.printf("%d. %s\n", i + 1, tasks.get(i));
                }
            } else {
                tasks.add(input);
                System.out.println("added: " + input);
            }
        }
    }
}
