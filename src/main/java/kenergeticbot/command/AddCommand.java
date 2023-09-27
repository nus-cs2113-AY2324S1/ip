package kenergeticbot.command;

import kenergeticbot.TaskList;
import kenergeticbot.exceptionhandler.KenergeticBotException;
import kenergeticbot.task.Deadline;
import kenergeticbot.task.Event;
import kenergeticbot.task.Task;
import kenergeticbot.task.Todo;
import kenergeticbot.ui.TextUi;

import static kenergeticbot.exceptionhandler.KenergeticBotException.*;

public class AddCommand extends Command {
    private static String item = null;
    private String taskType;
    public AddCommand(String item) throws KenergeticBotException {
        this.item = item;
        if (BooleanChecks.checkTextForTodo(item)) {
            this.taskType = "T";
        } else if (BooleanChecks.checkTextForDeadline(item)) {
            this.taskType = "D";
        } else if (BooleanChecks.checkTextForEvent(item)) {
            this.taskType = "E";
        }else {
            throw new KenergeticBotException(INVALID_COMMAND); //throws exception when input does not match any known command
        }
    }

    public void execute(TaskList taskList){
        TextUi.printLine();
        switch (taskType) {
        case "T":
            try {
                addTodo(taskList, item);
            } catch (KenergeticBotException e) { //throws exception when the todo command encounters a problem, the error message is tagged to the thrown exception
                System.out.println(e.getMessage());
            }
            break;
        case "D":
            try {
                addDeadline(taskList, item);
            } catch (KenergeticBotException e) { //throws exception when the deadline command encounters a problem, the error message is tagged to the thrown exception
                System.out.println(e.getMessage());
            }
            break;
        case "E":
            try {
                addEvent(taskList, item);;
            } catch (KenergeticBotException e) { //throws exception when the event command encounters a problem, the error message is tagged to the thrown exception
                System.out.println(e.getMessage());
            }
            break;
        }
        TextUi.printLine();
    }

    //Creates a "Todo" object and adds to the taskList
    public static void addTodo(TaskList taskList ,String item) throws KenergeticBotException {
        String formattedString = item.replace("todo", "").trim();
        if (formattedString.isEmpty()) {
            throw new KenergeticBotException(TODO_MISSING_DESCRIPTION);
        }
        Task newTask = new Todo(formattedString);
        taskList.add(newTask);
        TextUi.printAddedTaskMessage(taskList, newTask);
    }

    //Creates a "Deadline" object and adds to the taskList
    public static void addDeadline(TaskList taskList, String item) throws KenergeticBotException {
        String[] formattedString = item.replace("deadline", "").trim().split("/");
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
        taskList.add(newTask);
        TextUi.printAddedTaskMessage(taskList, newTask);
    }

    //Creates a "Event" object and adds to the taskList
    public static void addEvent(TaskList taskList, String item) throws KenergeticBotException {
        String[] formattedString = item.replace("event", "").trim().split("/");
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
        taskList.add(newTask);
        TextUi.printAddedTaskMessage(taskList, newTask);
    }
}
