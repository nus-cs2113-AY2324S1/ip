package dawson.storage;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import dawson.exception.DawsonException;
import dawson.parser.Parser;
import dawson.task.Task;
import dawson.task.TaskList;

/**
 * Handles saved tasks information via I/O with storage text file
 */
public class Storage {

    private String filePathString;
    private File storageFile;

    public Storage(String filePath) {
        filePathString = filePath;
        storageFile = new File(filePath);
    }

    /**
     * Creates the Dawson storage txt file.
     * Create any necessary parent directories, if it does not exist.
     *
     * @throws DawsonException If there is an issue creating the file or parent directories.
     */
    private void createDawsonFile() throws DawsonException {
        // Create the necessary parent directories for new file
        if (!storageFile.exists()) {
            storageFile.getParentFile().mkdirs();
        }

        // Create file if it does not exist
        try {
            storageFile.createNewFile();
        } catch (IOException e) {
            throw new DawsonException("Fatal: Error creating file: " + filePathString + " Exiting Dawson");
        }
    }

    /**
     * Loads a list of tasks from a storage txt file and returns them as an ArrayList of Task objects.
     * Decode each line into the corresponding task types with previously saved information using Parser.
     *
     * @return An ArrayList containing Task objects loaded from the storage file.
     * @throws DawsonException If there is an issue with file I/O, the file is not found, or parsing the file data fails.
     */
    public ArrayList<Task> load() throws DawsonException {
        createDawsonFile();

        Scanner fileScanner;
        try {
            fileScanner = new Scanner(storageFile);
        } catch (FileNotFoundException e) {
            throw new DawsonException("Invalid file path: " + filePathString);
        }

        ArrayList<Task> taskList = new ArrayList<>();
        while (fileScanner.hasNext()) {
            String line = fileScanner.nextLine();
            Task task = Parser.parseTask(line);
            taskList.add(task);
        }

        fileScanner.close();
        return taskList;
    }

    /**
     * Saves a TaskList to a file by encoding each task as a string and writing it to the storage txt file.
     *
     * @param taskList The TaskList object to be saved.
     * @throws DawsonException If there is an issue with file I/O or the file cannot be written to.
     */
    public void save(TaskList taskList) throws DawsonException {
        createDawsonFile();

        try {
            FileWriter fileWriter = new FileWriter(storageFile);
            String taskListString = taskList.encodeTaskList();
            fileWriter.write(taskListString);
            fileWriter.close();
            
        } catch (IOException e) {
            throw new DawsonException("Unable to write to path: " + storageFile.getAbsolutePath());
        }
    }
}
