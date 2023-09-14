import java.util.Scanner;
import java.util.regex.*;

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

    public static void addNewTodo(String input) throws LemonException {
        try {
            String inputPattern = "todo (.+)";

            Pattern r = Pattern.compile(inputPattern);
            Matcher m = r.matcher(input);

            String task;

            if (m.find()) {
                task = m.group(1).trim();
            } else {
                throw new LemonException("Oopsie! Please use the format 'todo <task>'!");
            }

            if (task.isEmpty()) {
                throw new LemonException("Oopsie! Please state the task!");
            }

            taskListIndex++;
            tasks[taskListIndex] = new Todo(task);
            taskCount++;

            printAddedTask();
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Uh-oh! The basket is full!");
        }
    }

    public static void addNewDeadline(String input) throws LemonException {
        try {
            String inputPattern = "deadline (.+?) /by (.+)";

            Pattern r = Pattern.compile(inputPattern);
            Matcher m = r.matcher(input);

            String task, dateTime;

            if (m.find()) {
                task = m.group(1).trim();
                dateTime = m.group(2).trim();
            } else {
                throw new LemonException("Oopsie! Please use the format 'deadline <task> /by <date/time>'!");
            }

            if (task.isEmpty() || dateTime.isEmpty()) {
                throw new LemonException("Oopsie! Please state the task and date/time!");
            }

            taskListIndex++;
            tasks[taskListIndex] = new Deadline(task, dateTime);
            taskCount++;

            printAddedTask();
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Uh-oh! The basket is full!");
        }
    }

    public static void addNewEvent(String input) throws LemonException {
        try {
            String inputPattern = "event (.+?) /from (.+?) /to (.+)";

            Pattern r = Pattern.compile(inputPattern);
            Matcher m = r.matcher(input);

            String task, startDateTime, endDateTime;

            if (m.find()) {
                task = m.group(1).trim();
                startDateTime = m.group(2).trim();
                endDateTime = m.group(3).trim();
            } else {
                throw new LemonException("Oopsie! Please use the format 'event <task> " +
                        "/from <starting date/time> /to <ending date/time>'!");
            }

            if (task.isEmpty() || startDateTime.isEmpty() || endDateTime.isEmpty()) {
                throw new LemonException("Oopsie! Please state the task, starting date/time and ending date/time!");
            }

            taskListIndex++;
            tasks[taskListIndex] = new Event(task, startDateTime, endDateTime);
            taskCount++;

            printAddedTask();
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Uh-oh! The basket is full!");
        }
    }

    public static void printAddedTask() {
        System.out.println("Got it! This task has been squeezed into your basket:");
        System.out.println("\t" + tasks[taskListIndex].toString());
        System.out.println("Now you have " + taskCount + " fruitful tasks in your basket!");
    }

    public static void printList(boolean isEmpty) throws LemonException {
        if (isEmpty) {
            throw new LemonException("Your basket is on a lemonade break right now! Add some fruitful tasks!");
        } else {
            System.out.println("Your basket is looking citrusy-fresh:");
            int taskSerialNo = 1;
            for (int i = 0; i < taskCount; i++) {
                System.out.println(taskSerialNo + ". " + tasks[i].toString());
                taskSerialNo++;
            }
        }
    }

    public static void markTask(String input, boolean isDone, boolean isEmptyList) throws LemonException {
        if (isEmptyList) {
            if (isDone) {
                throw new LemonException("Your basket is on a lemonade break right now! " +
                        "There are no tasks to be marked. Add some fruitful tasks!");
            } else {
                throw new LemonException("Your basket is on a lemonade break right now! " +
                        "There are no tasks to be unmarked. Add some fruitful tasks!");
            }
        }

        try {
            String inputPattern = "(mark|unmark) (\\d+)";

            Pattern r = Pattern.compile(inputPattern);
            Matcher m = r.matcher(input);

            if (m.find()) {
                int taskSerialNo = Integer.parseInt(m.group(2).trim());
                int taskIndex = taskSerialNo - 1;

                if (isDone) {
                    tasks[taskIndex].markAsDone();
                    System.out.println("Great job! This task is now juiced:");
                } else {
                    tasks[taskIndex].markAsNotDone();
                    System.out.println("No problem! This task is back into the basket:");
                }

                System.out.println("\t" + tasks[taskIndex].toString());
            } else {
                throw new LemonException("Oopsie! Please use the format 'mark/unmark <task number>'!");
            }
        } catch (NumberFormatException e) {
            System.out.println("Oopsie! Please enter a valid task number!");
        } catch (ArrayIndexOutOfBoundsException | NullPointerException e) {
            System.out.println("Oopsie! Please enter a task number that is on the list!");
        }
    }

    public static void exit() {
        System.out.println("Squeeze the day! Until we meet again, stay fresh and zesty!");
        System.out.print(LEMON_EMOJI + " " + LEMON_EMOJI + " "+ LEMON_EMOJI);
    }

    public static void main(String[] args) {
        boolean isFinished = false;
        boolean isEmptyList;
        String input;

        greet();

        while (!isFinished) {
            isEmptyList = taskCount <= 0;
            input = getInput();

            if (input.equals("bye")) {
                exit();
                isFinished = true;
            } else if (input.equals("list")) {
                try {
                    printList(isEmptyList);
                } catch (LemonException e) {
                    System.out.println(e.getMessage());
                }
            } else if (input.matches("todo\\b.*")) {
                try {
                    addNewTodo(input);
                } catch (LemonException e) {
                    System.out.println(e.getMessage());
                }
            } else if (input.matches("deadline\\b.*")) {
                try {
                    addNewDeadline(input);
                } catch (LemonException e) {
                    System.out.println(e.getMessage());
                }
            } else if (input.matches("event\\b.*")) {
                try {
                    addNewEvent(input);
                } catch (LemonException e) {
                    System.out.println(e.getMessage());
                }
            } else if (input.matches("mark\\b.*")) {
                try {
                    markTask(input, true, isEmptyList);
                } catch (LemonException e) {
                    System.out.println(e.getMessage());
                }
            } else if (input.matches("unmark\\b.*")) {
                try {
                    markTask(input, false, isEmptyList);
                } catch (LemonException e) {
                    System.out.println(e.getMessage());
                }
            } else {
                System.out.println("Hmmm! I'm not sure what that means. Could you please specify your request?");
            }
        }
    }
}
