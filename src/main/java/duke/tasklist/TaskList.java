package duke.tasklist;

import duke.exception.DukeTaskException;
import duke.parser.Parser;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.Todo;
import duke.ui.TextUi;

import java.io.IOException;
import java.util.ArrayList;

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
    private int tasksCount = 0;

    public TaskList(ArrayList<Task> tasks)  {
        ui = new TextUi();
        parser = new Parser();

        this.tasks = tasks;
        tasksCount = tasks.size();
    }

    public int getTasksCount() {
        return tasksCount;
    }

    public Task getTask(int index) {
        return tasks.get(index);
    }

    public Task getRecentTask() {
        return tasks.get(tasksCount - 1);
    }

    public int setMarkAsDone(String input) {
        int index = parser.parseIndex(input);

        tasks.get(index).markAsDone();

        return index;
    }

    public int setUnmarkAsDone(String input) {
        int index = parser.parseIndex(input);

        tasks.get(index).unmarkAsDone();

        return index;
    }

    public int deleteTask(String input) throws IOException {
        int index = parser.parseIndex(input);
        Task removedTask = tasks.remove(index);

        ui.printDeletedTask(removedTask);

        tasksCount--;

        ui.printNumOfTasks(tasksCount);
        return index;
    }

    public boolean checkEmptyTodoInput(String input) {
        return input.trim().isEmpty();
    }

    public String addTodo(String input) throws DukeTaskException {
        if (checkEmptyTodoInput(input)) {
            throw new DukeTaskException();
        }

        tasks.add(new Todo(input.trim()));
        tasksCount++;

        return TODO_DATA_TEMPLATE + input.trim();
    }

    public String addDeadline(String input) throws DukeTaskException {
        String[] parsedInput = parser.parseTask(input, BY_KEYWORD);

        if (!(parsedInput.length == 2)) {
            throw new DukeTaskException();
        }

        tasks.add(new Deadline(parsedInput[0].trim(), parsedInput[1].trim()));
        tasksCount++;

        return DEADLINE_DATA_TEMPLATE + parsedInput[0].trim() + " | "  + parsedInput[1].trim();
    }

    public boolean checkNumOfEventKeywords(String input) {
        int numOfFromKeyword = input.split(FROM_KEYWORD).length - 1;
        int numOfToKeyword = input.split(TO_KEYWORD).length - 1;

        return numOfFromKeyword == 1 && numOfToKeyword == 1;
    }

    public boolean checkPosOfEventKeywords(String input) {
        return input.indexOf(FROM_KEYWORD) < input.indexOf(TO_KEYWORD);
    }

    public String addEvent(String input) throws DukeTaskException, IOException {
        if (!checkNumOfEventKeywords(input) || !checkPosOfEventKeywords(input)) {
            throw new DukeTaskException();
        }

        String regex = FROM_KEYWORD + "|" + TO_KEYWORD;
        String[] parsedInput = parser.parseTask(input, regex);

        tasks.add(new Event(parsedInput[0].trim(), parsedInput[1].trim(), parsedInput[2].trim()));

        String dataString = EVENT_DATA_TEMPLATE + parsedInput[0].trim() + " | "  + parsedInput[1].trim()
                + " | " + parsedInput[2].trim();

        tasksCount++;
        return dataString;
    }
}
