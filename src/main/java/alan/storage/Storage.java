package alan.storage;

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

    // load from text file
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

    private ArrayList<Task> extractAndStoreDataInTaskList(ArrayList<String> list) {
        //todo: after this line should check if the data is in correct format
        for (String task : list) {
            String[] splitTaskString = task.split(" \\| ");
            String taskType = splitTaskString[0];
            String isDoneString = splitTaskString[1];
            String description = splitTaskString[2];
            boolean isDone = isDoneStringToBoolean(isDoneString);

            addTaskToTaskList(taskType, description, splitTaskString);

            int lastTaskIndex = taskArrayList.size() - 1;
            taskArrayList.get(lastTaskIndex).setDone(isDone);
        }

        return taskArrayList;
    }

    public void addTaskToTaskList(String taskType, String description, String[] splitTaskString) {
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
            //todo handle invalid task type
            break;
        }
    }

    private boolean isDoneStringToBoolean (String string) {
        // todo check if isDone is correct value, error handling
        return string.equals("1");
    }

    // Save to text file
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

    private void writeToFile(String filePath, String textToAdd) throws IOException {
        FileWriter fw = new FileWriter(filePath);
        fw.write(textToAdd);
        fw.close();
    }
    private void appendToFile(String filePath, String textToAdd) throws IOException {
        FileWriter fw = new FileWriter(filePath, true);
        fw.write(textToAdd);
        fw.close();
    }
}
