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

    public static void fileManager() {
        boolean hasFile = bootUp();


        TaskManager.inputTask();

    }

    //Checks for any previously saved file
    public static boolean bootUp() {
        File file = new File(FILE_PATH);
        if (file.exists()) {
            return true;
        }
        return false;
    }

    public static void saver(ArrayList<Task> tasks) {
        try {
            FileWriter writer = new FileWriter(FILE_PATH);

            for (Task task : tasks) {
                // Write each task in the desired format to the file
                writer.write(task.toFileString() + "\n");
            }
            writer.close();
        } catch (IOException e) {
            System.out.println("Error saving tasks to file: " + e.getMessage());
        }
    }


    private static void writeToFile(String filePath, String textToAdd) throws IOException {
        FileWriter fw = new FileWriter(filePath);
        fw.write(textToAdd);
        fw.close();
    }

    private static void printFileContents(String filePath) throws FileNotFoundException {
        File file = new File(filePath);    // create a File for the given file path
        Scanner fileReader = new Scanner(file);     // create a Scanner using the File as the source
        while (fileReader.hasNext()) {
            System.out.println(fileReader.nextLine());
        }
    }
}
