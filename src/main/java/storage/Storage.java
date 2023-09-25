package storage;

import data.TaskList;
import data.task.Deadline;
import data.task.Event;
import data.task.Task;
import data.task.Todo;

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
        Scanner data = new Scanner(file);
        while (data.hasNext()) {
            String taskDetails = data.nextLine();
            char taskType = taskDetails.charAt(0);
            String[] descriptions = taskDetails.split("[|]");
            int isMarked = Integer.parseInt(descriptions[1].trim());
            switch (taskType) {
            case 'T':
                Task todo = new Todo(descriptions[2].trim(), isMarked);
                tasks.add(todo);
                break;
            case 'D':
                LocalDateTime formattedDate = LocalDateTime.parse(descriptions[3].trim());
                Task deadline = new Deadline(descriptions[2].trim(), formattedDate, isMarked);
                tasks.add(deadline);
                break;
            case 'E':
                LocalDateTime formattedStart= LocalDateTime.parse(descriptions[3].trim());
                LocalTime formattedEnd = LocalTime.parse(descriptions[4].trim());
                Task event = new Event(descriptions[2].trim(), formattedStart, formattedEnd, isMarked);
                tasks.add(event);
                break;
            default:
                break;
            }
        }
        return tasks;
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
        FileWriter fw = new FileWriter(file);
        ArrayList<Task> tasks = taskList.getTasks();
        for (Task task : tasks) {
            String output = null;
            boolean isMarked = task.getTaskStatus();
            int markedIndex;

            if (isMarked) {
                markedIndex = 1;
            } else {
                markedIndex = 0;
            }

            if (task instanceof Todo) {
                output = "T | " + Integer.toString(markedIndex) + " | " + task.getDescription();
            } else if (task instanceof Deadline) {
                output = "D | " + Integer.toString(markedIndex) + " | " + task.getDescription()
                        + " | " + ((Deadline) task).getBy();
            } else if (task instanceof Event) {
                output = "E | " + Integer.toString(markedIndex) + " | " + task.getDescription()
                        + " | " + ((Event) task).getStart() + " | " + ((Event) task).getEnd();
            }
            fw.write(output);
            fw.write("\n");
        }
        fw.close();
    }
}
