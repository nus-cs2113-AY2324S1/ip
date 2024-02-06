package chattie;

import chattie.Parser;
import chattie.TaskList;
import chattie.error.ChattieException;
import chattie.error.ErrorType;
import chattie.tasks.Deadline;
import chattie.tasks.Event;
import chattie.tasks.Task;
import chattie.tasks.Todo;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;


/**
 * Deals with loading tasks from the file and saving tasks in the file
 */
public class Storage {

    private static String folderPath;
    private static String filePath;

    public Storage(String folderPath, String filePath) {
        this.folderPath = folderPath;
        this.filePath = filePath;
    }

    /**
     * Loads chattie.txt
     *
     * @return List of tasks from chattie.txt
     * @throws ChattieException If file cannot be loaded
     */
    public static ArrayList<Task> load() throws ChattieException{
        createFile();
        return readFromFile();
    }

    /**
     * Checks if chattie.txt exists and creates a new file if it doesn't exist
     *
     * @throws ChattieException If file cannot be created
     */
    public static void createFile() throws ChattieException {
        File folder = new File(folderPath);
        File file = new File(filePath);

        if (!folder.exists()) {
            folder.mkdirs();
        }

        try {
            file.createNewFile();
        } catch (IOException e) {
            throw new ChattieException(ErrorType.FILE_LOAD_FAIL);
        }
    }

    private static ArrayList<Task> readFromFile() throws ChattieException{
        ArrayList<Task> list = new ArrayList<>();
        File file = new File(filePath);
        try {
            Scanner scanner = new Scanner(file);
            while (scanner.hasNext()) {
                list.add(Parser.parseTask(scanner.nextLine()));
            }
        } catch (FileNotFoundException e) {
            throw new ChattieException(ErrorType.FILE_LOAD_FAIL);
        }
        return list;
    }


    private static void writeToFile(String task) throws IOException{
        FileWriter fileWriter = new FileWriter(filePath, true);
        fileWriter.write(task + System.lineSeparator());
        fileWriter.close();
    }

    private static String formatTaskString(Task task) {
        String description = task.getTask();
        String done = task.isDone() ? "1" : "0";
        String taskString = "";
        if (task instanceof Todo) {
            taskString = "T | " + done + " | " + description;
        } else if (task instanceof Deadline) {
            Deadline deadline = (Deadline) task;
            taskString = "D | " + done + " | " + description + "| " + deadline.getBy();
        } else {
            Event event = (Event) task;
            taskString = "E | " + done + " | " + description + "| " + event.getFrom() + "-" + event.getTo();
        }
        return taskString;
    }

    /**
     * Rewrites chattie.txt with updated array list of tasks
     *
     * @param list Updated list of tasks
     * @throws IOException If an exception occurred in writeToFile()
     */
    public static void updateFile(ArrayList<Task> list) throws IOException {
        FileWriter clearFile = new FileWriter(filePath);
        clearFile.write("");
        clearFile.close();
        for (Task task : list) {
            if (task == null) {
                continue;
            }
            writeToFile(formatTaskString(task));
        }
    }
}
