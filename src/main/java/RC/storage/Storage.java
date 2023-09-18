package RC.storage;

import RC.RCException;
import RC.TaskList;
import RC.task.Deadline;
import RC.task.Event;
import RC.task.Task;
import RC.task.Todo;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Storage {
    private static final String FILE_PATH = "data/tasks.txt";
    private final Path path = Paths.get("data");
    private static void createDirectory(Path path) throws IOException {
        System.out.println("\tDirectory doesn't exist. Creating directory...");
        Files.createDirectory(path);
    }

    public Storage(String filePath) {
        if (!Files.exists(path)) {
            try {
                createDirectory(path);
            } catch (IOException e) {
                System.out.println("\tError: " + e.getMessage());
            }
        }
    }

    public void save(TaskList tasks) throws RCException {
        write(tasks);
    }

    public void load(TaskList taskList) throws RCException {
        try {
            BufferedReader inputFile = new BufferedReader(new FileReader(FILE_PATH));
            String line;
            System.out.println("\tLoading existing file...");

            while ((line = inputFile.readLine()) != null) {
                String[] split = line.split("\\|");
                String command = split[0].trim();
                String input;
                String isDone = split[1].trim();
                Task task;
                switch (command) {
                case "T":
                    input = split[2].trim();
                    task = new Todo(input);
                    break;
                case "D":
                    input = split[2].trim();
                    String byString = split[3].trim();
                    task = new Deadline(input, byString);
                    break;
                case "E":
                    input = split[2].trim();
                    String fromString = split[3].trim();
                    String toString = split[4].trim();
                    task = new Event(input, fromString, toString);
                    break;
                default:
                    throw new RCException("\tError loading file.");
                }
                if (isDone.equals("1")) {
                    task.markAsDone();
                }
                taskList.load(task);
            }
            inputFile.close();
        } catch (IOException e) {
            String message = "\tFile not found. Creating new file...";
            throw new RCException(message);
        }
        System.out.println("\tLoading is complete.");
    }

    public static void write(TaskList taskList) throws RCException {
        try {
            FileWriter fr = new FileWriter(FILE_PATH);
            for (int i = 0; i < taskList.tasks.size(); i++) {
                String text = taskList.tasks.get(i).formatString() + "\n";
                fr.write(text);
            }
            fr.close();
        } catch (IOException e) {
            String errorMessage = "\tOOPS!!! Error writing to file.";
            throw new RCException(errorMessage);
        }
    }
}
