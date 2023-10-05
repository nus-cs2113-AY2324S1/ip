package torchie.parser;

import torchie.command.Command;
import torchie.command.AddCommand;
import torchie.command.DeleteCommand;
import torchie.command.ExitCommand;
import torchie.command.FindCommand;
import torchie.command.InvalidCommand;
import torchie.command.ListCommand;
import torchie.command.SetStatusCommand;
import torchie.exception.InvalidCommandException;
import torchie.exception.TorchieException;
import java.time.LocalDateTime;
import torchie.storage.Storage;
import torchie.task.Deadline;
import torchie.task.Event;
import torchie.task.TaskList;
import torchie.task.ToDo;

import java.util.Scanner;

public class Parser {

    public final String LIST = "list";
    public final String EXIT = "bye";
    public final String UNMARK = "unmark";
    public final String MARK = "mark";
    public final String TODO = "todo";
    public final String DEADLINE = "deadline";
    public final String EVENT = "event";
    public final String DELETE = "delete";
    public final String FIND = "find";


    private TaskList taskList;
    private Storage dataManager;
    private TaskDetailsParser taskDetailsParser;
    public Parser(TaskList tl, Storage dm) {
        this.taskList = tl;
        this.dataManager = dm;
        this.taskDetailsParser = new TaskDetailsParser();
    }

    /**
     * Parses a user input to identify the command and then handle the commands accordingly
     *
     * @param input user input string that contains the command and other task details
     * @return Command object that will be used to perform commands
     *
     * @throws TorchieException the date and time of the input string is in the wrong format
     *
     *
     */
    public Command parseCommand(String input) throws TorchieException {

        String command = input.split(" ")[0];
        Command commandObj = new InvalidCommand();

        switch (command) {
        case LIST:
            commandObj = new ListCommand(taskList);
            break;

        case MARK:
        case UNMARK:
            try {
                String itemNum_str = taskDetailsParser.getContent(input);
                int itemNum = Integer.parseInt(itemNum_str) - 1;

                commandObj = new SetStatusCommand(command, taskList, itemNum);
            } catch (TorchieException e) {
                e.showExceptionMessage();
            }
            break;
        case EXIT:
            commandObj =  new ExitCommand(taskList);
            break;
        case TODO:
            try {
                String taskDescription = taskDetailsParser.getContent(input);
                ToDo td = new ToDo(taskDescription);
                commandObj = new AddCommand(td, taskList);
            } catch (TorchieException e) {
                e.showExceptionMessage();
            }
            break;
        case DEADLINE:
            try {
                String taskDescription = taskDetailsParser.getContent(input);
                LocalDateTime taskDeadline = taskDetailsParser.getDeadlineDate(input);
                Deadline d = new Deadline(taskDescription, taskDeadline);
                commandObj =  new AddCommand(d, taskList);
            } catch (TorchieException e) {
                e.showExceptionMessage();
            }
            break;
        case EVENT:
            try {
                String taskDescription = taskDetailsParser.getContent(input);
                LocalDateTime taskEventStart = taskDetailsParser.getEventStart(input);
                LocalDateTime taskEventEnd = taskDetailsParser.getEventEnd(input);
                Event e = new Event(taskDescription, taskEventStart, taskEventEnd);
                commandObj = new AddCommand(e, taskList);
            } catch (TorchieException e) {
                e.showExceptionMessage();
            }
            break;
        case DELETE:
            try {
                String itemNum_str = taskDetailsParser.getContent(input);
                int itemNum = Integer.parseInt(itemNum_str) - 1;
                commandObj = new DeleteCommand(taskList, itemNum);
            } catch (TorchieException e) {
                e.showExceptionMessage();
            }
            break;
        case FIND:
            try {
                String keyword = taskDetailsParser.getContent(input);
                commandObj = new FindCommand(taskList, keyword);
            } catch (TorchieException e) {
                e.showExceptionMessage();
            }
            break;
        default:
            throw new InvalidCommandException();
        }

        return commandObj;
    }

    public void getUserCommand() {
        Scanner scanner = new Scanner(System.in);
        String input;

        do {
            input = scanner.nextLine();
            try {
                Command command = parseCommand(input);
                command.handleCommand();
                dataManager.save(taskList);
            } catch (TorchieException e) {
                e.showExceptionMessage();
            }

        } while (!input.equals(EXIT));
    }
}
