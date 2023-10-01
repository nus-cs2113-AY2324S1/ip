package duke;

import duke.exceptions.CorruptedFileException;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Handles the loading and saving of data from and to the data file.
 */
public class Storage {

    private String path;
    private ArrayList<Task> tasks;

    private static final DateTimeFormatter DTF = DateTimeFormatter.ofPattern("MMM-dd-yyyy'T'HH:mm");

    /**
     * Default Constructor for Storage with path set to "data/duke.txt".
     */
    public Storage() {
        this.path = "data/duke.txt";
    }

    /**
     * Constructor for Storage with path set to the given path.
     * @param path Path to the file which the Storage will read from and write to.
     */
    public Storage(String path) {
        this.path = path;
    }

    /**
     * Loads data from the file specified by the path into a task list.
     * If the file does not exist, an empty task list will be returned.
     * @return ArrayList of Tasks read from the file.
     */
    public ArrayList<Task> loadData(){
        try {
            tasks = new ArrayList<Task>();
            File f = new File(path);
            Scanner s = new Scanner(f);
            while (s.hasNext()) {
                String input = s.nextLine();
                readDataLine(input);
            }
        } catch (IOException e) {
            System.out.println("An error occurred when accessing the file.");
        }
        return tasks;
    }

    /**
     * Reads one line of data from the file and adds the corresponding task to the task list.
     * If the input formatting of the line is incorrect, the line will be skipped and the user will be notified about
     * the
     * file corruption.
     * @param input Line of data from the file.
     */
    public void readDataLine(String input) {
        String[] parts = input.split(" \\| ");
        try {
            Task task;

            switch(parts[0]) {
            case "T":
                task = new Todo(parts[2]);
                break;
            case "D":
                task = new Deadline(parts[2], LocalDateTime.parse(parts[3].replace(" ", "T"), DTF));
                break;
            case "E":
                task = new Event(parts[2], LocalDateTime.parse(parts[3].replace(" ", "T"), DTF),
                        LocalDateTime.parse(parts[4].replace(" ", "T"), DTF));
                break;
            default:
                throw new CorruptedFileException();
            }

            int binaryIsDone = Integer.parseInt(parts[1]);
            task.setDone(binaryIsDone);
            tasks.add(task);
        } catch (ArrayIndexOutOfBoundsException | NumberFormatException | CorruptedFileException e) {
            System.out.println("Failed to read line, the file is corrupted.");
        }
    }

    /**
     * Store the given task list to the file specified by the path.
     * If the file does not exist, a new file will be created.
     * @param tasks Task list to be saved.
     */
    public void saveData(ArrayList<Task> tasks) {
        try {
            this.tasks = tasks;
            File f = new File(path);
            f.getParentFile().mkdirs();
            f.createNewFile();
            FileWriter fw = new FileWriter(path);
            for (Task task : tasks) {
                fw.write(task.toFileString()+"\n");
            }
            fw.close();
        } catch (IOException e) {
            System.out.println("An error occurred when accessing the file.");
        }
    }

}
