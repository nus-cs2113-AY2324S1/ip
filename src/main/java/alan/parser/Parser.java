package alan.parser;

import alan.data.TaskList;
import alan.data.exception.AlanException;
import alan.data.task.Task;
import alan.ui.Ui;

import static alan.common.Messages.MESSAGE_FIND_TASK;
import static alan.common.Messages.MESSAGE_LIST_COMMAND;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static alan.data.exception.AlanException.checkEmptyDescription;
import static alan.data.exception.AlanException.checkEventInputFromFormat;
import static alan.data.exception.AlanException.checkEventInputToFormat;
import static alan.data.exception.AlanException.checkOutOfTaskListIndex;
import static alan.data.exception.AlanException.invalidInputCommand;
import static alan.data.exception.AlanException.checkDeadlineInputFormat;

/**
 * Represents a parser that parses the user input.
 */
public class Parser {
    public static final String DATE_PARSE_PATTERN = "dd MMM yyyy";
    private TaskList tasks;
    private Ui ui;

    public Parser(TaskList tasks, Ui ui) {
        this.tasks = tasks;
        this.ui = ui;
    }
    /**
     * Handles extracting the command from the user input and executes the respective command.
     *
     * @param userInput text of the user input.
     * @throws AlanException If an error is detected from any of the command handlers.
     */
    public void processCommandHandler(String userInput) throws AlanException {
        String[] userInputWords = userInput.split(" ");
        String command = userInputWords[0];

        switch (command) {
        case "bye":
            ui.showExitMessage();
            break;
        case "list":
            listCommandHandler();
            break;
        case "mark":
            markingCommandHandler(userInput, true);
            break;
        case "unmark":
            markingCommandHandler(userInput, false);
            break;
        case "todo":
            checkEmptyDescription(userInput);
            todoCommandHandler(userInput);
            break;
        case "deadline":
            checkEmptyDescription(userInput);
            deadlineCommandHandler(userInput);
            break;
        case "event":
            checkEmptyDescription(userInput);
            eventCommandHandler(userInput);
            break;
        case "delete":
            deleteCommandHandler(userInput);
            break;
        case "find":
            findCommandHandler(userInput);
            break;
        default:
            invalidInputCommand();
        }
    }

    /**
     * Handles displaying all the tasks within the TaskList.
     */
    public void listCommandHandler() {
        ui.showToUser(MESSAGE_LIST_COMMAND);
        ui.printTasks(tasks.getTaskList());
    }

    /**
     * Checks for any text found after /from parameter.
     *
     * @param userInput text of the user input.
     * @param isMark mark or unmark the selected task.
     * @throws AlanException If there is only 1 String element found in array.
     */
    public void markingCommandHandler(String userInput, boolean isMark) throws AlanException {
        String[] words = userInput.split(" ");
        int selectedTaskIndex = Integer.parseInt(words[1]) - 1;

        checkOutOfTaskListIndex(selectedTaskIndex, tasks.getTaskList());
        Task targetTask = tasks.getSelectedTask(selectedTaskIndex);

        if (isMark) {
            tasks.markTask(selectedTaskIndex, true);
            ui.showMarkTaskMessage(targetTask);
        } else {
            tasks.markTask(selectedTaskIndex, false);
            ui.showUnmarkTaskMessage(targetTask);
        }
    }

    /**
     * Handles extracting description text.
     * Adds <code>Todo</code> object to the TaskList.
     *
     * @param userInput text of the user input.
     */
    public void todoCommandHandler(String userInput) {
        String description = userInput.replace("todo ", "");
        tasks.addToDo(description);

        ui.showTaskAddedMessage(tasks.getTaskList());
    }

    /**
     * Handles extracting description and 'by' time period text.
     * Adds <code>Deadline</code> object to the TaskList.
     *
     * @param userInput text of the user input.
     * @throws AlanException If input format of /by is not correct.
     */
    public void deadlineCommandHandler(String userInput) throws AlanException {
        String filteredUserInput = userInput.replace("deadline ", "");
        String[] data = filteredUserInput.split(" /by ");

        checkDeadlineInputFormat(data);

        String description = data[0];
        String by = data[1];

        String parsedBy = parseDate(by);

        tasks.addDeadline(description, parsedBy);
        ui.showTaskAddedMessage(tasks.getTaskList());
    }

    /**
     * Handles extracting description, 'from' time period and 'to' time period text.
     * Adds <code>Event</code> object to the TaskList.
     *
     * @param userInput text of the user input.
     * @throws AlanException If input format of /from or /to is not correct.
     */
    public void eventCommandHandler(String userInput) throws AlanException {
        String filteredUserInput = userInput.replace("event ", "");
        String[] splitDescriptionAndDate = filteredUserInput.split(" /from ");

        checkEventInputFromFormat(splitDescriptionAndDate);

        String[] splitFromAndTo = splitDescriptionAndDate[1].split(" /to ");

        checkEventInputToFormat(splitFromAndTo);

        String description = splitDescriptionAndDate[0];
        String from = splitFromAndTo[0];
        String to = splitFromAndTo[1];

        String parsedFrom = parseDate(from);
        String parsedTo = parseDate(to);

        tasks.addEvent(description, parsedFrom, parsedTo);

        ui.showTaskAddedMessage(tasks.getTaskList());
    }

    /**
     * Handles extracting the user's selected task index.
     * Deletes selected task from TaskList.
     *
     * @param userInput text of the user input.
     * @throws AlanException If the task index is not found in TaskList.
     */
    public void deleteCommandHandler(String userInput) throws AlanException {
        String[] words = userInput.split(" ");
        int selectedTaskIndex = Integer.parseInt(words[1]) - 1;
        checkOutOfTaskListIndex(selectedTaskIndex, tasks.getTaskList());

        Task targetTask = tasks.getSelectedTask(selectedTaskIndex);
        ui.showDeleteTaskMessage(targetTask);
        tasks.removeTask(selectedTaskIndex);

        int numberOfTasks = tasks.getTaskListSize();
        ui.showNumberOfTasksMessage(numberOfTasks);
    }

    public void findCommandHandler(String userInput) {
        String findText = userInput.replace("find ", "");
        TaskList findResultTasks = new TaskList();

        for (Task task : tasks.getTaskList()) {
            String taskDescription = task.getDescription();

            if (taskDescription.matches("(.*)" + findText + "(.*)")) {
                findResultTasks.getTaskList().add(task);
            }
        }

        ui.showToUser(MESSAGE_FIND_TASK);
        ui.printTasks(findResultTasks.getTaskList());
    }
    private String parseDate(String inputDate) {
        if (isValidDate(inputDate)) {
            LocalDate parsedDate = LocalDate.parse(inputDate);
            inputDate = parsedDate.format(DateTimeFormatter.ofPattern(DATE_PARSE_PATTERN));
        }
        return inputDate;
    }

    public boolean isValidDate(String inDate) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd");
        dateFormat.setLenient(false);
        try {
            dateFormat.parse(inDate.trim());
        } catch (ParseException pe) {
            return false;
        }
        return true;
    }
}
