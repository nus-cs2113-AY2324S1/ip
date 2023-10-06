package doli.commands;

import doli.exceptions.DoliExceptions;
import doli.files.Storage;
import doli.tasks.TaskList;
import doli.tasks.Event;
import doli.tasks.Deadline;
import doli.tasks.ToDo;

import java.time.DateTimeException;
import java.time.LocalDate;

/**
 * <h3>Command class</h3>
 * The command class aims at organising the input of the user,
 * by delegating an action according to the specific command.
 * Its core is represented by a multitude of methods to be applied to the input.
 *
 * @author pappalardodaniel
 * @version 1.0
 * @since 2023-10-03
 */
public class Command {
    /** Command keyword input by the user */
    protected String command;
    /** Details following command input by the user */
    protected String[] details;
    /** Boolean checking whether the user wants to stop the program. */
    protected boolean isExit;
    /** Output response by Doli */
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
    private static final String LATE_COMMAND = "late";
    private static final String OVERVIEW_BY_SPECIFIC_DATE_COMMAND = "overview";
    private static final String HELP_COMMAND = "help";
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
    private static final String LATE_TASKS_OVERVIEW = "Here's an overview of tasks that were due and past events:";
    private static final String NO_LATE_TASKS = "You have no late tasks, great job!";
    private static final String HELP = "Refer to the user guide for help: https://danielpappa.github.io/ip/";
    private static final String NO_TASKS_FOUND = "The given keyword did not produce any search results in your agenda";
    private static final String EXIT = "Alright, I hope I was able to help you out.";

    /**
     * Constructs an object of type Command. The boolean isExit is automatically
     * set to false when the command is initialised without no further checks.
     *
     * @param command of type String specifying the action to be carried out.
     * @param details an array of Strings containing further information about the command.
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
     *
     * @return isExit of type boolean.
     */
    public boolean isExit() {
        return this.isExit;
    }

