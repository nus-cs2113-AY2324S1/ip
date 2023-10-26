import Tasks.Deadline;
import Tasks.Event;
import Tasks.Task;
import Tasks.Todo;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * The Storage class handles reading from and writing to a file.
 */
public class Storage {

    protected String filePath;

    /**
     * Constructs a Storage object with the specified file path.
     *
     * @param filePath The file path of the storage file.
     */
    public Storage(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Checks if the file exists, and creates it if it doesn't.
     *
     * @param filePath The file path to check and create.
     */
    protected void checkFileExists(String filePath) {
        File file = new File(filePath);
        // Check if the file or directory exists, and create it if it doesn't
        String dirName = "docs";
        Path dirPath = Paths.get(dirName);
        if (!Files.exists(dirPath)) {
            try {
                Files.createDirectory(dirPath);
            } catch (IOException e) {
                throw new RuntimeException("Error creating directory: " + dirName, e);
            }
        }
        try {
            file.createNewFile();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Checks if the specified directory exists, and creates it if it doesn't.
     *
     * @param directoryPath The path of the directory to be checked and created if necessary.
     */
    protected void checkDirectoryExists(String directoryPath) {
        File directory = new File(directoryPath);
        // Check if the directory exists, and create it if it doesn't
        if (!directory.exists()) {
            if (directory.mkdirs()) {
                System.out.println("Directory created");
            } else {
                System.out.println("Failed to create directory");
            }
        }
    }



    /**
     * Appends the details of the last task in the list to the storage file.
     *
     * @param filePath The file path to write to.
     * @param tasks    The list of tasks.
     * @throws IOException If an I/O error occurs.
     */
    protected void taskArrayToFile(String filePath, ArrayList<Task> tasks) throws IOException {
        checkFileExists(filePath);
        FileWriter fw = new FileWriter(filePath, true);
        int index = tasks.size() - 1;
        Task lastTask = tasks.get(tasks.size() - 1);
        fw.write((index + 1) + "." + lastTask);
        fw.write("\n");
        fw.close();
    }
    /**
     * Reads tasks from the storage file and adds them to the tasks list.
     *
     * @param filePath The file path to read from.
     * @param tasks    The list of tasks to populate.
     * @throws IOException If an I/O error occurs.
     */
    protected void fileToTaskArray(String filePath, ArrayList<Task> tasks) throws IOException {
        File f = new File(filePath); // create a File for the given file path
        Scanner list = new Scanner(f); // create a Scanner using the File as the source
        while (list.hasNext()) {
            String line = list.nextLine();
            char type = line.charAt(3);
            boolean mark = false;
            if (line.charAt(6) == 'X') {
                mark = true;
            }
            switch (type) {
                case 'T':
                    addTodoFromFile(line, tasks, mark);
                    break;
                case 'D':
                    addDeadlineFromFile(line, tasks, mark);
                    break;
                case 'E':
                    addEventFromFile(line, tasks, mark);
                    break;
                default:
                    break;
            }
        }
    }
    /**
     * Adds a Todo task to the tasks list based on the input line.
     *
     * @param line  The input line.
     * @param tasks The list of tasks.
     * @throws IOException If an I/O error occurs.
     */
    private static void addTodoFromFile(String line, ArrayList<Task> tasks, boolean mark) throws IOException {
        Task element = new Todo(line.substring(8).trim());
        if (line.charAt(6) == 'X') {
            element.setDone();
        }
        tasks.add(element);
        if (mark) {
            tasks.get(tasks.size() - 1).setDone();
        }
    }
    /**
     * Adds a Deadline task to the tasks list based on the input line.
     *
     * @param line  The input line.
     * @param tasks The list of tasks.
     * @throws IOException If an I/O error occurs.
     */
    private static void addDeadlineFromFile(String line, ArrayList<Task> tasks, boolean mark) throws IOException {
        int endOfDescriptionIndex = line.indexOf("(by");
        String description = line.substring(8, endOfDescriptionIndex).trim();
        int startOfDueIndex = line.indexOf(':');
        int endOfDueIndex = line.indexOf(')');
        String due = line.substring(startOfDueIndex + 1, endOfDueIndex).trim();
        Task element = new Deadline(description, due);
        tasks.add(element);
        if (mark) {
            tasks.get(tasks.size() - 1).setDone();
        }
    }
    /**
     * Adds an Event task to the tasks list based on the input line.
     *
     * @param line  The input line.
     * @param tasks The list of tasks.
     * @throws IOException If an I/O error occurs.
     */
    private static void addEventFromFile(String line, ArrayList<Task> tasks, boolean mark) throws IOException {
        int endOfDescriptionIndex = line.indexOf("(from:");
        String description = line.substring(8, endOfDescriptionIndex).trim();
        int startOfFromIndex = endOfDescriptionIndex + 7;
        int endOfFromIndex = line.indexOf("to:");
        String from = line.substring(startOfFromIndex, endOfFromIndex).trim();
        String to = line.substring(endOfFromIndex + 4, line.indexOf(')')).trim();
        Task element = new Event(from, to, description);
        tasks.add(element);
        if (mark) {
            tasks.get(tasks.size() - 1).setDone();
        }
    }

    /**
     * Deletes a specific line from a file.
     *
     * @param index     The line number to be deleted.
     * @param filePath  The path of the file to perform the deletion.
     * @throws IOException If an I/O error occurs while deleting the line.
     */
    protected void deleteLineFromFile(int index, String filePath) throws IOException {
        File inputFile = new File(filePath);
        File tempFile = new File("tempFile.txt");
        Scanner list = new Scanner(inputFile);
        PrintWriter writer = new PrintWriter(new FileWriter(tempFile));
        // Read all lines into a list
        List<String> lines = new ArrayList<>();
        while (list.hasNextLine()) {
            lines.add(list.nextLine());
        }
        // Remove the line at the specified index
        if (index >= 0 && index < lines.size()) {
            lines.remove(index);
        }
        // Write the modified list back to the file
        for (String line : lines) {
            writer.println(line);
        }
        list.close();
        writer.close();
        if (!inputFile.delete()) {
            throw new IOException("Could not delete original file");
        }
        if (!tempFile.renameTo(inputFile)) {
            throw new IOException("Could not rename temp file");
        }
    }
    /**
     * Marks a task as done in the file by updating its status.
     *
     * @param index     The index of the task to be marked as done.
     * @param filePath  The path of the file containing the task list.
     * @throws IOException If an I/O error occurs while marking the task.
     */
    public void markTaskInFile(int index, String filePath) throws IOException {
        File inputFile = new File(filePath);
        File tempFile = new File("temp.txt");
        Scanner list = new Scanner(inputFile);
        PrintWriter writer = new PrintWriter(new FileWriter(tempFile));
        int count = -1;
        while (list.hasNextLine()) {
                String line = list.nextLine();
                count++;
                if (count == index && line.length() >= 6) {
                    StringBuilder modifiedLine = new StringBuilder(line);
                    modifiedLine.setCharAt(6, 'X');
                    line = modifiedLine.toString();
                }
            writer.write(line + System.lineSeparator());
        }
        writer.close();
        list.close();
        inputFile.delete();
        tempFile.renameTo(inputFile);
    }

    public void unmarkTaskInFile(int index, String filePath) throws IOException {
        File inputFile = new File(filePath);
        File tempFile = new File("temp.txt");
        Scanner list = new Scanner(inputFile);
        PrintWriter writer = new PrintWriter(new FileWriter(tempFile));
        int count = -1;
        while (list.hasNextLine()) {
            String line = list.nextLine();
            count++;
            if (count == index && line.length() >= 6) {
                StringBuilder modifiedLine = new StringBuilder(line);
                modifiedLine.setCharAt(6, ' ');
                line = modifiedLine.toString();
            }
            writer.write(line + System.lineSeparator());
        }
        writer.close();
        list.close();
        inputFile.delete();
        tempFile.renameTo(inputFile);
    }


}

