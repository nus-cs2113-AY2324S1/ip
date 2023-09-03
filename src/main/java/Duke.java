import java.util.Scanner;

public class Duke {
    private static final Scanner IN = new Scanner(System.in);

    private static String input = null;
    private static boolean isFinished = false;
    private static final Task[] tasks = new Task[100];
    private static int taskCount = 0;
    private static int taskListIndex = taskCount - 1;

    public static void greet() {
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

    }

    public static String getInput() {
        System.out.println("\uD83C\uDF4B\n");
        String line;
        line = IN.nextLine();
        if (line.equals("bye")) {
            exit();
        }
        return line;
    }

    public static void addNewTodo() {
        String task = input.replace("todo ", "");

        taskListIndex++;
        tasks[taskListIndex] = new Todo(task);
        taskCount++;

        printAddedTask();
    }

    public static void addNewDeadline() {
        int byIndex = input.indexOf("/by");
        int deadlineWordLength = 9;
        int byWordLength = 4;
        String task = input.substring(deadlineWordLength, byIndex - 1);
        String dateTime = input.substring(byIndex + byWordLength);

        taskListIndex++;
        tasks[taskListIndex] = new Deadline(task, dateTime);
        taskCount++;

        printAddedTask();
    }

    public static void addNewEvent() {
        int fromIndex = input.indexOf("/from");
        int toIndex = input.indexOf("/to");
        int eventWordLength = 6;
        int fromWordLength = 6;
        int toWordLength = 4;
        String task = input.substring(eventWordLength, fromIndex - 1);
        String startDateTime = input.substring(fromIndex + fromWordLength, toIndex - 1);
        String endDateTime = input.substring(toIndex + toWordLength);

        taskListIndex++;
        tasks[taskListIndex] = new Event(task, startDateTime, endDateTime);
        taskCount++;

        printAddedTask();
    }

    public static void addNewTask() {
        taskListIndex++;
        tasks[taskListIndex] = new Task(input);
        taskCount++;

        printAddedTask();
    }

    public static void printAddedTask() {
        System.out.println("Got it! This task has been squeezed into your basket:");
        System.out.println("\t" + tasks[taskListIndex].toString());
        System.out.println("Now you have " + taskCount + " fruitful tasks in your basket!");
    }

    public static void printList(boolean isEmpty) {
        if (isEmpty) {
            System.out.println("Your basket is on a lemonade break right now.");
        } else {
            System.out.println("Your basket is looking citrusy-fresh:");
            int taskSerialNo = 1;
            for (int i = 0; i < taskCount; i++) {
                System.out.println(taskSerialNo + ". " + tasks[i].toString());
                taskSerialNo++;
            }
        }
    }

    public static void markTask(boolean isDone) {
        int taskSerialNo = Integer.parseInt(input.replaceAll("[^0-9]", ""));
        int taskIndex = taskSerialNo - 1;
        boolean isIndexWithinRange = taskSerialNo <= taskCount;

        if (!isIndexWithinRange) {
            System.out.println("Oops! There is no task " + taskSerialNo + "!");
        } else {
            if (isDone) {
                tasks[taskIndex].markAsDone();
                System.out.println("Great job! This task is now juiced:");
            } else {
                tasks[taskIndex].markAsNotDone();
                System.out.println("No problem! This task is back into the basket:");
            }
            System.out.println("[" + tasks[taskIndex].getStatusIcon() + "] " + tasks[taskIndex].description);
        }
    }

    public static void exit() {
        System.out.println("Squeeze the day! Until we meet again, stay fresh and zesty!");
        System.out.println("\uD83C\uDF4B \uD83C\uDF4B \uD83C\uDF4B");
        isFinished = true;
    }

    public static void main(String[] args) {
        greet();
        input = getInput();

        while (!isFinished) {
            boolean isEmpty = taskCount <= 0;

            if (input.equals("list")) {
                printList(isEmpty);
            } else if (input.matches(".*\\bmark\\b.*")) {
                if (isEmpty) {
                    printList(true);
                } else {
                    markTask(true);
                }
            } else if (input.matches(".*\\bunmark\\b.*")) {
                if (isEmpty) {
                    printList(true);
                } else {
                    markTask(false);
                }
            } else if (input.matches(".*\\btodo\\b.*")) {
                addNewTodo();
            } else if (input.matches(".*\\bdeadline\\b.*")) {
                addNewDeadline();
            } else if (input.matches(".*\\bevent\\b.*")) {
                addNewEvent();
            } else {
                addNewTask();
            }
            input = getInput();
        }
    }
}
