package neo.util;

import neo.task.Deadline;
import neo.task.Event;
import neo.task.Task;
import neo.task.Todo;
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
     * Content of data.txt are used to fill up the task list.
     *
     * @param list This is the list of tasks to be filled up according to the content
     *             of existing data.txt file.
     */
    public static void findFile(ArrayList<Task> list) {
        try {
            generateFile(list);
        } catch (IOException e) {
            System.out.println("Error with data.txt file");
        }
    }

    private static void generateFile(ArrayList<Task> list) throws IOException {
        File directory = new File(fileDirectory);

        if (directory.mkdir()) {
            System.out.println("Creating new data folder...");
        }
        File f = new File(filePath);
        if (f.createNewFile()) {
            System.out.println("Creating new data.txt file...");
        }

        Scanner s = new Scanner(f);
        readFile(list, s);
    }

    private static void readFile(ArrayList<Task> list, Scanner s) {
        while (s.hasNext()) {
            String line = s.nextLine();
            String[] task = line.split(" / ");
            String taskType = task[0];
            int mark = Integer.parseInt(task[1]);
            boolean isMarked = checkMarked(mark);

            switch (taskType) {
            case "T":
                Todo todo = new Todo(task[2]);
                todo.setDone(isMarked);
                list.add(todo);
                break;
            case "D":
                Deadline deadline = new Deadline(task[2], task[3]);
                deadline.setDone(isMarked);
                list.add(deadline);
                break;
            case "E":
                Event event = new Event(task[2], task[3], task[4]);
                event.setDone(isMarked);
                list.add(event);
                break;
            default:
                System.out.println("Unable to add task from data.txt");
                break;
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
            System.out.println("Error with data.txt file.");
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
