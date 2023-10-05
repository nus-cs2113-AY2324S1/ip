package parser;

import commands.AddTaskCommand;
import commands.Command;
import commands.UpdateTaskStatus;
import enumeration.TaskStatus;
import exceptions.InvalidCommandException;
import exceptions.InvalidFormatException;
import storage.FileManager;
import tasks.*;
import ui.Ui;

import java.util.Scanner;

public class CommandParser {
    private TaskList tasks;
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
    public Command handleInput(String actionOfInput, String userInput) throws InvalidCommandException, InvalidFormatException {
        switch (actionOfInput) {
        /*case "list":
            tasks.listTasks();
            break;*/
        case "mark":
        case "unmark":
            StatusParser statusParser = new StatusParser(userInput, tasks);

            TaskStatus status = statusParser.getStatus();
            Task task = statusParser.getTask();

            return new UpdateTaskStatus(status, task);
        case "todo":
        case "deadline":
        case "event":
            TaskParser taskParser = new TaskParser(userInput);
            Task newTask = taskParser.createTask();
            return new AddTaskCommand(newTask, tasks);
        /*case "delete":
            tasks.deleteTask(userInput);
            break;*/
        default:
            throw new InvalidCommandException();
        }
    }


    /**
     * Scan for input from user and hands input over to handleInput()
     * @param file
     */
    public void execute(FileManager file) throws IndexOutOfBoundsException, InvalidCommandException, InvalidFormatException {
        Scanner input = new Scanner(System.in);
        boolean isRunning = true;

        do {
            String userInput = input.nextLine();
            String actionOfInput = userInput.split(" ")[0];
            try{
                if (actionOfInput.equals("bye")){
                    isRunning = false;
                } else {
                    Command command = handleInput(actionOfInput, userInput);
                    command.handleCommand();
                }
            } catch (IndexOutOfBoundsException e){
                Ui.printOneTabMessage("You have an extra input OR you are missing an input!\n CORRECT IT BEFORE THE KNAVE OF HEART COMES!");
                Ui.printLineDivider();
            } catch (InvalidCommandException e){
                //Error message printed in InvalidCommandException class
            } catch (InvalidFormatException e) {
                //Error message printed in InvalidFormatException class
            }
        } while (isRunning);
        input.close();
    }

}
