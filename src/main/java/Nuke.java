import java.util.ArrayList;
import java.util.Arrays;
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
        String[] args = Arrays.copyOfRange(words, 1, words.length);

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
        case "todo":
            String todoName = String.join(" ", args);
            addTask(new Todo(todoName));
            break;
        case "deadline":
            int byIdx = Arrays.asList(args).indexOf("/by");
            String[] deadlineNameArr = Arrays.copyOfRange(args, 0, byIdx);
            String deadlineName = String.join(" ", deadlineNameArr);
            String[] byArr = Arrays.copyOfRange(args, byIdx + 1, args.length);
            String by = String.join(" ", byArr);
            addTask(new Deadline(deadlineName, by));
            break;
        case "event":
            int fromIdx = Arrays.asList(args).indexOf("/from");
            int toIdx = Arrays.asList(args).indexOf("/to");
            String[] eventNameArr = Arrays.copyOfRange(args, 0, fromIdx);
            String eventName = String.join(" ", eventNameArr);
            String[] fromArr = Arrays.copyOfRange(args, fromIdx + 1, toIdx);
            String from = String.join(" ", fromArr);
            String[] toArr = Arrays.copyOfRange(args, toIdx + 1, args.length);
            String to = String.join(" ", toArr);
            addTask(new Event(eventName, from, to));
            break;
        default:
            addTask(new Task(line));
        }
    }

    private static void addTask(Task task) {
        tasks.add(task);
        System.out.println("[☢] Got it. I've added this task:");
        System.out.println("  " + task);
        System.out.printf("[☢] Now you have %d tasks in the list.\n", tasks.size());
    }

    private static void listTask() {
        System.out.println("[☢] Here are the tasks in you list:");
        for(int i = 0; i < tasks.size(); i++) {
            Task task = tasks.get(i);
            System.out.printf("%d.%s\n", i + 1, task.toString());
        }
    }

    private static void markTask(int idx) {
        Task task = tasks.get(idx);
        task.setDone(true);
        System.out.println("[☢] Nice! I've marked this task as done:");
        System.out.println("  " + task);
    }

    private static void unmarkTask(int idx) {
        Task task = tasks.get(idx);
        task.setDone(false);
        System.out.println("[☢] OK, I've marked this task as not done yet:");
        System.out.println("  " + task);
    }
}
