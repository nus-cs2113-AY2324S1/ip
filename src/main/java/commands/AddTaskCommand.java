package commands;

import exceptions.UserInputValidation;
import exceptions.ZranErrorMessages;
import exceptions.ZranExceptions;
import taskmanagement.*;
import userinputs.Ui;

import java.time.LocalDate;
import java.util.ArrayList;

/**
 * Add a task to the task list in the Zran application.
 * Extends the abstract Commands class.
 */
public class AddTaskCommand extends Commands {
    /**
     * Constructs an AddTaskCommand instance with the given input.
     *
     * @param input User's input into the application.
     */
    public AddTaskCommand(String input) {
        super(input);
    }

    /**
     * Executes the add task command by calling the 'addTask' function.
     *
     * @param tasks   The task list of class 'TaskList' where the task is to be added.
     * @param ui      The ui component of class 'UI' for displaying messages.
     * @param storage The storage component of class 'Storage' for saving task data.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        addTask(tasks, ui);
    }

    /**
     * Adds a task to the task list based on the user's input by calling
     * the 'createTaskFromInput' function.
     *
     * @param tasks The task list of class 'TaskList' where the task is to be added.
     * @param ui    The ui component of class 'UI' for displaying messages.
     */
    private void addTask(TaskList tasks, Ui ui) {
        try {
            Task task = createTaskFromInput(input);
            tasks.listItems.add(task);
            Ui.echo(tasks.listItems, task, input);
        } catch (ZranExceptions e) {
            ui.showError(e.getMessage());
        }
    }

    /**
     * Creates a task object based on the user's input and command type.
     *
     * @param input User's input into the application.
     * @return A Task object of the created task.
     * @throws ZranExceptions If an error occurs during task creation.
     */
    private Task createTaskFromInput(String input) throws ZranExceptions {
        if (input.toLowerCase().startsWith(Commands.TODO_TASK_COMMAND)) {
            return createTodoTask(input);
        } else if (input.toLowerCase().startsWith(Commands.DEADLINE_TASK_COMMAND)) {
            return createDeadlineTask(input);
        } else if (input.toLowerCase().startsWith(Commands.EVENT_TASK_COMMAND)){
            return createEventTask(input);
        } else {
            throw new ZranExceptions(ZranErrorMessages.UNRECOGNISED_COMMAND.message);
        }
    }

    /**
     * Generates a 'ToDos' task object based on user input.
     * This function validates user inputs, ensuring they are in the correct format.
     *
     *
     * @param input User's input provided to the application.
     * @return An instance of the 'ToDos' class.
     * @throws ZranExceptions If an error arises during the creation of a 'ToDos' object.
     */
    private Task createTodoTask(String input) throws ZranExceptions {
        String description = UserInputValidation.validateAddTodoCommand(input);
        return new ToDos(description);
    }

    /**
     * Generates a 'Deadline' task object based on user input.
     * Validates the user's input, ensuring it is in the correct format.
     *
     * @param input User's input provided to the application.
     * @return An instance of the 'Deadline' class.
     * @throws ZranExceptions If an error occurs during the creation of a 'Deadline' object.
     */
    private Task createDeadlineTask(String input) throws ZranExceptions {
        String[] taskInfo = UserInputValidation.validateAddDeadlineCommand(input);
        String description = UserInputValidation.validateDeadlineDescription(input);
        String by;
        try {
            by = UserInputValidation.validateDate(taskInfo[1]).toString();
        } catch (ZranExceptions e){
            System.out.println(e.getMessage());
            by = taskInfo[1];
        }

        return new Deadline(description, by);
    }

    /**
     * Produces an 'Event' task object based on user input.
     * Validates the user's input, ensuring it is in the correct format.
     *
     * @param input User's input provided to the application.
     * @return An instance of the 'Event' class.
     * @throws ZranExceptions If an error occurs during the creation of an 'Event' object.
     */
    private Task createEventTask(String input) throws ZranExceptions {
        String[] taskInfo = UserInputValidation.validateAddEventCommand(input);
        String from;
        String to;
        String description = UserInputValidation.validateEventDescription(input);
        try {
            from = UserInputValidation.validateDate(taskInfo[1]).toString();
            to = UserInputValidation.validateDate(taskInfo[2]).toString();
        } catch (ZranExceptions e){
            System.out.println(e.getMessage());
            from = taskInfo[1];
            to = taskInfo[2];
        }
        return new Event(description, from, to);
    }

}

