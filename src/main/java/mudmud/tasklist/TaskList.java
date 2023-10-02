package mudmud.tasklist;

import mudmud.exception.DukeTaskException;
import mudmud.parser.Parser;
import mudmud.task.Deadline;
import mudmud.task.Event;
import mudmud.task.Task;
import mudmud.task.Todo;
import mudmud.ui.TextUi;

import java.time.LocalDateTime;
import java.util.ArrayList;

import static java.util.stream.Collectors.toList;

/**
 * Represents a list of tasks and contains all tasks available in the program.
 */
public class TaskList {
    private final String TODO_DATA_TEMPLATE = "T | false | " ;
    private final String DEADLINE_DATA_TEMPLATE = "D | false | ";
    private final String EVENT_DATA_TEMPLATE = "E | false | ";
    private final String BY_KEYWORD = " /by ";
    private final String FROM_KEYWORD = " /from ";
    private final String TO_KEYWORD = " /to ";

    private TextUi ui;
    private ArrayList<Task> tasks;
    private Parser parser;
    private int tasksCount;

    /**
     * Creates a task list with saved tasks.
     *
     * @param tasks The saved tasks from previous session.
     */
    public TaskList(ArrayList<Task> tasks)  {
        ui = new TextUi();
        parser = new Parser();

        this.tasks = tasks;
        tasksCount = tasks.size();
    }

    public int getTasksCount() {
        return tasksCount;
    }

    /**
     * Gets a task from the list with a specified index.
     *
     * @param index The specified index.
     * @return The task with the specified index.
     */
    public Task getTask(int index) {
        return tasks.get(index);
    }

    /**
     * Gets the most recent added task from the list.
     *
     * @return The recent task.
     */
    public Task getRecentTask() {
        return tasks.get(tasksCount - 1);
    }

    /**
     * Marks a task with the specified index.
     *
     * @param input The selected index.
     */
    public void setMarkAsDone(String input) {
        int index = parser.parseIndex(input);

        tasks.get(index).markAsDone();
    }

    /**
     * Unmarks a task with the specified index.
     *
     * @param input The selected index.
     */
    public void setUnmarkAsDone(String input) {
        int index = parser.parseIndex(input);

        tasks.get(index).unmarkAsDone();
    }

    /**
     * Deletes a task with the given index in the list.
     *
     * @param input The selected index.
     */
    public void deleteTask(String input) {
        int index = parser.parseIndex(input);
        Task removedTask = tasks.remove(index);

        ui.printDeletedTask(removedTask);

        tasksCount--;

        ui.printNumOfTasks(tasksCount);
    }

    /**
     * Adds a todo into the task list.
     *
     * @param input The task input.
     * @return A data string that will be stored in the database.
     * @throws DukeTaskException If an input has an invalid format.
     */
    public String addTodo(String input) throws DukeTaskException {
        if (input.trim().isEmpty()) {
            throw new DukeTaskException();
        }

        tasks.add(new Todo(input.trim()));
        tasksCount++;

        return TODO_DATA_TEMPLATE + input.trim();
    }

    /**
     * Adds an event into the task list.
     *
     * @param input The task input.
     * @return A data string that will be stored in the database.
     * @throws DukeTaskException If an input has an invalid format.
     */
    public String addDeadline(String input) throws DukeTaskException {
        String[] parsedInput = parser.parseTask(input, BY_KEYWORD);

        if (!(parsedInput.length == 2)) {
            throw new DukeTaskException();
        }

        LocalDateTime by = parser.parseDateTime(parsedInput[1].trim());
        String description = parsedInput[0].trim();
        String byBeforeParse = parsedInput[1].trim();

        tasks.add(new Deadline(description, by));
        tasksCount++;

        return DEADLINE_DATA_TEMPLATE + description + " | "  + byBeforeParse;
    }

    /**
     * Checks the number of each event keywords in the input.
     *
     * @param input The task input.
     * @return Confirmation if the number of keywords are correct.
     */
    private boolean checkNumOfEventKeywords(String input) {
        int numOfFromKeyword = input.split(FROM_KEYWORD).length - 1;
        int numOfToKeyword = input.split(TO_KEYWORD).length - 1;

        return numOfFromKeyword == 1 && numOfToKeyword == 1;
    }

    /**
     * Checks the input's event keywords are in order.
     *
     * @param input The task input.
     * @return Confirmation if the input's keywords are in the right sequence.
     */
    private boolean checkPosOfEventKeywords(String input) {
        return input.indexOf(FROM_KEYWORD) < input.indexOf(TO_KEYWORD);
    }

    /**
     * Adds an event into the task list.
     *
     * @param input The task input.
     * @return A data string that will be stored in the database.
     * @throws DukeTaskException If an input has an invalid format.
     */
    public String addEvent(String input) throws DukeTaskException {
        if (!checkNumOfEventKeywords(input) || !checkPosOfEventKeywords(input)) {
            throw new DukeTaskException();
        }

        String regex = FROM_KEYWORD + "|" + TO_KEYWORD;
        String[] parsedInput = parser.parseTask(input, regex);

        LocalDateTime start = parser.parseDateTime(parsedInput[1].trim());
        LocalDateTime end = parser.parseDateTime(parsedInput[2].trim());
        String description = parsedInput[0].trim();
        String startBeforeParse = parsedInput[1].trim();
        String endBeforeParse = parsedInput[2].trim();

        tasks.add(new Event(description, start, end));

        String dataString = EVENT_DATA_TEMPLATE + description + " | "  + startBeforeParse
                + " | " + endBeforeParse;

        tasksCount++;
        return dataString;
    }

    /**
     * Filters out tasks that do not have their description matches with the keyword.
     *
     * @param keyword The selected keyword to match.
     * @return The filtered list.
     */
    public ArrayList<Task> filterTasks(String keyword) {
         ArrayList<Task> filteredList = (ArrayList<Task>) tasks.stream()
                 .filter((task) -> task.getDescription().contains(keyword))
                 .collect(toList());

         return filteredList;
    }
}
