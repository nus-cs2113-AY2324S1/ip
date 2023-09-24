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
import java.util.ArrayList;
import java.util.Scanner;

public class Storage {
    private String filePath;

    public Storage (String filePath) {
        this.filePath = filePath;
    }

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
                Task deadline = new Deadline(descriptions[2].trim(), descriptions[3].trim(), isMarked);
                tasks.add(deadline);
                break;
            case 'E':
                Task event = new Event(descriptions[2].trim(), descriptions[3].trim(), descriptions[4].trim(),
                        isMarked);
                tasks.add(event);
                break;
            default:
                break;
            }
        }
        return tasks;
    }

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
