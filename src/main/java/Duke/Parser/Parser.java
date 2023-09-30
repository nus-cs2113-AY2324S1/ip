package Duke.Parser;

import Duke.Command.Command;
import Duke.Task.Deadline;
import Duke.Task.Event;
import Duke.Task.TaskList;
import Duke.Task.ToDo;

/**
 * Accepts input from the user and execute the relevant commands.
 */
public class Parser {
    private static final String LIST_INSTRUCTION = "list";
    private static final String MARK_INSTRUCTION = "mark";
    private static final String UNMARK_INSTRUCTION = "unmark";
    private static final String CREATE_TODO_INSTRUCTION = ToDo.taskType;
    private static final String CREATE_DEADLINE_INSTRUCTION = Deadline.taskType;
    private static final String CREATE_EVENT_INSTRUCTION = Event.taskType;
    private static final String DELETE_INSTRUCTION = "delete";
    private static final String FIND_INSTRUCTION = "find";
    private static final String INVALID_COMMAND_PROMPT = "Command is Invalid!";

    public Parser() {

    }

    public void generateResponse(String input, TaskList taskList) {

        String[] commandDetails = input.split(" ", 2);
        String instructionString = commandDetails[0].trim();

        switch (instructionString) {
        case (LIST_INSTRUCTION):
            Command.executeListCommand(taskList);
            break;
        case (UNMARK_INSTRUCTION):
            Command.executeUnmarkCommand(commandDetails[1], taskList);
            break;
        case (MARK_INSTRUCTION):
            Command.executeMarkCommand(commandDetails[1], taskList);
            break;
        case (CREATE_TODO_INSTRUCTION):
            Command.createNewTask(CREATE_TODO_INSTRUCTION, commandDetails[1], taskList);
            break;
        case (CREATE_DEADLINE_INSTRUCTION):
            Command.createNewTask(CREATE_DEADLINE_INSTRUCTION, commandDetails[1], taskList);
            break;
        case (CREATE_EVENT_INSTRUCTION):
            Command.createNewTask(CREATE_EVENT_INSTRUCTION, commandDetails[1], taskList);
            break;
        case (DELETE_INSTRUCTION):
            Command.deleteTask(commandDetails[1], taskList);
            break;
        case (FIND_INSTRUCTION):
            Command.findTasks(commandDetails[1], taskList);
            break;
        default:
            System.out.println(INVALID_COMMAND_PROMPT);
            break;
        }
    }
}
