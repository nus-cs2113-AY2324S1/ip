package skippy;

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

    private final boolean WITHOUT_SCANNER = false;
    public SkippyUi ui;
    private static final String FILE_DIRECTORY = "data";
    private static final String FILE_PATH = "data/skippy.txt";

    public FileManager() {
        this.ui = new SkippyUi(WITHOUT_SCANNER);
    }

    public void createNewFileDirectory() throws IOException{
        File f = new File(FILE_DIRECTORY);
        ui.printLine();
        if (f.mkdir()) {
            System.out.println("Directory created: " + f.getName());
        } else {
            System.out.println("Directory already exists.");
        }
    }

    public void createNewSaveFile() throws IOException{
        File f = new File(FILE_PATH);
        if (f.createNewFile()) {
            System.out.println("File created: " + f.getName());
        } else {
            System.out.println("File already exists.");
        }
    }

    /**
     * Prints all tasks saved in the file.
     *
     * @throws FileNotFoundException if save file cannot be found
     */
    public void printFileContents() throws FileNotFoundException {
        try {
            File f = new File(FILE_PATH); // create a File for the given file path
            Scanner s = new Scanner(f);
            System.out.println("File Contents: ");
            while (s.hasNext()) {
                System.out.println(s.nextLine());
            }
            ui.printLine();
        } catch (FileNotFoundException e) {
            throw new FileNotFoundException();
        }
    }

    /**
     * Writes the tasks into the file.
     *
     * @param tasklist the tasklist containing the tasks
     * @throws IOException if i/O errors occur while reading the file.
     */
    public void writeToFile(TaskList tasklist) throws IOException {
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
    public TaskList parseSaveFile() throws IOException {
        Scanner s = new Scanner(FILE_PATH);
        TaskList taskList = new TaskList(s);
        s.close();
        return taskList;
    }
}


