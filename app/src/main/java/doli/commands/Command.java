package doli.commands;

import doli.GUI.Ui;
import doli.exceptions.DoliExceptions;
import doli.files.Storage;
import doli.tasks.*;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * <h3>Command class</h3>
 * The command class aims at organising the input of the user,
 * by delegating an action according to the specific command.
 * Its core is a collection of methods to be applied to the input.
 *
 * @author pappalardodaniel
 * @version 1.0
 * @since 2023-10-03
 */
public class Command {
    protected String command;
    protected String[] details;
    protected boolean isExit;
    protected String response;
    private static final String TODO_COMMAND = "todo";
    private static final String DEADLINE_COMMAND = "deadline";
    private static final String EVENT_COMMAND = "event";
    private static final String LIST_COMMAND = "list";
    private static final String DELETE_COMMAND = "delete";
    private static final String CLEAR_COMMAND = "clear";
    private static final String MARK_COMMAND = "mark";
    private static final String UNMARK_COMMAND = "unmark";
    private static final String FIND_COMMAND = "find";
    private static final String OVERVIEW_BY_SPECIFIC_DATE_COMMAND = "overview";
    private static final String EXIT_COMMAND = "bye";
    private static final String UNRECOGNIZED_COMMAND = "I am so sorry, but I do not recognize this command. "
            + "Please try typing something else.";
    private static final String ADDED_TASK_SUCCESSFULLY = "Got it! I've added the following task to your agenda:";
    private static final String DELETED_TASK_SUCCESSFULLY = "Got it! I've deleted task %d";
    private static final String DELETED_ALL_TASKS = "Got it! I've deleted all tasks. Your agenda is now empty.";
    private static final String SUMMARIZING_CURRENT_AGENDA_ENTRIES = "Now you have a total of %d tasks in your agenda.";
    private static final String ENCOURAGE_TO_MARK = "Would you like to mark/unmark something else?";
    private static final String AGENDA_OVERVIEW = "Here is an overview of your agenda:";
    private static final String FAILED_TO_FIND_TASK = "Watch out! The given task index does not exist";
    private static final String MARKED_SUCCESSFULLY = "I've successfully marked task %d as done.";
    private static final String UNMARKED_SUCCESSFULLY = "I've unmarked task %d. You better get it done soon!";
    private static final String NO_TASKS_FOR_THIS_DATE = "There are no entries in your agenda until this date.";
    private static final String MATCHING_TASKS = "Here are the tasks matching your input keyword:";
    private static final String NO_TASKS_FOUND = "The given keyword did not produce any search results in your agenda";
    private static final String EXIT = "It was a pleasure, bye. See you later!";

    /**
     * Constructs an object of type Command. The boolean isExit is automatically
     * set to false when the command is initialised without no further checks.
     *
     * @param command of type String specifying the action to be carried out
     * @param details an array of Strings containing further information about the command
     */
    public Command(String command, String[] details) {
        this.command = command;
        this.details = details;
        this.isExit = false;
    }

    /**
     * Checks whether the boolean variable isExit is
     * true or false; This method is used to halt the
     * program when the user wants to stop running it.
     * @return isExit of type boolean
     */
    public boolean isExit() {
        return this.isExit;
    }

    /**
     * Initialises a new todo task with the description given
     * by the first element in the array of the details of the user input
     * command, adds it to the TaskList and summarises this within the
     * String variable response.
     * @param tasks of type TaskList into which the new todo will be inserted.
     */
    private void initializeNewTodo(TaskList tasks) {
        ToDo newTodo = new ToDo(details[0]);
        tasks.addTask(newTodo);
        response = String.format("%s\n\t%s\n%s",
                ADDED_TASK_SUCCESSFULLY,
                newTodo.toString(),
                String.format(SUMMARIZING_CURRENT_AGENDA_ENTRIES, tasks.getSize()));
    }
    /**
     * Initialises a new deadline task with the description given
     * by the first element in the array of the details of the user input
     * command and the deadline itself given by the second element,
     * adds it to the TaskList and summarises this within the
     * String variable response.
     * @param tasks of type TaskList into which the new deadline will be inserted.
     */
    private void initializeNewDeadline(TaskList tasks) {
        Deadline newDeadline = new Deadline(details[0], details[1].trim());
        tasks.addTask(newDeadline);
        response = newDeadline.toString();
    }
    /**
     * Initialises a new event task with the description given
     * by the first element in the array of the details of the user input
     * command, the start date given by the second element and the end
     * date given by the third element of the orrray provided by the user,
     * adds it to the TaskList and summarises this within the
     * String variable response.
     * @param tasks of type TaskList into which the new event will be inserted.
     */
    private void initializeNewEvent(TaskList tasks) {
        Event newEvent = new Event(details[0], details[1].trim(), details[2].trim());
        tasks.addTask(newEvent);
        response = newEvent.toString();
    }

