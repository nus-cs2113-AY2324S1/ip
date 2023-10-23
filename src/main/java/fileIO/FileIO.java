package fileIO;

import Oriento.Oriento;
import exception.OrientoException;
import exception.InvalidTimeException;
import task.Task;
import task.Todo;
import message.Text;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.Scanner;


public class FileIO {
    /**
     * This method check if target repository and input,output files is found
     * if not, create the file for users
     * if error occurs in restoring data, e.g. the saved content contains invalid contents,
     * clear All the data and starts with a empty file
     * @throws IOException once cannot access file or file not found
     * @throws OrientoException from method restoreSavedData
     */
    public static void outputFileInitialization() throws IOException {
        checkAndCreateDataFolder();
        checkAndCreateFile("data/taskList.txt");
        checkAndCreateFile("data/backup_taskList.txt");
        clearData();
        try{
            restoreSavedData("data/backup_taskList.txt");
        } catch (Exception e){
            System.out.println("Failed to load the saved file. Please check if your backup is in the correct format.");
            System.out.println("We will start with a new empty file.");
            clearData();
        }
    }

    /**
     *
     * @param path represents the output file path
     * @throws IOException only when file is not found and failed to create a new file
     */
    private static void checkAndCreateFile(String path) throws IOException {
        File f = new File(path);
        if(!f.exists()){
            if(!f.createNewFile()){
                throw new IOException();
            }
        }
    }

    /**
     * similar to checkAndCreateFile
     */
    private static void checkAndCreateDataFolder() throws IOException {
        File folder = new File("data");
        if (!folder.isDirectory()) {
            if(!folder.mkdirs()){
                throw new IOException();
            }
        }
    }

    /**
     * save all the current content to another backup file
     */
    public static void backupTaskFile() {
        try {
            String currentContent = Task.getConcatenateTasks();
            overwriteToFile("data/backup_taskList.txt", currentContent);
        } catch (IOException e) {
            System.out.println("Oh No! I cannot save the file. Please check if the backup file is available.");
        }
    }

    /**
     * clear all the saved data and set the taskList as an empty list
     * use when error occurs in restoring data, e.g. containing message in wrong format
     * @throws IOException if failed to access the file
     */
    private static void clearData() throws IOException {
        Path clearFile = Paths.get("data/taskList.txt");
        if (Files.size(clearFile) != 0) {
            Files.write(clearFile, new byte[0], StandardOpenOption.WRITE, StandardOpenOption.TRUNCATE_EXISTING);
        }
        Task[] emptylist = new Task[100];
        Oriento.list = emptylist;
        Oriento.taskCount = 0;
    }

    /**
     *
     * @param filePath path of target file
     * @param taskAppend the content to replace
     * @throws IOException if failed to access target file
     */
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
    private static void restoreOneTask(Scanner keyboard) throws IndexOutOfBoundsException,
            InvalidTimeException, OrientoException, IOException {
        String line = keyboard.nextLine();
        line = removeNumberAndDot(line).trim();  //each line looks like [T][ ] Description
        boolean isDone = line.charAt(4) == 'X';
        String description = line.substring(7);  //remove brackets [T][ ]
            switch (line.charAt(1)) {
            case 'T':
                String todoCmd = "todo " + description;
                Oriento.list[Oriento.taskCount] = Todo.newTodoTask(todoCmd);
                if (isDone){
                    Oriento.list[Oriento.taskCount].restoreIsDone();
                }
                Oriento.taskCount++;
                Text.restoreTaskIntoFile();
                break;

            case 'D':
                //3.[D][ ] return book (by: Friday)
                String due = ddlExtract(description);
                String ddltask = description.substring(0, description.indexOf("(by: ")).trim();
                String ddlCmd = "deadline " + ddltask + " /by " + due;
                Oriento.list[Oriento.taskCount] = task.Deadline.newDdl(ddlCmd);
                if (isDone){
                    Oriento.list[Oriento.taskCount].restoreIsDone();
                }
                Oriento.taskCount++;
                Text.restoreTaskIntoFile();
                break;

            case 'E':
                String eventCmd = getEventCmd(description);
                Oriento.list[Oriento.taskCount] = task.Event.newEventTask(eventCmd);
                if (isDone){
                    Oriento.list[Oriento.taskCount].restoreIsDone();
                }
                Oriento.taskCount++;
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
        int indexOfEnd = description.indexOf(")");
        String start = description.substring(indexOfFrom + 7, indexOfTo).trim();
        String end = description.substring(indexOfTo + 4, indexOfEnd);
        String eventTask = description.substring(0, indexOfFrom);
        return  "event " + eventTask + " /from " + start + " /to " + end;
    }

    private static String removeNumberAndDot(String input) {
        return input.replaceFirst("^\\d+\\.\\s*", "");
    }

    /**
     * helper function to extract deadline task descriptions from user input
     * expected input format: 5. [D][ ] asw (by: mon)
     */
    private static String ddlExtract(String description) {
        int startIndex = description.indexOf("(by: ") + 5;
        int endIndex = description.indexOf(")");
        return description.substring(startIndex, endIndex);
    }

    /**
     * This method will restore the saved data, but scanner the old data,
     * and simple generate a new taskList again with the saved data
     * @param DATA_PATH path of backUp file
     * @throws IOException if failed to access files
     * Exception is generated when creating a task using the saved data
     */
    public static void restoreSavedData(String DATA_PATH) throws IOException, OrientoException, IndexOutOfBoundsException{
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
