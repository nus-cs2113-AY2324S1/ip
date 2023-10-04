package tasklist;

import java.util.ArrayList;
import java.util.Scanner;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import skippy.SkippyUi;
import tasks.Deadline;
import tasks.Event;
import tasks.Task;
import tasks.ToDo;


/**
 *
 * The manager of the task list - adding, marking or unmarking tasks,
 * deleting a task or finding a keyword in the task list.
 */
public class TaskList {

    private final String DELIMITER = "\\|";
    private final String DEADLINE_DELIMITER = "/by";
    private final String EVENT_START = "/from";
    private final String EVENT_END = "/to";
    private final boolean WITHOUT_SCANNER = false;
    private ArrayList<Task> tasks = new ArrayList<>();

    SkippyUi ui = new SkippyUi(WITHOUT_SCANNER);

    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    public void addTask(Task task) {
        tasks.add(task);
    }

    public ArrayList<Task> getTasks() {
        return tasks;
    }

    public void markTaskDone(int index) {
        if (isValidIndex(index)) {
            Task task = tasks.get(index - 1);
            task.setDone(true);
        }
    }

    public void unmarkTask(int index) {
        if (isValidIndex(index)) {
            Task task = tasks.get(index - 1);
            task.setDone(false);
        }
    }

    private boolean isValidIndex(int index) {
        return index >= 1 && index <= tasks.size();
    }

    private boolean hasBlankArgument(String input, String type) {
        if (input.isEmpty()) {
            ui.printBlankArgumentError(type);
            return true;
        }
        return false;
    }

    /**
     * Returns true or false if there is any missing input.
     * Calls print function to print missing message.
     *
     * @param index index of the missing keyword
     * @param type  type of missing keyword
     * @return true or false
     */
    private boolean hasMissingInput(int index, String type) {
        if (index == -1) {
            ui.printMissingKeyword(type);
            return true;
        }
        return false;
    }

    /**
     * Method for when user inputs todo.
     *
     * @param input The string of input that the user typed in
     */
    public void addToDoTask(String input) {
        try {
            String todoDescription = input.substring(5);
            ToDo todo = new ToDo(todoDescription);
            addTask(todo);
            ui.printAddedTask(todo, this);
        } catch (IndexOutOfBoundsException e) {
            ui.printToDoTaskException();
        }
    }

    /**
     * Method for when user inputs deadline.
     *
     * @param input The string of input that the user typed in
     */
    public void addDeadline(String input) {
        int byIndex = input.indexOf(DEADLINE_DELIMITER);
        if (hasMissingInput(byIndex, DEADLINE_DELIMITER)) {
            return;
        }

        String deadlineDescription = input.substring(9, byIndex).trim();
        if (hasBlankArgument(deadlineDescription, "Deadline Name")) {
            return;
        }

        String by = input.substring(byIndex + 3).trim();
        if (hasBlankArgument(by, DEADLINE_DELIMITER)) {
            return;
        }

        Deadline deadline = new Deadline(deadlineDescription, by);
        addTask(deadline);
        ui.printAddedTask(deadline, this);
    }

    /**
     * Method for when user inputs event.
     *
     * @param input The string of input that the user typed in
     */
    public void addEvent(String input) {
        int fromIndex = input.indexOf(EVENT_START);
        int toIndex = input.indexOf(EVENT_END);
        if (hasMissingInput(fromIndex, EVENT_START)
                || hasMissingInput(toIndex, EVENT_END)) {
            return;
        }

        String eventDescription = input.substring(6, fromIndex).trim();
        if (hasBlankArgument(eventDescription, "Event Name")) {
            return;
        }

        String from = input.substring(fromIndex + 6, toIndex).trim();
        if (hasBlankArgument(from, EVENT_START)) {
            return;
        }

        String to = input.substring(toIndex + 3).trim();
        if (hasBlankArgument(to, EVENT_END)) {
            return;
        }

        Event event = new Event(eventDescription, from, to);
        addTask(event);
        ui.printAddedTask(event, this);
    }

