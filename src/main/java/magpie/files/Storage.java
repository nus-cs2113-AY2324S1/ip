package magpie.files;

import magpie.exceptions.MagpieException;
import magpie.task.TaskList;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;
import java.io.FileWriter;

/**
 * Contains methods for file operations such as reading, writing, and saving files.
 * Responsible for loading tasks from the file and saving tasks in the file.<br>
 */
public class Storage {

    private static String filePath;

    private File taskFile;

    private Scanner scanFile;

    private static String taskFileContents = "";

    private String taskLine;
    private String[] splitTaskLines;

    /**
     * Checks if data/data.txt path exists and creates directory if it doesn't exist.
     */
    public void createDirectory() {
        String directoryPath = System.getProperty("user.dir") + "/data";
        filePath = directoryPath + "/data.txt";
        File directory = new File(directoryPath);
        if (!directory.exists()) {
            directory.mkdir();
        }
    }

    /**
     * Calls <code>createDirectory</code> and initializes <code>taskFile</code> for file handling.
     * Creates a new <code>data.txt</code> file if not present.
     */
    public Storage() {

        createDirectory();
        taskFile = new File(filePath);

        try {
            boolean isCreated = taskFile.createNewFile();

            if (isCreated) {
                System.out.println("Created new data file for tasks :)");
                System.out.println("file created " + taskFile.getCanonicalPath());
            } else {
                System.out.println("Data file found! Now loading...");
            }
        } catch (IOException e) {
            System.out.println("Hmmmm...an IOException occurred. Check that data directory exists or try again!");
        }

    }

    /**
     * Trims leading and trailing whitespaces from each element in splitTaskLines
     */
    public void trimTaskLine() {

        for (int i = 0; i < splitTaskLines.length; i++) {
            splitTaskLines[i] = splitTaskLines[i].trim();
        }
    }

    /**
     * Matches type of task in <code>TaskLine</code> to a <code>Switch case</code>.
     * Pass required <code>Task</code> arguments and call its respective <code>add</code>
     * methods to load task into list.
     *
     * @throws MagpieException if data file contains invalid task lines.
     */
    public void addTaskLine() throws MagpieException {
        boolean isMark = !(splitTaskLines[1].equals("0"));
        switch(splitTaskLines[0]) {
        case "T":
            TaskList.addTodo(isMark, splitTaskLines[2]);
            break;
        case "D":
            TaskList.addDeadline(isMark, splitTaskLines[2], splitTaskLines[3]);
            break;
        case "E":
            TaskList.addEvent(isMark, splitTaskLines[2], splitTaskLines[3], splitTaskLines[4]);
            break;
        default:
            throw new MagpieException("Error: Could not read task from file");

        }
    }

    /**
     * Reads data file line by line using a <code>Scanner</code> object. For each line, retrieve
     * Task Details by splitting using "|" and call <code>addTaskLine</code> to load task.
     */
    public void loadFile() {

        try {
            scanFile = new Scanner(taskFile);
            while(scanFile.hasNext()) {
                taskLine = scanFile.nextLine();
                splitTaskLines = taskLine.split("\\|");
                trimTaskLine();
                addTaskLine();
            }
            scanFile.close();
        } catch (FileNotFoundException e) {
            System.out.println("Uh oh!! File not found!");
        } catch (ArrayIndexOutOfBoundsException e ) {
            System.out.println("Uh oh!! Looks like your data file's corrupted!");
        } catch (MagpieException e) {
            System.out.println(e.getErrorMessage());
        }

    }

    /**
     * Appends details of a new <code>Task</code> to <code>taskFileContents</code>.
     *
     * @param newTask Task to be appended.
     */
    public static void appendTaskToFile(String newTask) {
        taskFileContents += newTask;

    }

    /**
     * Deletes details of a <code>Task</code> from <code>taskFileContents</code>.
     *
     * @param taskToDelete Task to be deleted.
     */
    public static void deleteTaskFromFile(String taskToDelete) {

        taskFileContents = taskFileContents.replace(taskToDelete + System.lineSeparator(), "");
    }
    /**
     * Updates details of a <code>Task</code> in <code>taskFileContents</code>,
     * typically to update mark or unmark status.
     *
     * @param oldLine Line to be replaced.
     * @param newLine New Line to replace old Line.
     */
    public static void updateTaskInFile(String oldLine, String newLine) {
        taskFileContents = taskFileContents.replace(oldLine, newLine);
    }

    /**
     * Writes <code>data.txt</code> with contents of <code>taskFileContents</code> to save all task details onto
     * hard disk.
     *
     * @throws IOException if file writing was unsuccessful.
     */
    public void saveToFile() throws IOException {
        FileWriter writer = new FileWriter(filePath);

        writer.append(taskFileContents);

        writer.flush();
        writer.close();
    }
}