    /**
     * Initialises a new todo task with the description given
     * by the first element in the array of the details of the user input
     * command, adds it to the TaskList and summarises this within the
     * String variable response.
     *
     * @param tasks of type TaskList into which the new todo will be inserted.
     */
    private void initializeNewTodo(TaskList tasks) {
        String description = details[0];
        ToDo newTodo = new ToDo(description);
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
     *
     * @param tasks of type TaskList into which the new deadline will be inserted.
     */
    private void initializeNewDeadline(TaskList tasks) {
        String description = details[0];
        String deadline = details[1].trim();
        Deadline newDeadline = new Deadline(description, deadline);
        tasks.addTask(newDeadline);
        response = String.format("%s\n\t%s\n%s",
                ADDED_TASK_SUCCESSFULLY,
                newDeadline.toString(),
                String.format(SUMMARIZING_CURRENT_AGENDA_ENTRIES, tasks.getSize()));
    }
    /**
     * Initialises a new event task with the description given
     * by the first element in the array of the details of the user input
     * command, the start date given by the second element and the end
     * date given by the third element of the array provided by the user,
     * adds it to the TaskList and summarises this within the
     * String variable response.
     *
     * @param tasks of type TaskList into which the new event will be inserted.
     */
    private void initializeNewEvent(TaskList tasks) {
        String description = details[0];
        String startDate = details[1].trim();
        String endDate = details[2].trim();
        Event newEvent = new Event(description, startDate, endDate);
        tasks.addTask(newEvent);
        response = String.format("%s\n\t%s\n%s",
                ADDED_TASK_SUCCESSFULLY,
                newEvent.toString(),
                String.format(SUMMARIZING_CURRENT_AGENDA_ENTRIES, tasks.getSize()));
    }

    /**
     * Sets the response variable to a general overview of all the entries in
     * the agenda by using the overwritten method toString.
     *
     * @param tasks of type TaskList containing all the specific tasks.
     */
    private void listAgenda(TaskList tasks) {
        response = String.format("%s\n%s", AGENDA_OVERVIEW, tasks.toString());
    }

    /**
     * Deletes the task specified by the details provided by the user
     * within the command.
     *
     * @param tasks of type TaskList containing the entire list of tasks, of which one will be deleted.
     * @throws DoliExceptions in case the provided task was not found.
     */
    private void deleteTask(TaskList tasks) throws DoliExceptions {
        int taskNumber = tryToParse(details[0]);
        checkNotOutOfBounds(taskNumber, tasks);
        tasks.deleteTask(taskNumber);
        response = String.format(DELETED_TASK_SUCCESSFULLY, taskNumber);
    }

    /**
     * Deletes all tasks contained in the agenda so far by resetting the agenda to a blank TaskList.
     *
     * @param tasks of type TaskList, specifying the tasks to be deleted.
     */
    private void deleteAll(TaskList tasks) {
        tasks.deleteAll();
        response = DELETED_ALL_TASKS;
    }

    /**
     * Marks the task specified by the user as done (X). The user
     * input is expected to be of type command + task number.
     *
     * @param tasks of type TaskList containing the entire agenda.
     * @throws DoliExceptions to handle the cases in which the task number is not contained in the agenda.
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
     * input is expected to be of type command + task number.
     *
     * @param tasks of type TaskList containing the entire agenda.
     * @throws DoliExceptions to handle the cases in which the task number is not contained in the agenda.
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
     * The expected command input is of type command + date.
     *
     * @param tasks of type TaskList containing all the tasks in the agenda.
     */
    private void overviewBySpecificDate(TaskList tasks) {
        TaskList overview = new TaskList();
        try {
            LocalDate timeInput = LocalDate.parse(details[0]);
            tasks.stream()
                    .filter((t) -> t instanceof Deadline
                            && ((Deadline) t).getDeadline().isBefore(timeInput)).forEach(overview::addTask);
            tasks.stream()
                    .filter((t) -> t instanceof Event
                            && ((Event) t).getEndTime().isBefore(timeInput)).forEach(overview::addTask);
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
     *
     * @param tasks of type TaskList containing all entries of the agenda.
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
     * Lists all tasks of type deadline that were due before today
     * as well as all past events.
     *
     * @param tasks of type TaskList referring to the agenda where the tasks are contained.
     */
    private void listLateTasks(TaskList tasks) {
        TaskList overview = new TaskList();
        tasks.stream()
                .filter((t) -> t instanceof Deadline
                        && ((Deadline) t).getDeadline().isBefore(LocalDate.now())).forEach(overview::addTask);
        tasks.stream()
                .filter((t) -> t instanceof Event
                        && ((Event) t).getEndTime().isBefore(LocalDate.now())).forEach(overview::addTask);
        if (overview.getSize() > 0) {
            response = String.format("%s\n%s", LATE_TASKS_OVERVIEW, overview.toString());
        } else {
            response = NO_LATE_TASKS;
        }
    }

    /**
     * Sets response to a String containing the link to the user guide with an overview of the chatbots features.
     */
    private void help() {
        response = HELP;
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
     *
     * @param text of type String which is to be parsed into integers.
     * @return the successfully parsed integer or -1.
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
     *
     * @param inputNumber of type int specifying the task number to be carried an action upon.
     * @param tasks of type TaskList containing all the tasks in the agenda.
     * @throws DoliExceptions to handle the case in which the provided number could not be parsed or exceeds the limit.
     */
    private void checkNotOutOfBounds(int inputNumber, TaskList tasks) throws DoliExceptions{
        int totalTaskSize = tasks.getSize();
        int parsingFailed = -1;
        if (inputNumber > totalTaskSize || inputNumber == parsingFailed) {
            throw new DoliExceptions(FAILED_TO_FIND_TASK);
        }
    }

    /**
     * Handles commands and is the core method of the Command class,
     * responsible for identifying the specific cases and carrying out
     * the correct actions based on the input command. It successively also modifies/updates the file
     * in the storage, so that the agenda is stored in its updated version and can be retrieved as such
     * when Doli is run for the next time.
     *
     * @param tasks of type TaskList containing all entries of the agenda.
     * @param storage of type Storage to handle permanent modifications of the agenda.
     * @throws DoliExceptions handling any kind of error related to wrongly formatted input commands or similar.
     */
    public void handleCommand(TaskList tasks, Storage storage) throws DoliExceptions {
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
        case LATE_COMMAND:
            listLateTasks(tasks);
            break;
        case HELP_COMMAND:
            help();
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
     * Prints out the response variable which has been to an appropriate answer by the handleCommand() method.
     */
    public void getResponse() {
        System.out.printf(String.format("Doli:\n%s\n", response));
    }
}
