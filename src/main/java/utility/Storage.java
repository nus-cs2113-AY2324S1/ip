package utility;

import exception.*;
import task.*;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import java.util.ArrayList;

public class Storage {
    protected String filePath; // Relative path
    protected File fileObj;
    protected ArrayList<Task> taskData;

    public Storage(String filePath) throws IOException, FrankException {
        this.filePath = filePath;
        File dataFile = new File(filePath);
        this.fileObj = dataFile;
        boolean isCreated = dataFile.exists();
        if(!isCreated) {
            isCreated = createFile(this.filePath, dataFile);
        }
        // Check if createFile is successful
        if (!isCreated) {
            throw new FrankException("File creation failed. Try again. ");
        }

        Scanner scanner = new Scanner(dataFile);
        ArrayList<String> currentData = new ArrayList<>();
        while(scanner.hasNext()) {
            currentData.add(scanner.nextLine());
        }
    }

    public ArrayList<Task> getTaskData() {
        return this.taskData;
    }

    public void setTaskData(ArrayList<Task> taskData) throws IOException {
        this.taskData = taskData;
        updateFile();
    }
    // Return a bool true or false indicating successful creation
    public boolean createFile (String filePath, File dataFile) throws IOException {
        String[] pathArray;
        String fileName;
        if(filePath.contains("/")) {
            pathArray = filePath.split("/");
        } else if (filePath.contains("\\")) {
            pathArray = filePath.split("/");
        } else { // No separators
            pathArray = new String[] {filePath};
        }
        // To do: add more invalid file path checks
        fileName = pathArray[pathArray.length - 1];
        File directory = new File(filePath.replace(fileName,""));
        try {
            boolean success;
            success = directory.mkdirs();
            success = dataFile.createNewFile();
            return success;
        } catch (IOException e) {
            throw new IOException();
        }
    }

    private void updateFile() throws IOException {
        FileWriter writer = new FileWriter(this.fileObj);
        try {
            for(Task task : this.taskData) {
                writer.write(task.toString());
            }
        } catch (IOException e) {
            throw new IOException();
        } finally {
            writer.close();
        }
    }

}