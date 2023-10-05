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
     * Based on the command, return a command object using switch case.
     * For adding a new task, process details in TaskParser which returns a task object,
     * before passing it to AddTaskCommand(newTask, taskList)
     * @param userInput
     * @return
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
     * Scan for input from user and hands input over to handleInput()
     * @param file
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
                Ui.printOneTabMessage("You have an extra input OR you are missing an input!\n CORRECT IT BEFORE THE KNAVE OF HEART COMES!");
                Ui.printLineDivider();
            } catch (InvalidCommandException e){
                //Error message printed in InvalidCommandException class
            } catch (InvalidFormatException e) {
                //Error message printed in InvalidFormatException class
            } catch (InvalidDateTimeException e) {
                //Error message printed in InvalidDateTimeException class
            }
        } while (isRunning);
        input.close();
    }

}
