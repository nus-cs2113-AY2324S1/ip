package duke;

import java.io.*;
import java.util.ArrayList;

public class Storage {

    public Storage(String ignoredFilePath) {
    }

    public ArrayList<Task> loadTasks() throws IOException, DukeException {
        ArrayList<Task> tasks = new ArrayList<>();
        TasksHandler.readFromFile(tasks); // Pass an ArrayList<Task> to the readFromFile() method
        return tasks;
    }

    public static void saveTasks(ArrayList<Task> tasks) throws IOException {
        TasksHandler.writeToFile(tasks); // Pass the provided ArrayList<Task> to the writeToFile method
    }


}
