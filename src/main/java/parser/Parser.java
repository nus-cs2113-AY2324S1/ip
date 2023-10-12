package parser;

import command.*;
import exceptions.DescriptionFormatException;
import exceptions.InvalidCommandException;
import task.Task;
import task.TaskList;
import task.TaskType;
import ui.Ui;

/**
 * Parse everything received from the users on terminal
 * into a format that can be interpreted by other core classes
 */
public class Parser {

    /**
     * Parse the full command and carry out and create different commands
     * according to user input
     *
     * @param fullCommand the entire line of user input
     * @return the command requested by the user
     * @throws Exception if the task description if in the wrong format
     * or user input invalid command
     */
    public Command parse(String fullCommand) throws Exception {
        String[] inputStrings = splitInput(fullCommand);
        String commandType = inputStrings[0];
        Command command = null;
        String stringAfterCommandType = fullCommand.substring(commandType.length());

        try {
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
                String description = this.getTaskDescription(stringAfterCommandType);
                command = new AddTodoCommand(description);
            } else if (commandType.equals("deadline")) {
                String[] descriptionAndBy =
                        this.splitInputIntoDeadlineFormat(stringAfterCommandType);
                command = new AddDeadlineCommand(descriptionAndBy[0], descriptionAndBy[1]);
            } else if (commandType.equals("event")) {
                String[] descriptionAndStartEndTime =
                        this.splitInputIntoEventFormat(stringAfterCommandType);
                command = new AddEventCommand(descriptionAndStartEndTime[0],
                        descriptionAndStartEndTime[1], descriptionAndStartEndTime[2]);
            } else if (commandType.equals("delete")) {
                int taskId = this.getTaskId(fullCommand);
                command = new DeleteCommand(taskId);
            } else if (commandType.equals("find")) {
                String keyword = getKeyWord(stringAfterCommandType);
                command = new FindCommand(keyword);
            } else {
                throw new InvalidCommandException("OOPS!!! I'm sorry, but I don't know what that means :-(");
            }
        } catch (Exception e) {
            Ui.showError(e);
            command = new DefaultCommand();
        }
        return command;
    }

    /**
     * Parse data from the data file and add the tasks into taskList accordingly
     *
     * @param taskList the taskList of the user
     * @param fullCommand the entire line of text from data file
     */
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
                String[] descriptionAndBy = this.splitInputIntoDeadlineFormat(description);
                task = taskList.addDeadline(descriptionAndBy[0], descriptionAndBy[1]);
            } else if (command.equals("event")) {
                String[] descriptionAndStartEndTime = this.splitInputIntoEventFormat(description);
                task = taskList.addEvent(descriptionAndStartEndTime[0],
                        descriptionAndStartEndTime[1], descriptionAndStartEndTime[2]);
            } else {
                throw new InvalidCommandException("Error data format in file");
            }
            if (isDone.equals("true")) {
                task.setDone(true);
            }
        } catch (Exception e) {
            Ui.showError(e);
        }
    }

    /**
     * Split input into event description, start time and end time
     *
     * @param descriptions
     * @return
     * @throws DescriptionFormatException
     */
    private String[] splitInputIntoEventFormat(String descriptions) throws DescriptionFormatException {
        String[] descriptionAndStartEndTime = descriptions.split("/");

        if (descriptionAndStartEndTime.length != 3) {
            throw new DescriptionFormatException(
                    "Wrong input format. Follow this format to add an event: " +
                            "event [event description] " +
                            "/from[start time and date] /to[end time and date]");
        }

        return descriptionAndStartEndTime;
    }

    /**
     * Split input into deadline description and the date/time of the deadline
     *
     * @param descriptions the user input without the command
     * @return an array of strings with strings[0] bring the deadline description and strings[1]
     *          being the due date/time
     * @throws DescriptionFormatException if the length of the array of strings is < 2
     */
    private String[] splitInputIntoDeadlineFormat(String descriptions) throws DescriptionFormatException {
        String[] descriptionAndBy = descriptions.split("/by");

        if (descriptionAndBy.length < 2) {
            throw new DescriptionFormatException(
                    "Wrong input format. Follow this format to add a deadline: "
                            + "deadline [deadline description] /by[time and date of the deadline]");
        }

        return descriptionAndBy;
    }

    /**
     * Split the input into command and task id
     *
     * @param input the entire line of user input
     * @return the task id
     * @throws DescriptionFormatException when the length array of strings == 1
     */
    private int getTaskId(String input) throws DescriptionFormatException {
        String[] splitInput = input.split(" ", 2);
        if (splitInput.length == 1) {
            throw new DescriptionFormatException(
                    "Wrong input format. Follow this format to mark/unmark/delete a task: "
                            + "mark/unmark/delete [task id]");
        }
        return Integer.parseInt(splitInput[1]) - 1;
    }

    /**
     * Split the input and get the keyword for find
     *
     * @param description the user input without the command
     * @return the keyword
     * @throws DescriptionFormatException when the description is empty
     */
    private String getKeyWord(String description) throws DescriptionFormatException {
        if (description.isEmpty()) {
            throw new DescriptionFormatException(
                    "Wrong find format. Follow this format to find: "
                            + "find [keyword]");
        }
        return description;
    }

    /**
     * Get task description for todo
     *
     * @param descriptions the user input without the command
     * @return the task description
     * @throws DescriptionFormatException when the description is empty
     */
    private String getTaskDescription(String descriptions) throws DescriptionFormatException{
        if (descriptions.isEmpty()) {
            throw new DescriptionFormatException(
                    "Wrong todo format. Follow this format to add a todo: "
                            + "todo [todo description]");
        }
        return descriptions;
    }

    /**
     * Split input into action and full description
     *
     * @param line the entire user input
     * @return an array of strings
     */
    private static String[] splitInput(String line) {
        return line.split(" ", 3);
    }
}