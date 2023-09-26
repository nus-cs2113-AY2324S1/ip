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
            System.out.println("0 tasks loaded");
            // should do something else here
        }
        return (ArrayList<String>) Files.readAllLines(dataFile.toPath(), Charset.defaultCharset());
    }
    
    public TaskList loadTasks() {
        TaskList tasks = null;
        try {
            ArrayList<String> dataItems = readFile();
            tasks = Parser.parseFromFile(dataItems);
        } catch (IOException e) {
            e.printStackTrace();
            // need to do something here
        }
        return tasks;
    }

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
