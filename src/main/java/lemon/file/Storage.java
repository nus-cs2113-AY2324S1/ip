package lemon.file;

import lemon.exception.LemonException;
import lemon.task.Task;
import lemon.validation.Parser;

import static lemon.common.Messages.LINE_SEPARATOR;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Utility class for handling storage operations in the Lemon chatbot.
 * This class manages the reading and writing of task data to and from a file.
 */
public class Storage {
    private final String filePath;

    /**
     * Constructs a Storage instance with the specified file path.
     *
     * @param filePath Path of file that stores the task data.
     */
    public Storage(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Loads task data from the file specified in the constructor.
     * Reads and validates the task data and adds them to a task list.
     * Creates a new file at the specified file path if the file is not found at the specified path.
     *
     * @return A new task list with task data from the file.
     * @throws LemonException If file is not found and if there is error related to the IO.
     */
    public ArrayList<Task> load() throws LemonException{
        ArrayList<Task> tasks = new ArrayList<>();

        try {
            System.out.println("Loading data file...");

            Parser parser = new Parser();
            File file = new File(filePath);
            Scanner scanner = new Scanner(file);

            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                Task task = parser.parseFile(line);
                tasks.add(task);
            }
            scanner.close();
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
                    throw new LemonException("Uh-oh! An error has occurred while creating file.\n");
                }
            }
            System.out.println("New file created!");
        }

        return tasks;
    }

    /**
     * Saves task data to the file specified in the constructor.
     * Reads task data from the task list and writes them to the file.
     *
     * @param tasks List of tasks to read task data from
     */
    public void save(ArrayList<Task> tasks) {
        File file = new File(filePath);
        try {
            FileWriter fileWriter = new FileWriter(filePath, false);
            for (Task task : tasks) {
                fileWriter.write(task.toFile());
                fileWriter.write(LINE_SEPARATOR);
            }
            fileWriter.close();
        } catch (IOException e) {
            System.out.println("Uh-oh! An error has occurred while writing to file.\n");
        }
    }
}
