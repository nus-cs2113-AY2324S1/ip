package kenergeticbot.fileaccess;

import kenergeticbot.TaskList;
import kenergeticbot.task.Deadline;
import kenergeticbot.task.Event;
import kenergeticbot.task.Task;
import kenergeticbot.task.Todo;
import kenergeticbot.ui.TextUi;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Deals with loading tasks from the file and saving tasks in the file
 */
public class Storage {

    protected String filePath;
    public static final String folderPath = "data";

    public Storage(String filePath, TextUi ui) {
        this.filePath = filePath;
        initializeStorage(ui);
    }

    /**
     * Checks if the storage file exists, creates the file if not
     * @param ui The TextUI object created to handle I/O with the user
     * @throws IOException if the file exists but unable to access
     */
    public void initializeStorage(TextUi ui) {
        File f = new File(filePath);
        checkFolderExist(ui);
        try {
            if (f.createNewFile()) {
                ui.showToUser("File created: " + f.getName());
            } else {
                ui.showToUser("File already exists.");
            }
        } catch (IOException e) {
            ui.showToUser("An error occurred." + e.getMessage());
        }
        ui.showToUser("Save file is at: " + f.getAbsolutePath());
    }

    /**
     * Checks if the storage folder to store the storage file exists, creates the folder if not
     * @param ui The TextUI object created to handle I/O with the user
     */
    public void checkFolderExist(TextUi ui) {
        File f = new File(folderPath);
        if (f.mkdir()) {
            ui.showToUser("Directory is created");
        } else {
            ui.showToUser("Directory already exist");
        }
    }

    /**
     * Loads the storage data file into a TaskList object.
     * Calls readFromFile method
     * @param taskList The arraylist object created that stores current tasks
     * @throws FileNotFoundException if unable to locate storage file using pathfile
     */
    public void loadPreviousList(TaskList taskList, TextUi ui) {
        try {
            readFromFile(filePath, taskList, ui);
        } catch (FileNotFoundException e) {
            ui.showToUser(e.getMessage());
        }
    }

    /**
     * Access the storage data file to create the appropriate Task object
     * Creates a scanner to scan the storage data file, parses the text to determine respective Task type, isDone status and description
     * @param filePath The location of the data storage file
     * @param taskList The arraylist object created that stores current tasks
     */
    public void readFromFile(String filePath, TaskList taskList, TextUi ui) throws FileNotFoundException {
        File f = new File(filePath); // create a File for the given file path
        if (f.length() == 0) {
            return;
        }
        Scanner s = new Scanner(f);
        ArrayList<String> previousTaskList = new ArrayList<String>();
        while (s.hasNext()) {
            previousTaskList.add(s.nextLine());
        }
        s.close();

        for (String task : previousTaskList) {
            String[] taskVariables = task.split(" \\| ");
            String taskType = taskVariables[0];
            String taskMark = taskVariables[1];
            String taskDescription = taskVariables[2];

            switch (taskType) {
                case "T" :
                    Task previousTodo = new Todo(taskDescription, isMark(taskMark));
                    taskList.addTask(previousTodo);
                    ui.showToUser("adding:" + previousTodo);
                    break;
                case "D" :
                    String taskDeadline = taskVariables[3];
                    Task previousDeadline = new Deadline(taskDescription, taskDeadline, isMark(taskMark));
                    taskList.addTask(previousDeadline);
                    ui.showToUser("adding:" + previousDeadline);
                    break;
                case "E" :
                    String taskEventDateTime = taskVariables[3];
                    Task previousEvent = new Event(taskDescription, taskEventDateTime, isMark(taskMark));
                    taskList.addTask(previousEvent);
                    ui.showToUser("adding:" + previousEvent);
                    break;
                default :
                    throw new IllegalStateException("Unexpected value: " + taskType);
            }
        }
        try {
            new FileWriter(filePath, false).close();
        } catch (IOException e) {
            ui.showToUser(e.getMessage());
        }
    }

    /**
     * Writes a String into a data file for storage.
     * If data file does not exist, the method creates data file
     * If data file already exist, the method edits the data file
     * @param textToAdd The intended data to be stored into the data file
     * @throws IOException if the file exists but unable to access
     */
    public void writeToFile(String textToAdd, TextUi ui) {
        File f = new File(filePath);
        if (f.length() == 0) { //if file is empty
            try {
                FileWriter saveFileWriter = new FileWriter(filePath);
                saveFileWriter.write(textToAdd);
                saveFileWriter.close();
                ui.showToUser("Successfully wrote to the file.");
            } catch (IOException e) {
                ui.showToUser(e.getMessage());
            }
        } else { //if file is not empty
            try {
                FileWriter saveFileWriter = new FileWriter(filePath, true);
                saveFileWriter.write(textToAdd);
                saveFileWriter.close();
                ui.showToUser("Successfully wrote to the file.");
            } catch (IOException e) {
                ui.showToUser(e.getMessage());
            }
        }
    }

    /**
     * Saves the TaskList object into a data file for storage, iterating through the list.
     */
    public void saveList(TaskList taskList, TextUi ui) {
        for (int i = 0; i < taskList.getSize(); i++) {
            System.out.println(taskList.getTask(i));
            writeToFile(taskList.getTask(i).printTaskToSave(), ui);
        }
    }

    /**
     * Checks if the string has the task marked as done or not done
     * @param stringInput The String that stores the done status of the task
     * @return True is task is done, False if not done
     */
    public boolean isMark(String stringInput) {
        boolean isDone = stringInput.equals("1");
        return isDone;
    }
}
