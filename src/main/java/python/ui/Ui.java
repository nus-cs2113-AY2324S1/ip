package python.ui;

import python.exception.PythonException;
import python.parser.Command;
import python.parser.Parser;
import python.task.Deadline;
import python.task.Event;
import python.task.TaskList;
import python.task.Todo;
import python.task.Task;

import java.util.List;
import java.util.Scanner;

public class Ui {
    private static final Scanner in = new Scanner(System.in);
    final private static String PYTHON_EMOJI = "\uD83D\uDC0D";
    final private static int HORIZONTAL_LINE_LENGTH = 80;
    private String inputLine;
    private String inputCommand;

    final private static String PYTHON_ASCII_ART =
            "\t ____        _   _\n" +
                    "\t|  _ \\ _   _| |_| |__   ___  _ __\n" +
                    "\t| |_) | | | | __|  _ \\ / _ \\|  _ \\\n" +
                    "\t|  __/| |_| | |_| | | | (_) | | | |\n" +
                    "\t|_|    \\__, |\\__|_| |_|\\___/|_| |_|\n" +
                    "\t       |___/";

    private static void printHorizontalLine() {
        String horizontalLine = "—".repeat(HORIZONTAL_LINE_LENGTH);
        System.out.println("\t" + horizontalLine);
    }

    private static void addEmojiAndPrint(String message) {
        System.out.printf("\t%s: %s\n", PYTHON_EMOJI, message);
    }

    public void welcomeUser() {
        System.out.println(PYTHON_ASCII_ART);
        printHorizontalLine();
        addEmojiAndPrint(Message.MESSAGE_INTRODUCTION);
        displayTaskCount();
        addEmojiAndPrint(Message.MESSAGE_ASK);
        printHorizontalLine();
    }

    public void greetGoodBye() {
        addEmojiAndPrint(Message.MESSAGE_BYE);
    }

    public void displayTaskCount() {
        addEmojiAndPrint("You have " + TaskList.getNumberOfTasks() + " tasks!");
    }

    public void displayTasks(List<Task> tasks) {
        for (int taskNo = 0; taskNo < tasks.size(); taskNo++) {
            System.out.printf("\t\t\t%d. %s\n", taskNo + 1, tasks.get(taskNo));
        }
    }

    public void getUserInput() {
        this.inputLine = in.nextLine();
    }

    public void displayException(Exception e) {
        System.out.println("\tError: " + e.getMessage());
    }

    public void parseLine() {
        this.inputCommand = Parser.extractCommandFromInputLine(inputLine);
    }

    private void handleByeCommand() {
        greetGoodBye();
    }

    private void handleListCommand() {
        displayTaskCount();
        displayTasks(TaskList.getTasks());
    }

    private void handleMarkCommand() throws PythonException {
        int taskNo;
        try {
            taskNo = Parser.extractTaskNoFromInputLine(inputLine) - 1;
        } catch (NumberFormatException | ArrayIndexOutOfBoundsException e) {
            throw new PythonException(Message.MESSAGE_INT_AFTER_COMMAND);
        }

        // Handle unintended usage
        if (taskNo >= TaskList.getNumberOfTasks()) {
            addEmojiAndPrint(Message.MESSAGE_FUTURE_JOKE);
            return;
        }
        if (TaskList.getTask(taskNo).isDone()) {
            addEmojiAndPrint(Message.MESSAGE_PAST_JOKE);
            addEmojiAndPrint(TaskList.getTask(taskNo).toString());
            addEmojiAndPrint(Message.MESSAGE_TASK_ALREADY_DONE);
            return;
        }

        TaskList.markTask(taskNo);
        addEmojiAndPrint(Message.MESSAGE_CONGRATUALTE);
        addEmojiAndPrint(TaskList.getTask(taskNo).toString());
    }

    private void handleUnmarkCommand() throws PythonException {
        int taskNo;
        try {
            taskNo = Parser.extractTaskNoFromInputLine(inputLine) - 1;
        } catch (NumberFormatException | ArrayIndexOutOfBoundsException e) {
            throw new PythonException(Message.MESSAGE_INT_AFTER_COMMAND);
        }

        // Handle unintended usage
        if (taskNo >= TaskList.getNumberOfTasks()) {
            addEmojiAndPrint(Message.MESSAGE_FUTURE_JOKE);
            return;
        }
        if (!TaskList.getTask(taskNo).isDone()) {
            addEmojiAndPrint(Message.MESSAGE_UNMARK_TASK_JOKE);
            addEmojiAndPrint(TaskList.getTask(taskNo).toString());
            addEmojiAndPrint(Message.MESSAGE_TASK_IDLE);
            return;
        }

        TaskList.unmarkTask(taskNo);

        addEmojiAndPrint(Message.MESSAGE_MISTAKE_CONSOLATION);
        addEmojiAndPrint(TaskList.getTask(taskNo).toString());
    }

