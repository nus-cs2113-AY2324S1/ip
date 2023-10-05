import Tasks.Deadline;
import Tasks.Event;
import Tasks.Task;
import Tasks.Todo;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Storage {

    protected String filePath;

    public Storage(String filePath) {
        this.filePath = filePath;
    }
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
    protected void taskArrayToFile(String filePath, ArrayList<Task> tasks) throws IOException {
        checkFileExists(filePath);
        FileWriter fw = new FileWriter(filePath, true);
        int index = tasks.size() - 1;
        Task lastTask = tasks.get(tasks.size() - 1);
        fw.write((index + 1) + "." + lastTask);
        fw.write("\n");
        fw.close();
    }
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

    private static void addTodoFromFile(String line, ArrayList<Task> tasks) throws IOException {
        Task element = new Todo(line.substring(7));
        if (line.charAt(6) == 'X') {
            element.setDone();
        }
        tasks.add(element);
    }

    private static void addDeadlineFromFile(String line, ArrayList<Task> tasks) throws IOException {
        int endOfDescriptionIndex = line.indexOf("(by");
        String description = line.substring(8, endOfDescriptionIndex);
        int startOfDueIndex = line.indexOf(':');
        int endOfDueIndex = line.indexOf(')');
        String due = line.substring(startOfDueIndex + 1, endOfDueIndex);
        Task element = new Deadline(description, due);
        tasks.add(element);
    }

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

}
