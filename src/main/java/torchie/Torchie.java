package torchie;

import torchie.parser.CommandParser;
import torchie.parser.TaskDetailsParser;
import torchie.storage.DataManager;
import torchie.exception.TorchieException;
import torchie.task.TaskList;

import java.util.Scanner;

public class Torchie {
    public static void main(String[] args) throws TorchieException {
        // initialisation
        DataManager dataManager = new DataManager();
        TaskDetailsParser taskDetailsParser = new TaskDetailsParser();
        TaskList taskList = dataManager.retrieveData();
        Scanner scanner = new Scanner(System.in);
        CommandParser commandparser = new CommandParser(taskList, dataManager);

        taskList.start();
        commandparser.getUserCommand();
//        String input;

        /*do {
            input = scanner.nextLine();
            String firstWord = input.split(" ")[0];

            switch (firstWord) {
            case "list":
                taskList.showTasks();
                break;
            case "mark":
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
                break;
            case "bye":
                System.out.println("Awww bye :( Let's play again soon!");
                break;
            case "todo":
                ToDo td;
                try {
                    String taskDescription = taskDetailsParser.getContent(input);
                    td = new ToDo(taskDescription);

                    taskList.addTask(td);
                    td.announceTaskAdd();
                    taskList.announceListSize();
                    dataManager.save(taskList);
                } catch (ArrayIndexOutOfBoundsException e) {
                    System.out.println("Missing <task name>: Example: todo <read>");
                }
                break;
            case "deadline":
                try {
                    String taskDescription = taskDetailsParser.getContent(input);
                    String taskDeadline = taskDetailsParser.getDeadlineDate(input);
                    Deadline d = new Deadline(taskDescription, taskDeadline);

                    taskList.addTask(d);
                    d.announceTaskAdd();
                    taskList.announceListSize();
                    dataManager.save(taskList);
                } catch (ArrayIndexOutOfBoundsException e) {
                    System.out.println("Missing <task name>: Example: deadline <read> /by Aug 1st");
                } catch (TorchieException e) {
                    System.out.println("Missing <deadline>: Example: deadline read </by Aug 1st>");
                }
                break;
            case "event":
                try {
                    String taskDescription = taskDetailsParser.getContent(input);
                    String taskEventStart = taskDetailsParser.getEventStart(input);
                    String taskEventEnd = taskDetailsParser.getEventEnd(input);
                    Event e = new Event(taskDescription, taskEventStart, taskEventEnd);

                    taskList.addTask(e);
                    e.announceTaskAdd();
                    taskList.announceListSize();
                    dataManager.save(taskList);
                } catch (ArrayIndexOutOfBoundsException e) {
                    System.out.println("Missing <task name>: Example: event <read> /from Aug 1st 4pm /to 6pm");
                } catch (TorchieException e) {
                    System.out.println("Missing keyword </from start time> or </by end time> " +
                            "Example: event read </from Aug 1st 4pm> </to 6pm>");
                }
                break;
            case "delete":
                try {
                    String itemNum_str = taskDetailsParser.getContent(input);
                    int itemNum = Integer.parseInt(itemNum_str) - 1;
                    taskList.deleteTask(itemNum);
                    taskList.announceListSize();
                    dataManager.save(taskList);
                } catch (NumberFormatException | ArrayIndexOutOfBoundsException e) {
                    System.out.println("Invalid Format! Correct format: \"delete <index>\" where" +
                            " index is an integer ");
                } catch (NullPointerException e) {
                    System.out.println("Task number to delete cannot exceed: <" + taskList.getSize() + ">");
                }
                break;
            default:
                System.out.println("Invalid Command!");
            }

        } while (!input.equals("bye"));*/

    }
}
/**
 * This method does something extremely useful ...
 *
 * @param
 * @throws MyBusinessException if ... happens
 */