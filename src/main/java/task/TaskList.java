package task;

import UI.Ui;
import exception.*;
import java.util.ArrayList;
import static java.util.stream.Collectors.toList;

/**
 * Contains the task list and has operations to modify the list.
 */
public class TaskList {
    private ArrayList<Task> taskList;

    /**
     * Initialise the TaskList
     *
     * @param taskList
     */
    public TaskList(ArrayList<Task> taskList) {
        this.taskList = taskList;
    }

    public ArrayList<Task> getTaskList() {
        return taskList;
    }

    /**
     * Marks the task that the user specified using a task number as done and prints
     * the marked task for the user. The task list will be updated. This method print
     * an error message if the user inputs a wrong format or a task number that is out
     * of bounds.
     *
     * @param taskNumber
     * @param ui
     */
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

    /**
     * Unmarks the task that the user specified using a task number as done and prints
     * the unmarked task for the user. The task list will be updated. This method print
     * an error message if the user inputs a wrong format or a task number that is out
     * of bounds.
     *
     * @param taskNumber
     * @param ui
     */
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

    /**
     * Deletes the task that the user specified using a task number and prints
     * the deleted task for the user. The task list will be updated. This method print
     * an error message if the user inputs a wrong format or a task number that is out
     * of bounds.
     *
     * @param taskNumber
     * @param ui
     */
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

    /**
     * Creates a new todo task based on the description given. The tasks will be added
     * to the task list, and an add task message will be printed along with the current
     * number of tasks. Should the description be empty, SimonException will be thrown
     *
     * @param description
     * @param ui
     * @throws SimonException
     */
    public void addTodo(String description, Ui ui) throws SimonException {

        String[] splitDescriptions = description.split(" ");
        if (splitDescriptions.length == 0 || splitDescriptions[0].isEmpty()) {
            throw new SimonException();
        }

        taskList.add(new Todo(description));

        ui.printAddTaskMessage(taskList);
        ui.printNumberOfTasks(taskList);
    }

    /**
     * Creates a new event task based on the description given. The tasks will be added
     * to the task list, and an add task message will be printed along with the current
     * number of tasks. Should the description be empty, SimonException will be thrown.
     * Should the format of the user input be incorrect, SimonException2 will be thrown.
     *
     * @param event
     * @param ui
     * @throws SimonException
     * @throws SimonException2
     */
    public void addEvent(String event, Ui ui) throws SimonException, SimonException2 {
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

            ui.printAddTaskMessage(taskList);
            ui.printNumberOfTasks(taskList);

        } catch (IndexOutOfBoundsException e) {
            throw new SimonException2();
        }
    }

    /**
     * Creates a new deadline task based on the description given. The tasks will be added
     * to the task list, and an add task message will be printed along with the current
     * number of tasks. Should the description be empty, SimonException will be thrown.
     * Should the format of the user input be incorrect, SimonException2 will be thrown.
     *
     * @param deadline
     * @param ui
     * @throws SimonException
     * @throws SimonException2
     */
    public void addDeadline(String deadline, Ui ui) throws SimonException, SimonException2 {
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

            ui.printAddTaskMessage(taskList);
            ui.printNumberOfTasks(taskList);
        } catch (IndexOutOfBoundsException e) {
            throw new SimonException2();
        }
    }

    /**
     * This method takes in a keyword and selects every task that contains the keyword.
     * All the tasks will be placed in a new ArrayList and printed.
     *
     * @param word
     * @param ui
     */
    public void printMatchingTasks(String word, Ui ui) {
        ArrayList<Task> matchingList = (ArrayList<Task>) taskList.stream()
                .filter(n -> n.getDescription().contains(word))
                .collect(toList());
        ui.printMatchingList(matchingList);
    }
}
