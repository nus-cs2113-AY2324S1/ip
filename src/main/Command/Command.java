package Command;

import java.time.format.DateTimeParseException;

import Parser.Parser;
import Storage.Storage;
import TaskList.TaskList;
import Ui.Ui;

public class Command extends Parser {

    private boolean isExit;
    public Command() {
        super();
        this.isExit = false;
    }

    public String getCommand() {
        return command;
    }

    public boolean isExit() {
        return isExit;
    }

    public void setCommand(String command) {
        super.command = command;
    }

    /**
     * Executes the commands and prints out the output.
     * 
     * @param storage Storage object to store the data of the task list. 
     * @param ui Ui object to print UI messages to the console.
     * @param taskList TaskList object where task list operation happens.
     */
    public void execute(Storage storage, Ui ui, TaskList taskList) {
        ui.printLineDivider();
        switch (command) {
        case "list":
            ui.printList(taskList);
            break;
        case "bye":
            isExit = true;
            return;
        case "todo":
            try {
                setToDoArguments();
                ui.printTask(taskList.addToDoToList(getDescription()), false);
            } catch (IllegalArgumentException exception) {
                ui.printToDoUsage();
            }
            break;
        case "event":
            try {
                setEventArguments();
                ui.printTask(taskList.addEventToList(getDescription(), getFrom(), getTo()), false);
                ui.printListLength(taskList);
            } catch(IllegalArgumentException exception) {
                ui.printEventUsage();
            } catch(ArrayIndexOutOfBoundsException exception) {
                ui.printEventUsage();
            } catch(DateTimeParseException exception) {
                ui.printEventUsage();
            }
            break;
        case "deadline":
            try {
                setDeadlineArguments();
                ui.printTask(taskList.addDeadlineToList(getDescription(), getBy()), false);
                ui.printListLength(taskList);
            } catch(IllegalArgumentException exception) {
                ui.printDeadlineUsage();
            } catch(ArrayIndexOutOfBoundsException exception) {
                ui.printDeadlineUsage();
            } catch(DateTimeParseException exception) {
                ui.printDeadlineUsage();
            }
            break;
        case "unmark":
            try {
                ui.printMarked(taskList.markList(getIndex(), false));
            } catch (IndexOutOfBoundsException exception) {
                ui.printUnmarkUsage();
            } catch (NumberFormatException exception) {
                ui.printUnmarkUsage();
            }
            break;
        case "mark":
            try {
                ui.printMarked(taskList.markList(getIndex(), true));
            } catch (IndexOutOfBoundsException exception) {
                ui.printMarkUsage();
            } catch (NumberFormatException exeption) {
                ui.printMarkUsage();
            }
            break;
        case "delete":
            try {
                ui.printTask(taskList.deleteTaskFromList(getIndex()), true);
                ui.printListLength(taskList);
            } catch (IndexOutOfBoundsException exception) {
                ui.printDeleteUsage();
            } catch (IllegalArgumentException exception) {
                ui.printDeleteUsage();
            }
            break;
        case "find":
            try {
                setFindArguments();
                ui.printFindList(taskList, getKeyword());
            } catch (IllegalArgumentException exception) {
                ui.printFindUsage();
            }
            break;
        default:
            ui.printInvalidMessage();
        }
        ui.printLineDivider();
        storage.refreshData(taskList);
    }
}