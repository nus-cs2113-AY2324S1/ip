package Duke.Command;

import Duke.Exception.NoDateTimeSpecifiedException;
import Duke.Exception.NoTaskSpecifiedException;
import Duke.Task.*;
import Duke.Ui.Ui;

import java.io.FileWriter;
import java.io.IOException;

/**
 * The Command class is a static class that implements functionalities
 * provided to the users for the application.
 *
 * The functionalities include adding task, deleting task, creating task.
 */
public class Command {

    //TODO need to figure out how to eliminate the ui here
    private static final String INTEGER_PROMPT = "Please enter an integer.";
    private static final String TASK_CREATION_ERROR_PROMPT = "Please create a task to continue";
    private static final Ui ui = new Ui();

    /**
     * Create a new Deadline Task with specified tasks details.
     *
     * @param deadlineDetails which contains the task description,
     *                        and end date in a continuous string.
     * @return new Deadline Task
     * @throws NoTaskSpecifiedException
     * @throws NoDateTimeSpecifiedException
     */
    public static Task createDeadline(String deadlineDetails)
            throws NoTaskSpecifiedException, NoDateTimeSpecifiedException {

        String taskDescription;
        String byDate;
        try {
            String dateIndicator = "/by";
            String[] deadlineContents = deadlineDetails.split(dateIndicator, 2);
            taskDescription = deadlineContents[0].trim();
            byDate = deadlineContents[1].trim();
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new NoDateTimeSpecifiedException();
        }
        if (taskDescription.isEmpty()) {
            throw new NoTaskSpecifiedException();
        }
        if (byDate.isEmpty()) {
            throw new NoDateTimeSpecifiedException();
        }

        return new Deadline(taskDescription, byDate);
    }

    /**
     * Create a new Todo Task containing task description.
     *
     * @param taskDescription A String value that contains task description for todo task.
     * @return Todo Task
     * @throws NoTaskSpecifiedException
     */
    public static Task createToDo(String taskDescription) throws NoTaskSpecifiedException {
        taskDescription = taskDescription.trim();
        if (taskDescription.isEmpty()) {
            throw new NoTaskSpecifiedException();
        }
        return new ToDo(taskDescription);
    }

    /**
     * Marks a task with the given index in String as done.
     *
     * @param taskIndexString String value of task index of a task in the taskList
     *                        that is to be set as undone.
     * @param taskList A TaskList object that contains Task objects.
     */
    public static void executeMarkCommand(String taskIndexString, TaskList taskList) {
        int taskIndex;
        try {
            taskIndex = Integer.parseInt(taskIndexString);
            taskList.getTask(taskIndex).setDone();
            executeListCommand(taskList);
        } catch (NumberFormatException e) {
            System.out.println(INTEGER_PROMPT);

        } catch (NullPointerException | IndexOutOfBoundsException e) {
            if (taskList.getNumTask() == 0) {
                System.out.println(TASK_CREATION_ERROR_PROMPT);
            } else if (taskList.getNumTask() == 1) {
                System.out.println("There are " + taskList.getNumTask() + " task.");
                System.out.println("Please enter 'mark 1' to check the first task as completed");
            } else {
                System.out.println("There are " + taskList.getNumTask() + " tasks.");
                System.out.println("Please enter a valid number from 1 to " + taskList.getNumTask() + "(inclusive).");
            }
        }
    }


    /**
     * Execute the unmark command from the user to set a task as undone.
     *
     * @param taskIndexString String value of task index of a task in the taskList
     *                        that is to be set as undone.
     * @param taskList A TaskList object that contains Task objects.
     */
    public static void executeUnmarkCommand(String taskIndexString, TaskList taskList) {
        int taskIndex;
        try {
            taskIndex = Integer.parseInt(taskIndexString);
            taskList.getTask(taskIndex).setUndone();
            executeListCommand(taskList);
        } catch (NumberFormatException e) {
            System.out.println(INTEGER_PROMPT);
        } catch (NullPointerException | IndexOutOfBoundsException e) {
            if (taskList.getNumTask() == 0) {
                System.out.println(TASK_CREATION_ERROR_PROMPT);
            } else if (taskList.getNumTask() == 1) {
                System.out.println("There are " + taskList.getNumTask() + " task.");
                System.out.println("Please enter 'mark 1' to check the first task as completed");
            } else {
                System.out.println("There are " + taskList.getNumTask() + " tasks.");
                System.out.println("Please enter a valid number from 1 to " + taskList.getNumTask() + "(inclusive).");
            }
        }
    }

