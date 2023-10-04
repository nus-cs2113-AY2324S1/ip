package data;

import UI.Ui;
import exception.SimonException;
import task.*;

import static data.DataMethods.*;
import java.util.ArrayList;

import static data.Loader.readToList;
import static data.SimonFilePath.simontxtFilePath;

public class Storage {
    private String filePath;
    public Storage(String filePath) {
        this.filePath = filePath;
    }

    public ArrayList<Task> load() throws SimonException {
        if(!isFileExist(simontxtFilePath)) {
            throw new SimonException();
        }
        return readToList(filePath);
    }
    public void createSimonTxt(String userDirectory) {
        String dataDirectory = userDirectory + "/" + "data";
        createDirectory(dataDirectory);
        createFileInDirectory(dataDirectory, "simon.txt");
    }

    public void markTask(String taskNumber, ArrayList<Task> taskList) {
        //Convert task number to element in tasks array
        int target = Integer.parseInt(taskNumber) - 1;
        try {
            Task task = taskList.get(target);
            String newText = task.toText();
            editTextFile(simontxtFilePath, newText, target + 1);
        } catch (IndexOutOfBoundsException ignored) {

        }
    }

    public void unmarkTask(String taskNumber, ArrayList<Task> taskList) {
        //Convert task number to element in tasks array
        int target = Integer.parseInt(taskNumber) - 1;
        try {
            String newText = taskList.get(target).toText();
            editTextFile(simontxtFilePath, newText, target + 1);
        } catch (IndexOutOfBoundsException ignored) {

        }
    }

    public void deleteTask(String taskNumber, ArrayList<Task> taskList){
        int target = Integer.parseInt(taskNumber) - 1;
        try {
            for (int i = target; i < Task.getNumberOfTask(); i++) {
                String newText = taskList.get(i).toText();
                editTextFile(simontxtFilePath, newText, i + 1);
            }
            deleteLineFromFile(simontxtFilePath, Task.getNumberOfTask());
        } catch (IndexOutOfBoundsException ignored) {

        }
    }

    public void addTodo(String description, ArrayList<Task> taskList) throws SimonException {

        String[] splitDescriptions = description.split(" ");
        if (splitDescriptions.length == 0 || splitDescriptions[0].isEmpty()) {
            throw new SimonException();
        }

        addTextToFile(simontxtFilePath, taskList.get(Task.getNumberOfTask() - 1).toText());
    }

    public void addEvent(String event, ArrayList<Task> taskList) throws SimonException {
        try {
            //Split between 'description' and '/from and /to'
            String[] splitElements = event.split(" /from ", 2);
            String description = splitElements[0];

            String[] splitDescriptions = description.split(" ");
            if (splitDescriptions.length == 0 || splitDescriptions[0].isEmpty()) {
                throw new SimonException();
            }

            addTextToFile(simontxtFilePath, taskList.get(Task.getNumberOfTask() - 1).toText());
        } catch (IndexOutOfBoundsException ignored) {

        }
    }

    public void addDeadline(String deadline, ArrayList<Task> taskList) throws SimonException {
        try {
            //Split between 'description' and '/by'
            String[] splitElements = deadline.split(" /by ", 2);
            String description = splitElements[0];

            String[] splitDescriptions = description.split(" ");
            if (splitDescriptions.length == 0 || splitDescriptions[0].isEmpty()) {
                throw new SimonException();
            }

            addTextToFile(simontxtFilePath, taskList.get(Task.getNumberOfTask() - 1).toText());

        } catch (IndexOutOfBoundsException ignored) {

        }
    }

}
