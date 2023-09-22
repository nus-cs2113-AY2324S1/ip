package magpie.files;

import magpie.exceptions.MagpieException;
import magpie.task.TaskList;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;
import java.io.FileWriter;


public class Storage {

    private static String filePath;

    private File taskFile;

    private Scanner scanFile;

    private StringBuffer buffer;

    private static String taskFileContents = "";

    private String taskLine;
    private String[] splitTaskLines;

    public void createDirectory() {
        String directoryPath = System.getProperty("user.dir") + "/data";
        filePath = directoryPath + "/data.txt";
        File directory = new File(directoryPath);
        if (!directory.exists()){
            directory.mkdir();
        }
    }
    public Storage() {

        createDirectory();
        taskFile = new File(filePath);

        try {
            boolean isCreated = taskFile.createNewFile();

            if (isCreated) {
                System.out.println("Created new data file for tasks :)");
                System.out.println("file created " + taskFile.getCanonicalPath());
            }
            else {
                System.out.println("Data file found! Now loading...");

            }

        }
        catch (IOException e) {
            System.out.println("Hmmmm...an IOException occured. Check that data directory exists or try again!");
        }


    }

    public void addTaskLine() throws MagpieException {
        boolean isMark = !(splitTaskLines[1].trim().equals("0"));
        switch(splitTaskLines[0].trim()) {
        case "T":
            TaskList.addTodo(isMark, splitTaskLines[2]);
            break;
        case "D":
            TaskList.addDeadline(isMark, splitTaskLines[2], splitTaskLines[3]);
            break;
        case "E":
            TaskList.addEvent(isMark, splitTaskLines[2], splitTaskLines[3], splitTaskLines[4]);
            break;
        default:
            throw new MagpieException("Error: Could not read task from file");

        }
    }

    public void loadFile(TaskList taskManager) {

        try {
            scanFile = new Scanner(taskFile);
            buffer = new StringBuffer();
            while(scanFile.hasNext()){
                taskLine = scanFile.nextLine();
                buffer.append(taskLine+System.lineSeparator());
                splitTaskLines = taskLine.split("\\|");

                addTaskLine();

            }
            scanFile.close();
        } catch (FileNotFoundException e) {
            System.out.println("Uh oh!! File not found!");
        } catch (ArrayIndexOutOfBoundsException e ){
            System.out.println("Uh oh!! Looks like your data file's corrupted!");
        } catch (MagpieException e) {
            System.out.println(e.getErrorMessage());
        }

    }

    public static void appendTaskToFile(String newTask) {
        taskFileContents += newTask + System.lineSeparator();

    }

    public static void deleteTaskFromFile(String taskToDelete) {

        taskFileContents = taskFileContents.replace(taskToDelete + "\r\n", "");

    }

    public static void updateTaskInFile(String oldLine, String newLine) {
        taskFileContents = taskFileContents.replace(oldLine, newLine);

    }

    public void saveToFile() throws IOException {
        FileWriter writer = new FileWriter(filePath);

        writer.append(taskFileContents);

        writer.flush();
        writer.close();
    }
}


