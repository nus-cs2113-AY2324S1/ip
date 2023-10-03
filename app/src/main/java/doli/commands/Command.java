package doli.commands;

import doli.GUI.Ui;
import doli.exceptions.DoliExceptions;
import doli.files.Storage;
import doli.tasks.*;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;

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
    public Command(String command, String[] details) {
        this.command = command;
        this.details = details;
        this.isExit = false;
    }
    public boolean isExit() {
        return this.isExit;
    }
    private void initializeNewTodo(TaskList tasks) {
        ToDo newTodo = new ToDo(details[0]);
        tasks.addTask(newTodo);
        response = String.format("%s\n\t%s\n%s",
                ADDED_TASK_SUCCESSFULLY,
                newTodo.toString(),
                String.format(SUMMARIZING_CURRENT_AGENDA_ENTRIES, tasks.getSize()));
    }
    private void initializeNewDeadline(TaskList tasks) {
        Deadline newDeadline = new Deadline(details[0], details[1].trim());
        tasks.addTask(newDeadline);
        response = newDeadline.toString();
    }
    private void initializeNewEvent(TaskList tasks) {
        Event newEvent = new Event(details[0], details[1].trim(), details[2].trim());
        tasks.addTask(newEvent);
        response = newEvent.toString();
    }
    private void listAgenda(TaskList tasks) {
        response = String.format("%s\n%s", AGENDA_OVERVIEW, tasks.toString());
    }
    private void deleteTask(TaskList tasks) throws DoliExceptions {
        int taskNumber = tryToParse(details[0]);
        checkNotOutOfBounds(taskNumber, tasks);
        tasks.deleteTask(taskNumber);
        response = String.format(DELETED_TASK_SUCCESSFULLY, taskNumber);
    }
    private void deleteAll(TaskList tasks) {
        tasks.deleteAll();
        response = DELETED_ALL_TASKS;
    }
    private void setMark(TaskList tasks) throws DoliExceptions{
        int taskNumber = tryToParse(details[0]);
        checkNotOutOfBounds(taskNumber, tasks);
        tasks.getTask(taskNumber).markTaskAsDone();
        response = String.format("%s\n%s",
                String.format(MARKED_SUCCESSFULLY, taskNumber),
                ENCOURAGE_TO_MARK);
    }
    private void unsetMark(TaskList tasks) throws DoliExceptions {
        int taskNumber = tryToParse(details[0]);
        checkNotOutOfBounds(taskNumber, tasks);
        tasks.getTask(taskNumber).markTaskAsNotDone();
        response = String.format("%s\n%s",
                String.format(UNMARKED_SUCCESSFULLY, taskNumber),
                ENCOURAGE_TO_MARK);
    }
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
    private void unrecognizedInputCommand() {
        response = UNRECOGNIZED_COMMAND;
    }
    private void prepareForExit() {
        response = EXIT;
        this.isExit = true;
    }
    private static Integer tryToParse(String text) {
        try {
            return Integer.parseInt(text);
        } catch (NumberFormatException e) {
            return -1;
        }
    }
    private void checkNotOutOfBounds(int inputNumber, TaskList tasks) throws DoliExceptions{
        int totalTaskSize = tasks.getSize();
        int parsingFailed = -1;
        if (inputNumber > totalTaskSize || inputNumber == parsingFailed) {
            throw new DoliExceptions(FAILED_TO_FIND_TASK);
        }
    }
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
    public void getResponse() {
        System.out.println(response);
    }
}
