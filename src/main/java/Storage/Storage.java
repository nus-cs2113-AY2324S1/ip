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
            String[] tokens = dataStrings.get(i).split("\\" + Task.SPLIT_CHAR);
            switch(tokens[Task.TYPE_IDX]) {
            case Todo.TASK_CHAR:
                taskData.add(new Todo(tokens[Task.NAME_IDX]));
                break;
            case Deadline.TASK_CHAR:
                taskData.add(new Deadline(tokens[Task.NAME_IDX], tokens[Deadline.BY_IDX]));
                break;
            case Event.TASK_CHAR:
                taskData.add(new Event(tokens[Task.NAME_IDX], tokens[Event.FROM_IDX], tokens[Event.TO_IDX]));
                break;
            }
            if (tokens[Task.ISDONE_IDX].equals(Task.DONE_CHAR)) {
                taskData.get(i).setDone(true);
            }
        }
        return taskData;
    }

    private void updateFile() throws IOException {
        FileWriter dataFileWriter = new FileWriter(this.fileObject);
        try {
            for (Task task : this.taskData) {
                if (task instanceof Todo) {
                    dataFileWriter.write(((Todo) task).toTokenString());
                } else if (task instanceof Deadline) {
                    dataFileWriter.write(((Deadline) task).toTokenString());
                } else if (task instanceof Event) {
                    dataFileWriter.write(((Event) task).toTokenString());
                }
            }
            dataFileWriter.close();
        } catch (IOException e) {
            throw new IOException();
        } finally {
            dataFileWriter.close();
        }
    }

    public void setTaskData(ArrayList<Task> taskData) throws IOException {
        this.taskData = taskData;
        updateFile();
    }
}
