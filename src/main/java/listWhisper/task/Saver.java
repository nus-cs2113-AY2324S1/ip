package listWhisper.task;

import java.io.*;
import java.nio.file.Files;
public abstract class Saver {
    private final static String userWorkingDirectory = System.getProperty("user.dir");
    private final static  java.nio.file.Path FILE_PATH = java.nio.file.Paths.get(userWorkingDirectory, "data.txt");

    public static void saveList(List list) throws IOException {
        clearFile();
        Files.writeString(FILE_PATH, list.toString());
    }

    private static void clearFile() throws IOException {
        boolean directoryExists = java.nio.file.Files.exists(FILE_PATH);

        if (!directoryExists) {
            Files.createFile(FILE_PATH);
        } else {
            Files.delete(FILE_PATH);
            Files.createFile(FILE_PATH);
        }
    }
}
