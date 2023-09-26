package fredbot;

import fredbot.task.Task;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.util.ArrayList;

public class Storage {
    private File dataFile;

    public Storage(String fileName) {
        dataFile = new File(fileName);
    }

    /**
     * Creates a new file if file does not exist
     * creates a new directory as well if the path to the file does not exist
     */
    public void createFile()
    {
        try {
            if (dataFile.exists()) {
                System.out.println("file exists");
                return;
            }
            if (!dataFile.getParentFile().exists()) {
                dataFile.getParentFile().mkdirs();
            }
            dataFile.createNewFile();
        } catch (IOException e) {
            System.out.println("Cannot create file; reason: " + e.getMessage());
            // should do something here
        }
    }

    /**
     * Returns an array list of strings where each string in the array list contains each line in the file
     * specified in order.
     * @return an array list of strings containing information of the task
     * @throws IOException if there is an error reading from the file
     */
    private ArrayList<String> readFile() throws IOException {
        if (!dataFile.exists()) {
            createFile();
            return new ArrayList<String>();
        }
        if (dataFile.length() == 0) {
            System.out.println("0 tasks loaded");
            // should do something else here
        }
        return (ArrayList<String>) Files.readAllLines(dataFile.toPath(), Charset.defaultCharset());
    }

    /**
     * Returns a tasklist object that contains the current tasks loaded from strings in the file to
     * the form of the task objects
     * @return a tasklist object that contains current task and provide operation for adding, removing etc.
     * @throws IOException if there is an error reading from the file
     */
    public TaskList loadTasks() throws IOException {
        TaskList tasks = null;
        ArrayList<String> dataItems = readFile();
        tasks = Parser.parseFromFile(dataItems);
        return tasks;
    }

    /**
     * Updates the file specified with the new tasklist object after each operation (add, delete, mark)
     * @param tasks tasklist containing the updated tasks
     * @throws IOException if there is an error updating the file
     */
    public void addTaskstoFile(TaskList tasks) throws IOException {
        FileWriter fw = new FileWriter("./data/tasks.txt");
        StringBuilder tasksText = new StringBuilder();
        for (int i = 0; i < Task.getNumTask(); i++) {
            tasksText.append(tasks.getTask(i).toString()).append(System.lineSeparator());
        }
        fw.write(String.valueOf(tasksText));
        fw.close();
    }
}
