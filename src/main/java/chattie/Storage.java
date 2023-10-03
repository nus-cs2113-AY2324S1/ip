package chattie;

import chattie.Parser;
import chattie.TaskList;
import chattie.error.ChattieException;
import chattie.error.ErrorType;
import chattie.tasks.Deadline;
import chattie.tasks.Event;
import chattie.tasks.Task;
import chattie.tasks.Todo;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Storage {

    private static String folderPath;
    private static String filePath;

    public Storage(String folderPath, String filePath) {
        this.folderPath = folderPath;
        this.filePath = filePath;
    }

    public static ArrayList<Task> load() throws ChattieException{
        createFile();
        return readFromFile();
    }

    public static void createFile() throws ChattieException {
        File folder = new File(folderPath);
        File file = new File(filePath);

        if (!folder.exists()) {
            folder.mkdirs();
        }

        try {
            file.createNewFile();
        } catch (IOException e) {
            throw new ChattieException(ErrorType.FILE_LOAD_FAIL);
        }
    }

    public static ArrayList<Task> readFromFile() throws ChattieException{
        ArrayList<Task> list = new ArrayList<>();
        File file = new File(filePath);
        try {
            Scanner scanner = new Scanner(file);
            while (scanner.hasNext()) {
                list.add(Parser.parseTask(scanner.nextLine()));
            }
        } catch (FileNotFoundException e) {
            throw new ChattieException(ErrorType.FILE_LOAD_FAIL);
        }
        return list;
    }


    private static void writeToFile(String task) throws IOException{
        FileWriter fileWriter = new FileWriter(filePath, true);
        fileWriter.write(task + System.lineSeparator());
        fileWriter.close();
    }

    private static String formatTaskString(Task task) {
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

    public static void updateFile(TaskList tasks) throws IOException {
        ArrayList<Task> list = tasks.getList();
        FileWriter clearFile = new FileWriter(filePath);
        clearFile.write("");
        clearFile.close();
        for (Task task : list) {
            if (task == null) {
                continue;
            }
            writeToFile(formatTaskString(task));
        }
    }
}