    /**
     * Sets the response variable to a general overview of all the entries in
     * the agenda by using the overwritten method toString.
     * @param tasks of type TaskList containing all the specific tasks
     */
    private void listAgenda(TaskList tasks) {
        response = String.format("%s\n%s", AGENDA_OVERVIEW, tasks.toString());
    }

    /**
     * Deletes the task specified by the details provided by the user
     * within the command.
     * @param tasks of type TaskList containing the entire list of tasks, of which one will be deleted
     * @throws DoliExceptions in case the provided task was not found
     */
    private void deleteTask(TaskList tasks) throws DoliExceptions {
        int taskNumber = tryToParse(details[0]);
        checkNotOutOfBounds(taskNumber, tasks);
        tasks.deleteTask(taskNumber);
        response = String.format(DELETED_TASK_SUCCESSFULLY, taskNumber);
    }

    /**
     * Deletes all tasks contained in the agenda so far by resetting the agenda to a blank TaskList
     * @param tasks of type TaskList, specifying the tasks to be deleted.
     */
    private void deleteAll(TaskList tasks) {
        tasks.deleteAll();
        response = DELETED_ALL_TASKS;
    }

    /**
     * Marks the task specified by the user as done (X). The user
     * input is expected to be of type command + task number
     * @param tasks of type TaskList containing the entire agenda
     * @throws DoliExceptions to handle the cases in which the task number is not contained in the agenda
     */
    private void setMark(TaskList tasks) throws DoliExceptions{
        int taskNumber = tryToParse(details[0]);
        checkNotOutOfBounds(taskNumber, tasks);
        tasks.getTask(taskNumber).markTaskAsDone();
        response = String.format("%s\n%s",
                String.format(MARKED_SUCCESSFULLY, taskNumber),
                ENCOURAGE_TO_MARK);
    }

    /**
     * Marks the task specified by the user as not done (X). The user
     * input is expected to be of type command + task number
     * @param tasks of type TaskList containing the entire agenda
     * @throws DoliExceptions to handle the cases in which the task number is not contained in the agenda
     */
    private void unsetMark(TaskList tasks) throws DoliExceptions {
        int taskNumber = tryToParse(details[0]);
        checkNotOutOfBounds(taskNumber, tasks);
        tasks.getTask(taskNumber).markTaskAsNotDone();
        response = String.format("%s\n%s",
                String.format(UNMARKED_SUCCESSFULLY, taskNumber),
                ENCOURAGE_TO_MARK);
    }

    /**
     * Sets response equal to an overview of all the deadlines and events contained in the agenda
     * which are due (or scheduled to happen, in case of events) by a user-specified date.
     * The expected command input is of type command + date
     * @param tasks of type TaskList containing all the tasks in the agenda
     */
    private void overviewBySpecificDate(TaskList tasks) {
        TaskList overview = new TaskList();
        try {
            LocalDate timeInput = LocalDate.parse(details[0]);
            for (Task task : tasks) {
                if (task instanceof Deadline && ((Deadline) task).getDeadline().isBefore(timeInput)) {
                    overview.addTask(task);
                } else if (task instanceof Event && ((Event) task).getStartTime().isBefore(timeInput)
                        && ((Event) task).getEndTime().isAfter(timeInput)) {
                    overview.addTask(task);
                }
            }
        } catch(DateTimeException e) {
            System.out.println("Please provide a proper date as input");
        }
        if (overview.getSize() > 0) {
            response = String.format("%s\n%s", AGENDA_OVERVIEW, overview.toString());
        } else {
            response = NO_TASKS_FOR_THIS_DATE;
        }
    }

