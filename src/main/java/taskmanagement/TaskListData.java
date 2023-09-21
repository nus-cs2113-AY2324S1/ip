package taskmanagement;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Scanner;

public class TaskListData {
    private static final String FOLDER_PATH = "../data";
    private static final String FILE_PATH = "../data/zrantasks.txt";
    public static void init() throws IOException {
        File folder = new File(FOLDER_PATH);
        File data = new File(FILE_PATH);
        if (!folder.exists()){
            folder.mkdirs();
        }
        if(!data.exists()) {
            data.createNewFile();
        }
    }

    public static ArrayList<Task> loadTaskData() throws FileNotFoundException {
        File dataFile = new File(FILE_PATH);
        ArrayList<Task> storedTasks = new ArrayList<>();
        Scanner s = new Scanner(dataFile);
        while (s.hasNext()) {
            String data = s.nextLine();
            Task task = parseData(data);
            if(task!=null) {
                storedTasks.add(task);
            }
        }
        return storedTasks;
    }

    private static Task parseData(String data) {
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
            String to = taskData[3].trim();
            String from = taskData[4].trim();
            task = new Event(description, from, to, isDone);
            break;
        default:
            break;
        }
        return task;
    }


    public static void saveTasks(ArrayList<Task> tasks) throws IOException {
        FileWriter fw = new FileWriter(FILE_PATH, true);
        for (Task task : tasks) {
            String line = task.toString(); // Define a toFileString method in your Task class
            fw.write(line);
            fw.write(System.lineSeparator()); // Use system-specific line separator
        }
        fw.close();

    }

    private static String toString(Task task){
        String dataString= "";
        if (task instanceof ToDos){
            ToDos todo = (ToDos) task;
            dataString = todo.getTaskType() + " | " + task.getStatus();
        } else if (task instanceof Deadline) {
            Deadline deadline = (Deadline) task;
            dataString = deadline.getTaskType() + " | " + deadline.getStatus() + " | " + deadline.getBy();
        } else if (task instanceof Event) {
            Event event = (Event) task;
            dataString = event.getTaskType() + " | " + event.getStatus() + "|" + event.getFrom() + " | " + event.getTo();
        }
        return dataString;
    }

}
