import java.util.ArrayList;

/**
 * Execute commands from the user.
 */
public class Command {
    private String command;
    private String arg;
    private boolean isExit;

    /**
     * Constructor of a Command
     *
     * @param command Command to be executed
     * @param arg Arguments of the given command
     */
    public Command(String command, String arg) {
        this.command = command;
        this.arg = arg;
        this.isExit = false;
    }

    public void setExit(boolean exit) {
        isExit = exit;
    }

    public boolean isExit() {
        return isExit;
    }

    /**
     * Execute the command given by the user
     * Each command is handled by its corresponding method in {@code TaskList}. If there
     * is any changes to the list of tasks, it will be saved into the file through {@code Storage}.
     * Relevant information will be shown to the user through corresponding methods in {@code Ui}.
     * A custom exceptions will be thrown if an invalid command is received or an invalid argument of
     * the respective command.
     *
     * @param taskList TaskList object that contains a list of Tasks
     * @param ui User interface object that handles the printing of information
     * @param storage Storage object that saves the updated list into filepath
     * @throws DukeException Custom Exception with customer error message
     */
    public void execute(TaskList taskList, Ui ui, Storage storage) throws DukeException{
        switch(this.command) {
        case "bye":
            setExit(true);
            ui.showByeMessage();
            break;
        case "list":
            ui.showTasks(taskList);
            break;
        case "mark":
            try {
                taskList.markTask(this.arg, true);
                storage.save(taskList);
                ui.showMark(taskList, this.arg, true);
            } catch (DukeException e) {
                ui.showError(e);
            }
            break;
        case "unmark":
            try {
                taskList.markTask(this.arg, false);
                storage.save(taskList);
                ui.showMark(taskList, this.arg, false);
            } catch (DukeException e) {
                ui.showError(e);
            }
            break;
        case "todo":
            try {
                taskList.addTodo(this.arg);
                storage.save(taskList);
                ui.showAddTask(taskList);
            } catch (DukeException e) {
                ui.showError(e);
            }
            break;
        case "deadline":
            try {
                taskList.addDeadline(this.arg);
                storage.save(taskList);
                ui.showAddTask(taskList);
            } catch (DukeException e) {
                ui.showError(e);
            }
            break;
        case "event":
            try {
                taskList.addEvent(this.arg);
                storage.save(taskList);
                ui.showAddTask(taskList);
            } catch (DukeException e) {
                ui.showError(e);
            }
            break;
        case "delete":
            try {
                Task removedTask = taskList.deleteTask(this.arg);
                storage.save(taskList);
                ui.showDeleteTask(taskList, removedTask);
            } catch (DukeException e) {
                ui.showError(e);
            }
            break;
        case "find":
            try {
                ArrayList<Task> matchingTasks = taskList.find(this.arg);
                ui.showFind(matchingTasks);
            } catch (DukeException e) {
                ui.showError(e);
            }
            break;
        default: // Unknown command exception
            throw new DukeException("Invalid input, please try again");
        }
    }
}
