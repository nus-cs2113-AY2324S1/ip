package duke;

import task.Deadline;
import task.Event;
import task.Todo;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;

public abstract class HerbertReader {

    public static void createSaveFileIfNotExists() {
        Path folderPath = Paths.get("data");
        Path filePath = folderPath.resolve("HerbertTasks.txt");

        try {
            // Create directory if it doesn't exist
            if (!Files.exists(folderPath)) {
                Files.createDirectories(folderPath);
            }

            // Create file if it doesn't exist
            if (!Files.exists(filePath)) {
                Files.createFile(filePath);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void loadFromSaveFile(Herbert herbert) {
        Path filePath = Paths.get("data", "HerbertTasks.txt");

        try {
            BufferedReader reader = Files.newBufferedReader(filePath);
            String line = reader.readLine();

            while (line != null) {
                String[] split = line.split(" \\| ");

                switch (split[0]) {
                case "T":
                    Todo t = new Todo(split[2]);
                    t.setCompleted(split[1].equals("1"));
                    herbert.addTask(t);
                    break;
                case "D":
                    Deadline d = new Deadline(split[2], split[3]);
                    d.setCompleted(split[1].equals("1"));
                    herbert.addTask(d);
                    break;
                case "E":
                    Event e = new Event(split[2], split[3], split[4]);
                    e.setCompleted(split[1].equals("1"));
                    herbert.addTask(e);
                    break;
                }

                line = reader.readLine();
            }
        } catch (IOException | SecurityException e) {
            e.printStackTrace();
        }
    }

}
