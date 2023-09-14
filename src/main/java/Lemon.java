import java.util.Scanner;

public class Lemon {
    public static String LEMON_EMOJI = "\uD83C\uDF4B";
    private static final Task[] tasks = new Task[100];
    private static int taskCount = 0;
    private static int taskListIndex = taskCount - 1;

    public static void greet() {
        String LOGO = "                      .--:::.                      \n"
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

        System.out.println(LOGO);
        System.out.println("Hey there! I'm Lemon " +  LEMON_EMOJI);
        System.out.println("How can I add some zest to your day?");
        System.out.print( LEMON_EMOJI + " " +  LEMON_EMOJI + " ");
    }

    public static String getInput() {
        Scanner in = new Scanner(System.in);
        System.out.println(LEMON_EMOJI + "\n");
        return in.nextLine();
    }

    public static void addNewTodo(String input) {
        String task = input.replace("todo ", "");

        taskListIndex++;
        tasks[taskListIndex] = new Todo(task);
        taskCount++;

        printAddedTask();
    }

    public static void addNewDeadline(String input) {
        int byIndex = input.indexOf("/by");
        int DEADLINE_WORD_LENGTH = 9;
        int BY_WORD_LENGTH = 4;
        String task = input.substring(DEADLINE_WORD_LENGTH, byIndex - 1);
        String dateTime = input.substring(byIndex + BY_WORD_LENGTH);

        taskListIndex++;
        tasks[taskListIndex] = new Deadline(task, dateTime);
        taskCount++;

        printAddedTask();
    }

    public static void addNewEvent(String input) {
        int fromIndex = input.indexOf("/from");
        int toIndex = input.indexOf("/to");
        int EVENT_WORD_LENGTH = 6;
        int FROM_WORD_LENGTH = 6;
        int TO_WORD_LENGTH = 4;
        String task = input.substring(EVENT_WORD_LENGTH, fromIndex - 1);
        String startDateTime = input.substring(fromIndex + FROM_WORD_LENGTH, toIndex - 1);
        String endDateTime = input.substring(toIndex + TO_WORD_LENGTH);

        taskListIndex++;
        tasks[taskListIndex] = new Event(task, startDateTime, endDateTime);
        taskCount++;

        printAddedTask();
    }

    public static void addNewTask(String input) {
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

    public static void markTask(String input, boolean isDone) {
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
        System.out.print(LEMON_EMOJI + " " + LEMON_EMOJI + " ");
    }

    public static void main(String[] args) {
        boolean isFinished = false;

        greet();
        String input = getInput();

        while (!isFinished) {
            boolean isEmpty = taskCount <= 0;

            if (input.equals("bye")) {
                isFinished = true;
                exit();
            } else if (input.equals("list")) {
                printList(isEmpty);
            } else if (input.matches(".*\\bmark\\b.*")) {
                if (isEmpty) {
                    printList(true);
                } else {
                    markTask(input, true);
                }
            } else if (input.matches(".*\\bunmark\\b.*")) {
                if (isEmpty) {
                    printList(true);
                } else {
                    markTask(input, false);
                }
            } else if (input.matches(".*\\btodo\\b.*")) {
                addNewTodo(input);
            } else if (input.matches(".*\\bdeadline\\b.*")) {
                addNewDeadline(input);
            } else if (input.matches(".*\\bevent\\b.*")) {
                addNewEvent(input);
            } else {
                addNewTask(input);
            }
            input = getInput();
        }
    }
}
