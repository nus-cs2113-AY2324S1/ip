package Chatty;

import Chatty.tasks.*;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Storage {
    private String filePath;

    public Storage(String filePath) {
        this.filePath = filePath;
    }

    public ArrayList<Task> load() throws IOException {
        ArrayList<Task> tasks = new ArrayList<>();
        //System.out.println(filePath);
        File file = new File(filePath);
        if (!file.exists()){
            System.out.println("Making new file...");
            boolean isFileCreated = file.createNewFile();
            //System.out.println("New file made." + file.getAbsolutePath());
            return tasks;
        }
        Scanner scanner = new Scanner(file);
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            String[] parts = line.split("\\|");

            String type = parts[0].trim();
            String description = parts[2].trim();

            switch (type) {
            case "T":
                tasks.add(new Todo(description));
                break;
            case "D":
                String by = parts[3].trim();
                tasks.add(new Deadline(description, by));
                break;
            case "E":
                String fromTo = parts[3].trim();
                String[] fromToParts = fromTo.split(" to ");
                String from = fromToParts[0].trim();
                String to = fromToParts[1].trim();
                tasks.add(new Event(description, from, to));
                break;
            default:
                throw new IOException("Invalid data in file: Unknown task type.");
            }
        }
        scanner.close();
        return tasks;
    }

    public void save(ArrayList<Task> tasks) {
        try {
            FileWriter writer = new FileWriter(filePath);
            for (Task task : tasks) {
                writer.write(task.saveFormat() + "\n");
            }
            writer.close();
        } catch (IOException e) {
            System.err.println("Error saving data file: " + e.getMessage());
        }
    }
}
