package main.java.duke.util;

import main.java.duke.util.task.Task;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

/**
 * Main object for handling file writing and reading with list of tasks.
 */
public class FileStorage {

    private File dataFile;
    private FileWriter fileWriter;
    private Scanner fileScanner;
    private String filePath;

    public FileStorage (String filePath) {
        this.filePath = filePath;
        // file object with expected file path
        dataFile = new File(filePath);
    }

    /**
     * Method for writing to file with information in the list of tasks.
     * @param taskList TaskList object reference for getting the information of tasks
     */
    public void fillFileFromList(TaskList taskList) {
        // writing to file
        try {
            // attempt file creation
            dataFile.createNewFile();

            // add all tasks to file
            fileWriter = new FileWriter(dataFile);
            for(Task task : taskList.getTasks()) {
                fileWriter.write(task.toStringFile() + System.lineSeparator());
            }
            fileWriter.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Method for reading file information and populating the list of tasks.
     * @param taskList TaskList object reference for storing the information of tasks
     */
    public void fillListFromFile(TaskList taskList) {
        // IO file handling
        try {
            // attempt file creation, if file already exist, read from file
            if (!dataFile.createNewFile()) {
                fileScanner = new Scanner(dataFile);
                while (fileScanner.hasNext()) {
                    String line = fileScanner.nextLine();
                    String[] taskData = line.split("\\|");
                    switch (taskData[0]) {
                    case "T":
                        taskList.addTodo(taskData[2]);
                        break;
                    case "E":
                        taskList.addEvent(taskData[2], taskData[3], taskData[4]);
                        break;
                    case "D":
                        taskList.addDeadline(taskData[2], taskData[3]);
                        break;
                    }
                    if (Integer.parseInt(taskData[1]) == 1) {
                        taskList.markTask(taskList.size() - 1);
                    }
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
