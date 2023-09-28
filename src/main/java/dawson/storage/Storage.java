package dawson.storage;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import dawson.TaskList;
import dawson.exception.DawsonException;
import dawson.parser.Parser;
import dawson.task.Task;

public class Storage {

    private String filePathString;
    private File storageFile;

    public Storage(String filePath) {
        filePathString = filePath;
        storageFile = new File(filePath);
    }

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
