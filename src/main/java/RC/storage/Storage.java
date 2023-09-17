package RC.storage;

import RC.RCException;
import RC.TaskList;
import RC.task.Deadline;
import RC.task.Event;
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
        Files.createDirectories(path);
    }

    public Storage(String filePath) {
        if (!Files.isDirectory(path)) {
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

    public void load(TaskList tasks) throws IOException, RCException {
        BufferedReader inputFile = new BufferedReader(new FileReader(FILE_PATH));
        String line;
        System.out.println("\tLoading existing file...");

        while ((line = inputFile.readLine()) != null) {
            String[] split = line.split("\\|");
            String command = split[0].toLowerCase().trim();
            String inputCommand;
            boolean isDone;

            switch (command) {
            case "t":
                inputCommand = split[2].trim();
                Todo todo = new Todo(inputCommand);
                isDone = split[1].trim().equals("1");
                if (isDone) {
                    todo.markAsDone();
                }
                tasks.add(todo);
                break;
            case "d":
                inputCommand = split[2].trim();
                String byCommand = split[3].trim();
                Deadline deadline = new Deadline(inputCommand, byCommand);
                isDone = split[1].trim().equals("1");
                if (isDone) {
                    deadline.markAsDone();
                }
                tasks.add(deadline);
                break;
            case "e":
                inputCommand = split[2].trim();
                String fromString = split[3].trim();
                String toString = split[4].trim();
                RC.task.Event event = new Event(inputCommand, fromString, toString);
                isDone = split[1].trim().equals("1");
                if (isDone) {
                    event.markAsDone();
                }
                tasks.add(event);
                break;
            default:
                System.out.println("\tUnknown command");
            }
        }
        inputFile.close();
        System.out.println("\tLoading is complete.");
    }

    public static void write(TaskList tasks) throws RCException {
        try {
            FileWriter fr = new FileWriter(FILE_PATH);
            for (int i = 0; i < tasks.getSize(); i++) {
                String text = tasks.getTask(i).formatString() + "\n";
                fr.write(text);
            }
            fr.close();
        } catch (IOException e) {
            String errorMessage = "\tOOPS!!! Error writing to file.";
            throw new RCException(errorMessage);
        }
    }
}
