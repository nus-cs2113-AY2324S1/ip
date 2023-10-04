package jerry;

import jerry.task.Task;
import jerry.task.TaskList;
import jerry.commands.Command;
import jerry.commands.CommandResult;
import jerry.commands.ExitCommand;
import jerry.parser.Parser;
import jerry.ui.TextUi;
import jerry.storage.StorageFile;
import jerry.storage.StorageFile.InvalidStorageFilePathException;
import jerry.storage.StorageFile.StorageOperationException;

import java.util.List;
import java.util.Collections;
import java.util.Optional;

public class Main {

    private TaskList taskList;
    private List<Task> lastShownList = Collections.emptyList(); 
    private TextUi ui;
    private StorageFile storage;

    public static void main(String[] launchArgs) {
        new Main().run(launchArgs);
    }

    /** Runs the program until termination.  */
    public void run(String[] launchArgs) {
        start(launchArgs);
        runCommandLoopUntilExitCommand();
        exit();
    }

    /**
     * Sets up the required objects, and prints the welcome message.
     *
     */
    private void start(String[] launchArgs) {
        try {
            this.ui = new TextUi();
            this.storage = initializeStorage(launchArgs);
            this.taskList = storage.load();
            ui.showWelcomeMessage();
        } catch (InvalidStorageFilePathException | StorageOperationException e) {
            ui.showInitFailedMessage();
            exit();
            // throw new RuntimeException(e);
        }
    }

    /** Prints the Goodbye message and exits. */
    private void exit() {
        ui.showGoodbyeMessage();
        System.exit(0);
    }

    /** Reads the user command and executes it, until the user issues the exit command.  */
    private void runCommandLoopUntilExitCommand() {
        Command command;
        do {
            String userCommandText = ui.getUserCommand();
            command = new Parser().parseCommand(userCommandText);
            CommandResult result = executeCommand(command);
            recordResult(result);
            ui.showResultToUser(result);

        } while (!ExitCommand.isExit(command));
    }


    /**
     * Executes the command and returns the result.
     *
     * @param command user command
     * @return result of the command
     */
    private CommandResult executeCommand(Command command) {
        try {
            command.setData(taskList, lastShownList);
            CommandResult result = command.execute();
            storage.save(taskList);
            return result;
        } catch (Exception e) {
            ui.showToUser(e.getMessage());
            throw new RuntimeException(e);
        }
    }

    /** Updates the {@link #lastShownList} if the result contains a list of tasks. */
    private void recordResult(CommandResult result) {
        final Optional<List<Task>> taskList = result.getRelevantTasks();
        if (taskList.isPresent()) {
            lastShownList = taskList.get();
        }
    }

    /**
     * Creates the StorageFile object based on the user specified path (if any) or the default storage path.
     * @param launchArgs arguments supplied by the user at program launch
     * @throws InvalidStorageFilePathException if the target file path is incorrect.
     */
    private StorageFile initializeStorage(String[] launchArgs) throws InvalidStorageFilePathException {
        boolean isStorageFileSpecifiedByUser = launchArgs.length > 0;
        return isStorageFileSpecifiedByUser ? new StorageFile(launchArgs[0]) : new StorageFile();
    }
}
