package alice.parser;

import alice.commands.*;
import alice.enumeration.TaskStatus;
import alice.exceptions.InvalidCommandException;
import alice.exceptions.InvalidDateTimeException;
import alice.exceptions.InvalidFormatException;
import alice.storage.FileManager;
import alice.tasks.*;
import alice.ui.Ui;

import java.util.Scanner;

public class CommandParser {
    private TaskList tasks;
    private boolean isRunning = true;
    public CommandParser(TaskList tasks) {
        this.tasks = tasks;
    }


    /**
     * Handles user's input and executes necessary command
     *
     * @param actionOfInput the command word of the input
     * @param userInput the input by user
     * @return
     * @throws InvalidCommandException the command of the input is invalid
     * @throws InvalidFormatException the format of the input is invalid
     * @throws InvalidDateTimeException the date and time of the input is invalid
     */
    public Command handleInput(String actionOfInput, String userInput) throws InvalidCommandException, InvalidFormatException, InvalidDateTimeException {
        switch (actionOfInput) {
        case "bye":
            isRunning = false;
            return new ByeCommand();

        case "list":
            return new ListTasksCommand(tasks);

        case "mark":
        case "unmark":
            StatusParser statusParser = new StatusParser(userInput, tasks);
            TaskStatus status = statusParser.getStatus();
            Task task = statusParser.getTask();
            return new UpdateStatusCommand(status, task);

        case "todo":
        case "deadline":
        case "event":
            TaskParser taskParser = new TaskParser(userInput);
            Task newTask = taskParser.createTask();
            return new AddTaskCommand(newTask, tasks);

        case "delete":
            IndexValidityParser deleteParser = new IndexValidityParser(userInput, tasks);
            int deleteIndex = deleteParser.getValidTaskId();
            return new DeleteTaskCommand(deleteIndex, tasks);

        case "find":
            FindParser findParser = new FindParser(userInput);
            String keyword = findParser.getKeyword();
            return new FindTaskCommand(tasks, keyword);

        default:
            throw new InvalidCommandException();
        }
    }

    /**
     * Scan for input from user until program ends
     *
     * @param file the alice.txt file consisting of all the stored tasks
     * @throws IndexOutOfBoundsException the number of paramters for the input is not expected
     * @throws InvalidCommandException the command of input is invalid
     * @throws InvalidFormatException the format of input is invalid
     * @throws InvalidDateTimeException the date and time format of input is invalid
     */
    public void execute(FileManager file) throws IndexOutOfBoundsException, InvalidCommandException, InvalidFormatException, InvalidDateTimeException {
        Scanner input = new Scanner(System.in);

        do {
            String userInput = input.nextLine();
            String actionOfInput = userInput.split(" ")[0];
            try{
                Command command = handleInput(actionOfInput, userInput);
                command.handleCommand();
            } catch (IndexOutOfBoundsException e){
                Ui.printMissingInputError();
            } catch (InvalidCommandException e){
                e.getErrorMessage();
            } catch (InvalidFormatException e) {
                e.getErrorMessage();
            } catch (InvalidDateTimeException e) {
                e.getErrorMessage();
            }
            file.save(tasks);
        } while (isRunning);
        input.close();
    }
}
