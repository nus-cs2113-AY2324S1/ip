package duke;

import duke.exceptions.InvalidCommandException;
import duke.exceptions.InvalidTimeSpanException;

import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;

public class Command {

    private String command;
    private String argument;

    public Command(String command, String argument) {
        this.command = command;
        this.argument = argument;
    }

    public TaskList executeCommand(TaskList tasks, Ui ui) throws InvalidCommandException {
        switch (command) {
        case "list":
            ui.printList(tasks);
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

    public TaskList editTask(String argument, boolean done, TaskList tasks, Ui ui){
        try {
            int index = Integer.parseInt(argument);
            tasks.getTasks().get(index - 1).setDone(done);
            ui.printMark(done, tasks, index);
        } catch (IndexOutOfBoundsException | NumberFormatException e){
            ui.printInvalidTaskIdMessage();
        }
        return tasks;
    }

    public TaskList addToDo(String argument, TaskList tasks, Ui ui){
        if(argument==null || argument.isEmpty()){
            ui.printEmptyTodoMessage();
        }
        Task todo = new Todo(argument);
        tasks.add(todo);
        ui.printTaskAddedMessage(todo, tasks.getTasks());
        return tasks;
    }

    public TaskList addDeadline(String argument, TaskList tasks, Ui ui){
        try {
            LocalDateTime dueDate = LocalDateTime.parse(argument.split(" /by ")[1].replace(" ", "T"));
            String description = argument.split(" /by ")[0];
            Task deadline = new Deadline(description, dueDate);
            tasks.add(deadline);
            ui.printTaskAddedMessage(deadline, tasks.getTasks());
        } catch (ArrayIndexOutOfBoundsException e){
            ui.printInvalidDeadlineMessage();
        } catch (NullPointerException e){
            ui.printEmptyDeadlineMessage();
        } catch (DateTimeParseException e){
            ui.printInvalidDateTimeMessage();
        }
        return tasks;
    }

    public TaskList addEvent(String argument, TaskList tasks, Ui ui){
        try {
            String description = argument.split(" /from ")[0];
            LocalDateTime startDate = LocalDateTime.parse(argument.split(" /from ")[1].split(" /to ")[0].replace(" ", "T"));
            LocalDateTime endDate = LocalDateTime.parse(argument.split(" /from ")[1].split(" /to ")[1].replace(" ", "T"));
            if(startDate.isAfter(endDate)){
                throw new InvalidTimeSpanException();
            }
            Task event = new Event(description, startDate, endDate);
            tasks.add(event);
            ui.printTaskAddedMessage(event, tasks.getTasks());
        } catch (ArrayIndexOutOfBoundsException e){
            ui.printInvalidEventMessage();
        } catch (NullPointerException e){
            ui.printEmptyEventMessage();
        } catch (DateTimeParseException e){
            ui.printInvalidDateTimeMessage();
        } catch(InvalidTimeSpanException e){
            e.printErrorMessage();
        }
        return tasks;
    }

    public TaskList deleteTask(String argument, TaskList tasks, Ui ui) {
        try {
            int index = Integer.parseInt(argument);
            Task task = tasks.getTasks().get(index - 1);
            tasks.remove(index - 1);
            ui.printTaskRemovedMessage(task, tasks);
        } catch (IndexOutOfBoundsException | NumberFormatException e){
            ui.printInvalidTaskIdMessage();
        }
        return tasks;
    }

}
