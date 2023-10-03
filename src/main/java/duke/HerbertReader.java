package duke;

import task.Deadline;
import task.Event;
import task.Task;
import task.Todo;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.sql.SQLOutput;

public abstract class HerbertReader {

    private static final Path folderPath = Paths.get("data");
    private static final Path filePath = folderPath.resolve("HerbertTasks.txt");

    public static void createSaveFileIfNotExists() {

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
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void addTaskToSaveFile(Task t) {
        try {
            BufferedWriter writer = Files.newBufferedWriter(
                    filePath,
                    StandardCharsets.UTF_8,
                    StandardOpenOption.APPEND
            );

            StringBuilder s = new StringBuilder(String.format(
                    "%s | %s | %s",
                    t.getCode(),
                    t.isCompleted() ? "1" : "0",
                    t.getDescription()
            ));
            if (t instanceof Deadline) {
                s.append(String.format(" | %s", ((Deadline) t).getDueDate()));
            } else if (t instanceof Event) {
                Event e = (Event) t;
                s.append(String.format(" | %s | %s", e.getFrom(), e.getTo()));
            }

            writer.write(s.toString());
            writer.newLine();
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
