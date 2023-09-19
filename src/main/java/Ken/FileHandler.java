package Ken;

import Exceptions.KenMissingTaskException;
import Exceptions.KenReadFromFileException;
import Exceptions.KenWriteToFileException;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
public class FileHandler {
    private static final String FILE_PATH = "data/kenbot.txt";

    public static void readFromFile(TaskList list) throws KenReadFromFileException {
        File file = new File(FILE_PATH);
        if (!file.exists()) {
            try {
                file.getParentFile().mkdir();
                file.createNewFile();
            } catch (IOException e) {
                throw new KenReadFromFileException();
            }
        }
    }

    public static void writeToFile(TaskList list) throws KenWriteToFileException {
        try {
            FileWriter fw = new FileWriter(FILE_PATH);
            for (int i = 1; i <= list.getSize(); i++) {
                Task task = list.getTask(i);
                String taskText = task.toString().trim();
                fw.write(taskText);
            }
            fw.close();
        } catch (Exception e) {
            throw new KenWriteToFileException();
        }
    }
}
