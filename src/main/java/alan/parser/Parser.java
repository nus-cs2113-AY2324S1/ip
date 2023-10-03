package alan.parser;

import alan.data.TaskList;
import alan.data.exception.AlanException;
import alan.data.task.Task;
import alan.ui.Ui;

import static alan.data.exception.AlanException.checkDeadlineInputFormat;
import static alan.data.exception.AlanException.checkEmptyDescription;
import static alan.data.exception.AlanException.checkEventInputFromFormat;
import static alan.data.exception.AlanException.checkEventInputToFormat;
import static alan.data.exception.AlanException.checkOutOfTaskListIndex;
import static alan.data.exception.AlanException.invalidInputCommand;

/**
 * Represents a parser that parses the user input.
 */
public class Parser {
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
        default:
            invalidInputCommand();
        }
    }

    /**
     * Handles displaying all the tasks within the TaskList.
     */
    public void listCommandHandler() {
        ui.showListMessage(tasks.getTaskList());
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

        tasks.addDeadline(description, by);

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

        tasks.addEvent(description, from, to);

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
}
