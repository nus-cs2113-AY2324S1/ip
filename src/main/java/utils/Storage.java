package utils;

import task.Deadline;
import task.Event;
import task.Task;
import task.Todo;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.List;

/*
 * A storage class handles the saving and loading of data.
 */
public class Storage {

    private File storageFile;

    public Storage(String file_path) {
        storageFile = new File(file_path);
    }

    /*
     * Saves the current list of tasks in storageFile if it exists.
     * Otherwise, create the file and store the data there.
     *
     * @param tasks is the list of tasks
     */
    public void save(List<Task> tasks) {
        try {
            storageFile.getParentFile().mkdirs();
            storageFile.createNewFile();
            FileWriter fw = new FileWriter(storageFile.getPath());
            StringBuilder save = new StringBuilder();
            for (Task task : tasks) {
                save.append(task.getSaveString()).append("\n");
            }
            fw.write(save.toString());
            fw.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    /*
     * @return the list of tasks in our save file. If save file does not exists,
     * returns an empty list.
     */
    public List<Task> loadSave() {
        List<Task> tasks = new ArrayList<>();
        try {
            Scanner sc = new Scanner(storageFile);
            while (sc.hasNext()) {
                String[] line = sc.nextLine().split(" \\| ");
                boolean isMark = line[1].equals("1");
                String taskName = line[2];
                switch (line[0]) {
                    case "T": {
                        Task task = new Todo(taskName);
                        task.setIsComplete(isMark);
                        tasks.add(task);
                        break;
                    }
                    case "D": {
                        String by = line[3];
                        Task task = new Deadline(taskName, by);
                        task.setIsComplete(isMark);
                        tasks.add(task);
                        break;
                    }
                    case "E": {
                        String from = line[3].split("_")[0];
                        String to = line[3].split("_")[1];
                        Task task = new Event(taskName, from, to);
                        task.setIsComplete(isMark);
                        tasks.add(task);
                        break;
                    }
                    default: {
                        return new ArrayList<>();
                    }
                }
            }
        } catch (FileNotFoundException ignored) {}
        return tasks;
    }

}
