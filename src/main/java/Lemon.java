import lemon.exception.LemonException;
import lemon.task.Deadline;
import lemon.task.Event;
import lemon.task.Task;
import lemon.task.Todo;

import java.util.ArrayList;
import java.util.AbstractMap;
import java.util.Objects;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;

public class Lemon {
    public static String LEMON_EMOJI = "\uD83C\uDF4B";
    private static final ArrayList<Task> tasks = new ArrayList<>();

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

    public static AbstractMap.SimpleEntry<Task, String> addNewTodo(String input, boolean isDone, String filePath)
            throws LemonException {
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

            Task newTask = new Todo(task, isDone);
            tasks.add(newTask);
            String taskStr = String.format("T | %s | %s\n", newTask.getStatusIcon(), task);

            return new AbstractMap.SimpleEntry<>(newTask, taskStr);
        } else {
            throw new LemonException("Oopsie! Please use the format 'todo <task>'!");
        }
    }

    public static AbstractMap.SimpleEntry<Task, String> addNewDeadline(String input, boolean isDone, String filePath)
            throws LemonException {
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

            Task newTask = new Deadline(task, isDone, dateTime);
            tasks.add(newTask);
            String taskStr = String.format("D | %s | %s | %s\n", newTask.getStatusIcon(), task, dateTime);

            return new AbstractMap.SimpleEntry<>(newTask, taskStr);
        } else {
            throw new LemonException("Oopsie! Please use the format 'deadline <task> /by <date/time>'!");
        }
    }

    public static AbstractMap.SimpleEntry<Task, String> addNewEvent(String input, boolean isDone, String filePath)
            throws LemonException {
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

            Task newTask = new Event(task, isDone, startDateTime, endDateTime);
            tasks.add(newTask);
            String taskStr = String.format("E | %s | %s | %s | %s\n", newTask.getStatusIcon(), task, startDateTime,
                    endDateTime);

            return new AbstractMap.SimpleEntry<>(newTask, taskStr);
        } else {
            throw new LemonException("Oopsie! Please use the format 'event <task> " +
                    "/from <starting date/time> /to <ending date/time>'!");
        }
    }

    public static void deleteTask(String input, boolean isEmptyList, String filePath) throws LemonException {
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

                String taskStr = null;

                for (Task task : tasks) {
                    if (task instanceof Todo) {
                        taskStr = String.format("T | %s | %s\n", task.getStatusIcon(), task.getDescription());
                    } else if (task instanceof Deadline) {
                        Deadline deadline = (Deadline) task;
                        taskStr = String.format("D | %s | %s | %s\n", task.getStatusIcon(), task.getDescription(), deadline.getBy());
                    } else if (task instanceof Event) {
                        Event event = (Event) task;
                        taskStr = String.format("E | %s | %s | %s | %s\n", task.getStatusIcon(), task.getDescription(), event.getFrom(),
                                event.getTo());
                    } else {
                        throw new LemonException("Uh-oh! An error has occurred while deleting.");
                    }
                    overwriteFile(filePath, taskStr);
                }
                //deleteFromFile(filePath, taskSerialNo);
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

    public static void markTask(String input, String action, boolean isEmptyList, String filePath)
            throws LemonException {
        checkEmptyList(action, isEmptyList);

        try {
            String inputPattern = "(mark|unmark) (\\d+)";

            Pattern pattern = Pattern.compile(inputPattern);
            Matcher matcher = pattern.matcher(input);
            boolean matchFound = matcher.find();

            if (matchFound) {
                int taskSerialNo = Integer.parseInt(matcher.group(2).trim());
                int taskIndex = taskSerialNo - 1;

                String currentStatusIcon = tasks.get(taskIndex).getStatusIcon();

                if (action.equals("mark") && !Objects.equals(currentStatusIcon, "X")) {
                    tasks.get(taskIndex).markAsDone();
                    System.out.println("Great job! This task is now juiced:");
                } else if (action.equals("unmark") && !Objects.equals(currentStatusIcon, " ")) {
                    tasks.get(taskIndex).markAsNotDone();
                    System.out.println("No problem! This task is back into the basket:");
                } else {
                    throw new LemonException("The task has already been marked accordingly!");
                }

                System.out.println("\t" + tasks.get(taskIndex).toString());
                updateFile(filePath, taskSerialNo, action);
            } else {
                throw new LemonException("Oopsie! Please use the format 'mark/unmark <task number>'!");
            }
        } catch (NumberFormatException e) {
            System.out.println("Oopsie! Please enter a valid task number!");
        } catch (IndexOutOfBoundsException | NullPointerException e) {
            System.out.println("Oopsie! Please enter a task number that is on the list!");
        }
    }

    public static void loadFile(String filePath) throws LemonException {
        try {
            File file = new File(filePath);
            Scanner scanner = new Scanner(file);

            System.out.println("Loading data file...");

            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] taskStr = line.split(" \\| ");
                boolean isDone;

                if (taskStr[1].equals("X")) {
                    isDone = true;
                } else {
                    isDone = false;
                }

                switch (taskStr[0]) {
                case "T":
                    addNewTodo(String.format("todo %s", taskStr[2]), isDone, filePath);
                    break;
                case "D":
                    addNewDeadline(String.format("deadline %s /by %s", taskStr[2], taskStr[3]), isDone, filePath);
                    break;
                case "E":
                    addNewEvent(String.format("event %s /from %s /to %s", taskStr[2], taskStr[3], taskStr[4]),
                            isDone, filePath);
                    break;
                default:
                    throw new LemonException("Uh-oh! Data in the file is not in the right format! Please use the format " +
                            "'<T/D/E> | <X/ > | <task> [| <date/time>] [| <date/time>]'!\n");
                }
            }
            System.out.println("Data file loaded!\n");
        } catch (FileNotFoundException e) {
            File file = new File(filePath);
            if (!file.getParentFile().exists()) {
                file.getParentFile().mkdirs();
            }
            if (!file.exists()) {
                try {
                    file.createNewFile();
                } catch (IOException f) {
                    System.out.println("Uh-oh! An error has occurred while creating file.\n");
                }
            }

            System.out.println("New file created at " + file.getAbsolutePath() + " !\n");
        }
    }

    public static void writeToFile(String filePath, String taskStr) {
        File file = new File(filePath);
        try {
            FileWriter fileWriter = new FileWriter(filePath, true);
            fileWriter.write(taskStr);
            fileWriter.close();
        } catch (IOException e) {
            System.out.println("Uh-oh! An error has occurred while writing to file.\n");
        }
    }

    public static void overwriteFile(String filePath, String taskStr) {
        File file = new File(filePath);
        try {
            FileWriter fileWriter = new FileWriter(filePath, false);
            fileWriter.write(taskStr);
            fileWriter.close();
        } catch (IOException e) {
            System.out.println("Uh-oh! An error has occurred while writing to file.\n");
        }
    }

    public static void deleteFromFile(String filePath, int deleteLineSerialNo) throws LemonException {
        try {
            String TEMP_FILE_PATH = "./data/tempFile.txt";
            File file = new File(filePath);
            File tempFile = new File(TEMP_FILE_PATH);

            System.out.println("TEST3");
            BufferedReader reader = new BufferedReader(new FileReader(file));
            BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile));

            System.out.println("TEST4");
            String currentLine;
            int lineCount = 1;

            while ((currentLine = reader.readLine()) != null) {
                if (lineCount != deleteLineSerialNo) {
                    writer.write(currentLine + System.getProperty("line.separator"));
                }
                lineCount++;
            }
            System.out.println("TEST5");
            writer.close();
            System.out.println("TEST1");
            reader.close();
            System.out.println("TEST2");

            System.out.println(file.delete());

            if (file.delete()) {
                System.out.println("TEST6");
                if (!tempFile.renameTo(file)) {
                    throw new LemonException("Uh-oh! An error has occurred while deleting from file. not renamed\n");
                }
            } else {
                System.out.println("TEST7");
                throw new LemonException("Uh-oh! An error has occurred while deleting from file. not deleted\n");
            }
        } catch (IOException e) {
            System.out.println("Uh-oh! An error has occurred while deleting from file. io\n");
        }
    }

    public static void updateFile(String filePath, int updateLineSerialNo, String action) {
        try {
            String TEMP_FILE_PATH = "./data/tempFile.txt";
            File file = new File(filePath);
            File tempFile = new File(TEMP_FILE_PATH);

            BufferedReader reader = new BufferedReader(new FileReader(file));
            BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile));

            String currentLine;
            int lineCount = 1;

            while ((currentLine = reader.readLine()) != null) {
                if (lineCount == updateLineSerialNo) {
                    if (action.equals("mark")) {
                        currentLine = currentLine.replace(" |   | ", " | X | ");
                    } else if (action.equals("unmark")) {
                        currentLine = currentLine.replace(" | X | ", " |   | ");
                    }
                }
                writer.write(currentLine + System.getProperty("line.separator"));
                lineCount++;
            }

            writer.close();
            reader.close();

            if (file.delete()) {
                if (!tempFile.renameTo(file)) {
                    throw new IOException("Uh-oh! An error has occurred while updating file.");
                }
            } else {
                throw new IOException("Uh-oh! An error has occurred while updating file.");
            }
        } catch (IOException e) {
            System.out.println("Uh-oh! An error has occurred while updating file.");
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
        String FILE_PATH = "./data/lemon.txt";

        try {
            loadFile(FILE_PATH);
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Uh-oh! Data in the file is not in the right format! Please use the format " +
                    "'<T/D/E> | <X/ > | <task> [| <date/time>] [| <date/time>]'!\n");
        } catch (LemonException e) {
            System.out.println(e.getMessage());
        }

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
                    AbstractMap.SimpleEntry<Task, String> newTodo = addNewTodo(input, false, FILE_PATH);
                    printAddedOrDeletedTask(newTodo.getKey(), "add");
                    writeToFile(FILE_PATH, newTodo.getValue());
                } catch (LemonException e) {
                    System.out.println(e.getMessage());
                }
            } else if (input.matches("deadline\\b.*")) {
                try {
                    AbstractMap.SimpleEntry<Task, String> newDeadline = addNewDeadline(input, false, FILE_PATH);
                    printAddedOrDeletedTask(newDeadline.getKey(), "add");
                    writeToFile(FILE_PATH, newDeadline.getValue());
                } catch (LemonException e) {
                    System.out.println(e.getMessage());
                }
            } else if (input.matches("event\\b.*")) {
                try {
                    AbstractMap.SimpleEntry<Task, String> newEvent = addNewEvent(input, false, FILE_PATH);
                    printAddedOrDeletedTask(newEvent.getKey(), "add");
                    writeToFile(FILE_PATH, newEvent.getValue());
                } catch (LemonException e) {
                    System.out.println(e.getMessage());
                }
            } else if (input.matches("delete\\b.*")) {
                try {
                    deleteTask(input, isEmptyList, FILE_PATH);
                } catch (LemonException e) {
                    System.out.println(e.getMessage());
                }
            } else if (input.matches("mark\\b.*")) {
                try {
                    markTask(input, "mark", isEmptyList, FILE_PATH);
                } catch (LemonException e) {
                    System.out.println(e.getMessage());
                }
            } else if (input.matches("unmark\\b.*")) {
                try {
                    markTask(input, "unmark", isEmptyList, FILE_PATH);
                } catch (LemonException e) {
                    System.out.println(e.getMessage());
                }
            } else {
                System.out.println("Hmmm! I'm not sure what that means. Could you please specify your request?");
            }
        }
    }
}
