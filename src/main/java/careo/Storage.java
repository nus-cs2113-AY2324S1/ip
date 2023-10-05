package careo;

import java.io.File;
import java.io.IOException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

public class Storage {
    /** Where the persistence file (relative path) is stored */
    private String filePath;

    /**
     * Instantiates a Storage instance with access to a persistence file.
     *
     * @param filePath Where the persistence file (relative path) is stored.
     */
    public Storage(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Loads and returns stored tasks from the persistence file.
     *
     * @return The tasks stored in the persistence file.
     * @throws StorageLoadException When filePath doesn't point to a file.
     */
    public ArrayList<Task> load() throws StorageLoadException {
        ArrayList<Task> tasks = new ArrayList<>();

        try {
            File file = new File("temp.txt");

            FileReader fileReader = new FileReader(file);
            Scanner in = new Scanner(fileReader);

            while (in.hasNext()) {
                try {
                    String nextLine = in.nextLine();

                    String[] parts = nextLine.split("\\|");

                    Task newTask;
                    if (parts[0].equals("todo")) {
                        newTask = new ToDo(parts[1]);
                    } else if (parts[0].equals("event")) {
                        newTask = new Event(parts[1], parts[2], parts[3]);
                    } else {
                        newTask = new Deadline(parts[1], parts[2]);
                    }

                    tasks.add(newTask);
                } catch (Exception e) {
                    System.out.println("    An error occured. Maybe the file was corrupted.");
                    System.out.println("    Please quit the program now.");
                }
            }
        } catch (IOException e) {
            throw new StorageLoadException();
        }

        return tasks;
        // return tasks;
    }

    /**
     * Saves tasks to a persitence file.
     *
     * @param tasks The TaskList containing the tasks to be saved.
     * @param ui The Ui used to output an error if any occurs.
     */
    public void save(TaskList tasks, Ui ui) {
        try {
            File file = new File("temp.txt");

            FileWriter fileWriter = new FileWriter(file);
            PrintWriter printWriter = new PrintWriter(fileWriter);

            for (Task task : tasks.getTasks()) {
                if (task instanceof ToDo) {
                    printWriter.println("todo|" + task.getDescription());
                } else if (task instanceof Event) {
                    Event e = (Event) task;

                    printWriter.println("event|" + task.getDescription() + "|" + e.getFrom() + "" + e.getTo());
                } else {
                    Deadline d = (Deadline) task;

                    printWriter.println("deadline|" + task.getDescription() + "|" + d.getBy());
                }
            }

            printWriter.flush();
            fileWriter.flush();
        } catch (IOException e) {
            ui.showLoadingFileError();
        }
    }
}
