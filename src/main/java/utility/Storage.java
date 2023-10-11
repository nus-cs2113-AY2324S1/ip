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

    /**
     * Constructor to check if file already exists
     * If not, creates a new file in the desired filepath
     * Finally, reads from the file and parses the data
     * then copies the data into the taskData
     *
     * @param filePath where the file will be created/found
     * @throws IOException
     * @throws FrankException
     */
    public Storage(String filePath) throws IOException, FrankException {
        this.filePath = filePath;
        File dataFile = new File(filePath);
        this.fileObj = dataFile;

        // Create file if not already created
        boolean isCreated = dataFile.exists();
        if(!isCreated) {
            isCreated = createFile(this.filePath, dataFile);
        }
        // Check if createFile is successful
        if (!isCreated) {
            throw new FrankException("File creation failed. Try again. ");
        }

        // Finally, reads from the document
        Scanner scanner = new Scanner(dataFile);
        ArrayList<String> currentData = new ArrayList<>();
        while(scanner.hasNext()) {
            currentData.add(scanner.nextLine());
        }
        this.taskData = parseFile(currentData);
    }

    /**
     * Gets taskData (contents of the file)
     * @return taskData
     */
    public ArrayList<Task> getTaskData() {
        return this.taskData;
    }

    /**
     * Sets taskData (contents of the file)
     * @param taskData The data to be copied to the file
     * @throws IOException Input Exception
     */
    public void setTaskData(ArrayList<Task> taskData) throws IOException {
        this.taskData = taskData;
        updateFile();
    }

    /**
     * A function to create a file if not already created,
     * including creating the directories to it
     *
     * @param filePath the area to create the file
     * @param dataFile The actual file to save
     * @return A boolean indicating successful or unsuccessful file creation
     * @throws IOException Input Exception
     */
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
            success = directory.mkdirs() && dataFile.createNewFile();
            return success;
        } catch (IOException e) {
            throw new IOException();
        }
    }

    /**
     * Reads whatever is in the current Tasklist and updates
     * the txt file
     *
     * @throws IOException Input Exception
     */
    private void updateFile() throws IOException {
        FileWriter writer = new FileWriter(this.fileObj);
        try {
            for(Task task : this.taskData) {
                writer.write(task.toString());
                writer.write(System.getProperty("line.separator"));
            }
        } catch (IOException e) {
            throw new IOException();
        } finally {
            writer.close();
        }
    }

    /**
     * Interpret the Strings in the data file into
     * Tasks for use
     *
     * @param currentData An ArrayList of String Lines
     * @return ArrayList<Task> data from the file
     */
    private ArrayList<Task> parseFile(ArrayList<String> currentData) throws FrankException {
        ArrayList<Task> tasks = new ArrayList<>();
        int tasksLength = currentData.size();
        for(int i = 0; i < tasksLength; i++) {
            String[] words = currentData.get(i).split("] "); // split in the middle
            // [D][X] Example Task
            char taskType = words[0].charAt(1);
            boolean isDone = words[0].charAt(4) == 'X';
            String description, dueDate, startDate, endDate;
            int startIndex, endIndex;
            switch(taskType) {
            case 'T':
                Todo newTodo = new Todo(words[1]);
                newTodo.setIsDone(isDone);
                tasks.add(newTodo);
                break;
            case 'D':
                // [D][X] The deadline thing (by: 2359)
                startIndex = words[1].lastIndexOf(" (by: ");
                description = words[1].substring(0, startIndex);
                startIndex += 6;
                endIndex = words[1].lastIndexOf(")");
                dueDate = words[1].substring(startIndex, endIndex);
                Deadline newDeadline = new Deadline(description, dueDate);
                newDeadline.setIsDone(isDone);
                tasks.add(newDeadline);
                break;
            case 'E':
                // [E][X] The event thing (from: April 11 to: April 19)

                // The event thing
                startIndex = words[1].lastIndexOf(" (from: ");
                description = words[1].substring(0,startIndex);
                // April 11
                startIndex += 8;
                endIndex = words[1].lastIndexOf(" to: ");
                startDate = words[1].substring(startIndex, endIndex);
                // April 19
                startIndex = endIndex + 5;
                endIndex = words[1].lastIndexOf(")");
                endDate = words[1].substring(startIndex, endIndex);

                Event newEvent = new Event(description,startDate,endDate);
                newEvent.setIsDone(isDone);
                tasks.add(newEvent);
                break;
            default:
                // Corrupted, shouldn't have any tasks without identifier
                throw new FrankException("Brough the save file is corrupted, Task identifier missing! ");
            }
        }
        return tasks;
    }
}