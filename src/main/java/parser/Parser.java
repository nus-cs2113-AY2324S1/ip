package parser;

import command.*;
import exceptions.DescriptionFormatException;
import exceptions.InvalidCommandException;
import task.Task;
import task.TaskList;
import task.TaskType;
import ui.Ui;

public class Parser {

    public Parser() {}
    public Command parse(String fullCommand) throws Exception {
        String[] inputStrings = splitInput(fullCommand);
        String commandType = inputStrings[0];
        Command command;

        if (commandType.equals("bye")) {
            command = new ExitCommand();
        } else if (commandType.equals("list")) {
            command = new ListCommand();
        } else if (commandType.equals("mark")) {
            int taskId = this.getTaskId(fullCommand);
            command = new MarkCommand(taskId);
        } else if (commandType.equals("unmark")) {
            int taskId = this.getTaskId(fullCommand);
            command = new UnmarkCommand(taskId);
        } else if (commandType.equals("todo")) {
            String description = this.getTaskDescription(fullCommand);
            command = new AddTodoCommand(description);
        } else if (commandType.equals("deadline")) {
            String[] descriptionAndBy = this.splitInputIntoDeadlineFormat(fullCommand);
            command = new AddDeadlineCommand(descriptionAndBy[0], descriptionAndBy[1]);
        } else if (commandType.equals("event")) {
            String[] descriptionAndStartEndTime = this.splitInputIntoEventFormat(fullCommand);
            command = new AddEventCommand(descriptionAndStartEndTime[0],
                    descriptionAndStartEndTime[1], descriptionAndStartEndTime[2]);
        } else if (commandType.equals("delete")) {
            int taskId = this.getTaskId(fullCommand);
            command = new DeleteCommand(taskId);
        } else {
            throw new InvalidCommandException("OOPS!!! I'm sorry, but I don't know what that means :-(");
        }
        return command;
    }

    private String getTaskDescription(String fullCommand) throws DescriptionFormatException{
        String[] description = splitInput(fullCommand);
        if (description.length == 1) {
            throw new DescriptionFormatException(
                    "Wrong todo format. Follow this format to add a todo: "
                            + "todo [todo description]");
        }
        return description[1];
    }


    public void parseLoadingData(TaskList taskList, String fullCommand) {
        String[] inputStrings = splitInput(fullCommand);
        String command = inputStrings[0];
        String isDone = inputStrings[1];
        String description = inputStrings[2];
        Task task = null;

        try {
            if (command.equals("todo")) {
                task = taskList.addTodo(description);
            } else if (command.equals("deadline")) {
                String[] descriptionAndBy = this.splitInputIntoDeadlineFormat(fullCommand);
                task = taskList.addDeadline(descriptionAndBy[0], descriptionAndBy[1]);
            } else if (command.equals("event")) {
                String[] descriptionAndStartEndTime = this.splitInputIntoEventFormat(fullCommand);
                task = taskList.addEvent(descriptionAndStartEndTime[0],
                        descriptionAndStartEndTime[1], descriptionAndStartEndTime[2]);
            } else {
                throw new InvalidCommandException("Error data format in file");
            }
            if (isDone.equals("true")) {
                task.setDone(true);
            }
        } catch (InvalidCommandException e) {
            System.out.println(e);
        }
    }

    public String[] splitInputIntoEventFormat(String fullCommand) {
        int taskTypeLength = TaskType.event.toString().length();
        String description = fullCommand.substring(taskTypeLength);
        String[] descriptionAndStartEndTime = description.split("/");

        if (descriptionAndStartEndTime.length != 3) {
            Ui.showError( new DescriptionFormatException(
                    "Wrong input format. Follow this format to add an event: " +
                            "event [event description] " +
                            "/from[start time and date] /to[end time and date]"));
        }

        return descriptionAndStartEndTime;
    }

    //Split input into different formats
    public String[] splitInputIntoDeadlineFormat(String fullCommand) {
        int taskTypeLength = TaskType.deadline.toString().length();
        String description = fullCommand.substring(taskTypeLength);

        String[] descriptionAndBy = description.split("/by");

        if (descriptionAndBy.length < 2) {
            Ui.showError(new DescriptionFormatException(
                    "Wrong input format. Follow this format to add a deadline: "
                            + "deadline [deadline description] /by[time and date of the deadline]"));
        }

        return descriptionAndBy;
    }

    public int getTaskId(String input) {
        String[] splitInput = input.split(" ", 2);
        if (splitInput.length == 1) {
            Ui.showError( new DescriptionFormatException(
                    "Wrong input format. Follow this format to mark/unmark a task: "
                            + "mark/unmark [task id]"));
        }
        return Integer.parseInt(splitInput[1]) - 1;
    }

    public static String[] splitInput(String line) {
        return line.split(" ", 3);
    }

}
