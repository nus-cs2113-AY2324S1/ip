import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Herbert {

    private ArrayList<Task> tasks;

    public Herbert() {
        this.tasks = new ArrayList<>();
        this.sayHello();
    }

    private void sayHello() {
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

    private void sayGoodbye() {
        System.out.println("___________________________________________________________________________");
        System.out.println("\tBye. Hope to see you again soon!");
        System.out.println("___________________________________________________________________________");
    }

    private void displayHelp() {
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
        String lowerLine = line.toLowerCase();

        if (line.isEmpty()) {
            printMessageInvalidInput();
        }

        if (lowerLine.equals("bye")) {
            sayGoodbye();
            return 1;
        } else if (lowerLine.equals("list")) {
            listTasks();
        } else if (lowerLine.equals("help")) {
            displayHelp();
        } else if (lowerLine.startsWith("todo") || lowerLine.startsWith("deadline") || lowerLine.startsWith("event")) {
            addTask(line);
        } else if (lowerLine.startsWith("mark")) {
            markTask(line, true);
        } else if (lowerLine.startsWith("unmark")) {
            markTask(line, false);
        } else {
            printMessageUnknownCommand(line);
        }

        return 0;
    }

    private void markTask(String line, boolean completed) {
        // Check for valid user input
        if (checkInputMarkTask(line) == -1) {
            return;
        }

        // Extract task index and mark the task as completed
        int taskIndex = extractTaskIndex(line);
        if (taskIndex == -1) {
            return;
        }
        int verify = verifyTaskIndex(taskIndex);
        if (verify == -1) {
            return;
        }
        Task task = tasks.get(taskIndex);
        task.setCompleted(completed);

        // Print result message to user
        printMessageMarkTask(task, completed);
    }

    private int checkInputMarkTask(String line) {
        if (line.split(" ").length != 2) {
            printMessageInvalidInput("Please enter the task number you wish to change the status of.");
            return -1;
        }

        return 0;
    }

    private int extractTaskIndex(String line) {
        int taskIndex;
        try {
            taskIndex = Integer.parseInt(line.split(" ")[1]) - 1;
        } catch (NumberFormatException e) {
            printMessageInvalidInput("Task index must be a positive integer.");
            return -1;
        }

        return taskIndex;
    }

    private int verifyTaskIndex(int taskIndex) {
        if (taskIndex >= tasks.size()) {
            printMessageInvalidInput("No such task exists!");
            return -1;
        }
        if (taskIndex < 0) {
            printMessageInvalidInput("Task index must be a positive integer.");
            return -1;
        }
        return 0;
    }

    private void addTask(String line) {
        if (checkInputAddTask(line) == -1) {
            return;
        }

        String[] words = line.split(" ");
        switch (words[0]) {
        case "todo": {
            // Get details
            String description = line.substring(line.indexOf(" ") + 1);

            // Create and add task
            Todo td = new Todo(description);
            this.tasks.add(td);

            // Print success message
            printMessageAddTask(td);

            break;
        }
        case "deadline": {
            // Get details
            String[] dlDetails = getDeadlineDetails(line);
            if (dlDetails == null) {
                return;
            }

            // Create and add task
            Deadline dl = new Deadline(dlDetails);
            tasks.add(dl);

            // Print success message
            printMessageAddTask(dl);
            break;
        }
        case "event":
            // Get details
            String[] evDetails = getEventDetails(line);
            if (evDetails == null) {
                return;
            }

            // Create and add task
            Event ev = new Event(evDetails);
            tasks.add(ev);

            // Print success message
            printMessageAddTask(ev);
            break;
        }
    }

    private int checkInputAddTask(String line) {
        String[] words = line.split(" ");
        if (words.length < 2) {
            printMessageInvalidInput();
            return -1;
        }
        return 0;
    }

    private String[] getDeadlineDetails(String line) {
        String patternString = "^deadline\\s+(.+?)\\s+/by\\s+(.+)$";
        Pattern pattern = Pattern.compile(patternString);
        Matcher matcher = pattern.matcher(line);

        if (!matcher.find()) {
            printMessageInvalidInput();
            return null;
        }

        return new String[] {matcher.group(1), matcher.group(2)};
    }

    private String[] getEventDetails(String line) {
        String patternString = "^event\\s+(.+?)\\s+/from\\s+(.+?)\\s+/to\\s+(.+)$";
        Pattern pattern = Pattern.compile(patternString);
        Matcher matcher = pattern.matcher(line);

        if (!matcher.find()) {
            printMessageInvalidInput();
            return null;
        }

        return new String[] {matcher.group(1), matcher.group(2), matcher.group(3)};
    }

    public void listTasks() {
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

    //region Message methods
    private void printMessageInvalidInput(String errorMessage) {
        System.out.println("___________________________________________________________________________");
        System.out.printf("\t%s\n", errorMessage);
        System.out.println("\tUse 'help' for usage instructions.");
        System.out.println("___________________________________________________________________________");
    }

    private void printMessageInvalidInput() {
        System.out.println("___________________________________________________________________________");
        System.out.println("\tInvalid input. Use 'help' for usage instructions.");
        System.out.println("___________________________________________________________________________");

    }

    private void printMessageUnknownCommand(String userInput) {
        System.out.println("___________________________________________________________________________");
        System.out.printf("\tUnknown command '%s'. Use 'help' for a full list of user commands.\n", userInput);
        System.out.println("___________________________________________________________________________");
    }

    private void printMessageMarkTask(Task task, boolean completed) {
        System.out.println("___________________________________________________________________________");
        if (completed) {
            System.out.println("\tLovely! I've marked this task as done:");
        } else {
            System.out.println("\tOkay, I've unmarked this task for you:");
        }
        System.out.println("\t\t[" + task.getStatusIcon() + "] " + task.getDescription());
        System.out.println("___________________________________________________________________________");
    }

    private void printMessageAddTask(Task t) {
        System.out.println("___________________________________________________________________________");
        System.out.println("\tOkay, I've added this to your task list:");
        System.out.printf("\t\t[%s][%s] %s\n", t.getCode(), t.getStatusIcon(), t);
        System.out.printf("\tNow you have %d task(s) in your list.\n", tasks.size());
        System.out.println("___________________________________________________________________________");
    }
    //endregion

}



