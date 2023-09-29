package commands;

import exceptions.UserInputValidation;
import exceptions.ZranErrorMessages;
import exceptions.ZranExceptions;
import taskmanagement.*;
import userinputs.Ui;

import java.time.LocalDate;
import java.util.ArrayList;

public class AddTaskCommand extends Commands {
    public AddTaskCommand(String input) {
        super(input);
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        addTask(tasks, ui);
    }

    private void addTask(TaskList tasks, Ui ui) {
        try {
            Task task = createTaskFromInput(input, tasks);
            tasks.listItems.add(task);
            Ui.echo(tasks.listItems, task, input);
        } catch (ZranExceptions e) {
            ui.showError(e.getMessage());
        }
    }

    private Task createTaskFromInput(String input, TaskList tasks) throws ZranExceptions {
        String command = extractCommand(input);
        switch (command) {
        case Commands.TODO_TASK_COMMAND:
            return createTodoTask(input);
        case Commands.DEADLINE_TASK_COMMAND:
            return createDeadlineTask(input);
        case Commands.EVENT_TASK_COMMAND:
            return createEventTask(input);
        default:
            throw new ZranExceptions(ZranErrorMessages.UNRECOGNISED_COMMAND.message);
        }
    }

    private String extractCommand(String input) {
        int endIndex = input.indexOf(" ");
        if (endIndex == -1) {
            endIndex = input.length();
        }
        return input.substring(0, endIndex);
    }

    private Task createTodoTask(String input) throws ZranExceptions {
        String description = UserInputValidation.validateAddTodoCommand(input);
        return new ToDos(description);
    }

    private Task createDeadlineTask(String input) throws ZranExceptions {
        String[] taskInfo = UserInputValidation.validateAddDeadlineCommand(input);
        LocalDate by = UserInputValidation.validateDate(taskInfo[1]);
        String description = UserInputValidation.validateDeadlineDescription(input);
        return new Deadline(description, by);
    }

    private Task createEventTask(String input) throws ZranExceptions {
        String[] taskInfo = UserInputValidation.validateAddEventCommand(input);
        LocalDate from = UserInputValidation.validateDate(taskInfo[1]);
        LocalDate to = UserInputValidation.validateDate(taskInfo[2]);
        String description = UserInputValidation.validateEventDescription(input);
        return new Event(description, from, to);
    }

}

