package duke;

import java.io.IOException;
import java.io.FileWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import tasklist.TaskList;


/**
 * Provides the methods for managing the savefile. Creates the directory and savefile, and saves
 * the file when needed.
 */
public class FileManager {

    public static DukeUi ui = new DukeUi();
    private static final String FILE_DIRECTORY = "data";
    private static final String FILE_PATH = "data/duke.txt";
    private static final File SAVE_FILE = new File("data/duke.txt");

    public static void createNewFileDirectory() throws IOException{
        File f = new File(FILE_DIRECTORY);
        if (f.mkdir()) {
            System.out.println("Directory created: " + f.getName());
        } else {
            System.out.println("Directory already exists.");
        }
    }

    public static void createNewSaveFile() throws IOException{
        File f = new File(FILE_PATH);
        if (f.createNewFile()) {
            System.out.println("File created: " + f.getName());
        } else {
            System.out.println("File already exists.");
        }
    }

    public static void printFileContents() throws FileNotFoundException {
        File f = new File(FILE_PATH); // create a File for the given file path
        Scanner s = new Scanner(f);
        ui.printLine();
        System.out.println("File Contents: ");
        while (s.hasNext()) {
            System.out.println(s.nextLine());
        }
        ui.printLine();
    }

    public static void writeToFile(TaskList tasklist) throws IOException {
        FileWriter fw = new FileWriter(FILE_PATH);
        fw.write(tasklist.toString());
        fw.close();
    }

    /**
     * Parses the savefile into the tasklist.
     *
     * @return Tasklist with the save data
     *
     * @throws IOException if I/O errors occur while reading the file
     */
    public static TaskList parseSavefile() throws IOException {
        Scanner s = new Scanner(SAVE_FILE);
        TaskList taskList = new TaskList(s);
        s.close();
        return taskList;
    }
}


