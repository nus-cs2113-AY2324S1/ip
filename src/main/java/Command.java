import java.util.ArrayList;

public class Command {

    private String command;
    private String arg;
    private boolean isExit;
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
        default:
            throw new DukeException("Invalid input, please try again");
        }
    }
}