    /**
     * Method to mark a specified task as done
     *
     * @param inputWords The array of split input by user
     */
    public void markAsDone(String[] inputWords) {
        try {
            int taskIndex = Integer.parseInt(inputWords[1]);
            markTaskDone(taskIndex);
            ui.printMarkedTask(taskIndex, this);
        } catch (NumberFormatException e) {
            System.out.println("Invalid input. Please use 'mark + <number>'.");
            ui.printLine();
        } catch (IndexOutOfBoundsException e) {
            ui.printLine();
            System.out.println("Please specify the task number");
            ui.printLine();
        }
    }

    /**
     * Method to unmark a specified task as done.
     *
     * @param inputWords The array of split input by user
     */
    public void unmark(String[] inputWords) {
        try {
            int taskIndex = Integer.parseInt(inputWords[1]);
            unmarkTask(taskIndex);
            ui.printUnmarkedTask(taskIndex, this);
        } catch (NumberFormatException e) {
            System.out.println("Invalid input. Please use 'unmark + <number>'.");
            ui.printLine();
        } catch (IndexOutOfBoundsException e) {
            ui.printLine();
            System.out.println("Please specify the task number");
            ui.printLine();
        }
    }

    /**
     * Method to delete a task.
     *
     * @param inputWords The array of split input by user
     */
    public void deleteTask(String[] inputWords) {
        try {
            int taskIndex = Integer.parseInt(inputWords[1]);
            if (isValidIndex(taskIndex)) {
                Task removedTask = tasks.remove(taskIndex - 1);
                ui.printRemovedTask(removedTask, this);
            } else {
                System.out.println("Invalid task index.");
                ui.printLine();
            }
        } catch (NumberFormatException e) {
            ui.printDeleteTaskException();
        }
    }

    /**
     * Converts tasks into one string and append to the string builder which will
     * be written into the save file.
     *
     * @return returns the string of tasks in string format
     */
    public String toString() {
        StringBuilder saveString = new StringBuilder();
        for (Task task : tasks) {
            saveString.append(task.toSaveString());
            saveString.append(System.lineSeparator());
        }
        return saveString.toString();
    }

    /**
     * Instantiates a tasklist containing data in the file. Uses the parseTasks() method
     * to convert from the file to usable tasklist data.
     *
     * @param s scanner containing the lines in the savefile.
     */
    public TaskList(Scanner s) {
        while (s.hasNextLine()) {
            String line = s.nextLine();
            String[] args = line.split(DELIMITER);
            parseTasks(args);
        }
    }

    private void parseTasks(String[] args) {
        switch (args[0]) {
        case "T":
            tasks.add(new ToDo(args[2]));
            tasks.get(tasks.size() - 1).setDone(args[1].equals("Y"));
            break;
        case "D":
            tasks.add(new Deadline(args[2], args[3]));
            tasks.get(tasks.size() - 1).setDone(args[1].equals("Y"));
            break;
        case "E":
            tasks.add(new Event(args[2], args[3], args[4]));
            tasks.get(tasks.size() - 1).setDone(args[1].equals("Y"));
            break;
        }
    }

    /**
     *Search the task list to find any task containing the given keyword
     *
     * @param inputWords The array of split input by user
     */
    public void findTask(String[] inputWords) {
        String input = inputWords.length == 2 ? inputWords[1] : "";
        if (input.isEmpty()) {
            ui.printBlankArgumentError("Keyword");
            return;
        }
        input.trim().split(" ");
        int taskNum = 0;
        boolean taskFound = false;
        for (Task task : tasks) {
            if (task.getName().contains(input)) {
                if (!taskFound) {
                    taskFound = true;
                    ui.printTaskFoundMessage(input);
                }
                ui.printTaskWithNumber(taskNum, task);
            }
            taskNum++;
        }
        if (!taskFound) {
            System.out.println("Sorry, there are no such tasks found.");
            ui.printLine();
            return;
        }
        ui.printLine();
    }
}