package task;

import UI.Ui;
import exception.SimonException;
import java.util.ArrayList;
import static data.DataMethods.addTextToFile;
import static data.SimonFilePath.simontxtFilePath;

public class TaskList {
    private ArrayList<Task> taskList;

    public TaskList(ArrayList<Task> taskList) {
        this.taskList = taskList;
    }

    public ArrayList<Task> getTaskList() {
        return taskList;
    }
    public void markTask(String taskNumber, Ui ui) {
        //Convert task number to element in tasks array
        int target = Integer.parseInt(taskNumber) - 1;
        try {
            Task task = taskList.get(target);
            task.markAsDone();
            ui.printMarkAsDone(task);
        } catch (IndexOutOfBoundsException e) {
            ui.printOutOfBoundsError(taskList);
        }
    }

    public void unmarkTask(String taskNumber, Ui ui) {
        //Convert task number to element in tasks array
        int target = Integer.parseInt(taskNumber) - 1;
        try {
            Task task = taskList.get(target);
            task.unmarkAsDone();
            ui.printUnmarkAsDone(task);
        } catch (IndexOutOfBoundsException e) {
            ui.printOutOfBoundsError(taskList);
        }
    }

    public  void deleteTask(String taskNumber, Ui ui){
        int target = Integer.parseInt(taskNumber) - 1;
        try {
            Task task = taskList.get(target);
            taskList.remove(target);
            Task.deleteOneTask();
            ui.printDeleteTask(task);
            ui.printNumberOfTasks(taskList);
        } catch (IndexOutOfBoundsException e) {
            ui.printOutOfBoundsError(taskList);
        }
    }

    public void addTodo(String description, Ui ui) throws SimonException {

        String[] splitDescriptions = description.split(" ");
        if (splitDescriptions.length == 0 || splitDescriptions[0].isEmpty()) {
            throw new SimonException();
        }

        taskList.add(new Todo(description));

        addTextToFile(simontxtFilePath, taskList.get(Task.getNumberOfTask() - 1).toText());

        ui.printAddTaskMessage(taskList);
        ui.printNumberOfTasks(taskList);
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

            ui.printAddTaskMessage(taskList);
            ui.printNumberOfTasks(taskList);

        } catch (IndexOutOfBoundsException e) {
            System.out.println("\tPlease include when the time of your event in the following format:");
            System.out.println("\tevent [description] /from [start time] /to [end time]");
        }
    }

    public void addDeadline(String deadline, Ui ui) throws SimonException {
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
    }
}
