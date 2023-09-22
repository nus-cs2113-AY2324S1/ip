package doli.commands;

import doli.Doli;
import doli.GUI.Ui;
import doli.exceptions.DoliExceptions;
import doli.files.Storage;
import doli.tasks.*;

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
        Deadline newDeadline = new Deadline(details[0], details[1]);
        tasks.addTask(newDeadline);
        response = newDeadline.toString();
    }
    private void initializeNewEvent(TaskList tasks) {
        Event newEvent = new Event(details[0], details[1], details[2]);
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
        if (inputNumber > tasks.getSize() || inputNumber == -1) {
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
