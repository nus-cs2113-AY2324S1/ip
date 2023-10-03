package MySun.storage;

import MySun.data.TaskList;
import MySun.data.task.Deadline;
import MySun.data.task.Event;
import MySun.data.task.Task;
import MySun.data.task.Todo;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;

import java.time.LocalDateTime;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * Read from and write to a file.
 */
public class Storage {
    private String filePath;

    public Storage (String filePath) {
        this.filePath = filePath;
    }

    /**
     * Reads data from the storage file and return it.
     * If the file is not exist, create an empty one.
     * @return an arraylist of tasks
     * @throws FileNotFoundException If the storage file does not exist.
     */
    public ArrayList<Task> readDataFromFile() throws FileNotFoundException {
        ArrayList<Task> tasks = new ArrayList<>();
        File file = new File(filePath);

        if (!file.exists() || file.length() == 0) {
            return tasks;
        }

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
                    LocalDateTime formattedEnd = LocalDateTime.parse(descriptions[4].trim());
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
        FileWriter fw = new FileWriter(file);
        ArrayList<Task> tasks = taskList.getTasks();
        for (Task task : tasks) {
            int markedIndex;
            if (task.isDone()) {
                markedIndex = 1;
            } else {
                markedIndex = 0;
            }
            String output = toString(task, markedIndex);
            fw.write(output);
            fw.write("\n");
        }
        fw.close();
    }

    /**
     * Converts the task details to a String to be stored in the storage data file.
     * @param task task being converted to String
     * @param markedIndex integer indicator of the task status
     * @return a String object to be stored in the storage data file
     */
    private static String toString(Task task, int markedIndex) {
        if (task instanceof Todo) {
            return "T | " + markedIndex + " | " + task.getOnlyDescription();
        } else if (task instanceof Deadline) {
            return "D | " + markedIndex + " | " + task.getOnlyDescription() + " | " + ((Deadline) task).getBy();
        } else if (task instanceof Event) {
            return "E | " + markedIndex + " | " + task.getOnlyDescription() + " | " + ((Event) task).getStart()
                    + " | " + ((Event) task).getEnd();
        } else {
            return null;
        }
    }
}
