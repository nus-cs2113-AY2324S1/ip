import java.util.ArrayList;
import java.lang.StringBuilder;

public class Herbert {

    private ArrayList<Task> tasks;

    public Herbert() {
        this.tasks = new ArrayList<>();
        this.sayHello();
    }

    public void sayHello() {
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
    }

    public void sayGoodbye() {
        System.out.println("___________________________________________________________________________");
        System.out.println("\tBye. Hope to see you again soon!");
        System.out.println("___________________________________________________________________________");
    }

    public int processLine(String line) {
        line = line.strip();
        line = line.toLowerCase();

        if (line.equals("bye")) {
            sayGoodbye();
            return 1;
        } else if (line.equals("list")) {
            list();
        } else if (line.startsWith("mark")) {
            markTask(line, true);
        } else if (line.startsWith("unmark")) {
            markTask(line, false);
        } else {
            addTask(line);
        }

        return 0;
    }

    public void markTask(String line, boolean completed) {
        if (line.split(" ").length != 2) {
            if (completed) {
                System.out.println("Invalid command. Usage: mark <task number>");
            } else {
                System.out.println("Invalid command. Usage: unmark <task number>");
            }
        }

        int taskToMarkIndex = Integer.parseInt(line.split(" ")[1]) - 1;
        Task taskToMark = tasks.get(taskToMarkIndex);

        taskToMark.setCompleted(completed);

        System.out.println("___________________________________________________________________________");
        if (completed) {
            System.out.println("\tLovely! I've marked this task as done:");
        } else {
            System.out.println("\tOkay, I've unmarked this task for you:");
        }
        System.out.println("\t   [" + taskToMark.getStatusIcon() + "] " + taskToMark.getDescription());
        System.out.println("___________________________________________________________________________");
    }

    public void addTask(String line) {
        Task t = new Task(line);

        this.tasks.add(t);
        System.out.println("___________________________________________________________________________");
        System.out.println("\tAdded: " + t.getDescription());
        System.out.println("___________________________________________________________________________");
    }

    public void list() {
        System.out.println("___________________________________________________________________________");
        System.out.println("\tHere are the tasks in your list:");
        for (int i = 0; i < tasks.size(); i++) {
            String s = "\t" +
                    (i + 1) +
                    ". [" +
                    tasks.get(i).getStatusIcon() +
                    "] " +
                    tasks.get(i).getDescription();
            System.out.println(s);
        }
        System.out.println("___________________________________________________________________________");
    }

}

