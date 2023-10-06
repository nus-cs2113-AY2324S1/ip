import Tasks.Deadline;
import Tasks.Event;
import Tasks.Task;
import Tasks.Todo;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

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
        try {
            if (file.createNewFile()) {
                System.out.println("File created");
            } else {
                System.out.println("File already exists");
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
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
            switch (type) {
                case 'T':
                    addTodoFromFile(line, tasks);
                    break;
                case 'D':
                    addDeadlineFromFile(line, tasks);
                    break;
                case 'E':
                    addEventFromFile(line, tasks);
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
    private static void addTodoFromFile(String line, ArrayList<Task> tasks) throws IOException {
        Task element = new Todo(line.substring(7));
        if (line.charAt(6) == 'X') {
            element.setDone();
        }
        tasks.add(element);
    }
    /**
     * Adds a Deadline task to the tasks list based on the input line.
     *
     * @param line  The input line.
     * @param tasks The list of tasks.
     * @throws IOException If an I/O error occurs.
     */
    private static void addDeadlineFromFile(String line, ArrayList<Task> tasks) throws IOException {
        int endOfDescriptionIndex = line.indexOf("(by");
        String description = line.substring(8, endOfDescriptionIndex);
        int startOfDueIndex = line.indexOf(':');
        int endOfDueIndex = line.indexOf(')');
        String due = line.substring(startOfDueIndex + 1, endOfDueIndex);
        Task element = new Deadline(description, due);
        tasks.add(element);
    }
    /**
     * Adds an Event task to the tasks list based on the input line.
     *
     * @param line  The input line.
     * @param tasks The list of tasks.
     * @throws IOException If an I/O error occurs.
     */
    private static void addEventFromFile(String line, ArrayList<Task> tasks) throws IOException {
        int endOfDescriptionIndex = line.indexOf("(from:");
        String description = line.substring(8, endOfDescriptionIndex);
        int startOfFromIndex = endOfDescriptionIndex + 7;
        int endOfFromIndex = line.indexOf("to:");
        String from = line.substring(startOfFromIndex, endOfFromIndex);
        String to = line.substring(endOfFromIndex + 4, line.indexOf(')'));
        Task element = new Event(from, to, description);
        tasks.add(element);
    }

    protected void searchKeyword(String line, ArrayList<Task> tasks) {
        ArrayList<Task> searchResults = new ArrayList<>();
        String keyword = line.substring(4);
        for (Task task : tasks) {
            if (task.getDescription().contains(keyword)) {
                searchResults.add(task);
            }
        }
        Ui.outputHeader();
        for (Task task : searchResults) {
            System.out.println(task);
        }
        Ui.printLine();
    }

    // go to a specific line in the file
    protected void deleteLine(int index, String filePath) throws IOException {
        File inputFile = new File(filePath);
        File tempFile = new File("temp.txt"); // Create a temporary file

        BufferedReader reader = new BufferedReader(new FileReader(inputFile));
        BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile));

        int count = 0;
        String currentLine;
        while ((currentLine = reader.readLine()) != null) {
            count++;
            if (count != index) {
                writer.write(currentLine + System.getProperty("line.separator"));
            }
        }

        writer.close();
        reader.close();

        tempFile.renameTo(inputFile); // Rename the temporary file to the original file
    }


}

}
