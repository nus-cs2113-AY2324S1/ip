package fileIO;

import duke.Duke;
import exception.DukeException;
import exception.InvalidTimeException;
import task.Todo;
import message.Text;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.nio.file.StandardCopyOption;
import java.util.Scanner;


public class FileIO {
    /**
     * This method check if target repository and input,output files is found
     * if not, create the file for users
     * if error occurs in restoring data, e.g. the saved content contains invalid contents,
     * clear All the data and starts with a empty file
     * @throws IOException once cannot access file or file not found
     * @throws DukeException from method restoreSavedData
     */
    public static void outputFileInitialization() throws IOException, DukeException {
        checkAndCreateDataFolder();
        checkAndCreateFile("data\\taskList.txt");
        checkAndCreateFile("data/backup_taskList.txt");
        clearData();
        restoreSavedData("data/backup_taskList.txt");
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

    /**
     * save the file when exit the program
     */
    public static void backupTaskFile() {
        try {
            File src = new File("data\\taskList.txt");
            File dst = new File("data\\backup_taskList.txt");
            copyFile(src, dst);
            System.out.println("Great! I have saved the current tasks.");
        } catch (IOException e) {
            System.out.println("Oh No! I cannot save the file. Please check if the backup file is available.");
        }
    }

    private static void copyFile(File source, File dest) throws IOException {
        Files.copy(source.toPath(), dest.toPath(), StandardCopyOption.REPLACE_EXISTING);
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

    /**
     *
     * this method aims to restore all saved data from backup file once start the program
     * it implements by forming old user command for creating tasks,
     * and generate the task list using the command one by one
     */
    private static void restoreOneTask(Scanner keyboard) throws IOException, DukeException, IndexOutOfBoundsException,
            InvalidTimeException {
        String line = keyboard.nextLine();
        line = removeNumberAndDot(line).trim();  //each line looks like [T][ ] Description
        boolean isDone = line.charAt(4) == 'X';
        String description = line.substring(7);

        switch (line.charAt(1)) {
        case 'T':
            String todoCmd = "todo " + "description";
            Duke.list[Duke.taskCount] = Todo.newTodoTask(todoCmd);
            if (isDone){
                Duke.list[Duke.taskCount].restoreIsDone();
            }
            Duke.taskCount++;
            Text.restoreTaskIntoFile();
            break;

        case 'D':
            //3.[D][ ] return book (by: Friday)
            String due = ddlExtract(description);
            String ddltask = description.substring(0, description.indexOf("(by: ") - 2);
            String ddlCmd = "deadline " + ddltask + " /by " + due;
            Duke.list[Duke.taskCount] = task.Deadline.newDdl(ddlCmd);
            if (isDone){
                Duke.list[Duke.taskCount].restoreIsDone();
            }
            Duke.taskCount++;
            Text.restoreTaskIntoFile();
            break;

        case 'E':
            //4.[E][ ] java lesson (from: Friday 4pm to: 6pm)
            //event java lesson /from Friday 4pm /to 6pm
            String eventCmd = getEventCmd(description);
            Duke.list[Duke.taskCount] = task.Deadline.newDdl(eventCmd);
            if (isDone){
                Duke.list[Duke.taskCount].restoreIsDone();
            }
            Duke.taskCount++;
            Text.restoreTaskIntoFile();
            break;
        }
    }

    /**
     *
     * helper function to generate the old user input based on saved file
     */
    private static String getEventCmd(String description) throws IndexOutOfBoundsException{
        int indexOfFrom = description.indexOf("(from");
        int indexOfTo = description.indexOf("to:");
        String start = description.substring(indexOfFrom + 7, indexOfTo).trim();
        String end = description.substring(indexOfTo + 4);
        String eventTask = description.substring(0, indexOfFrom);
        return  "event " + eventTask + " /from " + start + " /to " + end;
    }

    private static String removeNumberAndDot(String input) {
        return input.replaceFirst("^\\d+\\.\\s*", "");
    }

    /**
     * helper function to extract deadline task descriptions from user input
     */
    private static String ddlExtract(String description) {
        int startIndex = description.indexOf("(by: ") + 5;
        int endIndex = description.indexOf(")");
        return description.substring(startIndex, endIndex);
    }

    public static void restoreSavedData(String DATA_PATH) throws IOException, DukeException, IndexOutOfBoundsException{
        try {
            File file = new File(DATA_PATH);
            Scanner scan = new Scanner(file);
            while (scan.hasNext()) {
                restoreOneTask(scan);
            }
        } catch (IndexOutOfBoundsException | InvalidTimeException ofb) {
            System.out.println("Failed to load the saved file. Please check if your backup is in the correct format.");
            System.out.println("We will start with a new empty file.");
            clearData();
        }
    }
}
