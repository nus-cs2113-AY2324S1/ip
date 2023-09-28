package storage;

// Inspired by TaskS
import parser.Parser;
import task.TaskList;
import ui.Ui;

import java.io.*;
import java.nio.file.Files;
import java.util.ArrayList;

public class Storage {
    private final static String userWorkingDirectory = System.getProperty("user.dir");
    private final static  java.nio.file.Path FILE_PATH = java.nio.file.Paths.get(userWorkingDirectory, "data.txt");
    private final Ui ui;

    public Storage(Ui ui) {
        this.ui = ui;
    }

    //write the data from tasklist to data.txt
    public static void saveList(TaskList taskList) {
        try {
            Files.delete(FILE_PATH);
            Files.writeString(FILE_PATH, taskList.prepareSaveList());
        } catch (IOException e) {
            Ui.showError(e);
        }
    }

    //write the data from data.txt to tasklist
    public TaskList load(Parser parser) throws IOException {
        TaskList taskList = new TaskList();
        ArrayList<String> data = this.readFile();
        for (String line : data) {
            parser.parseLoadingData(taskList, line);
        }
        return taskList;
    }

    // read the file and return all lines in arrayList<String>
    private ArrayList<String> readFile() throws IOException {
        boolean directoryExists = java.nio.file.Files.exists(FILE_PATH);

        if (!directoryExists) {
            Files.createFile(FILE_PATH);
        }

        return (ArrayList<String>) Files.readAllLines(FILE_PATH);
    }
}
