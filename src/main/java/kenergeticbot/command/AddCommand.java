package kenergeticbot.command;

import kenergeticbot.TaskList;
import kenergeticbot.exceptionhandler.KenergeticBotException;
import kenergeticbot.task.Deadline;
import kenergeticbot.task.Event;
import kenergeticbot.task.Task;
import kenergeticbot.task.Todo;
import kenergeticbot.ui.TextUi;
import static kenergeticbot.common.Messages.SEPARATING_LINE;

import static kenergeticbot.exceptionhandler.KenergeticBotException.*;

public class AddCommand extends Command {
    private static String item = null;
    private final String taskType;

    /**
     * Convenience constructor using input string.
     * @param userInput User's input used to determine the type of task to be created
     * @throws KenergeticBotException if input does not match any known command
     */
    public AddCommand(String userInput) throws KenergeticBotException {
        AddCommand.item = userInput;
        if (BooleanChecks.checkTextForTodo(userInput)) {
            this.taskType = "T";
        } else if (BooleanChecks.checkTextForDeadline(userInput)) {
            this.taskType = "D";
        } else if (BooleanChecks.checkTextForEvent(userInput)) {
            this.taskType = "E";
        }else {
            throw new KenergeticBotException(INVALID_COMMAND);
        }
    }

    @Override
    public void execute(TaskList taskList, TextUi ui){
        switch (taskType) {
        case "T":
            try {
                addTodo(taskList, item, ui);
            } catch (KenergeticBotException e) { //throws exception when the todo command encounters a problem, the error message is tagged to the thrown exception
                ui.showToUser(SEPARATING_LINE, e.getMessage(), SEPARATING_LINE);
            }
            break;
        case "D":
            try {
                addDeadline(taskList, item, ui);
            } catch (KenergeticBotException e) { //throws exception when the deadline command encounters a problem, the error message is tagged to the thrown exception
                ui.showToUser(SEPARATING_LINE, e.getMessage(), SEPARATING_LINE);
            }
            break;
        case "E":
            try {
                addEvent(taskList, item, ui);
            } catch (KenergeticBotException e) { //throws exception when the event command encounters a problem, the error message is tagged to the thrown exception
                ui.showToUser(SEPARATING_LINE, e.getMessage(), SEPARATING_LINE);
            }
            break;
        }
    }

    /**
     * Creates a "Todo" object and adds to the taskList
     * @param taskList The arraylist object created to store the newly created "Todo" Task object
     * @param userInput User's input containing the details used to create the "Todo" Task object
     * @param ui The TextUI object created to handle I/O with the user
     * @throws KenergeticBotException if userInput does not contain sufficient information required to create "Todo" Task object
     */
    public static void addTodo(TaskList taskList ,String userInput, TextUi ui) throws KenergeticBotException {
        String formattedString = userInput.replace("todo", "").trim();
        if (formattedString.isEmpty()) {
            throw new KenergeticBotException(TODO_MISSING_DESCRIPTION);
        }
        Task newTask = new Todo(formattedString);
        taskList.addTask(newTask);
        ui.printAddedTaskMessage(taskList, newTask);
    }

    /**
     * Creates a "Deadline" object and adds to the taskList
     * @param taskList The arraylist object created to store the newly created "Deadline" Task object
     * @param userInput User's input containing the details used to create the "Deadline" Task object
     * @param ui The TextUI object created to handle I/O with the user
     * @throws KenergeticBotException if userInput does not contain sufficient information required to create "Deadline" Task object
     */
    public static void addDeadline(TaskList taskList, String userInput, TextUi ui) throws KenergeticBotException {
        String[] formattedString = userInput.replace("deadline", "").trim().split("/");
        switch (formattedString.length) { //no default case needed since finding exceptions
            case 1: //no break; is needed since throwing exception
                if (formattedString[0].isEmpty()) {
                    throw new KenergeticBotException(DEADLINE_MISSING_DESCRIPTION);
                } else {
                    throw new KenergeticBotException(DEADLINE_MISSING_DATE_INTERMEDIATE);
                }
            case 2: //no break; is needed since throwing exception
                if (!formattedString[1].contains("by")) {
                    throw new KenergeticBotException(COMMAND_TYPO_DEADLINE_BY);
                } else if (formattedString[1].replace("by", "").trim().isEmpty()) {
                    throw new KenergeticBotException(DEADLINE_MISSING_DATE);
                }
        }
        String deadlineDate = "(" + formattedString[1].replace("by", "by:") + ")";
        Task newTask = new Deadline(formattedString[0], deadlineDate);
        taskList.addTask(newTask);
        ui.printAddedTaskMessage(taskList, newTask);
    }

    //
    /**
     * Creates an "Event" object and adds to the taskList
     * @param taskList The arraylist object created to store the newly created "Event" Task object
     * @param userInput User's input containing the details used to create the "Event" Task object
     * @param ui The TextUI object created to handle I/O with the user
     * @throws KenergeticBotException if userInput does not contain sufficient information required to create "Event" Task object
     */
    public static void addEvent(TaskList taskList, String userInput, TextUi ui) throws KenergeticBotException {
        String[] formattedString = userInput.replace("event", "").trim().split("/");
        switch(formattedString.length) { //no default case needed since finding exceptions
            case 1: //no break; is needed since throwing exception
                if (formattedString[0].isEmpty()) {
                    throw new KenergeticBotException(EVENT_MISSING_DESCRIPTION);
                } else {
                    throw new KenergeticBotException(EVENT_MISSING_START_INTERMEDIATE);
                }
            case 2: //no break; is needed since throwing exception
                if (!formattedString[1].contains("from")) {
                    throw new KenergeticBotException(COMMAND_TYPO_EVENT_FROM);
                } else if (formattedString[1].replace("from", "").trim().isEmpty()){
                    throw new KenergeticBotException(EVENT_MISSING_START);
                } else {
                    throw new KenergeticBotException(EVENT_MISSING_END_INTERMEDIATE);
                }
            case 3: //no break; is needed since throwing exception
                if (!formattedString[2].contains("to")) {
                    throw new KenergeticBotException(COMMAND_TYPO_EVENT_TO);
                } else if (formattedString[2].replace("to", "").trim().isEmpty()){
                    throw new KenergeticBotException(EVENT_MISSING_END);
                }
        }
        String eventFrom = formattedString[1].replace("from", "from:");
        String eventTo = formattedString[2].replace("to", "to:");
        String dateTime = "(" + eventFrom + eventTo + ")";
        Task newTask = new Event(formattedString[0], dateTime);
        taskList.addTask(newTask);
        ui.printAddedTaskMessage(taskList, newTask);
    }
}
