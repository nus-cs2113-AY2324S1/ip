import java.util.Scanner;

public class Duke {
    private static boolean isFinished = false;

    private static final Scanner IN = new Scanner(System.in);

    public static String getInput() {
        String line;
        line = IN.nextLine();
        if (line.equals("bye")) {
            exit();
        }
        return line;
    }

    public static String listEmptyGetNewCommand() {
        System.out.println("Your list is on a lemonade break right now.");
        System.out.println("\uD83C\uDF4B \n");
        return getInput();
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
        System.out.println("\uD83C\uDF4B \uD83C\uDF4B \uD83C\uDF4B \n");

        Task[] tasks = new Task[100];
        int tasksNum = 0;

        String input;
        input = getInput();

        while (!isFinished) {
            if (input.equals("list")) {
                if (tasksNum <= 0) {
                    input = listEmptyGetNewCommand();
                } else {
                    System.out.println("Your list is looking citrusy-fresh: ");
                    int index = 1;
                    for (int i = 0; i < tasksNum; i++) {
                        System.out.println(index + ". [" +tasks[index - 1].getStatusIcon() + "] " + tasks[index - 1].description);
                        index++;
                    }
                    System.out.println("\uD83C\uDF4B \n");
                    input = getInput();
                }
            } else if (input.matches(".*\\bmark\\b.*")) {
                if (tasksNum <= 0) {
                    input = listEmptyGetNewCommand();
                } else {
                    int index = Integer.parseInt(input.replaceAll("[^0-9]", ""));
                    if (index > tasksNum) {
                        System.out.println("Oops! There is no task " + index + "!");
                        System.out.println("\uD83C\uDF4B \n");
                        input = getInput();
                    } else {
                        tasks[index - 1].markAsDone();
                        System.out.println("Great job! This task is now juiced: ");
                        System.out.println("[" + tasks[index - 1].getStatusIcon() + "] " + tasks[index - 1].description);
                        System.out.println("\uD83C\uDF4B \n");
                        input = getInput();
                    }
                }
            } else if (input.matches(".*\\bunmark\\b.*")) {
                if (tasksNum <= 0) {
                    input = listEmptyGetNewCommand();
                } else {
                    int index = Integer.parseInt(input.replaceAll("[^0-9]", ""));
                    if (index > tasksNum) {
                        System.out.println("Oops! There is no task " + index + "!");
                        System.out.println("\uD83C\uDF4B \n");
                        input = getInput();
                    } else {
                        tasks[index - 1].markAsNotDone();
                        System.out.println("No problem! This task is back into the basket: ");
                        System.out.println("[" + tasks[index - 1].getStatusIcon() + "] " + tasks[index - 1].description);
                        System.out.println("\uD83C\uDF4B \n");
                        input = getInput();
                    }
                }
            } else {
                Task t = new Task(input);
                tasks[tasksNum] = t;
                System.out.println('"' + input + '"' + " has been squeezed into your list!");
                tasksNum++;
                System.out.println("\uD83C\uDF4B \n");
                input = getInput();
            }
        }
    }
}
