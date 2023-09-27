package duke;

import duke.exception.DukeCommandException;
import duke.exception.DukeTaskException;
import duke.storage.Storage;
import duke.tasklist.TaskList;
import duke.ui.TextUi;

import java.io.IOException;
import java.util.Scanner;

public class Duke {

    private static TextUi ui;
    private static Storage storage;
    private static TaskList tasks;

    public static void executeCommand(String input) {
        String[] parsedInput = input.split(" ", 2);
        String command = parsedInput[0];
        int selectedIndex = 0;
        input = parsedInput.length == 1 ? " " : parsedInput[1].trim();

        try {
            switch (command) {
            case "list":
                ui.printTasks(tasks);
                break;
            case "mark":
                selectedIndex = tasks.setMarkAsDone(input);
                ui.printModifiedTask(tasks.getTask(selectedIndex), true);
                break;
            case "unmark":
                selectedIndex = tasks.setUnmarkAsDone(input);
                ui.printModifiedTask(tasks.getTask(selectedIndex), true);
                break;
            case "delete":
                tasks.deleteTask(input);
                break;
            case "todo":
                tasks.addTodo(input);
                ui.printRecentTask(tasks);
                break;
            case "deadline":
                tasks.addDeadline(input);
                ui.printRecentTask(tasks);
                break;
            case "event":
                tasks.addEvent(input);
                ui.printRecentTask(tasks);
                break;
            case "bye":
                ui.tellGoodbye();
                System.exit(0);
            default:
                throw new DukeCommandException();
            }
        } catch (IndexOutOfBoundsException exception) {
            ui.handleIndexOutOfBoundsException(tasks.getTasksCount());
        } catch (NumberFormatException exception) {
            ui.handleNumberFormatException(input);
        } catch (DukeCommandException exception) {
            exception.handleDukeCommandException(command);
        } catch (DukeTaskException exception) {
            exception.handleDukeTaskException(command, input);
        } catch (IOException exception) {
            ui.handleIOException(exception);
        }
    }


    public static void main(String[] args) {
        String input;
        Scanner in = new Scanner(System.in);
        ui = new TextUi();

        tasks = new TaskList();

        ui = new TextUi();
        ui.tellGreeting();
        while (true) {
            input = ui.getInput(in);
            ui.showLine();
            executeCommand(input);
            ui.showLine();
        }
    }
}