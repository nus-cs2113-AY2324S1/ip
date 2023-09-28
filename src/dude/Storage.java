package dude;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Storage {

    private final String filePath;

    public Storage(String filePath) {
        this.filePath = filePath;
    }

    public void setupFile() {
        // Check if the directory exists, if not, create it
        File file = new File(filePath);
        if (!file.getParentFile().exists()) {
            file.getParentFile().mkdirs();
        }

        // Check if the file exists, if not, create it
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                System.out.println("Error creating file: " + e.getMessage());
            }
        }
    }

    public ArrayList<Task> loadFromFile() {
        ArrayList<Task> tasks = new ArrayList<>();
        try {
            File file = new File(filePath);
            Scanner fileReader = new Scanner(file);

            while (fileReader.hasNextLine()) {
                String line = fileReader.nextLine();
                char taskType = line.charAt(0);
                switch (taskType) {
                case 'T':
                    tasks.add(Task.fromFileFormat(line));
                    break;
                case 'D':
                    tasks.add(Deadline.fromFileFormat(line));
                    break;
                case 'E':
                    tasks.add(Event.fromFileFormat(line));
                    break;
                }
            }

            fileReader.close();
        } catch (IOException e) {
            System.out.println("Error reading from file: " + e.getMessage());
        }
        return tasks;
    }

    public void saveToFile(ArrayList<Task> tasks) {
        try {
            FileWriter fileWriter = new FileWriter(filePath);
            for (Task task : tasks) {
                fileWriter.write(task.toFileFormat() + "\n");
            }
            fileWriter.close();
        } catch (IOException e) {
            System.out.println("Error writing to file: " + e.getMessage());
        }
    }
}
