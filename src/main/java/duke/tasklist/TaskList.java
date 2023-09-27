package duke.tasklist;

import duke.exception.DukeTaskException;
import duke.parser.TaskParser;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.Todo;
import duke.storage.Storage;
import duke.ui.TextUi;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

public class TaskList {
    private final String TODO_DATA_TEMPLATE = "T | false | " ;
    private final String DEADLINE_DATA_TEMPLATE = "D | false | ";
    private final String EVENT_DATA_TEMPLATE = "E | false | ";
    private final String BY_KEYWORD = " /by ";
    private final String FROM_KEYWORD = " /from ";
    private final String TO_KEYWORD = " /to ";

    private Storage storage;
    private TextUi ui;
    private ArrayList<Task> tasks;
    private TaskParser parser;
    private int tasksCount = 0;

    public TaskList()  {
        ui = new TextUi();
        storage = new Storage();
        parser = new TaskParser();

        try {
            tasks = storage.restoreSavedData();
        } catch (FileNotFoundException exception) {
            storage.handleFileNotFoundException();
            System.exit(-1);
        }

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

    public int setMarkAsDone(String input) throws IOException {
        int index = parser.parseIndex(input);

        storage.updateTaskDatabase(index, true);

        tasks.get(index).markAsDone();

        return index;
    }

    public int setUnmarkAsDone(String input) throws IOException {
        int index = parser.parseIndex(input);

        storage.updateTaskDatabase(index, false);

        tasks.get(index).unmarkAsDone();

        return index;
    }

    public void deleteTask(String input) throws IOException {
        int index = parser.parseIndex(input);
        Task removedTask = tasks.remove(index);

        storage.deleteTaskData(index);

        ui.printDeletedTask(removedTask);

        tasksCount--;

        ui.printNumOfTasks(tasksCount);
    }

    public boolean checkEmptyTodoInput(String input) {
        return input.trim().isEmpty();
    }

    public void addTodo(String input) throws DukeTaskException, IOException {
        if (checkEmptyTodoInput(input)) {
            throw new DukeTaskException();
        }

        tasks.add(new Todo(input.trim()));

        String dataString =  TODO_DATA_TEMPLATE + input.trim();
        storage.addNewData(dataString, tasksCount);

        tasksCount++;
    }

    public void addDeadline(String input) throws DukeTaskException, IOException {
        String[] parsedInput = parser.parseTask(input, BY_KEYWORD);

        if (!(parsedInput.length == 2)) {
            throw new DukeTaskException();
        }

        tasks.add(new Deadline(parsedInput[0].trim(), parsedInput[1].trim()));

        String dataString = DEADLINE_DATA_TEMPLATE + parsedInput[0].trim() + " | "  + parsedInput[1].trim();
        storage.addNewData(dataString, tasksCount);

        tasksCount++;
    }

    public boolean checkNumOfEventKeywords(String input) {
        int numOfFromKeyword = input.split(FROM_KEYWORD).length - 1;
        int numOfToKeyword = input.split(TO_KEYWORD).length - 1;

        return numOfFromKeyword == 1 && numOfToKeyword == 1;
    }

    public boolean checkPosOfEventKeywords(String input) {
        return input.indexOf(FROM_KEYWORD) < input.indexOf(TO_KEYWORD);
    }

    public void addEvent(String input) throws DukeTaskException, IOException {
        if (!checkNumOfEventKeywords(input) || !checkPosOfEventKeywords(input)) {
            throw new DukeTaskException();
        }

        String regex = FROM_KEYWORD + "|" + TO_KEYWORD;
        String[] parsedInput = parser.parseTask(input, regex);

        tasks.add(new Event(parsedInput[0].trim(), parsedInput[1].trim(), parsedInput[2].trim()));

        String dataString = EVENT_DATA_TEMPLATE + parsedInput[0].trim() + " | "  + parsedInput[1].trim()
                + " | " + parsedInput[2].trim();
        storage.addNewData(dataString, tasksCount);

        tasksCount++;
    }
}
