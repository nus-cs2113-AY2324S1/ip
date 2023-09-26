import fredbot.Parser;
import fredbot.Storage;
import fredbot.TaskList;
import fredbot.Ui;
import fredbot.commands.Command;
import fredbot.error.*;
import fredbot.task.Deadline;
import fredbot.task.Event;
import fredbot.task.Task;
import fredbot.task.Todo;

import java.util.ArrayList;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class FredBot {
    public static final String INDENT = "    ";
    public static final String DIVIDER = "    ____________________________________________________________\n";
    public static final String TASK_LIST_MESSAGE = "Here are the tasks in your list\n";
    public static final String COMMAND_ERROR_MESSAGE = "☹ OOPS!!! I'm sorry, but I don't know what that means :-(";
    public static final String TODO_ERROR_MESSAGE = "☹ OOPS!!! The description of a todo cannot be empty.";
    public static final String DEADLINE_ERROR_MESSAGE = "☹ OOPS!!! The description of a deadline cannot be empty.";
    public static final String EVENT_ERROR_MESSAGE = "☹ OOPS!!! The description of a event cannot be empty.";
    public static final String MARK_ERROR_MESSAGE = "This task does not exist!";
    public static final String TASK_FILE_PATH = "./data/tasks.txt";
    public static final String WRITE_FILE_ERROR_MESSAGE = "Could not write to file. Exiting Application...";
    public static final String READ_FILE_ERROR_MESSAGE = "Could not read file. Exiting Application...";

    private final Storage storage;
    private TaskList tasks = new TaskList();
    private final Ui ui;
    public FredBot(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = storage.loadTasks();
        } catch (IOException e) {
            ui.showFatalError(e.getMessage());
            System.exit(0);
        }
    }
    public void run() {
        ui.showWelcome();
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                Command c = Parser.parseCommand(fullCommand);
                c.execute(tasks, storage, ui);
                isExit = c.isExit();
            } catch (FredBotException e) {
                ui.showError(e.getMessage());
            } catch (IOException e) {
                ui.showFatalError(e.getMessage());
                // printMessage(INDENT + WRITE_FILE_ERROR_MESSAGE);
                System.exit(0);
            }
        }
        ui.showGoodBye();
    }
    public static void printMessage(String message) {
        System.out.print(DIVIDER);
        System.out.println(message);
        System.out.println(DIVIDER);
    }
    public static void main(String[] args) {
        new FredBot(TASK_FILE_PATH).run();
    }
}
