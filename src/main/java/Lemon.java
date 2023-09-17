import lemon.command.LemonException;
import lemon.task.Deadline;
import lemon.task.Event;
import lemon.task.Task;
import lemon.task.Todo;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.ArrayList;

public class Lemon {
    public static String LEMON_EMOJI = "\uD83C\uDF4B";
    private static ArrayList<Task> tasks = new ArrayList<>();
    //private static int taskCount = 0;
    //private static int taskListIndex = taskCount - 1;

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
        String inputPattern = "todo (.+)";

        Pattern pattern = Pattern.compile(inputPattern);
        Matcher matcher = pattern.matcher(input);
        boolean matchFound = matcher.find();

        String task;

        if (matchFound) {
            task = matcher.group(1).trim();

            if (task.isEmpty()) {
                throw new LemonException("Oopsie! Please state the task!");
            }

            Task newTask = new Todo(task);
            tasks.add(newTask);
            printAddedOrDeletedTask(newTask, "add");
        } else {
            throw new LemonException("Oopsie! Please use the format 'todo <task>'!");
        }
    }

    public static void addNewDeadline(String input) throws LemonException {
        String inputPattern = "deadline (.+?) /by (.+)";

        Pattern pattern = Pattern.compile(inputPattern);
        Matcher matcher = pattern.matcher(input);
        boolean matchFound = matcher.find();

        String task, dateTime;

        if (matchFound) {
            task = matcher.group(1).trim();
            dateTime = matcher.group(2).trim();

            if (task.isEmpty() || dateTime.isEmpty()) {
                throw new LemonException("Oopsie! Please state the task and date/time!");
            }

            Task newTask = new Deadline(task, dateTime);
            tasks.add(newTask);
            printAddedOrDeletedTask(newTask, "add");
        } else {
            throw new LemonException("Oopsie! Please use the format 'deadline <task> /by <date/time>'!");
        }
    }

    public static void addNewEvent(String input) throws LemonException {
        String inputPattern = "event (.+?) /from (.+?) /to (.+)";

        Pattern pattern = Pattern.compile(inputPattern);
        Matcher matcher = pattern.matcher(input);
        boolean matchFound = matcher.find();

        String task, startDateTime, endDateTime;

        if (matchFound) {
            task = matcher.group(1).trim();
            startDateTime = matcher.group(2).trim();
            endDateTime = matcher.group(3).trim();

            if (task.isEmpty() || startDateTime.isEmpty() || endDateTime.isEmpty()) {
                throw new LemonException("Oopsie! Please state the task, starting date/time and ending date/time!");
            }

            Task newTask = new Event(task, startDateTime, endDateTime);
            tasks.add(newTask);
            printAddedOrDeletedTask(newTask, "add");
        } else {
            throw new LemonException("Oopsie! Please use the format 'event <task> " +
                    "/from <starting date/time> /to <ending date/time>'!");
        }
    }

    public static void deleteTask(String input, boolean isEmptyList) throws LemonException {
        checkEmptyList("delete", isEmptyList);

        try {
            String inputPattern = "delete (\\d+)";

            Pattern pattern = Pattern.compile(inputPattern);
            Matcher matcher = pattern.matcher(input);
            boolean matchFound = matcher.find();

            if (matchFound) {
                int taskSerialNo = Integer.parseInt(matcher.group(1).trim());
                int taskIndex = taskSerialNo - 1;

                Task deletedTask = tasks.remove(taskIndex);
                printAddedOrDeletedTask(deletedTask, "delete");
            } else {
                throw new LemonException("Oopsie! Please use the format 'delete <task number>'!");
            }
        } catch (NumberFormatException e) {
            System.out.println("Oopsie! Please enter a valid task number!");
        } catch (IndexOutOfBoundsException | NullPointerException e) {
            System.out.println("Oopsie! Please enter a task number that is on the list!");
        }
    }

    public static void printAddedOrDeletedTask(Task task, String action) {
        if (action.equals("add")) {
            System.out.println("Got it! This task has been squeezed into your basket:");
        } else {
            System.out.println("Got it! This task has been squeezed out of your basket:");
        }
        System.out.println("\t" + task.toString());
        System.out.println("Now you have " + tasks.size() + " fruitful tasks in your basket!");
    }

    public static void printList(boolean isEmpty) throws LemonException {
        if (isEmpty) {
            throw new LemonException("Your basket is on a lemonade break right now! Add some fruitful tasks!");
        } else {
            System.out.println("Your basket is looking citrusy-fresh:");
            int taskSerialNo = 1;
            for (Task task : tasks) {
                System.out.println(taskSerialNo + ". " + task.toString());
                taskSerialNo++;
            }
        }
    }

    public static void checkEmptyList(String action, boolean isEmptyList) throws LemonException {
        if (isEmptyList) {
            if (action.equals("mark")) {
                throw new LemonException("Your basket is on a lemonade break right now! " +
                        "There are no tasks to be marked. Add some fruitful tasks!");
            } else if (action.equals("unmark")) {
                throw new LemonException("Your basket is on a lemonade break right now! " +
                        "There are no tasks to be unmarked. Add some fruitful tasks!");
            } else {
                throw new LemonException("Your basket is on a lemonade break right now! " +
                        "There are no tasks to be deleted. Add some fruitful tasks!");
            }
        }
    }

    public static void markTask(String input, String action, boolean isEmptyList) throws LemonException {
        checkEmptyList(action, isEmptyList);

        try {
            String inputPattern = "(mark|unmark) (\\d+)";

            Pattern pattern = Pattern.compile(inputPattern);
            Matcher matcher = pattern.matcher(input);
            boolean matchFound = matcher.find();

            if (matchFound) {
                int taskSerialNo = Integer.parseInt(matcher.group(2).trim());
                int taskIndex = taskSerialNo - 1;

                if (action.equals("mark")) {
                    tasks.get(taskIndex).markAsDone();
                    System.out.println("Great job! This task is now juiced:");
                } else {
                    tasks.get(taskIndex).markAsNotDone();
                    System.out.println("No problem! This task is back into the basket:");
                }

                System.out.println("\t" + tasks.get(taskIndex).toString());
            } else {
                throw new LemonException("Oopsie! Please use the format 'mark/unmark <task number>'!");
            }
        } catch (NumberFormatException e) {
            System.out.println("Oopsie! Please enter a valid task number!");
        } catch (IndexOutOfBoundsException | NullPointerException e) {
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
            isEmptyList = tasks.size() <= 0;
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
            } else if (input.matches("delete\\b.*")) {
                try {
                    deleteTask(input, isEmptyList);
                } catch (LemonException e) {
                    System.out.println(e.getMessage());
                }
            } else if (input.matches("mark\\b.*")) {
                try {
                    markTask(input, "mark", isEmptyList);
                } catch (LemonException e) {
                    System.out.println(e.getMessage());
                }
            } else if (input.matches("unmark\\b.*")) {
                try {
                    markTask(input, "unmark", isEmptyList);
                } catch (LemonException e) {
                    System.out.println(e.getMessage());
                }
            } else {
                System.out.println("Hmmm! I'm not sure what that means. Could you please specify your request?");
            }
        }
    }
}
