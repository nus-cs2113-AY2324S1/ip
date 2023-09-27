package spaceman.storage;

import spaceman.data.TaskList;
import spaceman.data.task.Deadline;
import spaceman.data.task.Event;
import spaceman.data.task.Task;
import spaceman.data.task.Todo;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;

import java.time.LocalTime;
import java.time.LocalDateTime;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * Represents the file used to store the task data.
 */
public class Storage {
    private String filePath;

    public Storage (String filePath) {
        this.filePath = filePath;
    }

    /**
     * Reads data from the storage file and return it.
     * @return an arraylist of tasks
     * @throws FileNotFoundException If the storage file does not exist.
     */
    public ArrayList<Task> readDataFromFile() throws FileNotFoundException {
        ArrayList<Task> tasks = new ArrayList<>();
        File file = new File(filePath);
        taskDecoder(file, tasks);
        return tasks;
    }

    /**
     * Decodes the storage data file and store it into the arraylist of tasks.
     * @param file storage data file
     * @param tasks arraylist of tasks
     * @throws FileNotFoundException If the storage data file does not exist.
     */
    private static void taskDecoder(File file, ArrayList<Task> tasks) throws FileNotFoundException {
        Scanner data = new Scanner(file);
        while (data.hasNext()) {
            String taskDetails = data.nextLine();
            char taskType = taskDetails.charAt(0);
            String[] descriptions = taskDetails.split("[|]");
            int isMarked = Integer.parseInt(descriptions[1].trim());
            switch (taskType) {
            case 'T':
                tasks.add(new Todo(descriptions[2].trim(), isMarked));
                break;
            case 'D':
                LocalDateTime formattedDate = LocalDateTime.parse(descriptions[3].trim());
                tasks.add(new Deadline(descriptions[2].trim(), formattedDate, isMarked));
                break;
            case 'E':
                LocalDateTime formattedStart= LocalDateTime.parse(descriptions[3].trim());
                LocalTime formattedEnd = LocalTime.parse(descriptions[4].trim());
                tasks.add(new Event(descriptions[2].trim(), formattedStart, formattedEnd, isMarked));
                break;
            default:
                break;
            }
        }
    }

    /**
     * Writes the data to the storage file.
     * Creates a new file if the file does not exist.
     * @param taskList list of tasks
     * @throws IOException If there were errors converting and/or storing the data to the file.
     */
    public void writeToFile(TaskList taskList) throws IOException {
        File file = new File(filePath);
        if (!file.exists()) {
            file.getParentFile().mkdirs();
            file.createNewFile();
        }
        FileWriter fw = taskEncoder(taskList, file);
        fw.close();
    }

    private static FileWriter taskEncoder(TaskList taskList, File file) throws IOException {
        FileWriter fw = new FileWriter(file);
        ArrayList<Task> tasks = taskList.getTasks();
        for (Task task : tasks) {
            int markedIndex = encodeTaskStatus(task.getTaskStatus());
            String output = toString(task, markedIndex);
            fw.write(output);
            fw.write("\n");
        }
        return fw;
    }

    private static int encodeTaskStatus(boolean isMarked) {
        if (isMarked) {
            return 1;
        } else {
            return 0;
        }
    }

    private static String toString(Task task, int markedIndex) {
        if (task instanceof Todo) {
            return "T | " + markedIndex + " | " + task.getDescription();
        } else if (task instanceof Deadline) {
            return "D | " + markedIndex + " | " + task.getDescription() + " | " + ((Deadline) task).getBy();
        } else if (task instanceof Event) {
            return "E | " + markedIndex + " | " + task.getDescription() + " | " + ((Event) task).getStart()
                    + " | " + ((Event) task).getEnd();
        }
        return null;
    }
}
