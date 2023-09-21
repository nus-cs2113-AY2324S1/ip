package chattie.tasks;

import chattie.tasks.Event;
import chattie.tasks.Task;
import chattie.tasks.Todo;
import chattie.tasks.Deadline;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class TaskFile {
    private static final String FOLDER_PATH = "./data";
    private static final String FILE_PATH = "./data/chattie.txt";

    public static void loadFile() throws IOException {
        File folder = new File(FOLDER_PATH);
        File file = new File(FILE_PATH);

        if (!folder.exists()) {
            folder.mkdirs();
        }

        if (!file.exists()) {
            file.createNewFile();
        }
    }

    private static void writeToFile(String task) throws IOException{
        FileWriter fileWriter = new FileWriter(FILE_PATH, true);
        fileWriter.write(task + System.lineSeparator());
        fileWriter.close();
    }

    private static String lineToAdd(Task task) {
        String description = task.getTask();
        String done = task.isDone() ? "Done" : "Not Done";
        String taskString = "";
        if (task instanceof Todo) {
            taskString = "T | " + done + " | " + description;
        } else if (task instanceof Deadline) {
            Deadline deadline = (Deadline) task;
            taskString = "D | " + done + " | " + description + "| " + deadline.getBy();
        } else {
            Event event = (Event) task;
            taskString = "E | " + done + " | " + description + "| " + event.getFrom() + "-" + event.getTo();
        }
        return taskString;
    }

    public static void updateFile(ArrayList<Task> list) throws IOException {
        File file = new File(FILE_PATH);
        file.delete();
        file.createNewFile();
        for (Task task : list) {
            if (task == null) {
                continue;
            }
            writeToFile(lineToAdd(task));
        }
    }
}
