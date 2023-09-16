package magpie.files;

import magpie.task.taskHandler;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;
import java.io.FileWriter;


public class fileHandler {

    private static final String FILEPATH = "src/main/data/data.txt";

    private final File taskFile;

    private Scanner scanFile;

    private StringBuffer buffer;

    private static String taskFileContent = "";

    private String taskLine;
    private String[] splitTaskLine;
    public fileHandler() {


        taskFile = new File(FILEPATH);

        try{
            boolean isCreated = taskFile.createNewFile();

            if (isCreated) {
                System.out.println("Created new data file for tasks :)");
                System.out.println("file created "+taskFile.getCanonicalPath());
            }
            else {
                System.out.println("Data file found! Now loading...");

            }

        }
        catch (IOException e) {
            System.out.println("Hmmmm...an IOException occured. Check that data directory exists or try again!");
        }


    }

    public void addTaskLine() {
        boolean isMark = !(splitTaskLine[1].trim().equals("0"));
        switch(splitTaskLine[0].trim()) {
        case "T":
            taskHandler.addTodo(isMark, splitTaskLine[2]);
            break;
        case "D":
            taskHandler.addDeadline(isMark, splitTaskLine[2], splitTaskLine[3]);
            break;
        case "E":
            taskHandler.addEvent(isMark,splitTaskLine[2],splitTaskLine[3],splitTaskLine[4]);
            break;
        default:
            System.out.println("Something's wrong with your data file!");

        }
    }

    public void readFile(taskHandler taskManager) throws FileNotFoundException {

        try {
            scanFile = new Scanner(taskFile);
            buffer = new StringBuffer();
            while(scanFile.hasNext()){
                taskLine = scanFile.nextLine();
                buffer.append(taskLine+System.lineSeparator());
                splitTaskLine = taskLine.split("\\|");

                addTaskLine();

            }
            scanFile.close();
        } catch (FileNotFoundException e) {
            System.out.println("Uh oh!! File not found!");
        } catch (ArrayIndexOutOfBoundsException e ){
            System.out.println("Uh oh!! Looks like your data file's corrupted!");
        }

    }

    public static void appendTaskToFile(String newTask) {
        taskFileContent += newTask + System.lineSeparator();

    }

    public static void deleteTaskFromFile(String taskToDelete) {

        taskFileContent = taskFileContent.replace(taskToDelete + "\r\n", "");

    }

    public static void updateTaskInFile(String oldLine, String newLine) {
        taskFileContent = taskFileContent.replace(oldLine, newLine);

    }

    public void saveToFile() throws IOException {
        FileWriter writer = new FileWriter(FILEPATH);

        writer.append(taskFileContent);

        writer.flush();
        writer.close();
    }
}


