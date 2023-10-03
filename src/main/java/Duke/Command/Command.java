package Duke.Command;

import Duke.Exception.InvalidDateTimeSpecifiedException;
import Duke.Exception.NoTaskSpecifiedException;
import Duke.Task.*;
import Duke.Ui.Ui;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

/**
 * The Command class is a static class that implements functionalities
 * provided to the users for the application.
 * <p>
 * The functionalities include adding task, deleting task, creating task.
 */
public class Command {

    private static final String INTEGER_PROMPT = "Please enter an integer.\n";
    private static final String NO_TASK_ERROR_PROMPT = "Please create a task to continue\n";
    public static final String NUM_TASK_MESSAGE = "There are %d task(s).\n";
    private static final Ui ui = new Ui();
    public static final String INVALID_COMMAND_PROMPT = "Command is invalid!\n";
    public static final String INVALID_DATE_ERROR_PROMPT = "Please ensure that the date is given in the yyyy-mm-dd format\n";
    public static final String NO_TASK_DESCRIPTION_ERROR_PROMPT = "Please ensure that the task description is provided.\n";
    public static final String INVALID_NUMBER_PROMPT = "Please enter a valid number.\n";
    public static final String DELETE_TASK_NOTIFICATION = "\tNoted. I've removed this task:";
    public static final String NUM_TASK_MESSAGE_AFTER_ACTION = "\tNow you have %d tasks in the list.\n";
    public static final String MARK_ONE_TASK_REMINDER = "Please enter 'mark 1' to check the first task as completed\n";
    public static final String MARK_MANY_TASKS_REMINDER = "Please enter a valid number from 1 to %d (inclusive).\n";
    public static final String UNMARK_ONE_TASK_REMINDER = "Please enter 'unmark 1' to check the first task as completed\n";
    public static final String UNMARK_MANY_TASKS_REMINDER = "Please enter a valid number from 1 to %d (inclusive).\n";
    public static final String TASK_ID_OUT_OF_BOUND_ERROR_PROMPT = "There are only %d tasks." +
            "Please enter a valid value or create a new task.\n";
    public static final String EVENT_START_DATE_INDICATOR = "/from";
    public static final String EVENT_END_DATE_INDICATOR = "/to";
    public static final String TASK_FOUND_MESSAGE = "Here are your results:\n\n";
    public static final String TASK_NOT_FOUND_MESSAGE = "Sorry, we cannot find any task with the word \"%s\" \n";
    public static final String DEADLINE_END_DATE_INDICATOR = "/by";
    public static final String CREATE_TODO_INSTRUCTION = ToDo.taskType;
    public static final String CREATE_DEADLINE_INSTRUCTION = Deadline.taskType;
    public static final String CREATE_EVENT_INSTRUCTION = Event.taskType;
    public static final String LIST_ALL_TASK_MESSAGE = "Here are all your tasks:\n\n";

    public Command() {

    }


    /**
     * Create a new Deadline Task with specified tasks details.
     *
     * @param deadlineDetails which contains the task description,
     *                        and end date in a continuous string.
     * @return new Deadline Task
     * @throws NoTaskSpecifiedException          if no task description is found.
     * @throws InvalidDateTimeSpecifiedException if invalid datetime is given.
     */
    public static Task createDeadline(String deadlineDetails)
            throws NoTaskSpecifiedException, InvalidDateTimeSpecifiedException {

        String taskDescription;
        String byDateString;
        LocalDate byDate;
        try {
            String[] deadlineContents = deadlineDetails.split(DEADLINE_END_DATE_INDICATOR, 2);
            taskDescription = deadlineContents[0].trim();
            byDateString = deadlineContents[1].trim();
            byDate = LocalDate.parse(byDateString);

        } catch (ArrayIndexOutOfBoundsException | DateTimeParseException e) {
            throw new InvalidDateTimeSpecifiedException();
        }
        if (taskDescription.isEmpty()) {
            throw new NoTaskSpecifiedException();
        }

        return new Deadline(taskDescription, byDate);
    }

    /**
     * Create a new Todo Task containing task description.
     *
     * @param taskDescription A String value that contains task description for todo task.
     * @return Todo Task
     * @throws NoTaskSpecifiedException if no task description is found.
     */
    public static Task createToDo(String taskDescription) throws NoTaskSpecifiedException {
        String trimmedTaskDescription = taskDescription.trim();
        if (trimmedTaskDescription.isEmpty()) {
            throw new NoTaskSpecifiedException();
        }
        return new ToDo(trimmedTaskDescription);
    }

    /**
     * Marks a task with the given index in String as done.
     *
     * @param taskIndexString String value of task index of a task in the taskList
     *                        that is to be set as undone.
     * @param taskList        A TaskList object that contains Task objects.
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
                System.out.println(NO_TASK_ERROR_PROMPT);
            } else if (taskList.getNumTask() == 1) {
                System.out.printf(NUM_TASK_MESSAGE, taskList.getNumTask());
                System.out.println(MARK_ONE_TASK_REMINDER);
            } else {
                System.out.printf(NUM_TASK_MESSAGE, taskList.getNumTask());
                System.out.printf(MARK_MANY_TASKS_REMINDER, taskList.getNumTask());
            }
        }
    }


    /**
     * Execute the unmark command from the user to set a task as undone.
     *
     * @param taskIndexString String value of task index of a task in the taskList
     *                        that is to be set as undone.
     * @param taskList        A TaskList object that contains Task objects.
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
                System.out.println(NO_TASK_ERROR_PROMPT);
            } else if (taskList.getNumTask() == 1) {
                System.out.printf(NUM_TASK_MESSAGE, taskList.getNumTask());
                System.out.println(UNMARK_ONE_TASK_REMINDER);
            } else {
                System.out.printf(NUM_TASK_MESSAGE, taskList.getNumTask());
                System.out.printf(UNMARK_MANY_TASKS_REMINDER, taskList.getNumTask());
            }
        }
    }


    /**
     * Creates a new task based on the taskType and taskDetails and add it to the taskList.
     *
     * @param taskType    a string value that determines the type of task to be created.
     * @param taskDetails contains details to create the specific type of task.
     * @param taskList    A TaskList object that contains Task objects.
     */

