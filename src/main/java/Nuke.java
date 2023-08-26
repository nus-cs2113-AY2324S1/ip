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

    private static ArrayList<Task> tasks = new ArrayList<>();

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
                    Task task = tasks.get(i);
                    if(task.isDone()) {
                        System.out.printf("%d.[X] %s\n", i + 1, task.getName());
                    } else {
                        System.out.printf("%d.[ ] %s\n", i + 1, task.getName());
                    }
                }
            } else if(input.startsWith("mark ")) {
                int idx = Integer.parseInt(input.substring(5)) - 1;
                tasks.get(idx).setDone(true);
                System.out.println("[☢] Nice! I've marked this task as done:");
                System.out.println("  [X] " + tasks.get(idx).getName());
            } else if(input.startsWith("unmark ")) {
                int idx = Integer.parseInt(input.substring(7)) - 1;
                tasks.get(idx).setDone(false);
                System.out.println("[☢] OK, I've marked this task as not done yet:");
                System.out.println("  [ ] " + tasks.get(idx).getName());
            } else {
                tasks.add(new Task(input));
                System.out.println("added: " + input);
            }
        }
    }
}
