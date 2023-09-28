package Duke.Parser;

import Duke.Command.Command;
import Duke.Task.TaskList;

public class Parser {
    public Parser(){

    }

    public void generateResponse(String input, TaskList records) {

        String[] commandDetails = input.split(" ", 2);
        String instructionString = commandDetails[0].trim();


        switch (instructionString) {
        case ("list"):
            Command.executeListCommand(records);
            break;
        case ("unmark"):
            Command.executeUnmarkCommand(commandDetails[1], records);
            break;
        case ("mark"):
            Command.executeMarkCommand(commandDetails[1], records);
            break;
        case ("todo"):
            Command.createNewTask("todo", commandDetails[1], records);
            break;
        case ("deadline"):
            Command.createNewTask("deadline", commandDetails[1], records);
            break;
        case ("event"):
            Command.createNewTask("event", commandDetails[1], records);
            break;
        case ("delete"):
            Command.deleteTask(commandDetails[1], records);
            break;
        default:
            System.out.println("Invalid Command");
            break;
        }
    }
}
