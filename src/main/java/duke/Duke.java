package duke;

import duke.exception.DukeCommandException;
import duke.exception.DukeTaskException;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.Todo;
import duke.ui.TextUi;

import java.util.ArrayList;
import java.io.FileWriter;
import java.io.IOException;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Duke {

    private static TextUi ui;

    private static final String NAME = "MudMud";
    private static final String HORIZONTAL_LINE = "____________________________________________________________";
    private static final String BY_KEYWORD = " /by ";
    private static final String FROM_KEYWORD = " /from ";
    private static final String TO_KEYWORD = " /to ";
    private static final String DATA_PATH = "data/tasks.txt";
    private static final String DATA_DIRECTORY = "data";

    private static ArrayList<Task> tasks = new ArrayList<>();
    private static int tasksCount = 0;

    public static String getInput(Scanner in) {
        return in.nextLine().trim();
    }

    public static boolean checkEmptyTodoInput(String input) {
        return input.trim().isEmpty();
    }

    private static void addNewData(String dataString) throws IOException {
        FileWriter writer = new FileWriter(DATA_PATH, true);

        if (tasksCount != 0) {
            writer.write(System.lineSeparator());
        }

        writer.write(dataString);
        writer.close();
    }

    public static void addTodo(String input) throws DukeTaskException, IOException {
        if (checkEmptyTodoInput(input)) {
            throw new DukeTaskException();
        }

        tasks.add(new Todo(input.trim()));

        String dataString = "T | false | " + input.trim();
        addNewData(dataString);

        tasksCount++;
    }

    public static void addDeadline(String input) throws DukeTaskException, IOException {
        String[] parsedInput = input.split(BY_KEYWORD);

        if (!(parsedInput.length == 2)) {
            throw new DukeTaskException();
        }

        tasks.add(new Deadline(parsedInput[0].trim(), parsedInput[1].trim()));

        String dataString = "D | false | " + parsedInput[0].trim() + " | "  + parsedInput[1].trim();
        addNewData(dataString);

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
        tasks.add(new Event(parsedInput[0].trim(), parsedInput[1].trim(), parsedInput[2].trim()));

        String dataString = "E | false | " + parsedInput[0].trim() + " | "  + parsedInput[1].trim()
                + " | " + parsedInput[2].trim();
        addNewData(dataString);

        tasksCount++;
    }

    public static int parseIndex(String input) {
        return Integer.parseInt(input) - 1;
    }

    private static void excludeDeletedData(int index, int currentIndex, Scanner scanner, StringBuilder buffer) {
        if (currentIndex == index) {
            scanner.nextLine();
        } else {
            buffer.append(scanner.nextLine());

            if (scanner.hasNext()) {
                buffer.append(System.lineSeparator());
            }
        }
    }

    //Solution below adapted by https://www.tutorialspoint.com/how-to-overwrite-a-line-in-a-txt-file-using-java
    public static void deleteTaskData(int index) throws IOException {
        Scanner scanner = new Scanner(new File(DATA_PATH));

        StringBuilder buffer = new StringBuilder();
        int currentIndex = 0;

        while (scanner.hasNext()) {
            excludeDeletedData(index, currentIndex, scanner, buffer);
            currentIndex++;
        }

        FileWriter writer = new FileWriter(DATA_PATH);
        writer.append(buffer.toString());
        writer.close();
    }

    public static void deleteTask(String input) {
        int index = parseIndex(input);
        Task removedTask = tasks.remove(index);

        try {
            deleteTaskData(index);
        } catch (IOException exception) {
            ui.handleIOException(exception);
        }

        ui.printDeletedTask(removedTask);

        tasksCount--;

        ui.printNumOfTasks(tasksCount);
    }

    private static void modifyTaskData(int index, int currentIndex, Scanner scanner, String change,
                                       StringBuilder buffer) {
        if (currentIndex == index) {
            String[] parsedTask = scanner.nextLine().split(" \\| ");
            parsedTask[1] = change;

            for (int i = 0; i < parsedTask.length; i++) {
                buffer.append(parsedTask[i]);

                if (i < parsedTask.length - 1) {
                    buffer.append(" | ");
                }
            }

            if (scanner.hasNext()) {
                buffer.append(System.lineSeparator());
            }

        } else {
            buffer.append(scanner.nextLine());

            if (scanner.hasNext()) {
                buffer.append(System.lineSeparator());
            }
        }
    }

    //Solution below adapted by https://www.tutorialspoint.com/how-to-overwrite-a-line-in-a-txt-file-using-java
    public static void updateTaskDatabase(int index, boolean taskStatus) throws IOException {
        Scanner scanner = new Scanner(new File(DATA_PATH));

        StringBuilder buffer = new StringBuilder();
        String change = taskStatus ? "true" : "false";
        int currentIndex = 0;

        while (scanner.hasNext()) {
            modifyTaskData(index, currentIndex, scanner, change, buffer);
            currentIndex++;
        }

        FileWriter writer = new FileWriter(DATA_PATH);
        writer.append(buffer.toString());
        writer.close();
    }

    public static void setMarkAsDone(String input) {
        int index = parseIndex(input);

        try {
            updateTaskDatabase(index, true);
        } catch (IOException exception) {
            ui.handleIOException(exception);
        }

        tasks.get(index).markAsDone();

        ui.printModifiedTask(tasks.get(index), true);
    }

    public static void setUnmarkAsDone(String input) {
        int index = parseIndex(input);

        try {
            updateTaskDatabase(index, false);
        } catch (IOException exception) {
            ui.handleIOException(exception);
        }

        tasks.get(index).unmarkAsDone();

        ui.printModifiedTask(tasks.get(index), false);
    }

    public static void executeCommand(String input) {
        String[] parsedInput = input.split(" ", 2);
        String command = parsedInput[0];
        input = parsedInput.length == 1 ? " " : parsedInput[1].trim();

        try {
            switch (command) {
            case "list":
                ui.printTasks(tasks, tasksCount);
                break;
            case "mark":
                setMarkAsDone(input);
                break;
            case "unmark":
                setUnmarkAsDone(input);
                break;
            case "delete":
                deleteTask(input);
                break;
            case "todo":
                addTodo(input);
                ui.printRecentTask(tasks.get(tasksCount - 1), tasksCount);
                break;
            case "deadline":
                addDeadline(input);
                ui.printRecentTask(tasks.get(tasksCount - 1), tasksCount);
                break;
            case "event":
                addEvent(input);
                ui.printRecentTask(tasks.get(tasksCount - 1), tasksCount);
                break;
            case "bye":
                ui.tellGoodbye();
                System.exit(0);
            default:
                throw new DukeCommandException();
            }
        } catch (IndexOutOfBoundsException exception) {
            ui.handleIndexOutOfBoundsException(tasksCount);
        } catch (NumberFormatException exception) {
            ui.handleNumberFormatException(input);
        } catch (DukeCommandException exception) {
            exception.handleDukeCommandException(command);
        } catch (DukeTaskException exception) {
            exception.handleDukeTaskException(command, input);
        } catch (IOException exception) {
            ui.handleIOException(exception);
        }
    }

    private static void createNewData(Scanner scan) {
        String[] parsedTask = scan.nextLine().split(" \\| ");

        String taskType = parsedTask[0];

        switch (taskType) {
        case "T":
            tasks.add(new Todo(parsedTask[2] , parsedTask[1]));
            tasksCount++;
            break;
        case "D":
            tasks.add(new Deadline(parsedTask[2], parsedTask[1], parsedTask[3]));
            tasksCount++;
            break;
        case "E":
            tasks.add(new Event(parsedTask[2], parsedTask[1], parsedTask[3], parsedTask[4]));
            tasksCount++;
            break;
        }
    }

    public static void restoreSavedData() throws FileNotFoundException {
        File file = new File(DATA_PATH);
        Scanner scan = new Scanner(file);

        while (scan.hasNext()) {
            createNewData(scan);
        }
    }

    private static void createDukeDirectory(File newDirectory) {
        if (!newDirectory.isDirectory() && newDirectory.mkdir()) {
            System.out.println("\tDirectory successfully created!");
        } else {
            System.out.println("\tDirectory found!");
        }
    }

    private static void createDukeFile(File newDatabase) throws IOException {
        if (!newDatabase.isFile() && newDatabase.createNewFile()) {
            System.out.println("\tData text file successfully created!");
        } else {
            System.out.println("\tText file found!");
        }
    }

    public static void handleFileNotFoundException() {
        ui.showLine();
        System.out.println("\tLooks like I can't find the file. Let me make it for you.");

        File newDirectory = new File(DATA_DIRECTORY);
        File newDatabase = new File(DATA_PATH);

        try {
            createDukeDirectory(newDirectory);
            createDukeFile(newDatabase);
        } catch (IOException exception){
            ui.handleIOException(exception);
        }

        System.out.println("\tPlease try to run the app again.");
        ui.showLine();
    }

    public static void main(String[] args) {
        String input;
        Scanner in = new Scanner(System.in);

        try {
            restoreSavedData();
        } catch (FileNotFoundException e){
            handleFileNotFoundException();
            System.exit(1);
        }

        ui = new TextUi();
        ui.tellGreeting();
        while (true) {
            input = getInput(in);
            ui.showLine();
            executeCommand(input);
            ui.showLine();
        }
    }
}