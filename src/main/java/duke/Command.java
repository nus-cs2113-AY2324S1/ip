package duke;

/**
 * The {@code Command} class represents a user command that the application can execute.
 * Each {@code Command} object encapsulates a command type, represented by a command string,
 * and any necessary arguments to execute the command.
 * <p>
 * The {@code Command} class is responsible for executing the user command on a given {@code TaskList}.
 * The execution behavior is defined by the {@code execute} method, which interprets the command string
 * and performs the corresponding action on the task list. The class supports a variety of command types,
 * including adding tasks, deleting tasks, marking tasks as complete or incomplete, listing tasks,
 * and finding tasks, among others.
 * </p>
 * <p>
 * This class also holds a flag, {@code Exit}, to signal when the user has issued an exit command, allowing
 * the application to terminate its loop and exit gracefully.
 * </p>
 *
 * <ul>
 *     <li>{@link #execute(TaskList)} - Executes the command on the given {@code TaskList}.</li>
 *     <li>{@link #setExit()} - Sets the exit flag to true.</li>
 *     <li>{@link #isExit()} - Returns the current state of the exit flag.</li>
 * </ul>
 *
 * @see TaskList
 * @see DukeException
 *
 * @author  Ashok Balaji
 * @version 1.0
 * @since   2023-09-25
 */
public class Command {
    protected String COMMAND;
    protected String[] ARGUMENTS;
    protected boolean Exit;

    public Command(String command, String[] arguments){
        this.COMMAND = command;
        this.ARGUMENTS = arguments;
        this.Exit = false;
    }

    public void execute(TaskList TASKS) throws DukeException{
        switch (this.COMMAND) {
            case "find":
                TASKS.findTasksInList(this.ARGUMENTS);
                return;

            case "todo":
                TASKS.addTodoInList(this.ARGUMENTS);
                return;

            case "deadline":
                TASKS.addDeadlineInList(this.ARGUMENTS);
                return;

            case "event":
                TASKS.addEventInList(this.ARGUMENTS);
                return;

            case "list":
                TASKS.printList();
                return;

            case "mark":
                TASKS.markTaskComplete(this.ARGUMENTS);
                return;

            case "unmark":
                TASKS.markTaskIncomplete(this.ARGUMENTS);
                return;

            case "delete":
                TASKS.deleteTaskInList(this.ARGUMENTS);
                return;

            case "bye":
                setExit();
                return;

            default: // unknown command exception
                throw new DukeException("I don't know what that means...");
        }
    }

    public void setExit(){
        this.Exit = true;
    }

    public boolean isExit(){
        return this.Exit;
    }
}
