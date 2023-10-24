package duke;

import duke.commands.*;
import duke.inputProcess.Parser;
import duke.inputProcess.TaskList;

/**
 * The `CommandExecutor` class is responsible for executing various commands based on user input in the Hilary robot.
 * It delegates the execution of specific commands to their respective command classes and handles error cases.
 * This class serves as the central controller for processing user commands and interacting with the task list.
 *
 * @author Cheung Ka Yuen
 * @version Final
 * @since 2023-10-24
 */
public class CommandExecutor {
    String command;
    String userInput;
    Parser parser;
    TaskList tasks;

    /**
     * Constructs an `CommandExecutor` object with the specified command, user input, parser, and task list.
     *
     * @param command The command to be executed.
     * @param userInput The user input associated with the command.
     * @param parser The parser for processing user input.
     * @param tasks The task list where tasks are managed.
     */
    public CommandExecutor(TaskList tasks) {
        this.command = command;
        this.userInput = userInput;
        this.parser = parser;
        this.tasks = tasks;
    }

    /**
     * Executes the appropriate action based on the provided command.
     */
    public void executeCommand(String command, String userInput, Parser parser) {
        switch (command) {
        case "list":
            new PrintList(tasks).print();
            break;
        case "unmark":
            new UnmarkTask(userInput, tasks).unmark();
            break;
        case "mark":
            new MarkTask(userInput, tasks).mark();
            break;
        case "deadline":
            new AddDeadline(parser, tasks).addDeadlineTask();
            break;
        case "event":
            new AddEvent(parser, tasks).addEventTask();
            break;
        case "todo":
            new AddTodo(userInput, tasks).addTodoTask();
            break;
        case "delete":
            new DeleteTask(userInput, tasks).delete();
            break;
        case "find":
            new FindTasks(userInput, tasks).find();
            break;
        case "help":
            Ui.help();
            break;
        default:
            System.out.println("\tOOPS!!! I'm sorry, but I don't know what that means :-(");
        }
    }
}
