package duke;

import duke.command.Command;
import duke.exception.DukeCommandException;
import duke.storage.Storage;
import duke.tasklist.TaskList;
import duke.ui.TextUi;
import duke.parser.Parser;

import java.util.Scanner;

public class Duke {

    private TextUi ui;
    private Storage storage;
    private TaskList tasks;
    private Parser parser;

//    public void executeCommand(String input) {
//        String[] parsedInput = input.split(" ", 2);
//        String command = parsedInput[0];
//        int selectedIndex = 0;
//        String dataString;
//        input = parsedInput.length == 1 ? " " : parsedInput[1].trim();
//
//        try {
//            switch (command) {
//            case "list":
//                ui.printTasks(tasks);
//                break;
//            case "mark":
//                selectedIndex = tasks.setMarkAsDone(input);
//                storage.updateTaskDatabase(selectedIndex, true);
//                ui.printModifiedTask(tasks.getTask(selectedIndex), true);
//                break;
//            case "unmark":
//                selectedIndex = tasks.setUnmarkAsDone(input);
//                storage.updateTaskDatabase(selectedIndex, false);
//                ui.printModifiedTask(tasks.getTask(selectedIndex), false);
//                break;
//            case "delete":
//                selectedIndex = tasks.deleteTask(input);
//                storage.deleteTaskData(selectedIndex);
//                break;
//            case "todo":
//                Command c = parser.parseCommand(input);
//                c.executeCommand(tasks, ui, storage);
//                break;
//            case "deadline":
//                dataString = tasks.addDeadline(input);
//                storage.addNewData(dataString, tasks.getTasksCount());
//                ui.printRecentTask(tasks);
//                break;
//            case "event":
//                dataString = tasks.addEvent(input);
//                storage.addNewData(dataString, tasks.getTasksCount());
//                ui.printRecentTask(tasks);
//                break;
//            case "bye":
//                ui.tellGoodbye();
//                System.exit(0);
//            default:
//                throw new DukeCommandException();
//            }
//        } catch (IndexOutOfBoundsException exception) {
//            ui.handleIndexOutOfBoundsException(tasks.getTasksCount());
//        } catch (NumberFormatException exception) {
//            ui.handleNumberFormatException(input);
//        } catch (DukeCommandException exception) {
//            exception.handleDukeCommandException(command);
//        } catch (DukeTaskException exception) {
//            exception.handleDukeTaskException(command, input);
//        } catch (IOException exception) {
//            ui.handleIOException(exception);
//        }
//    }

    private void setup() {
        ui = new TextUi();
        storage = new Storage();
        tasks = new TaskList(storage.restoreSavedData());
        parser = new Parser();
    }

    private void run() {
        String input;
        Scanner in = new Scanner(System.in);
        boolean isExit = false;

        ui.tellGreeting();
        while (!isExit) {
            input = ui.getInput(in);
            try {
                ui.showLine();
                Command command = parser.parseCommand(input);
                command.executeCommand(tasks, ui, storage);
                isExit = command.isExit();
            } catch (DukeCommandException exception) {
                exception.handleDukeCommandException(parser.parseCommandType(input)[0]);
            } finally {
                ui.showLine();
            }
        }
    }

    private void start() {
        setup();
        run();
    }

    public static void main(String[] args) {
        new Duke().start();
    }
}