    public static void createNewTask(String taskType, String taskDetails,
                                     TaskList taskList) {

        Task task = null;
        try {
            switch (taskType) {
            case CREATE_TODO_INSTRUCTION:
                task = createToDo(taskDetails);
                break;
            case CREATE_DEADLINE_INSTRUCTION:
                task = createDeadline(taskDetails);
                break;
            case CREATE_EVENT_INSTRUCTION:
                task = createEvent(taskDetails);
                break;
            default:
                System.out.println(INVALID_COMMAND_PROMPT);
                break;
            }
            if (task != null) {
                addTaskToTaskList(task, taskList);
                ui.printTaskAdded(task, taskList);
            }
        } catch (StringIndexOutOfBoundsException e) {
            System.out.println("Please ensure that " + taskType + " has the correct number of parameters.");
        } catch (InvalidDateTimeSpecifiedException e) {
            System.out.println(INVALID_DATE_ERROR_PROMPT);
        } catch (NoTaskSpecifiedException e) {
            System.out.println(NO_TASK_DESCRIPTION_ERROR_PROMPT);
        }
    }

    /**
     * Removes task identified by taskIndex from taskList.
     *
     * @param taskIndexString A string version of task index for deletion.
     * @param taskList        A TaskList object that contains Task objects.
     */
    public static void deleteTask(String taskIndexString, TaskList taskList) {
        try {
            int taskIndex = Integer.parseInt(taskIndexString);
            Task task = taskList.getTask(taskIndex);
            taskList.deleteTask(taskIndex);
            ui.printLine();
            System.out.println(DELETE_TASK_NOTIFICATION);
            System.out.println(task);
            System.out.printf(NUM_TASK_MESSAGE_AFTER_ACTION, taskList.getNumTask());
            ui.printLine();
        } catch (NumberFormatException e) {
            System.out.println(INVALID_NUMBER_PROMPT);
        } catch (IndexOutOfBoundsException e) {
            System.out.printf(TASK_ID_OUT_OF_BOUND_ERROR_PROMPT, taskList.getNumTask());
        }
    }

    /**
     * Print all the tasks in the given taskList in default user interface requirement.
     *
     * @param taskList A TaskList object that contains Task objects.
     */
    public static void executeListCommand(TaskList taskList) {
        ui.printAllTasks(LIST_ALL_TASK_MESSAGE, taskList);
    }

    /**
     * Creates a new Event Tasks with the given eventDetails.
     *
     * @param eventDetails a string that contains event description, start date, end date.
     * @return Event Task object
     * @throws NoTaskSpecifiedException          if no task description is found.
     * @throws InvalidDateTimeSpecifiedException if invalid datetime is given.
     */
    public static Task createEvent(String eventDetails) throws
            NoTaskSpecifiedException, InvalidDateTimeSpecifiedException {

        String taskDescription;
        String fromDateString;
        String toDateString;
        LocalDate fromDate;
        LocalDate toDate;
        String eventSplitNotation = EVENT_START_DATE_INDICATOR + "|" + EVENT_END_DATE_INDICATOR;
        String[] eventContents = eventDetails.split(eventSplitNotation);
        try {
            taskDescription = eventContents[0].trim();
            fromDateString = eventContents[1].trim();
            toDateString = eventContents[2].trim();
            fromDate = LocalDate.parse(fromDateString);
            toDate = LocalDate.parse(toDateString);

        } catch (ArrayIndexOutOfBoundsException | DateTimeParseException e) {
            throw new InvalidDateTimeSpecifiedException();
        }
        if (taskDescription.isEmpty()) {
            throw new NoTaskSpecifiedException();
        }

        return new Event(taskDescription, fromDate, toDate);
    }

    /**
     * Add a task into the provided taskList.
     *
     * @param task     A Task object, which could be a Todo, Deadline or Event.
     * @param taskList A TaskList object that contains Task objects.
     */
    public static void addTaskToTaskList(Task task, TaskList taskList) {
        taskList.addTask(task);
    }

    public static void findTasks(String keyword, TaskList records) {

        TaskList tasksFound = new TaskList();
        Task task;
        String taskDescription;

        for (int i = 1; i < records.getNumTask() + 1; i++) {
            task = records.getTask(i);
            taskDescription = task.getDescription();
            if (!taskDescription.contains(keyword)) {
                continue;
            }
            tasksFound.addTask(task);
        }
        printTasksFound(keyword, tasksFound);
    }

    public static void printTasksFound(String keyword, TaskList records) {
        if (records.getNumTask() == 0) {
            System.out.printf(TASK_NOT_FOUND_MESSAGE, keyword);
            return;
        }
        ui.printAllTasks(TASK_FOUND_MESSAGE, records);
    }
}
