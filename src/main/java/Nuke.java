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

    private static final ArrayList<Task> tasks = new ArrayList<>();

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
            runCommand(input);
        }
    }

    private static void runCommand(String line) {
        String[] words = line.split(" ");
        String type = words[0];
        // String[] args = Arrays.copyOfRange(words, 1, words.length);

        int idx;
        switch(type) {
        case "bye":
            System.out.println("[☢] Bye. Hope to see you again soon!");
            running = false;
            break;
        case "list":
            listTask();
            break;
        case "mark":
            idx = Integer.parseInt(words[1]) - 1;
            markTask(idx);
            break;
        case "unmark":
            idx = Integer.parseInt(words[1]) - 1;
            unmarkTask(idx);
            break;
        default:
            addTask(line);
        }
    }

    private static void addTask(String taskName) {
        tasks.add(new Task(taskName));
        System.out.println("added: " + taskName);
    }

    private static void listTask() {
        for(int i = 0; i < tasks.size(); i++) {
            Task task = tasks.get(i);
            String mark = task.isDone()? "X" : " ";
            System.out.printf("%d.[%s] %s\n", i + 1, mark, task.getName());
        }
    }

    private static void markTask(int idx) {
        tasks.get(idx).setDone(true);
        System.out.println("[☢] Nice! I've marked this task as done:");
        System.out.println("  [X] " + tasks.get(idx).getName());
    }

    private static void unmarkTask(int idx) {
        tasks.get(idx).setDone(false);
        System.out.println("[☢] OK, I've marked this task as not done yet:");
        System.out.println("  [ ] " + tasks.get(idx).getName());
    }
}
