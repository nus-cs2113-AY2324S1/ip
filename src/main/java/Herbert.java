import java.util.ArrayList;
import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
        System.out.println("\tHello! I'm Herbert." + System.lineSeparator() + "\tWhat can I do for you?");
        System.out.println(System.lineSeparator() + "\tYou may choose from the following commands:");
        for (Command s : Command.values()) {
            System.out.println("\t- " + s.name().toLowerCase());
        }
        System.out.println("___________________________________________________________________________");
    }

    public void sayGoodbye() {
        System.out.println("___________________________________________________________________________");
        System.out.println("\tBye. Hope to see you again soon!");
        System.out.println("___________________________________________________________________________");
    }

    public void displayHelp() {
        System.out.println("###########################################################################");
        System.out.println("\tWelcome to the Herbert Helpline!" + System.lineSeparator());
        System.out.println("\tCOMMANDS");
        for (Command s : Command.values()) {
            System.out.println("\t* " + s.name().toLowerCase());
            System.out.println(s);
            System.out.println();
        }
        System.out.println("###########################################################################");
    }

    public int processLine(String line) {
        line = line.strip();
        line = line.toLowerCase();

        if (line.equals("bye")) {
            sayGoodbye();
            return 1;
        } else if (line.equals("list")) {
            list();
        } else if (line.equals("help")) {
            displayHelp();
        } else if (line.startsWith("todo") || line.startsWith("deadline") || line.startsWith("event")) {
            addTask(line);
        } else if (line.startsWith("mark")) {
            markTask(line, true);
        } else if (line.startsWith("unmark")) {
            markTask(line, false);
        } else {
            printInvalidInputMessage();
        }

        return 0;
    }

    public void markTask(String line, boolean completed) {
        if (line.split(" ").length != 2) {
            printInvalidInputMessage();
            return;
        }

        // Extract which task the user wishes to (un)mark
        int taskIndex;
        try {
            taskIndex = Integer.parseInt(line.split(" ")[1]) - 1;
            if (taskIndex < 0) {
                throw new IllegalArgumentException("The second argument must be a positive integer.");
            }
        } catch (NumberFormatException e) {
            System.out.printf("The second argument must be numeric (%s)\n", e);
            printInvalidInputMessage();
            return;
        } catch (IllegalArgumentException e) {
            System.out.println(e);
            printInvalidInputMessage();
            return;
        }

        Task task = tasks.get(taskIndex);

        task.setCompleted(completed);

        System.out.println("___________________________________________________________________________");
        if (completed) {
            System.out.println("\tLovely! I've marked this task as done:");
        } else {
            System.out.println("\tOkay, I've unmarked this task for you:");
        }
        System.out.println("\t\t[" + task.getStatusIcon() + "] " + task.getDescription());
        System.out.println("___________________________________________________________________________");
    }

    public void addTask(String line) {
        String[] words = line.split(" ");

        if (words.length < 2) {
            printInvalidInputMessage();
            return;
        }

        switch (words[0]) {
        case "todo": {
            // TODO
            String[] todoArray = Arrays.copyOfRange(words, 1, words.length);
            String description = String.join(" ", todoArray);

            Todo td = new Todo(description);
            this.tasks.add(td);

            System.out.println("___________________________________________________________________________");
            System.out.println("\tOkay, I've added this todo to your task list:");
            System.out.printf("\t\t[%s][%s] %s\n", td.getCode(), td.getStatusIcon(), td);
            System.out.printf("\tNow you have %d task(s) in your list.\n", tasks.size());
            System.out.println("___________________________________________________________________________");

            break;
        }
        case "deadline": {
            String patternString = "^deadline\\s+(.+?)\\s+/by\\s+(.+)$";
            Pattern pattern = Pattern.compile(patternString);
            Matcher matcher = pattern.matcher(line);

            if (!matcher.find()) {
                printInvalidInputMessage();
                return;
            }

            String description = matcher.group(1);
            String date = matcher.group(2);

            Deadline dl = new Deadline(description, date);
            tasks.add(dl);

            System.out.println("___________________________________________________________________________");
            System.out.println("\tOkay, I've added this deadline to your task list:");
            System.out.printf("\t\t[%s][%s] %s\n", dl.getCode(), dl.getStatusIcon(), dl);
            System.out.printf("\tNow you have %d task(s) in your list.\n", tasks.size());
            System.out.println("___________________________________________________________________________");
            break;
        }
        case "event":
            String patternString = "^event\\s+(.+?)\\s+/from\\s+(.+?)\\s+/to\\s+(.+)$";
            Pattern pattern = Pattern.compile(patternString);
            Matcher matcher = pattern.matcher(line);

            if (!matcher.find()) {
                printInvalidInputMessage();
                return;
            }

            String description = matcher.group(1);
            String from = matcher.group(2);
            String to = matcher.group(3);

            Event e = new Event(description, from, to);
            tasks.add(e);

            System.out.println("___________________________________________________________________________");
            System.out.println("\tOkay, I've added this event to your task list:");
            System.out.printf("\t\t[%s][%s] %s\n", e.getCode(), e.getStatusIcon(), e);
            System.out.printf("\tNow you have %d task(s) in your list.\n", tasks.size());
            System.out.println("___________________________________________________________________________");
            break;
        }
    }

    public void list() {
        System.out.println("___________________________________________________________________________");

        if (tasks.size() == 0) {
            System.out.println("\tThere are no tasks in your list! Sit back, relax, and enjoy.");
            System.out.println("___________________________________________________________________________");
            return;
        }

        System.out.println("\tHere are the tasks in your list:");
        for (int i = 0; i < tasks.size(); i++) {
            System.out.printf("\t%d. [%s][%s] %s\n",
                    (i + 1),
                    tasks.get(i).getCode(),
                    tasks.get(i).getStatusIcon(),
                    tasks.get(i).getDescription()
            );
        }

        System.out.println("___________________________________________________________________________");
    }

    public void printInvalidInputMessage() {
        System.out.println("___________________________________________________________________________");
        System.out.println("\tInvalid input. Use 'help' for usage instructions.");
        System.out.println("___________________________________________________________________________");
    }
}



