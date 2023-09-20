package Storage;

import Soccat.*;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import java.util.ArrayList;

public class Storage {
    protected String filePath;
    protected File fileObject;
    protected ArrayList<Task> taskData;

    public Storage(String filePath) throws IOException, SoccatException {
        this.filePath = filePath;
        File dataFile = new File(filePath);
        this.fileObject = dataFile;
        boolean isCreated = dataFile.exists();
        // Check if the file is created and create file if file is not present
        if (!isCreated) {
            isCreated = createFile(filePath, dataFile);
        }
        // If file is still not created, throw an exception
        if (!isCreated) {
            throw new SoccatException();
        }
        // Read file and put data into fileData
        Scanner dataFileScanner = new Scanner(dataFile);
        ArrayList<String> dataStrings = new ArrayList<>();
        while (dataFileScanner.hasNext()) {
            dataStrings.add(dataFileScanner.nextLine());
        }
        this.taskData = parseFile(dataStrings);
    }

    public boolean createFile(String filePath, File dataFile) throws IOException {
        String[] pathArray;
        String fileName;
        if (filePath.contains("/")) {
            pathArray = filePath.split("/");
        } else if (filePath.contains("\\")) {
            pathArray = filePath.split("/");
        } else {
            pathArray = new String[] {filePath};
        }
        fileName = pathArray[pathArray.length - 1];
        File directory = new File(filePath.replace(fileName, ""));
        try {
            boolean success;
            success = directory.mkdirs();
            success = dataFile.createNewFile();
            return success;
        } catch (IOException e) {
            throw new IOException();
        }
    }

    public ArrayList<Task> getTaskData() {
        return taskData;
    }

    private ArrayList<Task> parseFile(ArrayList<String> dataStrings) {
        ArrayList<Task> taskData = new ArrayList<>();
        int dataLength = dataStrings.size();
        for (int i=0; i<dataLength; i++) {
            String[] tokens = dataStrings.get(i).split("\\|");
            switch(tokens[0]) {
            case "T":
                taskData.add(new Todo(tokens[2]));
                break;
            case "D":
                taskData.add(new Deadline(tokens[2], tokens[3]));
                break;
            case "E":
                taskData.add(new Event(tokens[2], tokens[3], tokens[4]));
                break;
            }
            if (tokens[1].equals("1")) {
                taskData.get(i).setDone(true);
            }
        }
        return taskData;
    }

    private void updateFile() throws IOException {
        try (FileWriter dataFileWriter = new FileWriter(this.fileObject)) {
            for (Task task : this.taskData) {
                String taskType = "";
                String taskStatus = "";
                String taskDescription = "";
                if (task instanceof Todo) {
                    taskType = "T|";
                    taskDescription = task.getName();
                } else if (task instanceof Deadline) {
                    taskType = "D|";
                    taskDescription = task.getName() + "|" + ((Deadline) task).getBy();
                } else if (task instanceof Event) {
                    taskType = "E|";
                    taskDescription = task.getName() + "|" + ((Event) task).getFrom() + "|" + ((Event) task).getTo();
                }
                if (task.getDone()) {
                    taskStatus = "1|";
                } else {
                    taskStatus = "0|";
                }
                dataFileWriter.write(taskType + taskStatus + taskDescription + "\n");
            }
        } catch (IOException e) {
            throw new IOException();
        }
    }

    public void setTaskData(ArrayList<Task> taskData) throws IOException {
        this.taskData = taskData;
        updateFile();
    }
}
