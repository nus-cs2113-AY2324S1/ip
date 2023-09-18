package elvis;

import elvis.task.Task;

import java.io.File;
import java.io.FileWriter;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.IOException;

public class FileManager {
    private static final String FILE_PATH = "./tasks.txt";
    private static final String FILE_NAME = "tasks.txt";
    private static File openedFile;

    public static void fileManager() {
        boolean hasFile = bootUp();
        loadTasksFromFile();
        if (hasFile) {
            System.out.println("These are tasks loaded from before: ");
            TaskManager.inputTaskFromFile("list");
        }
        TaskManager.inputTaskManually();
    }

    //Checks for any previously saved file, creates if missing
    public static boolean bootUp() {
        File file = new File(FILE_PATH);

        if (!file.exists()) {   //File doesn't exist, so create it
            try {
                if (file.createNewFile()) { //Returns true if successfully created
                    openedFile = file;      //File used to save tasks
                    System.out.println("File does not exist.\nCreating new file...\nFile created successfully.");
                } else {
                    System.out.println("File creation failed.");
                }
            } catch (IOException exception) {
                System.out.println("An error occurred: " + exception.getMessage());
            }
            return false;
        } else {
            System.out.println("File already exists.\nOpening existing file...");
            openedFile = file;      //File used to save tasks
            return true;
        }
    }

    public static void loadTasksFromFile() {
        try {
            File file = openedFile;
            Scanner fileReader = new Scanner(openedFile);     // create a Scanner using the File as the source
            while (fileReader.hasNext()) {
                String lineOfFile = fileReader.nextLine();
                TaskManager.inputTaskFromFile(lineOfFile);   // Parse each line into the tasks ArrayList
            }
        } catch (FileNotFoundException exception) {
            System.out.println("Data file not found.");
        }
    }

    // Write each task in the desired format to the file
    public static void saver(ArrayList<Task> tasks) {
        try {
            FileWriter writer = new FileWriter(FILE_PATH);
            for (Task task : tasks) {
                writer.write(task.toFileString() + "\n");
            }
            writer.close();
        } catch (IOException exception) {
            System.out.println("Error saving tasks to file: " + exception.getMessage());
        }
    }
}
