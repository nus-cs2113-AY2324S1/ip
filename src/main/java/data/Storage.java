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

    /*public void addTodo(String description, Ui ui) throws SimonException {

        String[] splitDescriptions = description.split(" ");
        if (splitDescriptions.length == 0 || splitDescriptions[0].isEmpty()) {
            throw new SimonException();
        }

        taskList.add(new Todo(description));

        addTextToFile(simontxtFilePath, taskList.get(Task.getNumberOfTask() - 1).toText());

        ui.printLine();
        ui.printAddTaskMessage(taskList);
        ui.printNumberOfTasks(taskList);
        ui.printLine();
    }

    public void addEvent(String event, Ui ui) throws SimonException {
        try {
            //Split between 'description' and '/from and /to'
            String[] splitElements = event.split(" /from ", 2);
            String description = splitElements[0];

            String[] splitDescriptions = description.split(" ");
            if (splitDescriptions.length == 0 || splitDescriptions[0].isEmpty()) {
                throw new SimonException();
            }

            //Split between '/from' and '/to'
            String[] time = splitElements[1].split(" /to ", 2);
            String from = time[0];
            String to = time[1];
            taskList.add(new Event(description, from, to));
            addTextToFile(simontxtFilePath, taskList.get(Task.getNumberOfTask() - 1).toText());
            ui.printLine();
            ui.printAddTaskMessage(taskList);
            ui.printNumberOfTasks(taskList);
            ui.printLine();
        } catch (IndexOutOfBoundsException e) {
            ui.printLine();
            System.out.println("\tPlease include when the time of your event in the following format:");
            System.out.println("\tevent [description] /from [start time] /to [end time]");
            ui.printLine();
        }
    }

    public void addDeadline(String deadline, Ui ui) throws SimonException {
        ui.printLine();
        try {
            //Split between 'description' and '/by'
            String[] splitElements = deadline.split(" /by ", 2);
            String description = splitElements[0];

            String[] splitDescriptions = description.split(" ");
            if (splitDescriptions.length == 0 || splitDescriptions[0].isEmpty()) {
                throw new SimonException();
            }

            String by = splitElements[1];
            taskList.add(new Deadline(description, by));
            addTextToFile(simontxtFilePath, taskList.get(Task.getNumberOfTask() - 1).toText());

            ui.printAddTaskMessage(taskList);
            ui.printNumberOfTasks(taskList);
        } catch (IndexOutOfBoundsException e) {
            System.out.println("\tPlease include when the deadline of your task is in the following format:");
            System.out.println("\tdeadline [description] /by [deadline]");
        }
        ui.printLine();
    }*/

}
