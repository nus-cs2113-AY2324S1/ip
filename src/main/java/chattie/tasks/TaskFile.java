package chattie.tasks;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

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

    public static ArrayList<Task> readFromFile() throws FileNotFoundException, IOException {
        ArrayList<Task> list = new ArrayList<>();
        File file = new File(FILE_PATH);
        Scanner scanner = new Scanner(file);
        while (scanner.hasNext()) {
            list.add(parseTask(scanner.nextLine()));
        }
        return list;

    }

    private static Task parseTask(String line) {
        Task task;
        String[] split = line.split(" \\| ");
        String taskType = split[0].trim();
        boolean isDone = split[1].equals("1");
        String description = split[2] + " ";
        if (taskType.equals("T")) {
            task = new Todo(description);
        } else if (taskType.equals("D")) {
            String by = split[3];
            task = new Deadline(description, by);
        } else {
            String[] fromTo = split[3].split("-");
            String from = fromTo[0];
            String to = fromTo[1];
            task = new Event(description, from, to);
        }
        task.setDone(isDone);
        return task;
    }

    private static void writeToFile(String task) throws IOException{
        FileWriter fileWriter = new FileWriter(FILE_PATH, true);
        fileWriter.write(task + System.lineSeparator());
        fileWriter.close();
    }

    private static String lineToAdd(Task task) {
        String description = task.getTask();
        String done = task.isDone() ? "1" : "0";
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
        FileWriter clearFile = new FileWriter(FILE_PATH);
        clearFile.write("");
        clearFile.close();
        for (Task task : list) {
            if (task == null) {
                continue;
            }
            writeToFile(lineToAdd(task));
        }
    }
}
