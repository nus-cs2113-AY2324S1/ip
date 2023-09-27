package Commands;

import exceptions.ZranErrorMessages;
import exceptions.ZranExceptions;
import taskmanagement.*;
import userinputs.Ui;

import java.util.ArrayList;

public class AddTaskCommand extends Commands {
    static String outputFormat = "    ____________________________________________________________\n" +
            "    %s\n    ____________________________________________________________ ";
    public AddTaskCommand(String input) {
        super(input);
    }

    @Override
    public void execute(ArrayList<Task> tasks, Ui ui, Storage storage) {
        try {
            Task task = createTaskFromInput(input);
            tasks.add(task);
            TaskList.echo(tasks, task, input);
        } catch (ZranExceptions e) {
            System.out.printf((outputFormat) + "%n", e.getMessage());
        }
    }

    private Task createTaskFromInput(String input) throws ZranExceptions {
        if (input.startsWith(Ui.TODO_TASK_COMMAND)) {
            return createTodoTask(input);
        } else if (input.startsWith(Ui.DEADLINE_TASK_COMMAND)) {
            return createDeadlineTask(input);
        } else if (input.startsWith(Ui.EVENT_TASK_COMMAND)) {
            return createEventTask(input);
        } else {
            throw new ZranExceptions(ZranErrorMessages.UNRECOGNISED_COMMAND.message);
        }
    }

    private Task createTodoTask(String input) throws ZranExceptions {
        int todoIndex = input.indexOf(Ui.TODO_TASK_COMMAND);
        String description = input.substring(todoIndex + Ui.TODO_TASK_COMMAND.length()).trim();
        if (description.isEmpty()) {
            throw new ZranExceptions(ZranErrorMessages.INVALID_TASK_DESCRIPTION.message);
        }
        return new ToDos(description);
    }

    private Task createDeadlineTask(String input) throws ZranExceptions {
        int byIndex = input.indexOf(Ui.DEADLINE_DATE_COMMAND);
        if (byIndex == -1) {
            throw new ZranExceptions(ZranErrorMessages.INVALID_DEADLINE_FORMAT.message);
        }
        String description = input.substring(Ui.DEADLINE_TASK_COMMAND.length(), byIndex).trim();
        if (description.isEmpty()) {
            throw new ZranExceptions(ZranErrorMessages.INVALID_TASK_DESCRIPTION.message);
        }
        String by = input.substring(byIndex + Ui.DEADLINE_DATE_COMMAND.length()).trim();
        if (by.isEmpty()) {
            throw new ZranExceptions(ZranErrorMessages.EMPTY_DEADLINE.message);
        }
        return new Deadline(description, by);
    }

    private Task createEventTask(String input) throws ZranExceptions {
        int fromIndex = input.indexOf(Ui.EVENT_TASK_START);
        int toIndex = input.indexOf(Ui.EVENT_TASK_END);
        if (fromIndex == -1 || toIndex == -1) {
            throw new ZranExceptions(ZranErrorMessages.INVALID_EVENT_FORMAT.message);
        }
        String description = input.substring(Ui.EVENT_TASK_COMMAND.length(), fromIndex).trim();
        if (description.isEmpty()) {
            throw new ZranExceptions(ZranErrorMessages.INVALID_TASK_DESCRIPTION.message);
        }
        String from = input.substring(fromIndex + Ui.EVENT_TASK_START.length(), toIndex).trim();
        String to = input.substring(toIndex + Ui.EVENT_TASK_END.length()).trim();
        if (from.isEmpty() || to.isEmpty()) {
            throw new ZranExceptions(ZranErrorMessages.EMPTY_EVENT_DURATION.message);
        }
        return new Event(description, from, to);
    }
}

