package jarvis.storage;

import jarvis.tasks.Task;
import jarvis.tasklist.TaskList;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.File;
import java.io.IOException;

public class Storage {
    private File dataFile;

    public Storage(String filePath){
        dataFile = new File(filePath);
    }

    /*
        Write new data to file
        @param filePath - specify file location where data is written to
        @param dataToAdd - data to be written
        @param toAppend - if True, data is added to the back of the existing data
     */
    private void writeToFile(String filePath, String dataToAdd, boolean toAppend) throws IOException {
        FileWriter fileWriter = new FileWriter(filePath, toAppend);
        fileWriter.write(dataToAdd);
        fileWriter.close();
    }
}
