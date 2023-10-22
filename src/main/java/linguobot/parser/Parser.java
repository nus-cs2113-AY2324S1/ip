package linguobot.parser;

import linguobot.command.AddDeadline;
import linguobot.command.AddEvent;
import linguobot.command.AddTodo;
import linguobot.command.Command;
import linguobot.command.DeleteTask;
import linguobot.command.Exit;
import linguobot.command.FindTask;
import linguobot.command.MarkTask;
import linguobot.command.UnmarkTask;
import linguobot.command.ViewTaskList;
import linguobot.exceptions.LinguoBotException;
import linguobot.task.Deadline;
import linguobot.task.Event;
import linguobot.task.TaskList;
import linguobot.task.Todo;

/**
 * The Parser class is responsible for parsing user input and generating corresponding Command objects.
 * It processes input to create commands for adding tasks, listing tasks, deleting tasks, marking/unmarking tasks,
 * finding tasks, or exiting the application.
 */
public class Parser {
    private static final String ADD_TODO = "todo";
    private static final String ADD_DEADLINE = "deadline";
    private static final String ADD_EVENT = "event";
    private static final String VIEW_TASKLIST = "list";
    private static final String DELETE_TASK = "delete";
    private static final String MARK_TASK = "mark";
    private static final String UNMARK_TASK = "unmark";
    private static final String FIND_TASK = "find";
    private static final String EXIT = "bye";

    private final TaskList taskList;

    /**
     * Constructs a Parser with the provided taskList for processing user commands.
     * @param taskList The taskList used to store tasks.
     */
    public Parser(TaskList taskList) {
        this.taskList = taskList;
    }

    /**
     * Parses the user input and returns the corresponding Command object.
     * @param input User's input.
     * @return Command object representing the user's command.
     * @throws LinguoBotException if the input cannot be parsed or contains errors.
     */
    public Command parse(String input) throws LinguoBotException {
        String command = input.contains(" ") ? input.split(" ")[0] : input;
        switch (command) {
        case ADD_TODO:
            Todo todo = getTodo(input);
            return new AddTodo(todo, taskList);
        case ADD_DEADLINE:
            Deadline deadline = getDeadline(input);
            return new AddDeadline(deadline, taskList);
        case ADD_EVENT:
            Event event = getEvent(input);
            return new AddEvent(event, taskList);
        case VIEW_TASKLIST:
            return new ViewTaskList(taskList);
        case DELETE_TASK:
            return getDeleteTask(input, taskList);
        case MARK_TASK:
            return getMarkTask(input, taskList);
        case UNMARK_TASK:
            return getUnmarkTask(input, taskList);
        case FIND_TASK:
            return getFindTask(input, taskList);
        case EXIT:
            return new Exit();
        default:
            throw new LinguoBotException("☹ OOPS!!! I'm sorry, but I don't know what that means.\n" +
                    "Please include 'todo'/ 'deadline'/ 'event'/ 'mark'/ 'unmark'/ 'delete'/ 'find'/ 'list' in your input.");
        }
    }

    private Todo getTodo(String input) throws LinguoBotException {
        int TODO_DESCRIPTION_INDEX = 4;
        String description = input.substring(TODO_DESCRIPTION_INDEX).trim();
        if (description.isEmpty()) {
            throw new LinguoBotException("Todo description cannot be empty!");
        }
        return new Todo(description);
    }

    private Deadline getDeadline(String input) throws LinguoBotException {
        int DEADLINE_DESCRIPTION_INDEX = 8;
        int indexBy = input.indexOf("by");
        if (indexBy == -1) {
            throw new LinguoBotException("Invalid input. Please include 'by' in input.");
        }
        String description = input.substring(DEADLINE_DESCRIPTION_INDEX, indexBy).trim();
        if (description.isEmpty()) {
            throw new LinguoBotException("Deadline description cannot be empty!");
        }
        String date = input.substring(indexBy + 2).trim();
        if (!date.matches("\\d{1,2}/\\d{1,2}/\\d{4}") &&
                !date.matches("\\d{1,2}/\\d{1,2}/\\d{4} \\d{1,2}:\\d{2} [APap][Mm]")) {
            throw new LinguoBotException("Invalid date format. Please use the format 'D/M/YYYY H:MM AM/PM', " +
                    "e.g., '5/4/2023 6:00 PM'");
        }
        return new Deadline(description, date);
    }