    /**
     * Creates a new task based on the taskType and taskDetails and add it to the taskList.
     *
     * @param taskType a string value that determines the type of task to be created.
     * @param taskDetails contains details to create the specific type of task.
     * @param taskList A TaskList object that contains Task objects.
     */
    public static void createNewTask(String taskType, String taskDetails,
                                     TaskList taskList) {
        Task task = null;
        try {
            switch (taskType) {
            case "todo":
                task = createToDo(taskDetails);
                break;
            case "deadline":
                    task = createDeadline(taskDetails);
                break;
            case "event":
                    task = createEvent(taskDetails);
                break;
            default:
                System.out.println("Command is invalid!");
                break;
            }
            if (task != null) {
                addTaskToTaskList(task, taskList);
                ui.printTaskAdded(task, taskList);
            }
        } catch (StringIndexOutOfBoundsException e) {
            System.out.println("Please ensure that " + taskType + " has the correct number of parameters.");
        } catch (NoDateTimeSpecifiedException e){
            System.out.println("Please ensure that the date is given in the yyyy-mm-dd format");
        } catch (NoTaskSpecifiedException e){
            System.out.println("Please ensure that the task description is provided.");
        }
    }

    /**
     * Removes task identified by taskIndex from taskList.
     *
     * @param taskIndexString A string version of task index for deletion.
     * @param taskList A TaskList object that contains Task objects.
     */
    public static void deleteTask(String taskIndexString, TaskList taskList) {
        try {
            int taskIndex = Integer.parseInt(taskIndexString);
            Task task = taskList.getTask(taskIndex);
            taskList.deleteTask(taskIndex);
            ui.printLine();
            System.out.println("\tNoted. I've removed this task:");
            System.out.println(task);
            System.out.println("\tNow you have " + taskList.getNumTask() + " tasks in the list.");
            ui.printLine();
        } catch (NumberFormatException e) {
            System.out.println("Please enter a valid number.");
        } catch (IndexOutOfBoundsException e) {
            System.out.println("There are only " + taskList.getNumTask() + " tasks.");
        }
    }

    /**
     * Saves all the tasks from the taskList to the given filePath.
     *
     * @param filePath
     * @param taskList A TaskList object that contains Task objects.
     * @throws IOException
     */
    public void saveTaskList(String filePath, TaskList taskList) throws IOException {
        String taskSaveFormat;
        FileWriter fw = new FileWriter(filePath);

        for (int i = 1; i < taskList.getNumTask() + 1; i++) {
            taskSaveFormat = taskList.getTask(i).convertToSaveFormat();
            fw.write(taskSaveFormat + "\n");
        }
        fw.close();
    }

    /**
     * Print all the tasks in the given taskList in default user interface requirement.
     *
     * @param taskList A TaskList object that contains Task objects.
     */
    public static void executeListCommand(TaskList taskList) {
        ui.printLine();
        ui.printAllTasks(taskList);
        ui.printLine();
    }

    /**
     * Creates a new Event Tasks with the given eventDetails.
     * @param eventDetails a string that contains event description, start date, end date.
     * @return Event Task object
     * @throws NoTaskSpecifiedException
     * @throws NoDateTimeSpecifiedException
     */
    public static Task createEvent(String eventDetails) throws NoTaskSpecifiedException, NoDateTimeSpecifiedException {
        String taskDescription;
        String fromDate;
        String toDate;
        String startDateIndicator = "/from";
        String endDateIndicator = "/to";
        String eventSplitNotation = startDateIndicator + "|" + endDateIndicator;
        String[] eventContents = eventDetails.split(eventSplitNotation);
        try {
            taskDescription = eventContents[0].trim();
            fromDate = eventContents[1].trim();
            toDate = eventContents[2].trim();
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new NoDateTimeSpecifiedException();
        }
        if (taskDescription.isEmpty()) {
            throw new NoTaskSpecifiedException();
        }
        if (fromDate.isEmpty() | toDate.isEmpty()) {
            throw new NoDateTimeSpecifiedException();
        }
        return new Event(taskDescription, fromDate, toDate);
    }

    /**
     * Add a task into the provided taskList.
     *
     * @param task A Task object, which could be a Todo, Deadline or Event.
     * @param taskList A TaskList object that contains Task objects.
     */
    public static void addTaskToTaskList(Task task, TaskList taskList) {
        taskList.addTask(task);
    }
}