    private void handleDeleteCommand() throws PythonException {
        int taskNo;

        try {
            taskNo = Parser.extractTaskNoFromInputLine(inputLine) - 1;
        } catch (NumberFormatException | ArrayIndexOutOfBoundsException e) {
            throw new PythonException(Message.MESSAGE_INT_AFTER_COMMAND);
        }

        // Handle unintended usage
        if (taskNo >= TaskList.getNumberOfTasks()) {
            addEmojiAndPrint(Message.MESSAGE_FUTURE_JOKE);
            displayTaskCount();
            return;
        }

        addEmojiAndPrint(Message.MESSAGE_DELETE_CONFIRMATION);
        addEmojiAndPrint(TaskList.getTask(taskNo).toString());
        TaskList.deleteTask(taskNo);
        displayTaskCount();
    }


    private void handleTodoCommand() throws PythonException {
        String todoDescription;
        try {
            todoDescription = Parser.extractTodoDescFromInputLine(inputLine);
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new PythonException(Message.MESSAGE_DESC_AFTER_COMMAND);
        }

        addEmojiAndPrint(Message.MESSAGE_NEW_TODO);

        Todo todo = new Todo(todoDescription);

        TaskList.addTask(todo);

        addEmojiAndPrint(todo.toString());
        displayTaskCount();
    }


    private void handleDeadlineCommand() throws PythonException {
        String deadlineDetails, deadlineDescription, deadlineBy;
        try {
            deadlineDetails = Parser.extractDeadlineDetailsFromInputLine(inputLine);
            deadlineDescription = Parser.extractDeadlineDescFromDeadlineDetails(deadlineDetails);
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new PythonException(Message.MESSAGE_DESC_AFTER_COMMAND);
        }
        try {
            deadlineBy = Parser.extractDeadlineByFromDeadlineDetails(deadlineDetails);
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new PythonException(Message.MESSAGE_TIME_AFTER_BY_CLAUSE);
        }

        addEmojiAndPrint(Message.MESSAGE_NEW_DEADLINE);

        Deadline deadline = new Deadline(deadlineDescription, deadlineBy);
        TaskList.addTask(deadline);

        addEmojiAndPrint(deadline.toString());
        displayTaskCount();
    }

    private void handleEventCommand() throws PythonException {
        String eventDetails, eventDescription, eventFrom, eventTo;
        try {
            eventDetails = Parser.extractEventDetailsFromInputLine(inputLine);
            eventDescription = Parser.extractEventDescFromEventDetails(eventDetails);
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new PythonException(Message.MESSAGE_DESC_AFTER_COMMAND);
        }

        try {
            eventFrom = Parser.extractEventFromFromEventDetails(eventDetails);
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new PythonException(Message.MESSAGE_TIME_AFTER_FROM_CLAUSE);
        }

        try {
            eventTo = Parser.extractEventToFromEventDetails(eventDetails);
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new PythonException(Message.MESSAGE_TIME_AFTER_TO_CLAUSE);
        }

        addEmojiAndPrint(Message.MESSAGE_NEW_EVENT);

        Event event = new Event(eventDescription, eventFrom, eventTo);
        TaskList.addTask(event);

        addEmojiAndPrint(event.toString());
        displayTaskCount();
    }

    private void handleFindCommand() throws PythonException {
        String keyword;
        try {
            keyword = Parser.extractKeywordFromInputLine(inputLine);
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new PythonException(Message.MESSAGE_KEYWORD_AFTER_COMMAND);
        }

        List<Task> matchedTasks = TaskList.findTask(keyword);

        if (matchedTasks.isEmpty()) addEmojiAndPrint(Message.MESSAGE_NO_MATCH);
        else {
            addEmojiAndPrint(Message.MESSAGE_MATCHES_FOUND);
            displayTasks(matchedTasks);
        }
    }

    private void handleUnknownCommand() throws PythonException {
        if (inputLine.isEmpty()) {
            addEmojiAndPrint(Message.MESSAGE_EMPTY_COMMAND_JOKE);
            return;
        }
        addEmojiAndPrint(Message.MESSAGE_UNKNOWN_COMMAND);
    }


    public void executeLine() throws PythonException {
        printHorizontalLine();
        switch (inputCommand) {
        case Command.COMMAND_BYE:
            handleByeCommand();
            break;
        case Command.COMMAND_LIST:
            handleListCommand();
            break;
        case Command.COMMAND_MARK:
            handleMarkCommand();
            break;
        case Command.COMMAND_UNMARK:
            handleUnmarkCommand();
            break;
        case Command.COMMAND_DELETE:
            handleDeleteCommand();
            break;
        case Command.COMMAND_TODO:
            handleTodoCommand();
            break;
        case Command.COMMAND_DEADLINE:
            handleDeadlineCommand();
            break;
        case Command.COMMAND_EVENT:
            handleEventCommand();
            break;
        case Command.COMMAND_FIND:
            handleFindCommand();
            break;
        default:
            handleUnknownCommand();
            break;
        }
    }

    public boolean isExit() {
        return Command.isCommandBye(inputCommand);
    }

}