    /**
     * Looks for a user-provided keyword within the descriptions of the agenda and
     * sets response equal to the search result.
     * @param tasks of type TaskList containing all entries of the agenda
     * @throws DoliExceptions to handle the case in which the user input is incorrect.
     */
    private void find(TaskList tasks) throws DoliExceptions {
        TaskList searchResults = new TaskList();
        String keyword = details[0];
        if (details[0].trim().isEmpty()) {
            throw new DoliExceptions("Please provide a keyword to search your agenda.");
        }
        tasks.stream().filter((t) -> t.getDescription().contains(keyword)).forEach(searchResults::addTask);
        if (searchResults.getSize() > 0) {
            response = String.format("%s\n%s", MATCHING_TASKS, searchResults.toString());
        } else {
            response = NO_TASKS_FOUND;
        }
    }
    /**
     * Highlights how Doli was not able to capture the command as it did not recognize it
     * and sets response equal to a description of the previous.
     */
    private void unrecognizedInputCommand() {
        response = UNRECOGNIZED_COMMAND;
    }

    /**
     * Sets the boolean variable isExit to true which will cause the while loop
     * in the Doli class to come to a halt and the program to exit.
     */
    private void prepareForExit() {
        response = EXIT;
        this.isExit = true;
    }

    /**
     * Tries to parse an input String text into an integer value. If it does not succeed the method
     * returns the value -1 which is never an index of the agenda and hence easy to identify.
     * @param text of type String which is to be parsed into integers
     * @return the successfully parsed integer or -1
     */
    private static Integer tryToParse(String text) {
        try {
            return Integer.parseInt(text);
        } catch (NumberFormatException e) {
            return -1;
        }
    }

    /**
     * Checks if the user detail input is within the bounds of the agenda.
     * @param inputNumber of type int specifying the task number to be carried an action upon
     * @param tasks of type TaskList containing all the tasks in the agenda
     * @throws DoliExceptions to handle the case in which the provided number could not be parsed or exceeds the limit
     */
    private void checkNotOutOfBounds(int inputNumber, TaskList tasks) throws DoliExceptions{
        int totalTaskSize = tasks.getSize();
        int parsingFailed = -1;
        if (inputNumber > totalTaskSize || inputNumber == parsingFailed) {
            throw new DoliExceptions(FAILED_TO_FIND_TASK);
        }
    }

    /**
     * Core method of the Command class, responsible for identifying the specific cases and carrying out
     * the correct actions based on the input command. It successively also modifies/updates the file
     * in the storage, so that the agenda is stored in its updated version and can be retrieved as such
     * when Doli is run for the next time.
     * @param tasks of type TaskList containing all entries of the agenda
     * @param ui of type Ui
     * @param storage of type Storage
     * @throws DoliExceptions handling any kind of error related to wrongly formatted input commands or similar
     */
    public void handleCommand(TaskList tasks, Ui ui, Storage storage) throws DoliExceptions {
        switch (command) {
            case TODO_COMMAND:
                initializeNewTodo(tasks);
                break;
            case DEADLINE_COMMAND:
                initializeNewDeadline(tasks);
                break;
            case EVENT_COMMAND:
                initializeNewEvent(tasks);
                break;
            case LIST_COMMAND:
                listAgenda(tasks);
                break;
            case DELETE_COMMAND:
                deleteTask(tasks);
                break;
            case CLEAR_COMMAND:
                deleteAll(tasks);
                break;
            case MARK_COMMAND:
                setMark(tasks);
                break;
            case UNMARK_COMMAND:
                unsetMark(tasks);
                break;
            case OVERVIEW_BY_SPECIFIC_DATE_COMMAND:
                overviewBySpecificDate(tasks);
                break;
            case FIND_COMMAND:
                find(tasks);
                break;
            case EXIT_COMMAND:
                prepareForExit();
                break;
            default:
                unrecognizedInputCommand();
                break;
        }
        storage.modifyFile(tasks);
    }

    /**
     * Prints out the response variable which has been to an appropriate answer by the handleCommand() method
     */
    public void getResponse() {
        System.out.println(response);
    }
}
