import java.util.Scanner;

public class Duke {
    private static final Scanner IN = new Scanner(System.in);

    private static boolean isFinished = false;
    private static final Task[] tasks = new Task[100];
    private static int taskCount = 0;

    public static String getInput() {
        System.out.println("\uD83C\uDF4B \n");
        String line;
        line = IN.nextLine();
        if (line.equals("bye")) {
            exit();
        }
        return line;
    }

    public static String listEmptyGetNewCommand() {
        System.out.println("Your list is on a lemonade break right now.");
        return getInput();
    }

    public static void printAddedTask() {
        System.out.println("Got it! This task has been squeezed into your list: ");
        System.out.println("\t" + tasks[taskCount].toString());
        taskCount++;
        System.out.println("Now you have " + taskCount + " fruitful tasks in your list!");
    }

    public static void exit() {
        System.out.println("Squeeze the day! Until we meet again, stay fresh and zesty!");
        System.out.println("\uD83C\uDF4B \uD83C\uDF4B \uD83C\uDF4B");
        isFinished = true;
    }

    public static void main(String[] args) {
        String logo = "                      .--:::.                      \n"
                + "                .:-----::::.:-.                    \n"
                + "            .----:::::::::..:=:                    \n"
                + "         .---:::::::::::::::-*.                    \n"
                + "       .=-::::::::::::::::::::=.                   \n"
                + "      :=:::::::::::::::::::::::+.                  \n"
                + "     .=:::::::::::::::::::::::::=                  \n"
                + "     +.:::::::::::::::::::::::::=                  \n"
                + "    -:::::::::::::::::::::::::----:.               \n"
                + "    =:::::::::::::::::::::::-:.   .:---:           \n"
                + "    =:::::::::::::::::::::=* :.:: -  .:.--         \n"
                + "    =::::::::::::::::::::+--..   :::. .-.:=-       \n"
                + "    :-::::::::::::::::::=.:-.:    .:-.--. ::=      \n"
                + "     +:::::::::::::::::=:  + .:::::.-:-....: +     \n"
                + "     --::::::::-:::::::+   .+ -.....--:-..... =    \n"
                + "     :=::::::::::::::::=.   .=:.   .-: :-.  .:=.   \n"
                + "     --::::=--------=----.    --..:-:   :.: .:-.   \n"
                + "      .---=.    ...     =..    .-: .:.  .: .. =    \n"
                + "                        -   .    .-::-:..  .::     \n"
                + "                        .:::::.       .:=+=:       \n"
                + "                              .:::::::-:.          \n"
                + "    ______   ________  ________  ________  ________ \n"
                + "  ╱╱      ╲ ╱        ╲╱        ╲╱        ╲╱    ╱   ╲\n"
                + " ╱╱       ╱╱         ╱         ╱         ╱         ╱\n"
                + "╱        ╱╱        _╱         ╱         ╱         ╱ \n"
                + "╲________╱╲________╱╲__╱__╱__╱╲________╱╲__╱_____╱  \n";

        System.out.println(logo);

        System.out.println("Hey there! I'm Lemon \uD83C\uDF4B");
        System.out.println("How can I add some zest to your day?");
        System.out.print("\uD83C\uDF4B \uD83C\uDF4B ");

        String input;
        input = getInput();

        while (!isFinished) {
            if (input.equals("list")) {
                if (taskCount <= 0) {
                    input = listEmptyGetNewCommand();
                } else {
                    System.out.println("Your list is looking citrusy-fresh: ");
                    int index = 1;
                    for (int i = 0; i < taskCount; i++) {
                        System.out.println(index + "." + tasks[i].toString());
                        index++;
                    }
                    input = getInput();
                }
            } else if (input.matches(".*\\bmark\\b.*")) {
                if (taskCount <= 0) {
                    input = listEmptyGetNewCommand();
                } else {
                    int index = Integer.parseInt(input.replaceAll("[^0-9]", ""));
                    if (index > taskCount) {
                        System.out.println("Oops! There is no task " + index + "!");
                        input = getInput();
                    } else {
                        tasks[index - 1].markAsDone();
                        System.out.println("Great job! This task is now juiced: ");
                        System.out.println("[" + tasks[index - 1].getStatusIcon() + "] " + tasks[index - 1].description);
                        input = getInput();
                    }
                }
            } else if (input.matches(".*\\bunmark\\b.*")) {
                if (taskCount <= 0) {
                    input = listEmptyGetNewCommand();
                } else {
                    int index = Integer.parseInt(input.replaceAll("[^0-9]", ""));
                    if (index > taskCount) {
                        System.out.println("Oops! There is no task " + index + "!");
                        input = getInput();
                    } else {
                        tasks[index - 1].markAsNotDone();
                        System.out.println("No problem! This task is back into the basket: ");
                        System.out.println("[" + tasks[index - 1].getStatusIcon() + "] " + tasks[index - 1].description);
                        input = getInput();
                    }
                }
            } else if (input.matches(".*\\btodo\\b.*")) {
                input = input.replace("todo ", "");
                tasks[taskCount] = new Todo(input);
                printAddedTask();
                input = getInput();
            } else if (input.matches(".*\\bdeadline\\b.*")) {
                int byIdx = input.indexOf("/by");
                String task = input.substring(9, byIdx - 1);
                String dateTime = input.substring(byIdx + 4);

                tasks[taskCount] = new Deadline(task, dateTime);
                printAddedTask();
                input = getInput();
            } else if (input.matches(".*\\bevent\\b.*")) {
                int fromIdx = input.indexOf("/from");
                int toIdx = input.indexOf("/to");
                String task = input.substring(6, fromIdx - 1);
                String startDateTime = input.substring(fromIdx + 6, toIdx - 1);
                String endDateTime = input.substring(toIdx + 4);

                tasks[taskCount] = new Event(task, startDateTime, endDateTime);
                printAddedTask();
                input = getInput();
            } else {
                Task t = new Task(input);
                tasks[taskCount] = t;
                printAddedTask();
                input = getInput();
            }
        }
    }
}
