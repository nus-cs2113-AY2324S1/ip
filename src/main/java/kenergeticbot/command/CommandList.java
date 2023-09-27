package kenergeticbot.command;

import kenergeticbot.exceptionhandler.KenergeticBotException;
import kenergeticbot.task.Deadline;
import kenergeticbot.task.Event;
import kenergeticbot.task.Task;
import kenergeticbot.task.Todo;

import java.util.ArrayList;

import static kenergeticbot.exceptionhandler.KenergeticBotException.*;


public class CommandList {
    public static void list(ArrayList<Task> taskList) {
        CommonMessages.printLine();
        System.out.println("     Here are the tasks in your list:");
        for (int i = 0; i < taskList.size(); i++) {
            System.out.printf("     %d.%s\n", i+1, taskList.get(i));
        }
        CommonMessages.printLine();
    }

    public static void mark(ArrayList<Task> taskList, int listIndex) {
        CommonMessages.printLine();
        System.out.println("     Nice! I've marked this task as done:");
        taskList.get(listIndex - 1).mark();
        System.out.printf("       %s\n", taskList.get(listIndex - 1));
        CommonMessages.printLine();
    }

    public static void unmark(ArrayList<Task> taskList, int listIndex) {
        CommonMessages.printLine();
        System.out.println("     OK, I've marked this task as not done yet:");
        taskList.get(listIndex - 1).unmark();
        System.out.printf("       %s\n", taskList.get(listIndex - 1));
        CommonMessages.printLine();
    }

    //Creates a "Todo" object and adds to the taskList
    public static void addTodo(ArrayList<Task> taskList, String item) throws KenergeticBotException {
        String formattedString = item.replace("todo", "").trim();
        if (formattedString.isEmpty()) {
            throw new KenergeticBotException(TODO_MISSING_DESCRIPTION);
        }
        String taskType = "[T]";
        Task newTask = new Todo(formattedString, taskType);
        taskList.add(newTask);
        CommonMessages.printAddedTaskMessage(taskList, newTask);
    }

    //Creates a "Deadline" object and adds to the taskList
    public static void addDeadline(ArrayList<Task> taskList, String item) throws KenergeticBotException {
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
        String taskType = "[D]";
        String deadlineDate = "(" + formattedString[1].replace("by", "by:") + ")";
        Task newTask = new Deadline(formattedString[0], taskType, deadlineDate);
        taskList.add(newTask);
        CommonMessages.printAddedTaskMessage(taskList, newTask);
    }

    //Creates a "Event" object and adds to the taskList
    public static void addEvent(ArrayList<Task> taskList, String item) throws KenergeticBotException {
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
        String taskType = "[E]";
        String eventFrom = formattedString[1].replace("from", "from:");
        String eventTo = formattedString[2].replace("to", "to:");
        String dateTime = "(" + eventFrom + eventTo + ")";
        Task newTask = new Event(formattedString[0], taskType, dateTime);
        taskList.add(newTask);
        CommonMessages.printAddedTaskMessage(taskList, newTask);
    }

    //Controls the logic for adding items to the list
    public static void add(ArrayList<Task> taskList, String item) throws KenergeticBotException {
        CommonMessages.printLine();
        if (BooleanChecks.checkTextForTodo(item)) {
            try {
                addTodo(taskList, item);
            } catch (KenergeticBotException e) { //throws exception when the todo command encounters a problem, the error message is tagged to the thrown exception
                System.out.println(e.getMessage());
            }
        } else if (BooleanChecks.checkTextForDeadline(item)) {
            try {
                addDeadline(taskList, item);
            } catch (KenergeticBotException e) { //throws exception when the deadline command encounters a problem, the error message is tagged to the thrown exception
                System.out.println(e.getMessage());
            }
        } else if (BooleanChecks.checkTextForEvent(item)) {
            try {
                addEvent(taskList, item);;
            } catch (KenergeticBotException e) { //throws exception when the event command encounters a problem, the error message is tagged to the thrown exception
                System.out.println(e.getMessage());
            }
        } else {
            throw new KenergeticBotException(INVALID_COMMAND); //throws exception when input does not match any known command
        }
        CommonMessages.printLine();
    }

    public static void delete(ArrayList<Task> taskList, int listIndex) {
        CommonMessages.printLine();
        System.out.println("     Noted. I've removed this task:");
        System.out.println("       " + taskList.get(listIndex - 1));
        taskList.remove(listIndex - 1);
        System.out.printf("     Now you have %d tasks in the list.\n", taskList.size());
        CommonMessages.printLine();
    }
}
