package alan.storage;

import alan.data.exception.AlanException;
import alan.data.task.Deadline;
import alan.data.task.Event;
import alan.data.task.Task;
import alan.data.task.TaskType;
import alan.data.task.Todo;
import alan.ui.Ui;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Scanner;

import static alan.common.Messages.MESSAGE_INVALID_TASK_TYPE_FOUND;

/**
 * Handles reading from and storing data to the text file.
 */
public class Storage {
    private String filePath;
    private ArrayList<Task> taskArrayList;
    private Ui ui;

    public Storage(String filePath) {
        this.filePath = filePath;
        this.taskArrayList = new ArrayList<>();
        this.ui = new Ui();
    }

    public ArrayList<Task> load() throws FileNotFoundException {
        readFileHandler();
        return taskArrayList;
    }

    public void save() throws Exception {
        saveFileHandler();
    }

    /**
     * Reads the text file from the specified file path and stores the tasks in an ArrayList.
     *
     * @throws FileNotFoundException If file is not found at the specified file path.
     */
    private void readFileHandler() throws FileNotFoundException {
        String userWorkingDirectory = System.getProperty("user.dir");
        java.nio.file.Path tasksFilePath = java.nio.file.Paths.get(userWorkingDirectory, filePath);
        File textFile = new File(String.valueOf(tasksFilePath));
        if (textFile.length() != 0) {
            Scanner s = new Scanner(textFile);
            ArrayList<String> list = new ArrayList<>();

            while (s.hasNext()){
                list.add(s.nextLine());
            }
            s.close();

            taskArrayList = extractAndStoreDataInTaskList(list);
        }
    }

    /**
     * Handles the extraction of each parameter.
     * Creates a task using the extracted parameters and adds in TaskList.
     *
     *  @return ArrayList containing tasks extracted from the list of strings.
     */
    private ArrayList<Task> extractAndStoreDataInTaskList(ArrayList<String> list) {
        //todo: after this line should check if the data is in correct format
        for (String task : list) {
            String[] splitTaskString = task.split(" \\| ");
            String taskType = splitTaskString[0];
            String isDoneString = splitTaskString[1];
            String description = splitTaskString[2];
            boolean isDone = isDoneStringToBoolean(isDoneString);
            try {
                addTaskToTaskList(taskType, description, splitTaskString);
            } catch (AlanException e) {
                ui.showToUser(e.getMessage());
            }

            int lastTaskIndex = taskArrayList.size() - 1;
            taskArrayList.get(lastTaskIndex).setDone(isDone);
        }

        return taskArrayList;
    }

    /**
     * Adds task to ArrayList according to the respective taskType.
     *
     * @throws AlanException If taskType read is invalid.
     */
    public void addTaskToTaskList(String taskType, String description, String[] splitTaskString) throws AlanException {
        switch (taskType) {
        case "T":
            taskArrayList.add(new Todo(description));
            break;
        case "D":
            String by = splitTaskString[3];
            taskArrayList.add(new Deadline(description, by));
            break;
        case "E":
            String date = splitTaskString[3];
            String[] splitDate = date.split("-");
            String from = splitDate[0];
            String to = splitDate[1];

            taskArrayList.add(new Event(description, from, to));
            break;
        default:
            new AlanException(MESSAGE_INVALID_TASK_TYPE_FOUND);
            break;
        }
    }

    private boolean isDoneStringToBoolean (String string) {
        if (string.equals("1")) {
            return true;
        }
        return false;
    }

    /**
     * Saves the tasks in a text file at th specified path.
     *
     * @throws Exception If file or folder does not exist in the specified path.
     */
    private void saveFileHandler() throws Exception {
        String userWorkingDirectory = System.getProperty("user.dir");
        java.nio.file.Path tasksFilePath = java.nio.file.Paths.get(userWorkingDirectory, filePath);
        java.nio.file.Path dataFolderPath = tasksFilePath.getParent();
        File textFile = new File(String.valueOf(tasksFilePath));
        File folder = new File(String.valueOf(dataFolderPath));

        if (!Files.exists(dataFolderPath)) {
            folder.mkdir();
            ui.showFolderNotFoundMessage(userWorkingDirectory);
        }

        if (!Files.exists(tasksFilePath)) {
            textFile.createNewFile();
            ui.showFileNotFoundMessage(dataFolderPath);
        }

        //input arraylist data into text file
        for (int i = 0; i < taskArrayList.size(); i++) {
            String taskDataRow = getStringOfTaskInformation(i);

            if (i == 0) {
                writeToFile(tasksFilePath.toString(), taskDataRow);
            } else {
                appendToFile(tasksFilePath.toString(), taskDataRow);
            }
        }
    }

    /**
     * Formats the task into a string.
     *
     * @param i index of the task in ArrayList.
     * @return a string of the formatted task information to be stored in the text file.
     */
    private String getStringOfTaskInformation(int i) {
        Task task = taskArrayList.get(i);
        String taskDataRow = task.getTaskType() + " | " + task.getStatusValue() + " | " + task.getDescription();

        if (task.getTaskType() == TaskType.D) {
            Deadline deadline = (Deadline) task;
            taskDataRow = taskDataRow + " | " + deadline.getBy();
        }

        if (task.getTaskType() == TaskType.E) {
            Event event = (Event) task;
            taskDataRow = taskDataRow + " | " + event.getFrom() + "-" +event.getTo();
        }

        taskDataRow = taskDataRow + "\n";
        return taskDataRow;
    }

    /**
     * Writes text to the text file at the specified file path.
     * Will overwrite all text in text file.
     *
     * @param filePath file path of the text file.
     * @param textToAdd text to be written to the text file.
     * @throws IOException If I/O operations are interrupted.
     */
    private void writeToFile(String filePath, String textToAdd) throws IOException {
        FileWriter fw = new FileWriter(filePath);
        fw.write(textToAdd);
        fw.close();
    }

    /**
     * Appends text to the text file at the specified file path.
     * Will add text to text file.
     *
     * @param filePath file path of the text file.
     * @param textToAdd text to be added to the text file.
     * @throws IOException If I/O operations are interrupted.
     */
    private void appendToFile(String filePath, String textToAdd) throws IOException {
        FileWriter fw = new FileWriter(filePath, true);
        fw.write(textToAdd);
        fw.close();
    }
}
