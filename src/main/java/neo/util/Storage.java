package neo.util;

import neo.exception.NeoTaskException;
import neo.exception.NeoTimeException;
import neo.task.Deadline;
import neo.task.Event;
import neo.task.Task;
import neo.task.Todo;
import neo.type.CommandType;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Represents the methods relating to writing and reading from data.txt file.
 */
public abstract class Storage {
    private final static String filePath = "data/data.txt";
    private final static String fileDirectory = "data";

    private static boolean checkMarked(int mark) {
        return (mark == 1);
    }

    /**
     * Finds and read data.txt file in data/data.txt path relative to working directory.
     * If data.txt and its parent folder does not exist, it will create those files.
     * If data.txt file is unreadable, it will delete and create a new data.txt file
     * depending on the user's input.
     * If the user does not want to delete existing data.txt file, it will throw exception.
     * Content of data.txt are used to fill up the task list.
     *
     * @param list This is the list of tasks to be filled up according to the content
     *             of existing data.txt file.
     * @throws Exception if data.txt file remains unreadable.
     */
    public static void findFile(ArrayList<Task> list) throws Exception {
        try {
            generateFile(list);
        } catch (IOException e) {
            System.out.println("Error with reading data.txt file.");
            Ui.manualDeleteGuide();
            throw new Exception();
        }
    }

    private static void generateFile(ArrayList<Task> list) throws Exception {
        File directory = new File(fileDirectory);

        if (directory.mkdir()) {
            System.out.println("Creating new data folder...");
        }
        File f = new File(filePath);
        if (f.createNewFile()) {
            System.out.println("Creating new data.txt file...");
        }

        Scanner s = new Scanner(f);
        try {
            readFile(list, s);
        } catch (ArrayIndexOutOfBoundsException | NumberFormatException | NeoTimeException | NeoTaskException e) {
            Ui.dataErrorMessage();
            generateNewFile(list);
        }
    }

    private static void generateNewFile(ArrayList<Task> list) throws Exception {
        String input = Ui.readInput();
        while (!input.equalsIgnoreCase("N")) {
            if (input.trim().equalsIgnoreCase("Y")) {
                list.clear();
                System.out.println("Deleting data.txt file...");
                writeToFile(list);
                System.out.println("Creating new data.txt file...");
                break;
            } else {
                System.out.println("OOPS!!! Unable to read line. Please type 'Y' for yes or 'N' for no.");
            }
            input = Ui.readInput();
        }
        if (input.trim().equalsIgnoreCase("N")) {
            Ui.manualDeleteGuide();
            throw new Exception();
        }
    }

    private static void readFile(ArrayList<Task> list, Scanner s) throws ArrayIndexOutOfBoundsException, NumberFormatException, NeoTaskException, NeoTimeException {
        while (s.hasNext()) {
            String line = s.nextLine();

            String[] task = line.split("\\|");
            int taskArraySize = task.length;
            String taskType = task[0].trim();
            int mark = Integer.parseInt(task[1].trim());

            ErrorCatcher.catchReadMarkError(mark);

            String description = task[2].trim();
            boolean isMarked = checkMarked(mark);

            switch (taskType) {
            case "T":
                ErrorCatcher.catchReadFormatError(CommandType.TODO, taskArraySize);
                ErrorCatcher.catchEmptyDescription("description", description);

                Todo todo = new Todo(description);
                todo.setDone(isMarked);
                list.add(todo);
                break;
            case "D":
                String by = task[3].trim();

                ErrorCatcher.catchReadFormatError(CommandType.DEADLINE, taskArraySize);
                ErrorCatcher.catchEmptyDescription("description", description);
                ErrorCatcher.catchTimeFormatError(by);

                Deadline deadline = new Deadline(description, by);
                deadline.setDone(isMarked);
                list.add(deadline);
                break;
            case "E":
                String from = task[3].trim();
                String to = task[4].trim();

                ErrorCatcher.catchReadFormatError(CommandType.EVENT, taskArraySize);
                ErrorCatcher.catchEmptyDescription("description", description);
                ErrorCatcher.catchTimeFormatError(from);
                ErrorCatcher.catchTimeFormatError(to);

                Event event = new Event(description, from, to);
                event.setDone(isMarked);
                list.add(event);
                break;
            default:
                throw new NeoTaskException();
            }
        }
    }

    /**
     * Writes to data.txt file.
     *
     * @param list This is the list of tasks to be written into the file.
     */
    public static void updateFile(ArrayList<Task> list) {
        try {
            writeToFile(list);
        } catch (IOException e) {
            System.out.println("Error with updating data.txt file.");
        }
    }
    private static void writeToFile(ArrayList<Task> list) throws IOException {
        FileWriter fw = new FileWriter(filePath);

        for (Task task : list) {
            String formatTask = task.formatTask();
            fw.write(formatTask + System.lineSeparator());
        }

        fw.close();
    }
}
