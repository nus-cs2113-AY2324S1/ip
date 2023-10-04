package torchie.parser;

import torchie.command.*;
import torchie.exception.*;
import torchie.storage.Storage;
import torchie.task.Deadline;
import torchie.task.Event;
import torchie.task.TaskList;
import torchie.task.ToDo;

import java.util.Scanner;

public class CommandParser {

    public final String LIST = "list";
    public final String EXIT = "bye";
    public final String UNMARK = "unmark";
    public final String MARK = "mark";
    public final String TODO = "todo";
    public final String DEADLINE = "deadline";
    public final String EVENT = "event";
    public final String DELETE = "delete";


    private TaskList taskList;
    private Storage dataManager;
    private TaskDetailsParser taskDetailsParser;
    public CommandParser(TaskList tl, Storage dm) {
        this.taskList = tl;
        this.dataManager = dm;
        this.taskDetailsParser = new TaskDetailsParser();
    }

    /*public Command createSetStatusCommand(String c) {
        String itemNum_str = taskDetailsParser.getContent(c);
        int itemNum = Integer.parseInt(itemNum_str) - 1;
        dataManager.save(taskList);
        return new SetStatusCommand(c, taskList, itemNum);
    }
*/
    public Command parseCommand(String input) throws InvalidDeadlineFormatException, InvalidCommandException, MissingTaskNameException, InvalidEventFormatException, InvalidDeadlineFormatException {

        String command = input.split(" ")[0];

        switch (command) {
        case LIST:
            return new ListCommand(taskList);

        case MARK:
        case UNMARK:
            try {
//                commandObject = createSetStatusCommand(command)



                String itemNum_str = taskDetailsParser.getContent(input);
                int itemNum = Integer.parseInt(itemNum_str) - 1;

                return new SetStatusCommand(command, taskList, itemNum);
//                dataManager.save(taskList);
            } catch (TorchieException e) {
                e.showExceptionMessage();
            }/*catch (NumberFormatException | ArrayIndexOutOfBoundsException e) {
                System.out.println("Invalid Format! Correct format: \"mark <index>\", where index is an integer ");
                // throw invalid format exception
                throw new InvalidDeadlineFormatException();
            } catch (NullPointerException e) {
                System.out.println("Task number to mark cannot exceed: <" + taskList.getSize() + ">");
            }*/
            /*case "mark":
                try {
                    String itemNum_str = taskDetailsParser.getContent(input);
                    int itemNum = Integer.parseInt(itemNum_str) - 1;
                    taskList.markTask(itemNum);
                    dataManager.save(taskList);
                } catch (NumberFormatException | ArrayIndexOutOfBoundsException e) {
                    System.out.println("Invalid Format! Correct format: \"mark <index>\", where index is an integer ");
                } catch (NullPointerException e) {
                    System.out.println("Task number to mark cannot exceed: <" + taskList.getSize() + ">");
                }
                break;
            case "unmark":
                try {
                    String itemNum_str = taskDetailsParser.getContent(input);
                    int itemNum = Integer.parseInt(itemNum_str) - 1;
                    taskList.unmarkTask(itemNum);
                    dataManager.save(taskList);
                } catch (NumberFormatException | ArrayIndexOutOfBoundsException e) {
                    System.out.println("Invalid Format! Correct format: \"unmark <index>\" where" +
                            " index is an integer ");
                } catch (NullPointerException e) {
                    System.out.println("Task number to unmark cannot exceed: <" + taskList.getSize() + ">");
                }
                break;*/
        case EXIT:
            return new ExitCommand();
        case TODO:
            try {
                String taskDescription = taskDetailsParser.getContent(input);
                ToDo td = new ToDo(taskDescription);
                return new AddCommand(td, taskList);
                /*taskList.addTask(td);
                td.announceTaskAdd();
                taskList.announceListSize();*/
//                dataManager.save(taskList);
            } catch (TorchieException e) {
                e.showExceptionMessage();
            }
            break;
        case DEADLINE:
            try {
                String taskDescription = taskDetailsParser.getContent(input);
                String taskDeadline = taskDetailsParser.getDeadlineDate(input);
                Deadline d = new Deadline(taskDescription, taskDeadline);
                return new AddCommand(d, taskList);
                /*taskList.addTask(d);
                d.announceTaskAdd();
                taskList.announceListSize();*/
//                dataManager.save(taskList);
            } catch (TorchieException e) {
                e.showExceptionMessage();
            } /*catch (ArrayIndexOutOfBoundsException e) {
                System.out.println("Missing <task name>: Example: deadline <read> /by Aug 1st");
            }*/
            break;
        case EVENT:
            try {
                String taskDescription = taskDetailsParser.getContent(input);
                String taskEventStart = taskDetailsParser.getEventStart(input);
                String taskEventEnd = taskDetailsParser.getEventEnd(input);
                Event e = new Event(taskDescription, taskEventStart, taskEventEnd);
                return new AddCommand(e, taskList);
                /*taskList.addTask(e);
                e.announceTaskAdd();
                taskList.announceListSize();*/
//                dataManager.save(taskList);
            } catch (TorchieException e) {
                e.showExceptionMessage();
            }/*catch (ArrayIndexOutOfBoundsException e) {
                System.out.println("Missing <task name>: Example: event <read> /from Aug 1st 4pm /to 6pm");
            }*/
            break;
        case "delete":
            try {
                String itemNum_str = taskDetailsParser.getContent(input);
                int itemNum = Integer.parseInt(itemNum_str) - 1;
                return new DeleteCommand(taskList, itemNum);
                /*taskList.deleteTask(itemNum);
                taskList.announceListSize();*/
            } catch (TorchieException e) {
                e.showExceptionMessage();
            }/*catch (NumberFormatException | ArrayIndexOutOfBoundsException e) {
                System.out.println("Invalid Format! Correct format: \"delete <index>\" where" +
                        " index is an integer ");
            } catch (NullPointerException e) {
                System.out.println("Task number to delete cannot exceed: <" + taskList.getSize() + ">");
            }*/
            break;
        default:
            throw new InvalidCommandException();
        }

        return new InvalidCommand();
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
