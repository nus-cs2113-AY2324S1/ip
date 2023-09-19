package duke;

import duke.exception.DukeCommandException;
import duke.exception.DukeTaskException;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.Todo;


import java.io.FileWriter;
import java.io.IOException;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Duke {

    private static final String NAME = "MudMud";
    private static final String HORIZONTAL_LINE = "____________________________________________________________";
    private static final String BY_KEYWORD = " /by ";
    private static final String FROM_KEYWORD = " /from ";
    private static final String TO_KEYWORD = " /to ";

    private static Task[] tasks = new Task[100];
    private static int tasksCount = 0;

    public static String getInput(Scanner in) {
        return in.nextLine().trim();
    }

    public static void tellGreeting() {
        System.out.println("\t" + HORIZONTAL_LINE);
        System.out.println("\tOh hello! I'm " + NAME + ".");
        System.out.println("\tHow can I help you today?");
        System.out.println("\t" + HORIZONTAL_LINE);
    }

    public static void tellGoodbye() {
        System.out.println("\tGoodbye! I am going to sleep now.");
        System.out.println("\t" + HORIZONTAL_LINE);
    }

    public static boolean checkEmptyTodoInput(String input) {
        return input.trim().isEmpty();
    }

    public static void addTodo(String input) throws DukeTaskException, IOException {
        if (checkEmptyTodoInput(input)) {
            throw new DukeTaskException();
        }

        tasks[tasksCount] = new Todo(input.trim());

        FileWriter writer = new FileWriter("data/tasks.txt", true);

        if (tasksCount != 0) {
            writer.write(System.lineSeparator());
        }

        writer.write("T | " + tasks[tasksCount].getStatus() + " | " + tasks[tasksCount].getDescription());

        writer.close();

        tasksCount++;

    }

    public static void addDeadline(String input) throws DukeTaskException, IOException {
        String[] parsedInput = input.split(BY_KEYWORD);

        if (!(parsedInput.length == 2)) {
            throw new DukeTaskException();
        }

        tasks[tasksCount] = new Deadline(parsedInput[0].trim(), parsedInput[1].trim());

        FileWriter writer = new FileWriter("data/tasks.txt", true);

        if (tasksCount != 0) {
            writer.write(System.lineSeparator());
        }

        writer.write("D | " + tasks[tasksCount].getStatus() + " | " + parsedInput[0].trim() +
                 " | "  + parsedInput[1].trim());

        writer.close();

        tasksCount++;
    }

    public static boolean checkNumOfEventKeywords(String input) {
        int numOfFromKeyword = input.split(FROM_KEYWORD).length - 1;
        int numOfToKeyword = input.split(TO_KEYWORD).length - 1;

        return numOfFromKeyword == 1 && numOfToKeyword == 1;
    }

    public static boolean checkPosOfEventKeywords(String input) {
        return input.indexOf(FROM_KEYWORD) < input.indexOf(TO_KEYWORD);
    }

    public static void addEvent(String input) throws DukeTaskException, IOException {
        if (!checkNumOfEventKeywords(input) || !checkPosOfEventKeywords(input)) {
            throw new DukeTaskException();
        }

        String[] parsedInput = input.split(FROM_KEYWORD + "|" + TO_KEYWORD);

        tasks[tasksCount] = new Event(parsedInput[0].trim(), parsedInput[1].trim(), parsedInput[2].trim());

        FileWriter writer = new FileWriter("data/tasks.txt", true);

        if (tasksCount != 0) {
            writer.write(System.lineSeparator());
        }

        writer.write("E | " + tasks[tasksCount].getStatus() + " | " + parsedInput[0].trim() +
                " | "  + parsedInput[1].trim() + " | " + parsedInput[2].trim());

        writer.close();

        tasksCount++;
    }

    //Solution below adapted by https://www.tutorialspoint.com/how-to-overwrite-a-line-in-a-txt-file-using-java
    public static void updateTaskDatabase(int index, boolean taskStatus) throws FileNotFoundException, IOException {
        Scanner scanner = new Scanner(new File("data/tasks.txt"));

        StringBuffer buffer = new StringBuffer();
        String change = taskStatus ? "true" : "false";
        int currentIndex = 0;

        while (scanner.hasNext()) {
            if (currentIndex == index) {
                String[] parsedTask = scanner.nextLine().split(" \\| ");
                parsedTask[1] = change;

                for (int i = 0; i < parsedTask.length; i++) {
                    buffer.append(parsedTask[i]);

                    if (i < parsedTask.length - 1) {
                        buffer.append(" | ");
                    }
                }
                buffer.append(System.lineSeparator());
            } else {
                buffer.append(scanner.nextLine());

                if (scanner.hasNext()) {
                    buffer.append(System.lineSeparator());
                }
            }
            currentIndex++;
        }

        FileWriter writer = new FileWriter("data/tasks.txt");
        writer.append(buffer.toString());
        writer.close();
    }

    public static void setMarkAsDone(String input) {
        int index = Integer.parseInt(input) - 1;

        try {
            updateTaskDatabase(index, true);
        } catch (Exception e) {
            System.out.println("error");
        }

        tasks[index].markAsDone();

        System.out.println("\tYay! You have completed this task:");
        System.out.println("\t\t" + tasks[index]);
    }

    public static void setUnmarkAsDone(String input) {
        int index = Integer.parseInt(input) - 1;

        try {
            updateTaskDatabase(index, false);
        } catch (Exception e) {
            System.out.println("error");
        }

        tasks[index].unmarkAsDone();

        System.out.println("\tOh no! It seems that you haven't finish this task:");
        System.out.println("\t\t" + tasks[index]);
    }

    public static void printNumOfTasks() {
        System.out.println("\tI took a peak at the list and you have " + tasksCount
                + (tasksCount == 1 ? " task" : " tasks") + " currently.");
    }

    public static void printRecentTask(Task task) {
        System.out.println("\tI have added the following task into the list:");
        System.out.println("\t\t" + task);
        printNumOfTasks();
    }

    public static void printTasks() {
        System.out.println("\tHere are your tasks you have inputted:");
        for (int i = 1; i <= tasksCount; i++) {
            System.out.println("\t" + i + "." + tasks[i - 1]);
        }
    }

    public static void handleNullPointerException() {
        System.out.println("\tUmm, you tried to access a task that does not exist.");
        System.out.println("\tPerhaps you should put a valid number based on the number of tasks " +
                "you have currently. ");
        printNumOfTasks();
    }

    public static void handleNumberFormatException(String input) {
        System.out.println("\tPlease use a valid number for marking or unmarking a task.");
        System.out.println("\tThe number you tried to input is: " + input);
    }

    public static void executeCommand(String input) {
        String[] parsedInput = input.split(" ", 2);
        String command = parsedInput[0];
        input = parsedInput.length == 1 ? " " : parsedInput[1].trim();

        try {
            switch (command) {
            case "list":
                printTasks();
                break;
            case "mark":
                setMarkAsDone(input);
                break;
            case "unmark":
                setUnmarkAsDone(input);
                break;
            case "todo":
                addTodo(input);
                printRecentTask(tasks[tasksCount - 1]);
                break;
            case "deadline":
                addDeadline(input);
                printRecentTask(tasks[tasksCount - 1]);
                break;
            case "event":
                addEvent(input);
                printRecentTask(tasks[tasksCount - 1]);
                break;
            case "bye":
                tellGoodbye();
                System.exit(0);
            default:
                throw new DukeCommandException();
            }
        } catch (NullPointerException exception) {
            handleNullPointerException();
        } catch (NumberFormatException exception) {
            handleNumberFormatException(input);
        } catch (DukeCommandException exception) {
            exception.handleDukeCommandException(command);
        } catch (DukeTaskException exception) {
            exception.handleDukeTaskException(command, input);
        } catch (IOException exception) {
            System.out.println("error");
        }
    }

    public static void restoreSavedData() throws FileNotFoundException {
        File file = new File("data/tasks.txt");
        Scanner scan = new Scanner(file);
        String[] parsedTask;

        while (scan.hasNext()) {

            parsedTask = scan.nextLine().split(" \\| ");
            String taskType = parsedTask[0];

            switch (taskType) {
            case "T":
                tasks[tasksCount] = new Todo(parsedTask[2] , parsedTask[1]);
                tasksCount++;
                break;
            case "D":
                tasks[tasksCount] = new Deadline(parsedTask[2], parsedTask[1], parsedTask[3]);
                tasksCount++;
                break;
            case "E":
                tasks[tasksCount] = new Event(parsedTask[2], parsedTask[1], parsedTask[3], parsedTask[4]);
                tasksCount++;
                break;
            }
        }
    }

    public static void main(String[] args) {
        String input = "";
        Scanner in = new Scanner(System.in);

        try {
            restoreSavedData();
        } catch (FileNotFoundException e){
            System.out.println("error");
        }

        tellGreeting();
        while (true) {
            input = getInput(in);
            System.out.println("\t" + HORIZONTAL_LINE);
            executeCommand(input);
            System.out.println("\t" + HORIZONTAL_LINE);
        }
    }
}