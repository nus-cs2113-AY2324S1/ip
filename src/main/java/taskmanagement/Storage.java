package taskmanagement;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Storage {
    private static final String FOLDER_PATH = "./data";
    private String filePath;

    public Storage(String filePath) {
        this.filePath = filePath;
    }

    public void init() throws IOException {
        File folder = new File(FOLDER_PATH);
        if (!folder.exists()) {
            folder.mkdirs();
        }

        File data = new File(filePath);
        if (!data.exists()) {
            if (!data.createNewFile()) {
                throw new IOException("Unable to create a new file.");
            }
        }
    }

    public ArrayList<Task> load() throws IOException {
        init();
        ArrayList<Task> storedTasks = new ArrayList<>();
        File dataFile = new File(filePath);
        try (Scanner scanner = new Scanner(dataFile);) {
            while (scanner.hasNext()) {
                String data = scanner.nextLine();
                Task task = parseData(data);
                if (task != null) {
                    storedTasks.add(task);
                }
            }
        } catch (FileNotFoundException e) {
            throw new IOException("File not found: " + filePath, e);
        }
        return storedTasks;
    }

    private Task parseData(String data) {
        String[] taskData = data.split("\\|");
        String taskType = taskData[0].trim();
        String description = taskData[2].trim();
        boolean isDone = Boolean.parseBoolean(taskData[1].trim());

        Task task = null;
        switch (taskType) {
        case "T":
            task = new ToDos(description, isDone);
            break;
        case "D":
            String by = taskData[3].trim();
            task = new Deadline(description, by, isDone);
            break;
        case "E":
            String from = taskData[3].trim();
            String to = taskData[4].trim();
            task = new Event(description, from, to, isDone);
            break;
        default:
            break;
        }
        return task;
    }

    public void saveTasks(TaskList tasks) throws IOException {
        try (FileWriter fw = new FileWriter(filePath)) {
            for (Task task : tasks.listItems) {
                String line = toString(task);
                fw.write(line + System.lineSeparator());
            }
        }
    }

    private String toString(Task task) {
        if (task instanceof ToDos) {
            ToDos todo = (ToDos) task;
            return todo.getTaskType() + " | " + task.getStatus() + " | " + task.getDescription();
        } else if (task instanceof Deadline) {
            Deadline deadline = (Deadline) task;
            return deadline.getTaskType() + " | " + deadline.getStatus() + " | " +
                    task.getDescription() + " | " + deadline.getBy();
        } else if (task instanceof Event) {
            Event event = (Event) task;
            return event.getTaskType() + " | " + event.getStatus() + " | " +
                    task.getDescription() + " | " + event.getFrom() + " | " + event.getTo();
        }
        return "";
    }
}
