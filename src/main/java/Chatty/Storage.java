
package Chatty;

import Chatty.tasks.*;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
/**
 * The Storage class handles all the loading and saving of the tasks
 */
public class Storage {
    private String filePath;

    /**
     * initializes the Storage class
     * @param filePath filepath of the storage
     */
    public Storage(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Starts laoding the tasks
     * @return an ArrayList of type task
     * @throws IOException
     */
    public ArrayList<Task> load() throws IOException {
        ArrayList<Task> tasks = new ArrayList<>();
        //System.out.println(filePath);
        File file = new File(filePath);
        if (!file.exists()){
            if (!file.exists()) {
                file.getParentFile().mkdirs(); // Create parent directories if needed
                file.createNewFile();
            }
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

    /**
     * Saves all tasks
     * @param tasks current list of tasks
     */
    public void save(TaskList tasks) {
        try {
            FileWriter writer = new FileWriter(filePath);
            for (int i = 0; i < tasks.size(); i++) {
                writer.write(tasks.get(i).saveFormat() + "\n");
            }
            writer.close();
        } catch (IOException e) {
            System.err.println("Error saving data file: " + e.getMessage());
        }
    }
}
