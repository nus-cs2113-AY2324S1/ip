package duke;

import duke.exceptions.InvalidCommandException;
import duke.exceptions.InvalidTimeSpanException;

import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;

/**
 * Executes the commands provided by the user.
 */
public class Command {

    private String command;
    private String argument;

    /**
     * Constructor of Command.
     * @parm command Command to be executed.
     * @parm argument Arguments required for the specific command.
     * */
    public Command(String command, String argument) {
        this.command = command;
        this.argument = argument;
    }

    /**
     * Executes the given command.
     * @parm tasks TaskList object containing the list of tasks.
     * @parm ui Ui object to interact with the user.
     * @return TaskList object containing the list of tasks with the applied modifications.
     * @throws InvalidCommandException If the command is not included in the program or if there is a typo.
     * */
    public TaskList executeCommand(TaskList tasks, Ui ui) throws InvalidCommandException {
        switch (command) {
        case "list":
            ui.printList(tasks);
            break;
        case "find":
            findKeyword(tasks, argument, ui);
            break;
        case "mark":
            tasks = editTask(argument, true, tasks, ui);
            break;
        case "unmark":
            tasks = editTask(argument, false, tasks, ui);
            break;
        case "todo":
            tasks = addToDo(argument, tasks, ui);
            break;
        case "deadline":
            tasks = addDeadline(argument, tasks, ui);
            break;
        case "event":
            tasks = addEvent(argument, tasks, ui);
            break;
        case "delete":
            tasks = deleteTask(argument, tasks, ui);
            break;
        case "bye":
            break;
        default:
            throw new InvalidCommandException();
        }
        return tasks;
    }

    public void findKeyword(TaskList tasks, String keyword, Ui ui) {
        if (keyword == null || keyword.isEmpty()){
            ui.printList(tasks);
            return;
        }

        ArrayList<Task> matchingTasks = new ArrayList<Task>();
        for (Task task : tasks.getTasks()) {
            if (task.getDescription().contains(keyword)) {
                matchingTasks.add(task);
            }
        }

        ui.printKeywordSearchResult(matchingTasks);
    }

    /**
     * Marks or unmarks a given task.
     * If the index is out of bounds or if the argument is not a number, it will print an error message.
     * @parm argument Index of the task to be edited.
     * @parm done New status of the task.
     * @parm tasks TaskList object containing the list of tasks.
     * @parm ui Ui object to interact with the user.
     * @return TaskList object containing the list of tasks with the applied mark modifications.
     * */
    public TaskList editTask(String argument, boolean done, TaskList tasks, Ui ui) {
        try {
            int taskIndex = Integer.parseInt(argument);
            tasks.getTasks().get(taskIndex - 1).setDone(done);
            ui.printMark(done, tasks, taskIndex);
        } catch (IndexOutOfBoundsException | NumberFormatException e){
            ui.printInvalidTaskIdMessage();
        }
        return tasks;
    }

    /**
     * Adds a new Todo task with the description specified in argument.
     * If the description is empty, it will print an error message.
     * @parm argument Description of the task.
     * @parm tasks TaskList object containing the list of tasks.
     * @parm ui Ui object to interact with the user.
     * @return TaskList object containing the list of tasks with the new Todo.
     * @see Todo
     * */
    public TaskList addToDo(String argument, TaskList tasks, Ui ui) {
        if (argument == null || argument.isEmpty()) {
            ui.printEmptyTodoMessage();
        }
        Task todo = new Todo(argument);
        tasks.add(todo);
        ui.printTaskAddedMessage(todo, tasks.getTasks());
        return tasks;
    }

    /**
     * Adds a new Deadline task with the description and due date specified in argument.
     * If the description or due date is empty, it will print an error message.
     * @parm argument Description and due date of the task.
     * @parm tasks TaskList object containing the list of tasks.
     * @parm ui Ui object to interact with the user.
     * @return TaskList object containing the list of tasks with the newly added deadline.
     * @see Deadline
     * */
    public TaskList addDeadline(String argument, TaskList tasks, Ui ui) {
        try {
            String dueDateStr = argument.split(" /by ")[1];
            String description = argument.split(" /by ")[0];
            LocalDateTime dueDate = LocalDateTime.parse(dueDateStr.replace(" ", "T"));

            Task deadline = new Deadline(description, dueDate);
            tasks.add(deadline);
            ui.printTaskAddedMessage(deadline, tasks.getTasks());

        } catch (ArrayIndexOutOfBoundsException e) {
            ui.printInvalidDeadlineMessage();
        } catch (NullPointerException e) {
            ui.printEmptyDeadlineMessage();
        } catch (DateTimeParseException e) {
            ui.printInvalidDateTimeMessage();
        }
        return tasks;
    }

    /**
     * Adds a new Event task with the description, start date and end date specified in argument.
     * If the description, start date or end date is empty, it will print an error message.
     * @parm argument Description, start date and end date of the task.
     * @parm tasks TaskList object containing the list of tasks.
     * @parm ui Ui object to interact with the user.
     * @return TaskList object containing the list of tasks with the newly added Event.
     * @see Event
     * */
    public TaskList addEvent(String argument, TaskList tasks, Ui ui) {
        try {
            String description = argument.split(" /from ")[0];
            String startDateStr = argument.split(" /from ")[1].split(" /to ")[0];
            String endDateStr = argument.split(" /from ")[1].split(" /to ")[1];
            LocalDateTime startDate = LocalDateTime.parse(startDateStr.replace(" ", "T"));
            LocalDateTime endDate = LocalDateTime.parse(endDateStr.replace(" ", "T"));

            if (startDate.isAfter(endDate)) {
                throw new InvalidTimeSpanException();
            }

            Task event = new Event(description, startDate, endDate);
            tasks.add(event);
            ui.printTaskAddedMessage(event, tasks.getTasks());

        } catch (ArrayIndexOutOfBoundsException e) {
            ui.printInvalidEventMessage();
        } catch (NullPointerException e) {
            ui.printEmptyEventMessage();
        } catch (DateTimeParseException e) {
            ui.printInvalidDateTimeMessage();
        } catch(InvalidTimeSpanException e) {
            e.printErrorMessage();
        }
        return tasks;
    }

    /**
     * Deletes a task from the list of tasks based on the specified position within the list starting at index 1.
     * If the index is out of bounds or if the argument is not a number, it will print an error message.
     * @parm argument Index of the task to be deleted (>= 1).
     * @parm tasks TaskList object containing the list of tasks.
     * @parm ui Ui object to interact with the user.
     * @return TaskList object containing the updated list of tasks.
     * */
    public TaskList deleteTask(String argument, TaskList tasks, Ui ui) {
        try {
            int index = Integer.parseInt(argument);
            Task task = tasks.getTasks().get(index - 1);
            tasks.remove(index - 1);
            ui.printTaskRemovedMessage(task, tasks);
        } catch (IndexOutOfBoundsException | NumberFormatException e) {
            ui.printInvalidTaskIdMessage();
        }
        return tasks;
    }
}
