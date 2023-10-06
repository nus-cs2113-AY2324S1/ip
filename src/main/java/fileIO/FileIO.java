package fileIO;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.nio.file.StandardCopyOption;

public class FileIO {

    public static void outputFileInitialization() throws IOException {
        checkAndCreateDataFolder();
        checkAndCreateFile("data\\taskList.txt");
        clearData(); //if the output file in not empty
    }

    private static void checkAndCreateFile(String path) throws IOException {
        File f = new File(path);
        if(!f.exists()){
            if(!f.createNewFile()){
                throw new IOException();
            }
        }
    }

    private static void checkAndCreateDataFolder() throws IOException {
        File folder = new File("data");
        if (!folder.isDirectory()) {
            if(!folder.mkdirs()){
                throw new IOException();
            }
        }
    }

    private static void backupTaskFile() throws IOException{
        Path sourcePath = Paths.get("data/taskList.txt");
        Path targetPath = Paths.get("data/backup_taskList.txt");
        Files.copy(sourcePath, targetPath, StandardCopyOption.REPLACE_EXISTING); //if file not exist, create one;
        System.out.println("Great! I have saved the current tasks.");
    }

    private static void clearData() throws IOException {
        Path clearFile = Paths.get("data/taskList.txt");
        if (Files.size(clearFile) != 0) {
            Files.write(clearFile, new byte[0], StandardOpenOption.WRITE, StandardOpenOption.TRUNCATE_EXISTING);
        }
    }

    public static void overwriteToFile(String filePath, String taskAppend) throws IOException {
        FileWriter fw = new FileWriter(filePath);
        fw.write(taskAppend);
        fw.close();
    }
}
