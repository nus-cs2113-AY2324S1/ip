package storage;

// Inspired by TaskS
import parser.Parser;
import task.TaskList;
import ui.Ui;

import java.io.*;
import java.nio.file.Files;
import java.util.ArrayList;

/**
 * In charge of the storage of the data
 */
public class Storage {
    private final static String userWorkingDirectory = System.getProperty("user.dir");
    private final static  java.nio.file.Path FILE_PATH = java.nio.file.Paths.get(userWorkingDirectory, "data.txt");
    private final Ui ui;

    /**
     * constructor
     *
     * @param ui ui class for current session
     */
    public Storage(Ui ui) {
        this.ui = ui;
    }

    /**
     * Write the data from taskList to data.txt
     * It deletes everything in the data file before writing new data
     *
     * @param taskList taskList to be saved
     */
    public static void saveList(TaskList taskList) {
        try {
            Files.delete(FILE_PATH);
            Files.writeString(FILE_PATH, taskList.prepareSaveList());
        } catch (IOException e) {
            Ui.showError(e);
        }
    }

    /**
     * Load the data from data.txt to tasklist
     *
     * @param parser the parser for current session
     * @return taskList filled with data from data file
     * @throws IOException if the format of the data in data file is wrong
     */
    public TaskList load(Parser parser) throws IOException {
        TaskList taskList = new TaskList();
        ArrayList<String> data = this.readFile();
        for (String line : data) {
            parser.parseLoadingData(taskList, line);
        }
        return taskList;
    }

    /**
     * Read the file and return all lines in arrayList<String>
     *
     * @return arrayList<String>
     * @throws IOException if the format of the data in data file is wrong
     */
    // read the file and return all lines in arrayList<String>
    private ArrayList<String> readFile() throws IOException {
        boolean directoryExists = java.nio.file.Files.exists(FILE_PATH);

        if (!directoryExists) {
            Files.createFile(FILE_PATH);
        }

        return (ArrayList<String>) Files.readAllLines(FILE_PATH);
    }
}
