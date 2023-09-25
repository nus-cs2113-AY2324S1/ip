package fredbot;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.util.ArrayList;

public class Storage {
    private File dataFile;

    public Storage(String fileName) {
        dataFile = new File(fileName);
    }

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
    
    private ArrayList<String> readFile() throws IOException {
        if (!dataFile.exists()) {
            throw new FileNotFoundException();
        }
        if (dataFile.length() == 0) {
            System.out.println("empty file");
            throw new IOException();
            // should do something else here
        }
        return (ArrayList<String>) Files.readAllLines(dataFile.toPath(), Charset.defaultCharset());
    }
    
    public TaskList loadTasks() {
        TaskList tasks = null;
        try {
            ArrayList<String> dataItems = readFile();
            tasks = parse(dataItems);
        } catch (IOException e) {
            e.printStackTrace();
            // need to do something here
        }
        return tasks;
    }
}
