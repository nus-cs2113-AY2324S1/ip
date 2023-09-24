package Storage;

import Soccat.*;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import java.util.ArrayList;


/**
 * Represents a storage object corresponding to a data file.
 * Storage object contains various methods to create, read from,
 * write to and update the datafile.
 * */

public class Storage {
    protected String filePath;
    protected File fileObject;
    protected ArrayList<Task> taskData;

    /**
     * Attempt to create a storage object from a file pointed to by the file path
     *
     * @param filePath The relative path to the storage file
     * @throws IOException If the file creation has failed
     * @throws SoccatException If file is not created after trying to create file
     * */
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

    /**
     * Attempt to create missing files and folders pointed to by the path
     * by creating missing parent folders and then creating the file
     *
     * @param filePath The relative path to the file
     * @param dataFile The file object to be created
     * @return boolean value of whether file creation is successful
     * @throws IOException If file creation failed
     * */
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

    /**
     * Read the contents of the file containing tasks in string format
     * and convert them to the corresponding task formats.
     *
     * @param dataStrings An ArrayList of lines read from the file
     * @return ArrayList of tasks from parsed lines
     * */
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

    /**
     * Updates the file based on the contents of tasks.
     * Convert tasks to token strings and write them to the file.
     *
     * @throws IOException if file writing operation is unsuccessful
     * */
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

    /**
     * Update the Storage object of tasks and call
     * updateFile() function to write the changes to the file.
     *
     * @throws IOException if the update function has errors
     * */
    public void setTaskData(ArrayList<Task> taskData) throws IOException {
        this.taskData = taskData;
        updateFile();
    }
}
