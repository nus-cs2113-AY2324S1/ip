package Duchess.FunctionObjects;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import Duchess.TaskObjects.ToDo;
import Duchess.TaskObjects.Deadline;
import Duchess.TaskObjects.Event;
import Duchess.TaskObjects.Task;

/**
 * Class to handle file input and output.
 */
public class FileHandler {
    private String filePath;

    /**
     * Constructor for FileHandler.
     * @param filePath Path of file to be handled.
     */
    public FileHandler(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Loads the tasks from the file.
     * @return ArrayList of previously saved tasks.
     * @throws IOException invalid file path. (E.g. file does not exist)
     */
    public ArrayList<Task> load() throws IOException {
        ArrayList<Task> tasks = new ArrayList<>();
        BufferedReader br = new BufferedReader(new FileReader(this.filePath));
        String line;
        while ((line = br.readLine()) != null) {
            String[] task = line.split(" \\| ");
            switch (task[0]) {
            case "T":
                tasks.add(new ToDo(task[2]));
                break;
            case "D":
                tasks.add(new Deadline(task[2], task[3]));
                break;
            case "E":
                tasks.add(new Event(task[2], task[3], task[4]));
                break;
            }
            if (task[1].trim().equals("X")) {
                tasks.get(tasks.size() - 1).markAsDone();
            }
        }
        br.close();
        return tasks;
    }

    /**
     * Saves the tasks to the file.
     * @param tasks ArrayList of tasks to be saved.
     * @throws IOException invalid file path. (E.g. file does not exist)
     */
    public void save(ArrayList<Task> tasks) throws IOException {
        FileWriter fw = new FileWriter(this.filePath);
        for (Task task : tasks) {
            fw.write(task.toFileString() + "\n");
        }
        fw.close();
    }
}