    private Event getEvent(String input) throws LinguoBotException {
        int EVENT_DESCRIPTION_INDEX = 5;
        int indexFrom = input.indexOf("from");
        int indexTo = input.indexOf("to", indexFrom);
        if (indexFrom == -1 || indexTo == -1) {
            throw new LinguoBotException("Invalid input. Please include both 'from' and 'to' in input.");
        }
        String description = input.substring(EVENT_DESCRIPTION_INDEX, indexFrom - 1).trim();
        if (description.isEmpty()) {
            throw new LinguoBotException("Event description cannot be empty!");
        }
        String start = input.substring(indexFrom + 4, indexTo).trim();
        String end = input.substring(indexTo + 2).trim();
        if (start.isEmpty() || end.isEmpty()) {
            throw new LinguoBotException("Invalid input. Please include description after 'from' and 'to'!");
        }
        return new Event(description, start, end);
    }

    private Command getDeleteTask(String input, TaskList taskList) throws LinguoBotException {
        int DELETE_START_INDEX = 7;
        if (input.length() <= DELETE_START_INDEX) {
            throw new LinguoBotException("Invalid input format. Please provide a task index to delete.");
        }
        try {
            int deleteTaskIndex = Integer.parseInt(input.substring(DELETE_START_INDEX)) - 1;
            if (taskList.getNumberOfTasks() == 0) {
                throw new LinguoBotException("Task list is empty.");
            } else if (deleteTaskIndex > (taskList.getNumberOfTasks() - 1)) {
                throw new LinguoBotException("Invalid task index. Please input index ≤ " + (taskList.getNumberOfTasks()) + " to delete task.");
            }
            return new DeleteTask(deleteTaskIndex, taskList);
        } catch (NumberFormatException e) {
            throw new LinguoBotException("Please input an integer to delete a task.");
        }
    }

    private Command getMarkTask(String input, TaskList taskList) throws LinguoBotException {
        int MARK_START_INDEX = 5;
        if (input.length() <= MARK_START_INDEX) {
            throw new LinguoBotException("Invalid input format. Please provide a task index to mark.");
        }
        try {
            int markTaskIndex = Integer.parseInt(input.substring(MARK_START_INDEX)) - 1;
            if (taskList.getNumberOfTasks() == 0) {
                throw new LinguoBotException("Task list is empty.");
            } else if (markTaskIndex > (taskList.getNumberOfTasks() - 1)) {
                throw new LinguoBotException("Invalid task index. Please input index ≤ " + (taskList.getNumberOfTasks()) + " to mark task.");
            } else if (taskList.getTask(markTaskIndex).getStatusIcon().equals("X")) {
                throw new LinguoBotException("Task has already been marked.");
            }
            return new MarkTask(markTaskIndex, taskList);
        } catch (NumberFormatException e) {
            throw new LinguoBotException("Please input an integer to mark a task.");

        }
    }

    private Command getUnmarkTask(String input, TaskList taskList) throws LinguoBotException {
        int UNMARK_START_INDEX = 7;
        if (input.length() <= UNMARK_START_INDEX) {
            throw new LinguoBotException("Invalid input format. Please provide a task index to unmark.");
        }
        try {
            int unmarkTaskIndex = Integer.parseInt(input.substring(UNMARK_START_INDEX)) - 1;
            if (taskList.getNumberOfTasks() == 0) {
                throw new LinguoBotException("Task list is empty.");
            } else if (unmarkTaskIndex > (taskList.getNumberOfTasks() - 1)) {
                throw new LinguoBotException("Invalid task index. Please input index ≤ " + (taskList.getNumberOfTasks()) + " to unmark task.");
            } else if (taskList.getTask(unmarkTaskIndex).getStatusIcon().equals(" ")) {
                throw new LinguoBotException("Task has not been marked.");
            }
            return new UnmarkTask(unmarkTaskIndex, taskList);
        } catch (NumberFormatException e) {
            throw new LinguoBotException("Please input an integer to unmark a task.");
        }
    }

    private Command getFindTask(String input, TaskList taskList) throws LinguoBotException {
        int FIND_TASK_INDEX = 5;
        if (input.length() <= FIND_TASK_INDEX) {
            throw new LinguoBotException("Invalid input format. Please enter keyword after 'find'.");
        }
        String keyword = input.substring(FIND_TASK_INDEX);
        return new FindTask(keyword, taskList);
    }
}